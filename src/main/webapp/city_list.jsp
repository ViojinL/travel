<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>城市列表</title>
            <style>
                table {
                    border-collapse: collapse;
                    width: 100%;
                }

                th,
                td {
                    border: 1px solid black;
                    padding: 8px;
                    text-align: left;
                }

                th {
                    background-color: #f2f2f2;
                }

                .action-link {
                    margin-right: 10px;
                }
            </style>
        </head>

        <body>
            <h1>城市管理</h1>
            <a href="${pageContext.request.contextPath}/city/add">新建城市</a>
            <br><br>
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
                                <a class="action-link"
                                    href="${pageContext.request.contextPath}/city/edit?id=${city.id}">编辑</a>
                                <a class="action-link"
                                    href="${pageContext.request.contextPath}/attraction/list?cityId=${city.id}">显示景点</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </body>

        </html>