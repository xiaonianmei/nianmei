package org.liufeng.course.servlet;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.liufeng.weixin.util.WeixinUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * 核心请求处理类
 * 
 * @author liufeng
 * @date 2013-05-18
 */
public class JumpServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		/*String openId = request.getParameter("openId");
		String  userAgent = request.getParameter("userAgent");
		System.out.println(userAgent);
		String gid="";
		System.out.println("**************** openId ************ " + openId);*/
		/*String url= WeixinUtil.getuserid(StringUtils.isBlank(openId) ? "-1" : openId);*/
		String url=WeixinUtil.getOpenid();
		System.out.println(url);
		response.sendRedirect(url);
	}

	/**
	 * 处理微信服务器发来的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("进来了");

       this.doGet(request,response);
		// 调用核心业务类接收消息、处理消息

	}


}