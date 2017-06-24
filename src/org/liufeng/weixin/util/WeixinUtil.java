package org.liufeng.weixin.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.liufeng.weixin.pojo.AccessToken;

import org.liufeng.weixin.pojo.Menu;
import org.liufeng.weixin.util.http.HttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.liufeng.weixin.util.GetWxOrderno.doXMLParse;
import static org.liufeng.weixin.util.GetWxOrderno.httpclient;

/**
 * 公众平台通用接口工具类
 * 
 * @author liuyq
 * @date 2013-08-09
 */
public class WeixinUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

	// 菜单创建（POST） 限100（次/天）   
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"; 
	
	// 获取access_token的接口地址（GET） 限200（次/天）   
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	public final static String access_code_url="https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";

	/**
	 * 创建菜单
	 *
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}



	/** 
	 * 获取access_token 
	 *  
	 * @param appid 凭证 
	 * @param appsecret 密钥 
	 * @return 
	 */  
	public static AccessToken getAccessToken(String appid, String appsecret) {  
	    AccessToken accessToken = null;  
	  
	    String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
	    JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
	    // 如果请求成功   
	    if (null != jsonObject) {  
	        try {  
	            accessToken = new AccessToken();
	            accessToken.setToken(jsonObject.getString("access_token"));
	            accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
	        } catch (JSONException e) {  
	            accessToken = null;  
	            // 获取token失败   
	            log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
	        }  
	    }
	    return accessToken;  
	}

	public static String getOpenid() {
        String appid="wx49f1f2ac98ea0de6";
		String reurl="http://test.youpinyuntai.com/hellochat/showServlet";
		String rurl= URLEncoder.encode(reurl);
		//scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid=" + appid +
				"&redirect_uri=" +
				rurl+
				"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		System.out.println("url="+url);
		// 如果请求成功
		return url;
	}

	public static String getuserid(String lastOpenId) {
		String appid="wx49f1f2ac98ea0de6";
		String reurl="http://test.youpinyuntai.com/hellochat/openidServlet" + "?lastOpenId=" + lastOpenId+"&gid=goods";
		String rurl= URLEncoder.encode(reurl);
		//scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid=" + appid +
				"&redirect_uri=" +
				rurl+
				"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		System.out.println("url="+url);
		// 如果请求成功
		return url;
	}

	public static String getOpenidb(String code) {
		AccessToken accessToken = null;
		String appid="wx49f1f2ac98ea0de6";
		String secret="795ab215e3c975a976980e6b46a7d922";
		//scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
				"appid=" + appid +
				"&secret=" + secret+
				"&code=" + code+
				"&grant_type=authorization_code";
		System.out.println("url="+url);
		// 如果请求成功
		return url;
	}
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
			httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}
	public static String createSign(SortedMap<String, String> packageParams) {
		String key="djalkd4d654ad6adada46da45d6a4daa";
		StringBuffer sb = new StringBuffer();
	/*	String sb="";*/
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");

			}
		}
		/*sb=sb+"key="+key;*/
		sb.append("key=" +key );
		System.out.println("md5 sb:" + sb);
		String sign = MD5Util.MD5Encode(sb.toString(),"UTF-8")
				.toUpperCase();
		System.out.println("packge签名:" + sign);
		return sign;

	}
	/*public static String getPayNo(String url, String xmlParam) {
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,
				true);
		HttpPost httpost = HttpClientConnectionManager.getPostMethod(url);
		String prepay_id = "";
		try {
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
			String jsonStr = EntityUtils
					.toString(response.getEntity(), "UTF-8");
			Map<String, Object> dataMap = new HashMap<String, Object>();

			if (jsonStr.indexOf("FAIL") != -1) {
				return prepay_id;
			}
			Map map = doXMLParse(jsonStr);
			String return_code = (String) map.get("return_code");
			prepay_id = (String) map.get("prepay_id");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prepay_id;
	}*/
}