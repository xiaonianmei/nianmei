package org.liufeng.course.servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.liufeng.weixin.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

/**
 * 核心请求处理类
 * 
 * @author liufeng
 * @date 2013-05-18
 */
public class PayTotalServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		/*String code = request.getParameter("code");
		String urlb = WeixinUtil.getOpenidb(code);
		JSONObject jsonObject = WeixinUtil.httpRequest(urlb, "GET", null);
		String openid="";
		if (null != jsonObject) {
			openid = jsonObject.getString("openid");
			System.out.println("openId:" + openid);
		}
*/
		HttpSession session= request.getSession();
		String openid="oGmJ5t6QdZ1mGNAJABI_m9K0FdZM";
		session.setAttribute("openid",openid);
		String nonce_str = "dada155dawd15a";
		session.setAttribute("nonce_str",nonce_str);
		String body = "test001";
		String out_trade_no = RandomUtil.Random(8);
		session.setAttribute("out_trade_no",out_trade_no);
		String spbill_create_ip = request.getRemoteAddr();
		session.setAttribute("spbill_create_ip",spbill_create_ip);
		Integer total_fee = 2;
		String notify_url = "http://test.youpinyuntai.com/hellochat/index.jsp";
			String appid = "wx49f1f2ac98ea0de6";
			String trade_type ="JSAPI";
			String signType = "MD5";
			String mch_id = "1245158702";
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("appid", appid);
			packageParams.put("mch_id", mch_id);
			packageParams.put("nonce_str", nonce_str);
			packageParams.put("body", body);
			packageParams.put("out_trade_no", out_trade_no);
			packageParams.put("total_fee", total_fee + "");
			packageParams.put("spbill_create_ip",spbill_create_ip);
			packageParams.put("notify_url",notify_url);
			packageParams.put("trade_type", trade_type);
			packageParams.put("openid", openid);
			String sign = WeixinUtil.createSign(packageParams);
		    session.setAttribute("sign",sign);
			String xml = "<xml>" +
					"<appid>" + appid + "</appid>" +
					"<body>" + body + "</body>" +
					"<mch_id>" + mch_id + "</mch_id>" +
					"<nonce_str>" + nonce_str + "</nonce_str>" +
					"<notify_url>" + notify_url + "</notify_url>" +
					"<openid>" + openid + "</openid>" +
					"<out_trade_no>" + out_trade_no + "</out_trade_no>" +
					"<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>" +
					"<total_fee>" + total_fee + "" + "</total_fee>" +
					"<trade_type>" + trade_type + "</trade_type>" +
					"<sign>" + sign + "</sign>" +
					"</xml>";
			String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			String prepay_id = "";
			try {
				Map<String,String> param = new GetWxOrderno().getPayNo(createOrderURL, xml);
				prepay_id=param.get("prepay_id");
				session.setAttribute("prepay_id",prepay_id);
				String return_code=param.get("return_code");
				String return_msg=param.get("return_msg");
				session.setAttribute("return_code",return_code);
				session.setAttribute("return_msg",return_msg);
				System.out.println("prepay_id:" + prepay_id);
				if (prepay_id.equals("")) {
					request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
					session.setAttribute("messageinfo","预支付错误");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			String appid2 = appid;
			String timestamp = Sha1Util.getTimeStamp();
			String nonceStr2 = nonce_str;
			String prepay_id2 = "prepay_id=" + prepay_id;
			String packages = prepay_id2;
			finalpackage.put("appId", appid2);
			finalpackage.put("timeStamp", timestamp);
			finalpackage.put("nonceStr", nonceStr2);
			finalpackage.put("package", packages);
			finalpackage.put("signType", "MD5");
			String finalsign = WeixinUtil.createSign(finalpackage);
		    finalpackage.put("sign",finalsign);
		   JSONObject object=JSONObject.fromObject(finalsign);
		   response.getOutputStream().write(object.toString().getBytes(Charset.forName("UTF-8")));
		   response.getOutputStream().flush();
/*
			System.out.println("pay.jsp?appid=" + appid2 + "&timeStamp=" + timestamp + "&nonceStr=" + nonceStr2 + "&package=" + packages + "&sign=" + finalsign);
*/
			/*response.sendRedirect("pay.html?appid=" + appid2 + "&timeStamp=" + timestamp + "&nonceStr=" + nonceStr2 + "&package=" + packages + "&sign=" + finalsign);*/
		   /* response.sendRedirect("pay.jsp");*/
	}
	/**
	 * 处理微信服务器发来的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("进来了");
	}
}