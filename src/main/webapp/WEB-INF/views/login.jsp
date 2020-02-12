<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="common.jsp" %>
    <script src="/js/views/login.js"></script>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<section class="container">
    <div class="login">
        <h1>用户登录</h1>
        <form method="post">
            <p><input type="text" name="username" value="" placeholder="账号"></p>
            <p><input type="password" name="password" value="" placeholder="密码"></p>
            <p class="submit">
                <input type="button" value="登录" onclick="submitForm()">
                <input type="button" value="重置" onclick="resetForm()">
            </p>
        </form>
    </div>
</section>
<div style="text-align:center;" class="login-help">
    <p>Copyright ©2020 xxxx科技有限公司</p>
</div>
</body>
</html>
