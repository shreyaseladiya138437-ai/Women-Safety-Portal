package com.app.womensafety.dao;

import com.app.womensafety.config.DBUtil;
import com.app.womensafety.model.SosLog;
import java.sql.*;
import java.util.*;

public class SosLogDAO {

    public int create(SosLog s) throws SQLException {
        String sql = "INSERT INTO sos_logs(user_id,message,location,status) VALUES(?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, s.getUserId());
            ps.setString(2, s.getMessage());
            ps.setString(3, s.getLocation());
            ps.setString(4, "SAVED");
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        }
    }

    public List<SosLog> listAll() throws SQLException {
        String sql = "SELECT s.*, u.name, u.email FROM sos_logs s JOIN users u ON s.user_id=u.id ORDER BY s.id DESC";
        List<SosLog> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SosLog s = new SosLog();
                s.setId(rs.getInt("id"));
                s.setUserId(rs.getInt("user_id"));
                s.setMessage(rs.getString("message"));
                s.setLocation(rs.getString("location"));
                s.setCreatedAt(rs.getTimestamp("created_at"));
                s.setStatus(rs.getString("status"));
                list.add(s);
            }
        }
        return list;
    }
}
