/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.KhachHang;
import Model.HoaDon;
import db.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author Manh
 */
public class HoaDonController {

    public List<HoaDon> getTatCaHoaDon() throws SQLException {
        String sql = "SELECT HoaDonID, SoHoaDon, TenKhachHang, TongTien, ThueTien, NgayLapHoaDon, NgayHanThanhToan, TrangThai FROM HoaDon H, KhachHang K WHERE H.KhachHangID = K.KhachHangID";
        List<HoaDon> danhSach = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setHoaDonId(rs.getInt("HoaDonID"));
                hoaDon.setSoHoaDon(rs.getString("SoHoaDon"));

                KhachHang khachHang = new KhachHang();
                khachHang.setTen(rs.getString("TenKhachHang"));
                hoaDon.setKhachHang(khachHang);

                hoaDon.setTongTien(rs.getBigDecimal("TongTien"));
                hoaDon.setThueTien(rs.getBigDecimal("ThueTien"));
                hoaDon.setNgayLapHoaDon(rs.getDate("NgayLapHoaDon"));
                hoaDon.setNgayHanThanhToan(rs.getDate("NgayHanThanhToan"));
                hoaDon.setTrangThai(rs.getString("TrangThai"));
                danhSach.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return danhSach;
    }

    public HoaDon getHoaDonById(int hoaDonId) throws SQLException {
        String sql = "SELECT HoaDonID, SoHoaDon, TenKhachHang, TongTien, ThueTien, NgayLapHoaDon, NgayHanThanhToan, TrangThai FROM HoaDon H, KhachHang K WHERE H.KhachHangID = K.KhachHangID AND H.HoaDonID = ?";
        HoaDon hoaDon = null;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, hoaDonId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                hoaDon = new HoaDon();
                hoaDon.setHoaDonId(rs.getInt("HoaDonID"));
                hoaDon.setSoHoaDon(rs.getString("SoHoaDon"));

                KhachHang khachHang = new KhachHang();
                khachHang.setTen(rs.getString("TenKhachHang"));
                hoaDon.setKhachHang(khachHang);

                hoaDon.setTongTien(rs.getBigDecimal("TongTien"));
                hoaDon.setThueTien(rs.getBigDecimal("ThueTien"));
                hoaDon.setNgayLapHoaDon(rs.getDate("NgayLapHoaDon"));
                hoaDon.setNgayHanThanhToan(rs.getDate("NgayHanThanhToan"));
                hoaDon.setTrangThai(rs.getString("TrangThai"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return hoaDon;
    }

    public boolean themHoaDon(HoaDon hoaDon) throws SQLException {
        // Kiểm tra xem số hóa đơn đã tồn tại chưa
        if (isSoHoaDonExists(hoaDon.getSoHoaDon())) {
            System.out.println("Số hóa đơn đã tồn tại: " + hoaDon.getSoHoaDon());
            return false; // Trả về false nếu số hóa đơn đã tồn tại
        }

        String sql = "INSERT INTO HoaDon (SoHoaDon, KhachHangID, TongTien, ThueTien, NgayLapHoaDon, NgayHanThanhToan, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hoaDon.getSoHoaDon());
            pstmt.setInt(2, hoaDon.getKhachHang().getKhachHangId());
            pstmt.setBigDecimal(3, hoaDon.getTongTien());

            // Tính tiền thuế là 10% của tổng tiền
            BigDecimal thueTien = hoaDon.getTongTien().multiply(BigDecimal.valueOf(0.1));
            pstmt.setBigDecimal(4, thueTien);

            pstmt.setDate(5, new Date(hoaDon.getNgayLapHoaDon().getTime()));  // NgayLapHoaDon
            pstmt.setDate(6, new Date(hoaDon.getNgayHanThanhToan().getTime()));  // NgayHanThanhToan
            pstmt.setString(7, hoaDon.getTrangThai());

            return pstmt.executeUpdate() > 0; // Trả về true nếu chèn thành công
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Ném lại ngoại lệ để xử lý ở nơi khác
        }
    }

    public boolean isSoHoaDonExists(String soHoaDon) throws SQLException {
        String sql = "SELECT COUNT(*) FROM HoaDon WHERE SoHoaDon = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, soHoaDon);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu có ít nhất một bản ghi, trả về true
            }
        }
        return false; // Nếu không có bản ghi nào, trả về false
    }

    public boolean suaHoaDon(HoaDon hoaDon) throws SQLException {
        String sql = "UPDATE HoaDon SET SoHoaDon = ?, KhachHangID = ?, TongTien = ?, ThueTien = ?, NgayLapHoaDon = ?, NgayHanThanhToan = ?, TrangThai = ? WHERE HoaDonID = ?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, hoaDon.getSoHoaDon());
            pstmt.setInt(2, hoaDon.getKhachHang().getKhachHangId());
            pstmt.setBigDecimal(3, hoaDon.getTongTien());

            // Tính tiền thuế là 10% của tổng tiền
            BigDecimal thueTien = hoaDon.getTongTien().multiply(BigDecimal.valueOf(0.1));
            pstmt.setBigDecimal(4, thueTien);

            pstmt.setDate(5, new java.sql.Date(hoaDon.getNgayLapHoaDon().getTime())); // NgayLapHoaDon
            pstmt.setDate(6, new java.sql.Date(hoaDon.getNgayHanThanhToan().getTime())); // NgayHanThanhToan
            pstmt.setString(7, hoaDon.getTrangThai());
            pstmt.setInt(8, hoaDon.getHoaDonId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có ít nhất một bản ghi được cập nhật

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật hóa đơn: " + e.getMessage());
            throw e;
        }
    }

    public boolean xoaHoaDon(int id) throws SQLException {
        String sql = "DELETE FROM HoaDon WHERE HoaDonID=?";
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

    public int getKhachHangIdByName(String tenKhachHang) throws SQLException {
        String sql = "SELECT KhachHangID FROM KhachHang WHERE TenKhachHang = ?";
        int khachHangId = -1; // Giá trị mặc định nếu không tìm thấy
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tenKhachHang);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                khachHangId = rs.getInt("KhachHangID");
            }
        }
        return khachHangId;
    }

    public ResultSet hienThiHoaDonKhachHang() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM KhachHang";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public ResultSet hienThiHoaDonStatus() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT DISTINCT TrangThai FROM HoaDon WHERE TrangThai IN ('Đã thanh toán', 'chưa thanh toán');";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public List<HoaDon> timKiemHoaDon(String keyword) throws SQLException {
        String sql = "SELECT H.HoaDonID, H.SoHoaDon, K.TenKhachHang, H.TongTien, H.ThueTien, H.NgayLapHoaDon, H.NgayHanThanhToan, H.TrangThai "
                + "FROM HoaDon H JOIN KhachHang K ON H.KhachHangID = K.KhachHangID "
                + "WHERE H.SoHoaDon LIKE ? OR K.TenKhachHang LIKE ? OR "
                + "CAST(H.TongTien AS VARCHAR) LIKE ? OR "
                + "CAST(H.ThueTien AS VARCHAR) LIKE ? OR "
                + "CAST(H.NgayLapHoaDon AS VARCHAR) LIKE ? OR "
                + "CAST(H.NgayHanThanhToan AS VARCHAR) LIKE ? OR "
                + "H.TrangThai LIKE ?";
        List<HoaDon> danhSach = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            pstmt.setString(3, "%" + keyword + "%");
            pstmt.setString(4, "%" + keyword + "%");
            pstmt.setString(5, "%" + keyword + "%");
            pstmt.setString(6, "%" + keyword + "%");
            pstmt.setString(7, "%" + keyword + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setHoaDonId(rs.getInt("HoaDonID"));
                hoaDon.setSoHoaDon(rs.getString("SoHoaDon"));

                KhachHang khachHang = new KhachHang();
                khachHang.setTen(rs.getString("TenKhachHang"));
                hoaDon.setKhachHang(khachHang);

                hoaDon.setTongTien(rs.getBigDecimal("TongTien"));
                hoaDon.setThueTien(rs.getBigDecimal("ThueTien"));
                hoaDon.setNgayLapHoaDon(rs.getDate("NgayLapHoaDon"));
                hoaDon.setNgayHanThanhToan(rs.getDate("NgayHanThanhToan"));
                hoaDon.setTrangThai(rs.getString("TrangThai"));
                danhSach.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return danhSach;
    }

    public BigDecimal tinhTongTienHoaDon(int hoaDonId) throws SQLException {
        String sqlSelect = "SELECT SUM(SoLuong * DonGia) AS TongTien FROM ChiTietHoaDon WHERE HoaDonID = ?";
        BigDecimal tongTien = BigDecimal.ZERO;

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect)) {
            pstmtSelect.setInt(1, hoaDonId);
            ResultSet rs = pstmtSelect.executeQuery();

            if (rs.next()) {
                tongTien = rs.getBigDecimal("TongTien");
                // Kiểm tra nếu tongTien là null
                if (tongTien == null) {
                    tongTien = BigDecimal.ZERO;
                }
            }

            // Tính tiền thuế là 10% của tổng tiền
            BigDecimal thueTien = tongTien.multiply(BigDecimal.valueOf(0.1));

            // Cập nhật tổng tiền và tiền thuế vào bảng HoaDon
            String sqlUpdate = "UPDATE HoaDon SET TongTien = ?, ThueTien = ? WHERE HoaDonID = ?";
            try (PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate)) {
                pstmtUpdate.setBigDecimal(1, tongTien);
                pstmtUpdate.setBigDecimal(2, thueTien);
                pstmtUpdate.setInt(3, hoaDonId);
                pstmtUpdate.executeUpdate();
            }
        }
        return tongTien;
    }

    public int getHoaDonCountDaThanhToan() throws SQLException {
        String sql = "SELECT COUNT(*) FROM HoaDon WHERE TrangThai = 'Đã thanh toán'";
        int count = 0;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return count;
    }

    public int getHoaDonCountChuaThanhToan() throws SQLException {
        String sql = "SELECT COUNT(*) FROM HoaDon WHERE TrangThai = 'Chưa thanh toán'";
        int count = 0;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return count;
    }

    public int getTongTien() throws SQLException {
        String sql = "SELECT Sum(TongTien) FROM HoaDon WHERE TrangThai = 'Đã thanh toán'";
        int count = 0;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return count;
    }

    public int getHoaDonIdBySoHoaDon(String soHoaDon) throws SQLException {
        String sql = "SELECT HoaDonID FROM HoaDon WHERE SoHoaDon = ?";
        int hoaDonId = -1; // Giá trị mặc định nếu không tìm thấy

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, soHoaDon);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                hoaDonId = rs.getInt("HoaDonID"); // Lấy HoaDonID từ kết quả truy vấn
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Ném lại lỗi để xử lý ở nơi khác nếu cần
        }

        return hoaDonId; // Trả về HoaDonID hoặc -1 nếu không tìm thấy
    }
}
