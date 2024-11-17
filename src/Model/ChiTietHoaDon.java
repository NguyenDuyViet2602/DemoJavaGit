/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.SanPham;
import Model.HoaDon;
import java.math.BigDecimal;

/**
 *
 * @author Manh
 */
public class ChiTietHoaDon {

    private int chiTietHoaDonID;      // ID của chi tiết hóa đơn
    private HoaDon hoaDon;            // Hóa đơn liên kết
    private SanPham sanPham;          // Sản phẩm liên kết
    private int soLuong;              // Số lượng sản phẩm
    private BigDecimal donGia;        // Đơn giá sản phẩm
    private BigDecimal thanhTien;     // Tổng tiền

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int chiTietHoaDonID, HoaDon hoaDon, SanPham sanPham, int soLuong, BigDecimal donGia, BigDecimal thanhTien) {
        this.chiTietHoaDonID = chiTietHoaDonID;
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getChiTietHoaDonID() {
        return chiTietHoaDonID;
    }

    public void setChiTietHoaDonID(int chiTietHoaDonID) {
        this.chiTietHoaDonID = chiTietHoaDonID;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }
}
