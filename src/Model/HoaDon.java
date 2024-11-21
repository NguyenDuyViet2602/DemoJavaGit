/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Manh
 */
public class HoaDon {

    private int hoaDonId;           // ID của hóa đơn
    private String soHoaDon;        // Số hóa đơn
    private KhachHang khachHang;        // ID của khách hàng
    private BigDecimal tongTien;    // Tổng tiền của hóa đơn
    private BigDecimal thueTien;    // Tiền thuế của hóa đơn
    private Date ngayLapHoaDon;     // Ngày lập hóa đơn
    private Date ngayHanThanhToan;  // Ngày hạn thanh toán
    private String trangThai;       // Trạng thái của hóa đơn
    

    public HoaDon() {
    }

    public HoaDon(int hoaDonId, String soHoaDon, KhachHang khachHang, BigDecimal tongTien, BigDecimal thueTien, Date ngayLapHoaDon, Date ngayHanThanhToan, String trangThai) {
        this.hoaDonId = hoaDonId;
        this.soHoaDon = soHoaDon;
        this.khachHang = khachHang;
        this.tongTien = tongTien;
        this.thueTien = thueTien;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.ngayHanThanhToan = ngayHanThanhToan;
        this.trangThai = trangThai;
    }

    public int getHoaDonId() {
        return hoaDonId;
    }

    public void setHoaDonId(int hoaDonId) {
        this.hoaDonId = hoaDonId;
    }

    public String getSoHoaDon() {
        return soHoaDon;
    }

    public void setSoHoaDon(String soHoaDon) {
        this.soHoaDon = soHoaDon;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public BigDecimal getTongTien() {
        return tongTien != null ? tongTien : BigDecimal.ZERO;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public BigDecimal getThueTien() {
        return thueTien;
    }

    public void setThueTien(BigDecimal thueTien) {
        this.thueTien = thueTien;
    }

    public Date getNgayLapHoaDon() {
        return ngayLapHoaDon;
    }

    public void setNgayLapHoaDon(Date ngayLapHoaDon) {
        this.ngayLapHoaDon = ngayLapHoaDon;
    }

    public Date getNgayHanThanhToan() {
        return ngayHanThanhToan;
    }

    public void setNgayHanThanhToan(Date ngayHanThanhToan) {
        this.ngayHanThanhToan = ngayHanThanhToan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
