
package com.app.womensafety.web;

import com.app.womensafety.dao.ContactDAO;
import com.app.womensafety.model.Contact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {

    private ContactDAO dao = new ContactDAO();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
                          throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("uid") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try {
            int uid = (Integer) session.getAttribute("uid");

            Contact c = new Contact();

            c.setUserId(uid);
            c.setName(req.getParameter("name"));
            c.setEmail(req.getParameter("email"));
            c.setPhone(req.getParameter("phone"));

            dao.addContact(c);

            session.setAttribute("flash", "Contact added successfully!");

            resp.sendRedirect(req.getContextPath() + "/contacts.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Unable to add contact", e);
        }
    }
}
