<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户关系管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="common.jsp" %>
    <script src="/js/views/index.js?v=" + Math.random()></script>
</head>
<body>
<div id="cc" class="easyui-layout" fit="true" style="width:600px;height:400px;">
    <div data-options="region:'north'"
         style="height:100px;background:url('/img/banner-pic.gif') no-repeat;background-size:cover; ">
        <h1>客户关系管理系统</h1>
    </div>
    <div data-options="region:'west'" style="width:150px;">
        <!-- 手风琴+菜单 -->
        <div class="easyui-accordion" fit="true">
            <div title="菜单">
                <!-- 使用树组件来定义菜单 -->
                <ul id="menu"></ul>
            </div>
            <div title="帮助"></div>
            <div title="简介"></div>
        </div>
    </div>
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
        <!-- 正文内容 -->
        <div id="main_tabs" class="easyui-tabs" fit="true">
            <div title="欢迎页" closable="false">
                <div style="text-align: center;"><h1>欢迎登陆系统</h1></div>
            </div>
        </div>
    </div>
    <div data-options="region:'south'"
         style="height:30px;background:url('/img/banner-pic.gif') no-repeat;background-size:cover; ">
        <div style="text-align: center;">版权信息</div>
    </div>
</div>
</body>
</html>