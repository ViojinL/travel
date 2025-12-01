<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <c:set var="isEdit" value="${not empty attraction}" />
    <title>
        <c:choose>
            <c:when test="${isEdit}">编辑景点</c:when>
            <c:otherwise>新建景点</c:otherwise>
        </c:choose>
    </title>
    <style>
        label {
            display: block;
            margin-bottom: 12px;
        }

        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 6px;
            margin-top: 4px;
            box-sizing: border-box;
        }
    </style>
</head>

<body>
    <h1>
        <c:choose>
            <c:when test="${isEdit}">编辑景点</c:when>
            <c:otherwise>新建景点</c:otherwise>
        </c:choose>
    </h1>
    <c:url var="formAction" value="/attraction/add" />
    <c:if test="${isEdit}">
        <c:url var="formAction" value="/attraction/edit" />
    </c:if>
    <form method="post" action="${formAction}">
        <c:if test="${isEdit}">
            <input type="hidden" name="id" value="${attraction.id}">
        </c:if>
        <input type="hidden" name="cityId" value="${cityId}">
        <label>名称:
            <input type="text" name="name" required value="${attraction.name}">
        </label>
        <label>门票价格:
            <input type="number" step="0.01" name="price" required value="${attraction.price}">
        </label>
        <label>简介:
            <textarea name="description" required>${attraction.description}</textarea>
        </label>
        <button type="submit">保存</button>
    </form>
    <div class="links">
        <a class="action-btn" href="${pageContext.request.contextPath}/attraction/list?cityId=${cityId}">返回景点列表</a>
    </div>
</body>

</html>