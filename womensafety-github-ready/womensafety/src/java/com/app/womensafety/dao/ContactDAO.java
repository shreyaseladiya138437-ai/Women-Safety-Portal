package com.app.womensafety.dao;

import com.app.womensafety.config.DBUtil;
import com.app.womensafety.model.Contact;
import java.sql.*;
import java.util.*;

public class ContactDAO {

    public int addContact(Contact c) throws SQLException {
        String sql = "INSERT INTO contacts(user_id,name,email,phone) VALUES(?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, c.getUserId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getPhone());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        }
    }

    public List<Contact> listByUser(int userId) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE user_id=?";
        List<Contact> list = new ArrayList<>();

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contact c = new Contact();
                c.setId(rs.getInt("id"));
                c.setUserId(rs.getInt("user_id"));
                c.setName(rs.getString("name"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                list.add(c);
            }
        }
        return list;
    }
}
