<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <title>登录 - 旅游景点管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <style>
        body {
            background-color: #ffffff;
            font-family: "Segoe UI", Arial, sans-serif;
            color: #222222;
            margin: 0;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .login-panel {
            width: 360px;
            border: 1px solid #cccccc;
            padding: 24px;
            box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
        }

        form {
            max-width: 100%;
        }

        label {
            display: block;
            margin-bottom: 12px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 6px;
            margin-top: 4px;
            box-sizing: border-box;
        }

        button {
            padding: 8px 16px;
            background-color: #007acc;
            color: white;
            border: none;
        }

        .error {
            margin-bottom: 12px;
            color: #d93025;
        }
    </style>
</head>

<body>
    <div class="login-panel">
        <h1>登录</h1>
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/login">
        <label>
            用户名:
            <input type="text" name="username" required>
        </label>
        <label>
            密码:
            <input type="password" name="password" required>
        </label>
        <button type="submit">登录</button>
    </form>
    </div>
</body>

</html>
