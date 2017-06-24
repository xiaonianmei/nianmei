<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/24
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="jquery-1.11.0.min.js"></script>

</head>
<body>
<div id="editor">
    <p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p>
</div>

<!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
<script type="text/javascript" src="wangEditor.min.js"></script>
<script type="text/javascript">
    var E = window.wangEditor
    var editor = new E('#editor')
    // 或者 var editor = new E( document.getElementById('#editor') )
    editor.customConfig.menus =[
        'head',  // 标题
        'bold',  // 粗体
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'quote',  // 引用
        'emoticon',  // 表情
        'image',  // 插入图片
        'table',  // 表格
        'video',  // 插入视频
        'code',  // 插入代码
        'undo',  // 撤销
        'redo'  // 重复
    ]
            editor.create();
    function gethtml(){
        alert(editor.txt.html());
        editor.txt.append("<img src='http://o7u5uucj9.bkt.clouddn.com/14979359171497935917' >");
    }
     function gettext(){
         alert(editor.txt.text());
     }
</script>
<button id="btn1" onclick="gethtml()">获取html</button>
<button id="btn2" onclick="gettext()">获取text</button>
</body>
</html>
