<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>${empty city ? '新建城市' : '编辑城市'}</title>
        </head>

        <body>
            <h1>${empty city ? '新建城市' : '编辑城市'}</h1>
            <form method="post">
                <c:if test="${not empty city}">
                    <input type="hidden" name="id" value="${city.id}">
                </c:if>
                <label>名称: <input type="text" name="name" value="${city.name}" required></label><br><br>
                <label>省份: <input type="text" name="province" value="${city.province}" required></label><br><br>
                <button type="submit">保存</button>
            </form>
            <br>
            <a href="${pageContext.request.contextPath}/city/list">返回列表</a>
        </body>

        </html>