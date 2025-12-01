<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <title>${city.name} - 景点列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>

<body>
    <h1>${city.name} - 景点管理</h1>
    <div class="session">
            <c:if test="${not empty username}">
                <span>当前用户：${username}</span>
                <form method="post" action="${pageContext.request.contextPath}/logout">
                    <button type="submit" class="logout-button">退出</button>
                </form>
        </c:if>
    </div>
    <div class="toolbar">
        <a class="action-btn" href="${pageContext.request.contextPath}/attraction/add?cityId=${city.id}">新建景点</a>
        <a class="action-btn" href="${pageContext.request.contextPath}/city/list">返回城市列表</a>
    </div>
    <c:if test="${not empty message}">
        <p class="result">${message}</p>
    </c:if>
    <table>
        <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>门票价格</th>
                <th>简介</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="attraction" items="${attractions}" varStatus="loop">
                        <tr>
                            <td>${city.name}-${loop.count}</td>
                    <td>${attraction.name}</td>
                    <td>${attraction.price}</td>
                    <td>${attraction.description}</td>
                    <td>
                        <a class="action-btn"
                            href="${pageContext.request.contextPath}/attraction/upload?id=${attraction.id}">上传图片</a>
                        <a class="action-btn"
                            href="${pageContext.request.contextPath}/attraction/detail?id=${attraction.id}">详情</a>
                        <a class="action-btn"
                            href="${pageContext.request.contextPath}/attraction/edit?id=${attraction.id}">编辑</a>
                        <form class="inline-form" method="post" action="${pageContext.request.contextPath}/attraction/delete">
                            <input type="hidden" name="id" value="${attraction.id}">
                            <input type="hidden" name="cityId" value="${city.id}">
                            <button type="submit">删除</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>

</html>