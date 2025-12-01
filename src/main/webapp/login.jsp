<html>
<body>
<h2>Login</h2>
<% if(request.getParameter("error") != null) { %>
    <p style="color:red">Invalid username or password</p>
<% } %>
<form method="post" action="login">
    Username: <input type="text" name="username" required><br>
    Password: <input type="password" name="password" required><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
