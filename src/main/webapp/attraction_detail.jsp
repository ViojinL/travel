<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <title>景点详情</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>

    <body>
        <h1>景点详情</h1>
        <div class="session">
            <c:if test="${not empty username}">
                <span>当前用户：${username}</span>
                <form method="post" action="${pageContext.request.contextPath}/logout">
                    <button type="submit" class="logout-button">退出</button>
                </form>
            </c:if>
        </div>
    <c:if test="${not empty message}">
        <p class="result">${message}</p>
    </c:if>
    <p><strong>编号:</strong>
        <c:choose>
            <c:when test="${serial gt 0}">
                ${cityName}-${serial}
            </c:when>
            <c:otherwise>
                ${attraction.id}
            </c:otherwise>
        </c:choose>
    </p>
    <p><strong>名称:</strong> ${attraction.name}</p>
    <p><strong>门票价格:</strong> ${attraction.price}</p>
    <p><strong>简介:</strong> ${attraction.description}</p>
            <p><strong>图片:</strong><br>
                <c:if test="${not empty attraction.imagePath}">
                    <img src="${pageContext.request.contextPath}/${attraction.imagePath}" alt="Attraction Image">
                </c:if>
                <c:if test="${empty attraction.imagePath}">
            无图片
        </c:if>
    </p>
    <br>
            <a class="action-btn" href="${pageContext.request.contextPath}/attraction/list?cityId=${attraction.cityId}">返回该城市所有景点</a>
</body>

</html>