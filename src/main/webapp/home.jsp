<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%
    if(session != null && session.getAttribute("username") != null) {
        String username = (String) session.getAttribute("username");
%>
<h2>Welcome, <%= username %>!</h2>
<a href="login.jsp">Logout</a>
<%
    } else {
        response.sendRedirect("login.jsp");
    }
%>
</body>
</html>
