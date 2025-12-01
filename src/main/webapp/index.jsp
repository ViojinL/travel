<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>城市景点管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <style>
        header {
            margin-bottom: 24px;
        }

        nav a {
            color: #007acc;
            text-decoration: none;
            font-weight: bold;
        }

        nav a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>
    <header>
        <h1>旅游景点管理系统</h1>
        <p>对城市和景点进行增删改查，上传景点图片，统一通过表单+Servlet提交完成操作。</p>
    </header>
    <nav>
        <a class="action-btn" href="${pageContext.request.contextPath}/login">登录进入系统</a>
    </nav>
        <a href="${pageContext.request.contextPath}/login">登录进入系统</a>
    </nav>
</body>

</html>