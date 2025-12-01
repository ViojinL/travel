<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>上传图片</title>
    </head>

    <body>
        <h1>上传图片</h1>
        <form method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${id}">
            <label>选择图片: <input type="file" name="image" required></label><br><br>
            <button type="submit">上传</button>
        </form>
        <br>
        <a href="javascript:history.back()">返回</a>
    </body>

    </html>