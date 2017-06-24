package org.liufeng.test;

import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.liufeng.weixin.util.http.HttpClientConnectionManager;

import java.util.HashMap;
import java.util.Map;

import static org.liufeng.weixin.util.GetWxOrderno.doXMLParse;

/**
 * Created by Administrator on 2017/5/22.
 */
public class TestHttpClient {
    public static DefaultHttpClient httpclient;
    static
    {
        httpclient = new DefaultHttpClient();
        httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient);
    }
    public static void main(String[] args) {
        String xml="<xml>" +
                "<appid>wx49f1f2ac98ea0de6</appid>" +
                "<body>test001</body>" +
                "<mch_id>1245158702</mch_id>" +
                "<nonce_str>dada155dawd15a</nonce_str>" +
                "<notify_url>http://test.youpinyuntai.com/hellochat/index.jsp</notify_url>" +
                "<openid>oGmJ5t6QdZ1mGNAJABI_m9K0FdZM</openid>" +
                "<out_trade_no>test002</out_trade_no>" +
                "<spbill_create_ip>117.143.227.218</spbill_create_ip>" +
                "<total_fee>2</total_fee>" +
                "<trade_type>JSAPI</trade_type>" +
                "<sign>5B26F2BBA8F4D70D54FF9D1A53BCE181</sign>" +
                "</xml>";
        String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String prepay_id = "";
        prepay_id=TestHttpClient.getPayNo(createOrderURL,xml);
        System.out.println(prepay_id);
    }
    public static String getPayNo(String url, String xmlParam){
        Map<String,String> param=new HashedMap();
        System.out.println("xml是:"+xmlParam);
        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
        String prepay_id = "";
        try {
            httpost.setEntity(new StringEntity(xmlParam,"UTF-8"));
            HttpResponse response = httpclient.execute(httpost);
            String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            Map<String, Object> dataMap = new HashMap<String, Object>();
            System.out.println("json是:"+jsonStr);

            if(jsonStr.indexOf("FAIL")!=-1){
                return prepay_id;
            }
            Map map = doXMLParse(jsonStr);
            String return_code  = (String) map.get("return_code");
            String return_msg=(String) map.get("return_msg");

            prepay_id  = (String) map.get("prepay_id");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return prepay_id;
    }
}
