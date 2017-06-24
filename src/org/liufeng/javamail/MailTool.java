package org.liufeng.javamail;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Properties;

/**
 * Created by Administrator on 2017/6/21.
 */
public class MailTool {
    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
        Properties props = new Properties();
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
        //.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", SSL_FACTORY);

        Session session = Session.getInstance(props);

        Message msg = new MimeMessage(session);
        msg.setSubject("天问");
        StringBuilder builder = new StringBuilder();
        builder.append("The gears of fate have turned, the church bells ring at midnight, and destiny calls you forward through waiting without end");
        //builder.append("\n张大佬在睡觉");
        builder.append("\n时间 " + System.currentTimeMillis());
        msg.setText(builder.toString());
        Address toAddress = new InternetAddress("xiqinger13@163.com");
        Address cc1Address = new InternetAddress("405959744@qq.com");
        Address cc2Address = new InternetAddress("1181608503@qq.com");
        msg.addRecipient(Message.RecipientType.TO, toAddress);
        msg.addRecipient(Message.RecipientType.CC, cc1Address);
        msg.addRecipient(Message.RecipientType.CC, cc2Address);
        msg.setFrom(new InternetAddress("1159532047@qq.com","萧亦然"));
        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com", "1159532047@qq.com", "gyifotqzuwifbage");
        /*transport.sendMessage(msg, new Address[] { new InternetAddress("405959744@qq.com") });*/
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    }
}

