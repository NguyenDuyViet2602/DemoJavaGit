/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Invoice;

import Model.EmailSender;
import Controller.ChiTietHoaDonController;
import Controller.HoaDonController;
import Controller.SanPhamController;
import Model.ChiTietHoaDon;
import Model.HoaDon;
import Model.KhachHang;
import Model.SanPham;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

/**
 *
 * @author Manh
 */
public class TaoHoaDon extends javax.swing.JFrame {

    private final HoaDonController iv = new HoaDonController();
    private boolean cothem = true;
    private final SanPhamController sanPhamController = new SanPhamController();
    private final DefaultTableModel tableModel = new DefaultTableModel();
    private final DefaultTableModel tableModel1 = new DefaultTableModel();
    private final ChiTietHoaDonController ivdt = new ChiTietHoaDonController();
    private int currentId = 1;
    private String orderId = "";
    private final EmailSender EmailSender = new EmailSender();

    /**
     * Creates new form TaoHoaDon1
     */
    public TaoHoaDon() throws SQLException {
        initComponents();
        String[] colsSanPham = {"Id", "Tên sản phẩm", "Giá", "Mô tả", "Số lượng"}; // Đặt tên các cột cho bảng
        tableModel.setColumnIdentifiers(colsSanPham); // Cập nhật tiêu đề cột cho tableModel
        tblSanPham.setModel(tableModel); // Kết nối JTable với tableModel
        hienThiDuLieuSanPham(); // Gọi phương thức hiển thị dữ liệu vào bảng
        hienThiDuLieuKhachHangCombo();
        capNhatDiaChiTheoKhachHang();
        cbbKhachHang.addActionListener(e -> capNhatDiaChiTheoKhachHang());
        String[] colsChiTietHoaDon = {"Id", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Tổng Tiền"}; // Đặt tiêu đề cột cho tableModel
        tableModel1.setColumnIdentifiers(colsChiTietHoaDon);
        tblChiTietHoaDon.setModel(tableModel1);
        btnLuu.addActionListener(evt -> {
            // Lưu hóa đơn vào cơ sở dữ liệu
            btnLuuActionPerformed(evt);
            // Tạo PDF
            createPDF();
        });
    }

    public void hienThiDuLieuSanPham() {
        // Lấy danh sách sản phẩm từ cơ sở dữ liệu
        List<SanPham> danhSachSanPham;
        try {
            danhSachSanPham = sanPhamController.getTatCaSanPham();

            // Xóa dữ liệu cũ trong tableModel trước khi thêm dữ liệu mới
            tableModel.setRowCount(0);
            // Duyệt qua từng sản phẩm và thêm vào tableModel
            for (SanPham sanPham : danhSachSanPham) {
                Object[] row = {
                    sanPham.getSanPhamId(),
                    sanPham.getTenSanPham(),
                    sanPham.getGiaSanPham(),
                    sanPham.getSoluongton(),
                    sanPham.getMoTaSanPham()
                };
                tableModel.addRow(row); // Thêm dòng dữ liệu vào tableModel
            }
        } catch (SQLException e) {
            // Xử lý lỗi nếu có
            JOptionPane.showMessageDialog(null, "Lỗi khi hiển thị dữ liệu sản phẩm: " + e.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Xử lý lỗi chung khác
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra: " + e.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    final void hienThiDuLieuKhachHangCombo() {
        ResultSet result = null;
        try {
            result = iv.hienThiHoaDonKhachHang();
            while (result.next()) {
                cbbKhachHang.addItem(result.getString("TenKhachHang"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void capNhatDiaChiTheoKhachHang() {
        try {
            // Lấy tên sản phẩm từ ComboBox
            String tenKhachHang = (String) cbbKhachHang.getSelectedItem();

            // Kiểm tra nếu tên sản phẩm không null
            if (tenKhachHang != null && !tenKhachHang.isEmpty()) {
                // Gọi controller để lấy giá bán
                String diaChi = ivdt.getDiaChiByTenKhachHang(tenKhachHang);

                // Hiển thị giá bán lên txtGiaBan
                txtDiaChi.setText(diaChi.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy giá bán sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietHoaDon = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Date = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtSoHoaDon = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbbKhachHang = new javax.swing.JComboBox<>();
        btnThemSanPham = new javax.swing.JButton();
        btnXoaSanPham = new javax.swing.JButton();
        btnSuaSanPham = new javax.swing.JButton();
        Date1 = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        btnLuu = new javax.swing.JButton();
        btnDong = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtTimKiemSanPham = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 422));
        jPanel1.setLayout(new java.awt.BorderLayout());

        tblChiTietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblChiTietHoaDon);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(596, 180));

        jLabel1.setText("Ngày tạo:");

        jLabel2.setText("Số hóa đơn:");

        jLabel5.setText("Tên sản phẩm:");

        jLabel7.setText("Số lượng:");

        jLabel6.setText("Giá bán:");

        jLabel4.setText("Địa chỉ:");

        jLabel3.setText("Khách hàng:");

        cbbKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbKhachHangActionPerformed(evt);
            }
        });

        btnThemSanPham.setBackground(new java.awt.Color(0, 255, 0));
        btnThemSanPham.setText("Thêm sản phẩm");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        btnXoaSanPham.setBackground(new java.awt.Color(255, 0, 0));
        btnXoaSanPham.setText("Xóa sản phẩm");
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });

        btnSuaSanPham.setBackground(new java.awt.Color(255, 255, 102));
        btnSuaSanPham.setText("Sửa sản phẩm");

        jLabel10.setText("Đến hạn:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(btnThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(33, 33, 33)
                                .addComponent(btnXoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Date1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(32, 32, 32)
                                .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(txtTenSanPham))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDiaChi))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(Date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSanPham)
                    .addComponent(btnXoaSanPham)
                    .addComponent(btnSuaSanPham))
                .addGap(12, 12, 12))
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jLabel8.setText("Tổng tiên:");

