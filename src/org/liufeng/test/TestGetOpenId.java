package org.liufeng.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2017/5/19.
 */
public class TestGetOpenId extends Thread {

    public static void main(String[] args) throws InterruptedException {
        String httpUrl = "http://apis.baidu.com/heweather/pro/attractions";
        String httpArg = "cityid=DE2950159";
        String jsonResult = request(httpUrl, httpArg);
        System.out.println(jsonResult);
    }
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",  "25545cd9f60e5a160c1e4e28692fd51c");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
