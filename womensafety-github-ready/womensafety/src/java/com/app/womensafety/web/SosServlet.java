
package com.app.womensafety.web;

import com.app.womensafety.dao.SosLogDAO;
import com.app.womensafety.model.SosLog;

import javax.servlet.ServletException;

import javax.servlet.http.*;
import java.io.IOException;


public class SosServlet extends HttpServlet {

    private SosLogDAO logDAO = new SosLogDAO();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
                          throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("uid") == null) {
            session = req.getSession(true);
            session.setAttribute("flash", "Please login first");

            resp.sendRedirect(
                req.getContextPath() + "/login.jsp"
            );
            return;
        }

        int uid = (Integer) session.getAttribute("uid");

        String msg = req.getParameter("message");
        String location = req.getParameter("location");

        try {
            SosLog s = new SosLog();

            s.setUserId(uid);
            s.setMessage(msg != null ? msg : "SOS");
            s.setLocation(location);
            s.setStatus("SAVED");

            logDAO.create(s);

            session.setAttribute(
                "flash",
                "SOS alert recorded successfully."
            );

            resp.sendRedirect(
                req.getContextPath() + "/dashboard.jsp"
            );

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("SOS saving failed", e);
        }
    }
}
