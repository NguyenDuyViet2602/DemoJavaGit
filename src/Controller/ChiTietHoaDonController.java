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
        String sql = "INSERT INTO ChiTietHoaDon (HoaDonID, SanPhamID, SoLuong, DonGia) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, chiTietHoaDon.getHoaDon().getHoaDonId());
            pstmt.setInt(2, chiTietHoaDon.getSanPham().getSanPhamId());
            pstmt.setInt(3, chiTietHoaDon.getSoLuong());
            pstmt.setBigDecimal(4, chiTietHoaDon.getDonGia());

            int rowsInserted = pstmt.executeUpdate();
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

    public String getDiaChiByTenKhachHang(String tenKhachHang) {
        String diaChi = ""; // Giá trị mặc định nếu không tìm thấy
        String sql = "SELECT DiaChi FROM KhachHang WHERE TenKhachHang = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tenKhachHang); // Gán tham số tên khách hàng

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    diaChi = rs.getString("DiaChi"); // Lấy giá trị cột DiaChi
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi nếu xảy ra ngoại lệ
        }
        return diaChi; // Trả về địa chỉ
    }

    public boolean xoaChiTietHoaDon(int chiTietHoaDonID) throws SQLException {
        String selectSql = "SELECT SoLuong, SanPhamID FROM ChiTietHoaDon WHERE ChiTietHoaDonID = ?";
        String deleteSql = "DELETE FROM ChiTietHoaDon WHERE ChiTietHoaDonID = ?";
        String updateProductSql = "UPDATE SanPham SET SoLuongTon = SoLuongTon + ? WHERE SanPhamID = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement selectStmt = conn.prepareStatement(selectSql); PreparedStatement deleteStmt = conn.prepareStatement(deleteSql); PreparedStatement updateStmt = conn.prepareStatement(updateProductSql)) {

            // Lấy thông tin số lượng và sản phẩm từ chi tiết hóa đơn
            selectStmt.setInt(1, chiTietHoaDonID);
            ResultSet rs = selectStmt.executeQuery();

            int soLuong = 0;
            int sanPhamId = 0;
            if (rs.next()) {
                soLuong = rs.getInt("SoLuong");
                sanPhamId = rs.getInt("SanPhamID");
            }

            // Xóa chi tiết hóa đơn
            deleteStmt.setInt(1, chiTietHoaDonID);
            int rowsDeleted = deleteStmt.executeUpdate();

            if (rowsDeleted > 0) {
                // Cập nhật lại số lượng tồn kho của sản phẩm
                updateStmt.setInt(1, soLuong);
                updateStmt.setInt(2, sanPhamId);
                updateStmt.executeUpdate();
            }

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
