package com.app.womensafety.web;

import com.app.womensafety.dao.SosLogDAO;
import com.app.womensafety.model.SosLog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/logs")
public class AdminServlet extends HttpServlet {
    private SosLogDAO dao = new SosLogDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // OPTIONAL: check admin login / role here. For now just show logs.
        try {
            List<SosLog> logs = dao.listAll();
            req.setAttribute("logs", logs);
            req.getRequestDispatcher("/jsp/admin-logs.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
