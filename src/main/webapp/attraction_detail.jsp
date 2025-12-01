<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>景点详情</title>
            <style>
                img {
                    max-width: 300px;
                }
            </style>
        </head>

        <body>
            <h1>景点详情</h1>
            <p><strong>编号:</strong> ${attraction.id}</p>
            <p><strong>名称:</strong> ${attraction.name}</p>
            <p><strong>门票价格:</strong> ${attraction.price}</p>
            <p><strong>简介:</strong> ${attraction.description}</p>
            <p><strong>图片:</strong><br>
                <c:if test="${not empty attraction.image_path}">
                    <img src="${pageContext.request.contextPath}/${attraction.image_path}" alt="Attraction Image">
                </c:if>
                <c:if test="${empty attraction.image_path}">
                    无图片
                </c:if>
            </p>
            <br>
            <a href="list?cityId=${attraction.city_id}">返回该城市所有景点</a>
        </body>

        </html>