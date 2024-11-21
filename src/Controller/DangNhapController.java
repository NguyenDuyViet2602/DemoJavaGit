/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Home;
import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

/**
 *
 * @author Manh
 */
public class DangNhapController {

    public String login(String email, String password) throws SQLException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT userRole FROM appuser WHERE email=? AND password=? AND status='Active'";
            PreparedStatement prst = conn.prepareStatement(query);
            prst.setString(1, email);
            prst.setString(2, password);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                return rs.getString("userRole"); // Trả về userRole
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null; // Nếu không tìm thấy
    }
}
