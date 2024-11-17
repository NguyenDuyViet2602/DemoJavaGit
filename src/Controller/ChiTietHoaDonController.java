/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.HoaDon;
import Model.ChiTietHoaDon;
import Model.KhachHang;
import Model.SanPham;
import db.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Manh
 */
public class ChiTietHoaDonController {

    public List<ChiTietHoaDon> getChiTietHoaDonById() throws SQLException {
        String sql = "SELECT ChiTietHoaDonID, H.SoHoaDon, SP.TenSanPham, SoLuong, SP.DonGia, ThanhTien  FROM ChiTietHoaDon CTHD, SanPham SP, HoaDon H WHERE H.HoaDonID = CTHD.HoaDonID AND SP.SanPhamID = CTHD.SanPhamID";
        List<ChiTietHoaDon> danhSach = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setChiTietHoaDonID(rs.getInt("ChiTietHoaDonID"));

                HoaDon hoaDon = new HoaDon();
                hoaDon.setSoHoaDon(rs.getString("SoHoaDon"));
                chiTietHoaDon.setHoaDon(hoaDon);

                SanPham sanPham = new SanPham();
                sanPham.setTenSanPham(rs.getString("TenSanPham"));
                chiTietHoaDon.setSanPham(sanPham);

                chiTietHoaDon.setSoLuong(rs.getInt("SoLuong"));
                chiTietHoaDon.setDonGia(rs.getBigDecimal("DonGia"));
                BigDecimal soLuong = BigDecimal.valueOf(chiTietHoaDon.getSoLuong());
                BigDecimal donGia = chiTietHoaDon.getDonGia();
                BigDecimal thanhTien = soLuong.multiply(donGia);
                chiTietHoaDon.setThanhTien(thanhTien);
                danhSach.add(chiTietHoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return danhSach;
    }

    public List<ChiTietHoaDon> getChiTietHoaDonById(int chiTietHoaDonID) throws SQLException {
        String sql = "SELECT ChiTietHoaDonID, H.SoHoaDon, SP.TenSanPham, SoLuong, SP.DonGia, (CTHD.SoLuong * CTHD.DonGia) AS ThanhTien  FROM ChiTietHoaDon CTHD, SanPham SP, HoaDon H WHERE H.HoaDonID = CTHD.HoaDonID AND SP.SanPhamID = CTHD.SanPhamID AND CTHD.ChiTietHoaDonID = ?";
        List<ChiTietHoaDon> danhSach = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, chiTietHoaDonID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setChiTietHoaDonID(rs.getInt("ChiTietHoaDonID"));

                HoaDon hoaDon = new HoaDon();
                hoaDon.setSoHoaDon(rs.getString("SoHoaDon"));
                chiTietHoaDon.setHoaDon(hoaDon);

                SanPham sanPham = new SanPham();
                sanPham.setTenSanPham(rs.getString("TenSanPham"));
                chiTietHoaDon.setSanPham(sanPham);

                chiTietHoaDon.setSoLuong(rs.getInt("SoLuong"));
                chiTietHoaDon.setDonGia(rs.getBigDecimal("DonGia"));

                BigDecimal soLuong = BigDecimal.valueOf(chiTietHoaDon.getSoLuong());
                BigDecimal donGia = chiTietHoaDon.getDonGia();
                BigDecimal thanhTien = soLuong.multiply(donGia);
                chiTietHoaDon.setThanhTien(thanhTien);
                danhSach.add(chiTietHoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return danhSach;
    }

    public boolean themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) throws SQLException {
        String checkQtySql = "SELECT SoLuongTon FROM SanPham WHERE SanPhamID = ?";
        String insertSql = "INSERT INTO ChiTietHoaDon (HoaDonID, SanPhamID, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
        String updateProductSql = "UPDATE SanPham SET SoLuongTon = SoLuongTon - ? WHERE SanPhamID = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement checkStmt = conn.prepareStatement(checkQtySql); PreparedStatement insertStmt = conn.prepareStatement(insertSql); PreparedStatement updateStmt = conn.prepareStatement(updateProductSql)) {

            // Kiểm tra số lượng tồn kho hiện tại
            checkStmt.setInt(1, chiTietHoaDon.getSanPham().getSanPhamId());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int currentQty = rs.getInt("SoLuongTon");
                if (currentQty < chiTietHoaDon.getSoLuong()) {
                    // Số lượng tồn kho không đủ
                    throw new SQLException("Số lượng sản phẩm không đủ.");
                }
            }

            // Thêm chi tiết hóa đơn
            insertStmt.setInt(1, chiTietHoaDon.getHoaDon().getHoaDonId());
            insertStmt.setInt(2, chiTietHoaDon.getSanPham().getSanPhamId());
            insertStmt.setInt(3, chiTietHoaDon.getSoLuong());
            insertStmt.setBigDecimal(4, chiTietHoaDon.getDonGia());

            int rowsInserted = insertStmt.executeUpdate();
            if (rowsInserted > 0) {
                // Cập nhật số lượng sản phẩm trong kho
                updateStmt.setInt(1, chiTietHoaDon.getSoLuong());
                updateStmt.setInt(2, chiTietHoaDon.getSanPham().getSanPhamId());
                updateStmt.executeUpdate();
            }

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void capNhatChiTietHoaDon(ChiTietHoaDon obj) throws SQLException {
        String getCurrentQtySql = "SELECT SoLuong FROM ChiTietHoaDon WHERE HoaDonId = ? AND SanPhamId = ?";
        String checkQtySql = "SELECT SoLuongTon FROM SanPham WHERE SanPhamID = ?";
        String updateSql = "UPDATE ChiTietHoaDon SET SoLuong = ?, DonGia = ? WHERE HoaDonId = ? AND SanPhamId = ?";
        String updateProductSql = "UPDATE SanPham SET SoLuongTon = SoLuongTon - ? WHERE SanPhamID = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement getCurrentQtyStmt = conn.prepareStatement(getCurrentQtySql); PreparedStatement checkStmt = conn.prepareStatement(checkQtySql); PreparedStatement updateStmt = conn.prepareStatement(updateSql); PreparedStatement updateProductStmt = conn.prepareStatement(updateProductSql)) {

            getCurrentQtyStmt.setInt(1, obj.getHoaDon().getHoaDonId());
            getCurrentQtyStmt.setInt(2, obj.getSanPham().getSanPhamId());
            ResultSet rs = getCurrentQtyStmt.executeQuery();

            int currentQty = 0;
            if (rs.next()) {
                currentQty = rs.getInt("SoLuong");
            }

            int newQty = obj.getSoLuong();
            int difference = newQty - currentQty;

            checkStmt.setInt(1, obj.getSanPham().getSanPhamId());
            ResultSet checkRs = checkStmt.executeQuery();

            if (checkRs.next()) {
                int availableQty = checkRs.getInt("SoLuongTon");
                if (availableQty < difference) {
                    // Số lượng tồn kho không đủ
                    throw new SQLException("Số lượng sản phẩm không đủ.");
                }
            }

            updateStmt.setInt(1, newQty);
            updateStmt.setBigDecimal(2, obj.getDonGia());
            updateStmt.setInt(3, obj.getHoaDon().getHoaDonId());
            updateStmt.setInt(4, obj.getSanPham().getSanPhamId());

            int rowsUpdated = updateStmt.executeUpdate();
            if (rowsUpdated > 0) {
                updateProductStmt.setInt(1, difference);
                updateProductStmt.setInt(2, obj.getSanPham().getSanPhamId());
                updateProductStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ResultSet hienThiChiTietSanPham() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM SanPham";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public ResultSet hienThiChiTietHoaDon() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM HoaDon";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public int getSanPhamIdByName(String tenSanPham) throws SQLException {
        String sql = "SELECT SanPhamID FROM SanPham WHERE TenSanPham = ?";
        int sanPhamId = -1; // Giá trị mặc định nếu không tìm thấy
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tenSanPham);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                sanPhamId = rs.getInt("SanPhamID");
            }
        }
        return sanPhamId;
    }

