

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login - Women Safety</title>
  <style>
    body { font-family: Arial; background:#f0f0f0; text-align:center; }
    form { background:white; display:inline-block; padding:30px; margin-top:80px; border-radius:10px; box-shadow:0 0 10px #ccc; }
    input, button { margin:8px; padding:8px; width:250px; }
  </style>
</head>
<body>

  <h2>Login</h2>
  <%
    String msg = (String) session.getAttribute("flash");
    if (msg != null) {
%>
    <p style="color:red;"><%= msg %></p>
<%
        session.removeAttribute("flash");
    }
%>
  <form action="AuthServlet" method="post">
    <input type="hidden" name="action" value="login"/>
    <input type="email" name="email" placeholder="Email" required><br>
    <input type="password" name="password" placeholder="Password" required><br>
    <button type="submit">Login</button>
  </form>

  <p>New user? <a href="register.jsp">Register here</a></p>

</body>
</html>
