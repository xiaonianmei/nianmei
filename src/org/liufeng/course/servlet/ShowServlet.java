package org.liufeng.course.servlet;

import net.sf.json.JSONObject;
import org.liufeng.weixin.util.WeixinUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 核心请求处理类
 * 
 * @author liufeng
 * @date 2013-05-18
 */
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
        String code=request.getParameter("code");
		String state=request.getParameter("state");
        session.setAttribute("code",code);
		response.sendRedirect("pay.html?code="+code);
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