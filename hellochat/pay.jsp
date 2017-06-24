<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String appId = request.getParameter("appid");
String timeStamp = request.getParameter("timeStamp");
String nonceStr = request.getParameter("nonceStr");
String packageValue = request.getParameter("package");
String paySign = request.getParameter("sign");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>微信支付</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
      <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
      <script type="text/javascript" src="http://dl.sybspools.com/res/mobi/jweixin-1.0.0.js"></script>
  </head>
  
  <body>
		<br><br><br><br><br><br><br>
  			<div style="text-align:center;size:30px;"><input type="button" style="width:200px;height:80px;" value="微信支付" onclick="callpay()"></div>
            openid: ${openid}<br>
            prepay_id: ${prepay_id}<br>
            messageinfo:${messageinfo}<br>
            sign:${sign}<br>
            nonce_str:${nonce_str}<br>
            spbill_create_ip:${spbill_create_ip}<br>
            return_code:${return_code}<br>
            return_msg:${return_msg}<br>
            out_trade_no:${out_trade_no}<br>
            <%=appId%><br>
            <%=timeStamp%><br>
            <%=nonceStr%><br>
            <%=packageValue%><br>
            <%=paySign%><br>
  </body>
  <script type="text/javascript">
  	function callpay(){
        WeixinJSBridge.invoke('getBrandWCPayRequest',{
                "appId" : "<%=appId%>",
                "timeStamp" : "<%=timeStamp%>",
                "nonceStr" : "<%=nonceStr%>",
                "package" : "<%=packageValue%>",
                "signType" : "MD5",
                "paySign" : "<%=paySign%>"
   			},function(res){
				/*WeixinJSBridge.log(res.err_msg);*/
 				alert(res);
	            if(res.err_msg == "get_brand_wcpay_request:ok"){  
	                alert("微信支付成功!");  
	            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
	                alert("用户取消支付!");  
	            }else if(res.err_msg == "get_brand_wcpay_request:fail"){
                    alert('支付失败'+res.err_msg);
                }
	            else{
                    alert(res.err_msg);
	            }
			})
		}
  </script>
  
</html>
