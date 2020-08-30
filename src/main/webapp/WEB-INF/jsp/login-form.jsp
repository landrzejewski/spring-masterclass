<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Bank</title>
    <%@ include file="resources.jsp" %>
</head>
<body>
<div class="container">
    <form method="post" action="login.html">
        <div class="form-group">
            <label for="login"><s:message code="login-form.login"/></label>
            <input id="login" class="form-control" name="username">
        </div>
        <div class="form-group">
            <label for="password"><s:message code="login-form.password"/></label>
            <input id="password" type="password" class="form-control" name="password"/>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary"><s:message code="login-form.submit"/></button>
    </form>
    <c:if test="${param.error != null}">
        <s:message code="login-form.errorMessage"/>
    </c:if>
</div>
</body>
</html>