    public int getHoaDonIdByName(String soHoaDon) throws SQLException {
        String sql = "SELECT HoaDonID FROM HoaDon WHERE SoHoaDon = ?";
        int sanPhamId = -1; // Giá trị mặc định nếu không tìm thấy
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, soHoaDon);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                sanPhamId = rs.getInt("HoaDonID");
            }
        }
        return sanPhamId;
    }

    public BigDecimal getGiaBanByTenSanPham(String tenSanPham) {
        BigDecimal giaBan = BigDecimal.ZERO;
        String sql = "SELECT DonGia FROM SanPham WHERE TenSanPham = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tenSanPham);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                giaBan = rs.getBigDecimal("DonGia");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return giaBan;
    }

    public boolean xoaHoaDon(int id) throws SQLException {
        String sql = "DELETE FROM ChiTietHoaDon WHERE ChiTietHoaDonID=?";
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

    public List<ChiTietHoaDon> timKiemChiTietHoaDon(String keyword) throws SQLException {
        String sql = "SELECT H.ChiTietHoaDonID, HD.SoHoaDon, SP.TenSanPham, H.SoLuong, H.DonGia, H.ThanhTien "
                + "FROM ChiTietHoaDon H "
                + "JOIN SanPham SP ON H.SanPhamID = SP.SanPhamID "
                + "JOIN HoaDon HD ON H.HoaDonID = HD.HoaDonID "
                + "WHERE HD.SoHoaDon LIKE ? OR SP.TenSanPham LIKE ? OR "
                + "CAST(H.SoLuong AS VARCHAR) LIKE ? OR "
                + "CAST(H.DonGia AS VARCHAR) LIKE ? OR "
                + "CAST(H.ThanhTien AS VARCHAR) LIKE ?";
        List<ChiTietHoaDon> danhSach = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            pstmt.setString(3, "%" + keyword + "%");
            pstmt.setString(4, "%" + keyword + "%");
            pstmt.setString(5, "%" + keyword + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setChiTietHoaDonID(rs.getInt("ChiTietHoaDonID"));

                HoaDon hoaDon = new HoaDon();
                hoaDon.setSoHoaDon(rs.getString("SoHoaDon"));
                chiTietHoaDon.setHoaDon(hoaDon);

                SanPham sanPham = new SanPham();
                sanPham.setTenSanPham(rs.getString("TenSanPham"));
                chiTietHoaDon.setSanPham(sanPham);

                chiTietHoaDon.setSoLuong(rs.getInt("SoLuong"));
                chiTietHoaDon.setDonGia(rs.getBigDecimal("DonGia"));
                BigDecimal soLuong = BigDecimal.valueOf(chiTietHoaDon.getSoLuong());
                BigDecimal donGia = chiTietHoaDon.getDonGia();
                BigDecimal thanhTien = soLuong.multiply(donGia);
                chiTietHoaDon.setThanhTien(thanhTien);

                danhSach.add(chiTietHoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return danhSach;
    }
}
