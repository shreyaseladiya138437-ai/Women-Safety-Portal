
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Register - Women Safety</title>
  <style>
    body { font-family: Arial; background:#f8f9fa; text-align:center; }
    form { background:white; display:inline-block; padding:30px; margin-top:60px; border-radius:10px; box-shadow:0 0 10px #ccc; }
    input, button { margin:8px; padding:8px; width:250px; }
  </style>
</head>
<body>

  <h2>Register New User</h2>
  <%
    String msg = (String) session.getAttribute("flash");
    if (msg != null) {
%>
    <p style="color:red;"><%= msg %></p>
<%
        session.removeAttribute("flash");
    }
%>
<form action="auth" method="post">
    <input type="hidden" name="action" value="register"/>
    <input type="text" name="name" placeholder="Full Name" required><br>
    <input type="email" name="email" placeholder="Email" required><br>
    <input type="text" name="phone" placeholder="Phone Number"><br>
    <input type="password" name="password" placeholder="Password" required><br>
    <button type="submit">Register</button>
  </form>

  <p>Already have an account? <a href="login.jsp">Login here</a></p>

</body>
</html>
