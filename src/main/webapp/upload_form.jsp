<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>上传图片</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <style>
        form {
            margin-bottom: 16px;
        }

        label {
            display: block;
            margin-bottom: 12px;
        }

        input[type="file"] {
            margin-top: 4px;
        }
    </style>
</head>

<body>
    <h1>上传图片</h1>
    <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/attraction/upload">
        <input type="hidden" name="id" value="${id}">
        <label>选择图片:
            <input type="file" name="image" required>
        </label>
        <button type="submit">上传</button>
    </form>
    <a class="action-btn" href="${pageContext.request.contextPath}/attraction/list?cityId=${cityId}">返回景点列表</a>
</body>

</html>