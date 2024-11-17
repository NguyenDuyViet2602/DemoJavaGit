/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.*;
import db.DatabaseConnection;
import Model.KhachHang;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manh
 */
public class KhachHangController {

    public List<KhachHang> getAllKhachHang() throws SQLException {
        String sql = "SELECT * FROM KhachHang";
        List<KhachHang> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setKhachHangId(rs.getInt("KhachHangID"));
                khachHang.setTen(rs.getString("TenKhachHang"));
                khachHang.setEmail(rs.getString("Email"));
                khachHang.setSoDienThoai(rs.getString("SoDienThoai"));
                khachHang.setDiaChi(rs.getString("DiaChi"));
                khachHang.setMaSoThue(rs.getString("MaSoThue"));
                list.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    public KhachHang getKhachHangById(int khachHangId) throws SQLException {
        String sql = "SELECT * FROM KhachHang WHERE KhachHangID = ?";
        KhachHang khachHang = null;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, khachHangId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                khachHang = new KhachHang();
                khachHang.setKhachHangId(rs.getInt("KhachHangID"));
                khachHang.setTen(rs.getString("TenKhachHang"));
                khachHang.setEmail(rs.getString("Email"));
                khachHang.setSoDienThoai(rs.getString("SoDienThoai"));
                khachHang.setDiaChi(rs.getString("DiaChi"));
                khachHang.setMaSoThue(rs.getString("MaSoThue"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return khachHang;
    }

    public boolean addKhachHang(KhachHang khachHang) throws SQLException {
        String sql = "INSERT INTO KhachHang (TenKhachHang, Email, SoDienThoai, DiaChi, MaSoThue) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, khachHang.getTen());
            pstmt.setString(2, khachHang.getEmail());
            pstmt.setString(3, khachHang.getSoDienThoai());
            pstmt.setString(4, khachHang.getDiaChi());
            pstmt.setString(5, khachHang.getMaSoThue());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean suaKhachHang(KhachHang khachHang) throws SQLException {
        String sql = "UPDATE KhachHang SET TenKhachHang = ?, Email = ?, SoDienThoai = ?, DiaChi = ?, MaSoThue = ? WHERE KhachHangID = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, khachHang.getTen());
            pstmt.setString(2, khachHang.getEmail());
            pstmt.setString(3, khachHang.getSoDienThoai());
            pstmt.setString(4, khachHang.getDiaChi());
            pstmt.setString(5, khachHang.getMaSoThue());
            pstmt.setInt(6, khachHang.getKhachHangId()); // Đảm bảo bạn đã set ID của KhachHang

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có ít nhất một bản ghi được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean xoaKhachHang(int id) throws SQLException {
        String sql = "DELETE FROM KhachHang WHERE KhachHangID=?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<KhachHang> timKiemKhachHang(String keyword) throws SQLException {
        String sql = "SELECT * FROM KhachHang WHERE TenKhachHang LIKE ? OR Email LIKE ? OR SoDienThoai LIKE ? OR DiaChi LIKE ? OR MaSoThue LIKE ?";
        List<KhachHang> list = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Sử dụng wildcard '%' để tìm kiếm
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            pstmt.setString(3, "%" + keyword + "%");
            pstmt.setString(4, "%" + keyword + "%");
            pstmt.setString(5, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setKhachHangId(rs.getInt("KhachHangID"));
                khachHang.setTen(rs.getString("TenKhachHang"));
                khachHang.setEmail(rs.getString("Email"));
                khachHang.setSoDienThoai(rs.getString("SoDienThoai"));
                khachHang.setDiaChi(rs.getString("DiaChi"));
                khachHang.setMaSoThue(rs.getString("MaSoThue"));
                list.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }
}
