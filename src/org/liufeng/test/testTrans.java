package org.liufeng.test;






import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.liufeng.weixin.util.trans.ShowapiResBody;
import org.liufeng.weixin.util.trans.Status;
import org.liufeng.weixin.util.trans.Trans;

import javax.servlet.http.HttpUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 */
public class testTrans {
    public static void main(String[] args) {
        String host = "https://ali-deliver.showapi.com";
        String path = "/showapi_expressList";
        String method = "GET";
        String appcode = "a27da8d9073647ac9bc1bad1b404ffab";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE "+appcode);
        Map<String, String> querys = new HashMap<String, String>();
        /*querys.put("expName", "%E9%A3%8E");*/
        querys.put("maxSize", "3000");
        querys.put("page", "1");


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = org.liufeng.course.util.HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            /*System.out.println(EntityUtils.toString(response.getEntity()));*/
            /*JSONObject object= JSONObject.fromObject(EntityUtils.toString(response.getEntity()));*/
            JSONObject object=JSONObject.fromObject(EntityUtils.toString(response.getEntity()));
            System.out.println(object.toString());
            String[] aa=object.toString().split("\\[");
            String[] bb= aa[1].split("\\]");
            String list="["+bb[0]+"]";
            System.out.println(list);
            JSONArray jsonArray = JSONArray.fromObject(list);
            //Java集合
            List<Trans> lists = (List<Trans>) jsonArray.toCollection(jsonArray, Trans.class);
            for(Trans trans : lists){
                System.out.println(trans.getExpName());
            }
         /*   Map<String, Object> map= new HashedMap();
            List<Trans> list=new ArrayList<>();
            map.put("expressList",list);
            map.put("showapi_res_body", ShowapiResBody.class);
            Status teacherBean = (Status) JSONObject.toBean(object, Status.class, map);
            System.out.println(map.get("expressList"));*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
