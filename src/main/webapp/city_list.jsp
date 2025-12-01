<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <title>城市列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>

<body>
    <h1>城市管理</h1>
    <div class="session">
            <c:if test="${not empty username}">
                <span>当前用户：${username}</span>
                <form method="post" action="${pageContext.request.contextPath}/logout">
                    <button type="submit" class="logout-button">退出</button>
                </form>
        </c:if>
    </div>
    <div class="controls">
        <a class="action-btn" href="${pageContext.request.contextPath}/city/add">新建城市</a>
    </div>
    <c:if test="${not empty message}">
        <p class="result">${message}</p>
    </c:if>
    <table>
        <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>省份</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="city" items="${cities}">
                <tr>
                    <td>${city.id}</td>
                    <td>${city.name}</td>
                    <td>${city.province}</td>
                    <td>
                        <a class="action-btn" href="${pageContext.request.contextPath}/city/edit?id=${city.id}">编辑</a>
                        <a class="action-btn"
                            href="${pageContext.request.contextPath}/attraction/list?cityId=${city.id}">查看景点</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>

</html>