/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.SanPham;
import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manh
 */
public class SanPhamController {

    public List<SanPham> getTatCaSanPham() throws SQLException {
        String sql = "SELECT * FROM SanPham";
        List<SanPham> danhSach = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
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

    public SanPham getSanPhamById(int sanPhamId) throws SQLException {
        String sql = "SELECT * FROM SanPham WHERE SanPhamID = ?";
        SanPham sanPham = null;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, sanPhamId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                sanPham = new SanPham();
                sanPham.setSanPhamId(rs.getInt("SanPhamID"));
                sanPham.setTenSanPham(rs.getString("TenSanPham"));
                sanPham.setGiaSanPham(rs.getBigDecimal("DonGia"));
                sanPham.setMoTaSanPham(rs.getString("MoTa"));
                sanPham.setSoluongton(rs.getInt("SoLuongTon"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return sanPham;
    }

    public boolean themSanPham(SanPham sanPham) throws SQLException {
        String sql = "INSERT INTO SanPham (TenSanPham, DonGia, MoTa, SoLuongTon) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, sanPham.getTenSanPham());
            pstmt.setBigDecimal(2, sanPham.getGiaSanPham());
            pstmt.setString(3, sanPham.getMoTaSanPham());
            pstmt.setInt(4, sanPham.getSoluongton());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean suaSanPham(SanPham sanPham) throws SQLException {
        String sql = "UPDATE SanPham SET TenSanPham = ?, DonGia = ?, MoTa = ?, SoLuongTon = ? WHERE SanPhamID = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, sanPham.getTenSanPham());
            pstmt.setBigDecimal(2, sanPham.getGiaSanPham());
            pstmt.setString(3, sanPham.getMoTaSanPham());
            pstmt.setInt(4, sanPham.getSoluongton());
            pstmt.setInt(5, sanPham.getSanPhamId()); // Đảm bảo bạn đã set ID của sản phẩm

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có ít nhất một bản ghi được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean xoaSanPham(int id) throws SQLException {
        String sql = "DELETE FROM SanPham WHERE SanPhamID=?";
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

    public void capNhatSoLuongSanPham(int sanPhamId, int soLuongMoi) throws SQLException {
        String sql = "UPDATE SanPham SET SoLuongTon = ? WHERE SanPhamID = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, soLuongMoi);
            pstmt.setInt(2, sanPhamId);
            pstmt.executeUpdate();
        }
    }

    public int getSanPhamIdByTen(String tenSanPham) throws SQLException {
        String sql = "SELECT SanPhamID FROM SanPham WHERE TenSanPham = ?";
        int sanPhamId = -1;

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tenSanPham);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                sanPhamId = rs.getInt("SanPhamID");
            }
        }
        return sanPhamId;
    }

}
