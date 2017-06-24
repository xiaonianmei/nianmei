package org.liufeng.test;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

import static org.liufeng.weixin.util.WeixinUtil.httpRequest;

/**
 * Created by Administrator on 2017/5/19.
 */
public class TestPayPreId {
    private static String baseUrl = "http://weixin.xinfor.com";
    public static void main(String[] args) {
          String appid="wx49f1f2ac98ea0de6";
        String backUri = baseUrl + "/wx/toPay";

        //URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
        backUri = URLEncoder.encode(backUri);
        //scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" +appid +
                "&redirect_uri=" +
                backUri+
                "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
        System.out.println("url:" + url);
        httpRequest(url, "GET", null);
    }
}