        btnLuu.setBackground(new java.awt.Color(102, 255, 255));
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnDong.setText("Đóng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(31, 31, 31)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(btnLuu)
                .addGap(18, 18, 18)
                .addComponent(btnDong)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu)
                    .addComponent(btnDong))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 422));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jLabel9.setText("Tìm kiếm:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbKhachHangActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        try {
            int row = this.tblSanPham.getSelectedRow();

            // Kiểm tra nếu không có dòng nào được chọn
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(this.tblSanPham.getModel().getValueAt(row, 0).toString());
            SanPham sanPham = sanPhamController.getSanPhamById(id);

            // Kiểm tra nếu không tìm thấy sản phẩm
            if (sanPham != null) {
                txtTenSanPham.setText(sanPham.getTenSanPham());
                txtGiaBan.setText(sanPham.getGiaSanPham().toString());

            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm với ID " + id, "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            // Xử lý lỗi SQL
            JOptionPane.showMessageDialog(null, "Lỗi khi tải thông tin sản phẩm: " + e.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Xử lý lỗi chung
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra: " + e.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        try {
            String tenSanPham = txtTenSanPham.getText();
            String giaBanStr = txtGiaBan.getText();
            String soLuongStr = txtSoLuong.getText();

            if (tenSanPham.isEmpty() || giaBanStr.isEmpty() || soLuongStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            BigDecimal giaBan = new BigDecimal(giaBanStr);
            int soLuong = Integer.parseInt(soLuongStr);
            BigDecimal tongTien = giaBan.multiply(BigDecimal.valueOf(soLuong));

            // Kiểm tra sản phẩm đã tồn tại trong bảng chưa
            boolean sanPhamTonTai = false;
            int sanPhamId = -1; // Biến lưu trữ id sản phẩm

            for (int i = 0; i < tableModel1.getRowCount(); i++) {
                String tenSanPhamTrongBang = (String) tableModel1.getValueAt(i, 1);
                if (tenSanPhamTrongBang.equals(tenSanPham)) {
                    // Nếu sản phẩm đã tồn tại, tăng số lượng và cập nhật tổng tiền
                    int soLuongCu = (int) tableModel1.getValueAt(i, 2);
                    int soLuongMoi = soLuongCu + soLuong;

                    tableModel1.setValueAt(soLuongMoi, i, 2);
                    BigDecimal tongTienMoi = giaBan.multiply(BigDecimal.valueOf(soLuongMoi));
                    tableModel1.setValueAt(tongTienMoi, i, 4);

                    sanPhamTonTai = true;
                    break;
                }
            }

            // Nếu sản phẩm chưa tồn tại, thêm dòng mới
            if (!sanPhamTonTai) {
                Object[] row = {currentId, tenSanPham, soLuong, giaBan, tongTien};
                tableModel1.addRow(row);
                currentId++; // Tăng ID sau mỗi lần thêm sản phẩm

                // Tìm SanPhamId dựa trên tên sản phẩm
                sanPhamId = sanPhamController.getSanPhamIdByTen(tenSanPham);
            }

            // Cập nhật tổng tiền hóa đơn
            capNhatTongTienHoaDon();

            // Giảm số lượng trong bảng sản phẩm
            int soLuongTon = sanPhamController.getSanPhamById(sanPhamId).getSoluongton();
            if (soLuongTon >= soLuong) {
                sanPhamController.capNhatSoLuongSanPham(sanPhamId, soLuongTon - soLuong);
            } else {
                JOptionPane.showMessageDialog(this, "Không đủ số lượng sản phẩm trong kho!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cập nhật lại bảng sản phẩm
            hienThiDuLieuSanPham(); // Tải lại dữ liệu sản phẩm

            // Xóa dữ liệu trong các ô nhập liệu
            txtTenSanPham.setText("");
            txtGiaBan.setText("");
            txtSoLuong.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá bán và số lượng phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed
        try {
            // Lấy dòng được chọn trong bảng chi tiết hóa đơn
            int selectedRow = tblChiTietHoaDon.getSelectedRow();

            // Kiểm tra nếu không có dòng nào được chọn
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Lấy thông tin sản phẩm cần xóa
            String tenSanPham = (String) tableModel1.getValueAt(selectedRow, 1);
            int soLuongXoa = (int) tableModel1.getValueAt(selectedRow, 2);

            // Xóa dòng được chọn khỏi tableModel1
            tableModel1.removeRow(selectedRow);

            // Cập nhật lại tổng tiền hóa đơn sau khi xóa sản phẩm
            capNhatTongTienHoaDon();

            // Cập nhật số lượng sản phẩm trong kho
            int sanPhamId = sanPhamController.getSanPhamIdByTen(tenSanPham);
            int soLuongTon = sanPhamController.getSanPhamById(sanPhamId).getSoluongton();

            // Tăng số lượng sản phẩm trong kho khi xóa sản phẩm
            sanPhamController.capNhatSoLuongSanPham(sanPhamId, soLuongTon + soLuongXoa);

            // Cập nhật lại bảng sản phẩm
            hienThiDuLieuSanPham(); // Tải lại dữ liệu sản phẩm

            JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xóa sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private boolean isValidTrangThai(String trangThai) {
        return trangThai != null && (trangThai.equals("Chưa thanh toán") || trangThai.equals("Đã thanh toán") || trangThai.equals("Đã hủy"));
    }
    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        try {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setSoHoaDon(txtSoHoaDon.getText());
            hoaDon.setKhachHang(new KhachHang());
            hoaDon.getKhachHang().setKhachHangId(iv.getKhachHangIdByName((String) cbbKhachHang.getSelectedItem()));
            hoaDon.setTongTien(new BigDecimal(txtTongTien.getText()));

            // Kiểm tra và gán giá trị cho ngày lập hóa đơn
            Date ngayLapHoaDon = Date.getDate(); // Giả sử bạn đang sử dụng JDateChooser
            if (ngayLapHoaDon != null) {
                hoaDon.setNgayLapHoaDon(ngayLapHoaDon);
            } else {
                JOptionPane.showMessageDialog(this, "Ngày lập hóa đơn không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra và gán giá trị cho ngày hạn thanh toán
            Date ngayHanThanhToan = Date1.getDate(); // Giả sử bạn đang sử dụng JDateChooser
            if (ngayHanThanhToan != null) {
                hoaDon.setNgayHanThanhToan(ngayHanThanhToan);
            } else {
                JOptionPane.showMessageDialog(this, "Ngày hạn thanh toán không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Gán trạng thái mặc định
            String trangThai = "Chưa thanh toán"; // Trạng thái mặc định
            hoaDon.setTrangThai(trangThai);

            // Lưu Hóa Đơn vào cơ sở dữ liệu
            if (iv.themHoaDon(hoaDon)) {
                // Lấy HoaDonID vừa lưu
                int hoaDonId = iv.getHoaDonIdBySoHoaDon(hoaDon.getSoHoaDon()); // Cần viết phương thức này trong HoaDonController

                // Kiểm tra nếu hoaDonId hợp lệ
                if (hoaDonId == -1) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn vừa lưu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Lưu chi tiết hóa đơn
                for (int i = 0; i < tableModel1.getRowCount(); i++) {
                    ChiTietHoaDon chiTiet = new ChiTietHoaDon();
                    chiTiet.setHoaDon(hoaDon); // Gán hóa đơn cho chi tiết
                    chiTiet.getHoaDon().setHoaDonId(hoaDonId); // Gán ID hóa đơn vừa lưu
                    chiTiet.setSanPham(new SanPham());
                    chiTiet.getSanPham().setSanPhamId(sanPhamController.getSanPhamIdByTen((String) tableModel1.getValueAt(i, 1)));
                    chiTiet.setSoLuong((Integer) tableModel1.getValueAt(i, 2));
                    chiTiet.setDonGia((BigDecimal) tableModel1.getValueAt(i, 3));

                    // Gọi phương thức để thêm chi tiết hóa đơn
                    ivdt.themChiTietHoaDon(chiTiet);
                }
                JOptionPane.showMessageDialog(this, "Lưu hóa đơn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                // Tạo PDF
                createPDF();

                // Gửi email với hóa đơn đính kèm
                String recipientEmail = "customer@example.com"; // Địa chỉ email người nhận
                String subject = "Hóa Đơn #" + hoaDon.getSoHoaDon();
                String body = "Xin chào " + cbbKhachHang.getSelectedItem() + ",\n\nHóa đơn của bạn đã được tạo và đính kèm trong email này.\n\nTrân trọng!";
                String attachmentPath = "HoaDon.pdf"; // Đường dẫn đến tệp PDF

//            EmailSender.sendEmail(recipientEmail, subject, body, attachmentPath);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi lưu hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void capNhatTongTienHoaDon() {
        BigDecimal tongTienHoaDon = BigDecimal.ZERO;

        // Duyệt qua từng dòng trong bảng chi tiết hóa đơn
        for (int i = 0; i < tableModel1.getRowCount(); i++) {
            BigDecimal tongTien = (BigDecimal) tableModel1.getValueAt(i, 4);
            tongTienHoaDon = tongTienHoaDon.add(tongTien);
        }

        // Hiển thị tổng tiền lên txtTongTien
        txtTongTien.setText(tongTienHoaDon.toString());
    }

    private void createPDF() {
        Document document = new Document();
        try {
            String filePath = "C:/Users/Manh/Documents/NetBeansProjects/BaiTapLon/src/HoaDon.pdf"; // Đường dẫn lưu file PDF
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Thêm tiêu đề
            document.add(new Paragraph("Hóa Đơn", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));

            // Thêm thông tin khách hàng
            document.add(new Paragraph("Khách hàng: " + cbbKhachHang.getSelectedItem()));
            document.add(new Paragraph("Địa chỉ: " + txtDiaChi.getText()));
            document.add(new Paragraph("Ngày lập: " + new SimpleDateFormat("dd/MM/yyyy").format(Date.getDate())));

            // Tạo bảng cho chi tiết hóa đơn
            PdfPTable table = new PdfPTable(5);
            table.addCell("Tên sản phẩm");
            table.addCell("Số lượng");
            table.addCell("Đơn giá");
            table.addCell("Tổng tiền");
            table.addCell("Ghi chú");

            // Thêm dữ liệu vào bảng
            for (int i = 0; i < tableModel1.getRowCount(); i++) {
                table.addCell((String) tableModel1.getValueAt(i, 1)); // Tên sản phẩm
                table.addCell(String.valueOf(tableModel1.getValueAt(i, 2))); // Số lượng
                table.addCell(String.valueOf(tableModel1.getValueAt(i, 3))); // Đơn giá
                table.addCell(String.valueOf(tableModel1.getValueAt(i, 4))); // Tổng tiền
                table.addCell(""); // Ghi chú (nếu có)
            }

            document.add(table);

            // Thêm tổng tiền
            document.add(new Paragraph("Tổng tiền: " + txtTongTien.getText()));

            JOptionPane.showMessageDialog(this, "Hóa đơn đã được lưu thành công tại: " + filePath, "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tạo PDF: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            document.close();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TaoHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaoHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaoHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaoHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TaoHoaDon().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TaoHoaDon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Date;
    private com.toedter.calendar.JDateChooser Date1;
    private javax.swing.JButton btnDong;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSuaSanPham;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JComboBox<String> cbbKhachHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblChiTietHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtSoHoaDon;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTimKiemSanPham;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
