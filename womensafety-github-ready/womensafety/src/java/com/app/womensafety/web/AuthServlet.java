package com.app.womensafety.web;

import com.app.womensafety.config.DBUtil;
import com.app.womensafety.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    // Simple register/login in one servlet using "action" param (register | login)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("register".equalsIgnoreCase(action)) {
            handleRegister(req, resp);
        } else if ("login".equalsIgnoreCase(action)) {
            handleLogin(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
        }
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password"); // hash in production!

        // Simple check
        if (email == null || password == null) {
            req.getSession().setAttribute("flash", "Email and password required");
            resp.sendRedirect(req.getContextPath() + "/register.jsp");
            return;
        }

        // Hashing recommended (use BCrypt in real app). Here we store plain for simplicity
        String pwHash = password; // replace with hash

        String sql = "INSERT INTO users(name,email,phone,password_hash) VALUES(?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, pwHash);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int userId = rs.getInt(1);
                    HttpSession session = req.getSession();
                    session.setAttribute("uid", userId);
                    session.setAttribute("uname", name);
                    resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
                    return;
                }
            }
            req.getSession().setAttribute("flash", "Registration failed");
            resp.sendRedirect(req.getContextPath() + "/register.jsp");
        } catch (SQLException e) {
            // handle duplicate email nicely
            req.getSession().setAttribute("flash", "Registration error: " + e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/register.jsp");
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String sql = "SELECT id, name, password_hash FROM users WHERE email = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String pwHash = rs.getString("password_hash");
                    // In production compare hash (BCrypt). Here we compare plain text.
                    if (password.equals(pwHash)) {
                        int uid = rs.getInt("id");
                        String name = rs.getString("name");
                        HttpSession session = req.getSession();
                        session.setAttribute("uid", uid);
                        session.setAttribute("uname", name);
                        resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
                        return;
                    }
                }
            }
            req.getSession().setAttribute("flash", "Invalid credentials");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
