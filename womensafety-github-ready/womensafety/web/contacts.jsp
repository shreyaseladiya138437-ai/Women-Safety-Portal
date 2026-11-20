

<%@ page import="java.util.*,com.app.womensafety.dao.ContactDAO,com.app.womensafety.model.Contact" %>
<%
    Integer uid = (Integer) session.getAttribute("uid");

    if (uid == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    ContactDAO dao = new ContactDAO();
    List<Contact> contacts = new ArrayList<>();

    try {
        contacts = dao.listByUser(uid);
    } catch (Exception e) {
        e.printStackTrace();
        out.println("Error loading contacts: " + e.getMessage());
    }
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>My Contacts</title>
  <style>
    body { font-family: Arial; background:#f4f4f4; text-align:center; }
    form { margin:20px auto; background:white; padding:20px; width:300px; border-radius:10px; }
    table { margin:20px auto; border-collapse: collapse; }
    td,th { border:1px solid #aaa; padding:8px; }
  </style>
</head>
<body>

  <h2>Trusted Contacts</h2>

  <form action="${pageContext.request.contextPath}/contact" method="post">
    <input type="text" name="name" placeholder="Contact Name" required><br>
    <input type="email" name="email" placeholder="Email"><br>
    <input type="text" name="phone" placeholder="Phone"><br>
    <button type="submit">Add Contact</button>
  </form>

  <h3>Your Saved Contacts</h3>
  <table>
    <tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th></tr>
    <% for (Contact c : contacts) { %>
      <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getName() %></td>
        <td><%= c.getEmail() %></td>
        <td><%= c.getPhone() %></td>
      </tr>
    <% } %>
  </table>

</body>
</html>
