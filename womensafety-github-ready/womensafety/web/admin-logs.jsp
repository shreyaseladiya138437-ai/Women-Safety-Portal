

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.app.womensafety.dao.SosLogDAO,com.app.womensafety.model.SosLog" %>
<%
  SosLogDAO dao = new SosLogDAO();
  List<SosLog> logs = dao.listAll();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Admin - SOS Logs</title>
  <style>
    body { font-family: Arial; background:#fafafa; text-align:center; }
    table { margin:20px auto; border-collapse: collapse; }
    th, td { border:1px solid #aaa; padding:8px; }
    th { background:#eee; }
  </style>
</head>
<body>

  <h2>All SOS Alerts</h2>
  <table>
    <tr><th>ID</th><th>User ID</th><th>Message</th><th>Location</th><th>Time</th><th>Status</th></tr>
    <% for (SosLog s : logs) { %>
      <tr>
        <td><%= s.getId() %></td>
        <td><%= s.getUserId() %></td>
        <td><%= s.getMessage() %></td>
        <td><%= s.getLocation() %></td>
        <td><%= s.getCreatedAt() %></td>
        <td><%= s.getStatus() %></td>
      </tr>
    <% } %>
  </table>

</body>
</html>

