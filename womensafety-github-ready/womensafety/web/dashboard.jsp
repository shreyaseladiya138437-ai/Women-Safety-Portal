

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Dashboard - Women Safety</title>
  <style>
    body { font-family: Arial; background:#fff7f7; text-align:center; }
    textarea, input { width:300px; margin:10px; padding:8px; }
    button { background:red; color:white; border:none; padding:10px 20px; cursor:pointer; border-radius:6px; }
  </style>
</head>
<body>

  <h2>Welcome to Women Safety Dashboard</h2>

  <form action="${pageContext.request.contextPath}/sos" method="post">

    <textarea name="message">
        I need help. Please contact me ASAP.
    </textarea>

    <input type="text" name="location"
           placeholder="Enter your location (optional)">

    <button type="submit">🚨 Send SOS</button>

</form>

  <p style="color:green;">${sessionScope.flash}</p>

  <p><a href="contacts.jsp">Manage Contacts</a></p>

</body>
</html>
