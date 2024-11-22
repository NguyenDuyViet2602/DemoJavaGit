/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.NguoiDung;
import Model.SanPham;
import db.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class NguoiDungController {

    public boolean validateFields(String name, String mobileNumber, String email, String address, String password, String formType) {
        if (formType.equals("edit") && !name.isEmpty() && !mobileNumber.isEmpty() && !email.isEmpty() && !address.isEmpty()) {
            return true;
        } else if (formType.equals("new") && !name.isEmpty() && !mobileNumber.isEmpty() && !email.isEmpty() && !address.isEmpty() && !password.isEmpty()) {
            return true;
        }
        return false;
    }

    public void addUser(String name, String mobileNumber, String email, String password, String address, String status) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement("INSERT INTO appuser (userRole, name, mobileNumber, email, password, address, status) VALUES ('Admin', ?, ?, ?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, mobileNumber);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, address);
            ps.setString(6, status);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "User Added Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public void updateUser(int appuserPk, String name, String mobileNumber, String email, String address, String status) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Failed to connect to the database");
                return;
            }

            // Kiểm tra giá trị của appuserPk
            if (appuserPk <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid user ID for update");
                return;
            }

            PreparedStatement prst = conn.prepareStatement("update appuser set name=?, mobileNumber=?, email=?, address=?, status=? where appuser_pk=?");
            prst.setString(1, name);
            prst.setString(2, mobileNumber);
            prst.setString(3, email);
            prst.setString(4, address);
            prst.setString(5, status);
            prst.setInt(6, appuserPk);

            int rowsUpdated = prst.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "User Updated Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "No user was updated. Please check if the data is different.");
            }

            prst.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void showUsers(DefaultTableModel model) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "select * from appuser where userRole='Admin'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("appuser_pk"),
                    rs.getString("name"),
                    rs.getString("mobileNumber"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("status")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void handleUserTableClick(JTable tableUser, NguoiDung nguoiDung) {
        int index = tableUser.getSelectedRow();
        nguoiDung.setAppuserPk((int) tableUser.getValueAt(index, 0));
        nguoiDung.setName((String) tableUser.getValueAt(index, 1));
        nguoiDung.setMobileNumber((String) tableUser.getValueAt(index, 2));
        nguoiDung.setEmail((String) tableUser.getValueAt(index, 3));
        nguoiDung.setAddress((String) tableUser.getValueAt(index, 4));
        nguoiDung.setStatus((String) tableUser.getValueAt(index, 5));
    }

    public boolean DeleteData(int userId) {
        String sql = "DELETE FROM appuser WHERE appuser_pk=?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, userId); // Sử dụng setInt để đặt ID người dùng

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while deleting user: " + e.getMessage());
            return false; // Trả về false nếu có lỗi
        }
    }
    public List<SanPham> timKiemSanPham(String keyword) throws SQLException {
        String sql = "SELECT SanPhamID, TenSanPham, DonGia, MoTa, SoLuongTon FROM SanPham WHERE TenSanPham LIKE ? OR DonGia LIKE ? OR MoTa LIKE ? OR SoLuongTon LIKE ?";
        List<SanPham> danhSach = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Sử dụng wildcard '%' để tìm kiếm
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            pstmt.setString(3, "%" + keyword + "%");
            pstmt.setString(4, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setSanPhamId(rs.getInt("SanPhamID"));
                sanPham.setTenSanPham(rs.getString("TenSanPham"));
                sanPham.setGiaSanPham(rs.getBigDecimal("DonGia"));
                sanPham.setMoTaSanPham(rs.getString("MoTa"));
                sanPham.setSoluongton(rs.getInt("SoLuongTon"));
                danhSach.add(sanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return danhSach;
    }
}
