<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
<%--
<%
    String name = request.getParameter("name");
    if (name == null) {
        name = "";
    }
    out.println("Witaj " + name);
%>
--%>
Hello ${param.name}
</body>
</html>
