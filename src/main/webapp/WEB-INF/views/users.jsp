<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Shop</title>
    <%@ include file="resources.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container">
    <c:if test="${users.totalPages > 0}">
        <table class="table">
            <thead>
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Email</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users.data}">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.firstName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${users.pageNumber > 0}">
            <a href="show-users.html?pageNumber=${users.pageNumber - 1}">Back</a>
        </c:if>
        <c:if test="${users.pageNumber + 1 < users.totalPages}">
            <a href="show-users.html?pageNumber=${users.pageNumber + 1}" class="float-right">Next</a>
        </c:if>
        <div class="text-center">${users.pageNumber + 1} / ${users.totalPages}</div>
    </c:if>
</div>
</body>
</html>
