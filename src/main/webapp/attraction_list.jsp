<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>${city.name} - 景点列表</title>
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
            <h1>${city.name} - 景点管理</h1>
            <a href="${pageContext.request.contextPath}/attraction/add?cityId=${city.id}">新建景点</a>
            <a href="${pageContext.request.contextPath}/city/list">返回城市列表</a>
            <br><br>
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
                    <c:forEach var="attraction" items="${attractions}">
                        <tr>
                            <td>${attraction.id}</td>
                            <td>${attraction.name}</td>
                            <td>${attraction.price}</td>
                            <td>${attraction.description}</td>
                            <td>
                                <a class="action-link"
                                    href="${pageContext.request.contextPath}/attraction/upload?id=${attraction.id}">上传图片</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </body>

        </html>