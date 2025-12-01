<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${empty city}">
        <c:set var="formAction" value="${pageContext.request.contextPath}/city/add" />
    </c:when>
    <c:otherwise>
        <c:set var="formAction" value="${pageContext.request.contextPath}/city/edit" />
    </c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>

<head>
    <title>${empty city ? '新建城市' : '编辑城市'}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <style>
        form {
            max-width: 420px;
        }

        label {
            display: block;
            margin-bottom: 12px;
        }

        input[type="text"] {
            width: 100%;
            padding: 6px;
            margin-top: 4px;
            box-sizing: border-box;
        }
    </style>
</head>

<body>
    <h1>${empty city ? '新建城市' : '编辑城市'}</h1>
    <form method="post" action="${formAction}">
        <c:if test="${not empty city}">
            <input type="hidden" name="id" value="${city.id}">
        </c:if>
        <label>名称:
            <input type="text" name="name" value="${city.name}" required>
        </label>
        <label>省份:
            <input type="text" name="province" value="${city.province}" required>
        </label>
        <button type="submit">保存</button>
    </form>
    <br>
    <a class="action-btn" href="${pageContext.request.contextPath}/city/list">返回列表</a>
</body>

</html>