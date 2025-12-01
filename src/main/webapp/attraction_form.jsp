<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>新建景点</title>
    </head>

    <body>
        <h1>新建景点</h1>
        <form method="post">
            <input type="hidden" name="cityId" value="${cityId}">
            <label>名称: <input type="text" name="name" required></label><br><br>
            <label>门票价格: <input type="number" step="0.01" name="price" required></label><br><br>
            <label>简介: <textarea name="description" required></textarea></label><br><br>
            <button type="submit">保存</button>
        </form>
        <br>
        <a href="list?cityId=${cityId}">返回景点列表</a>
    </body>

    </html>