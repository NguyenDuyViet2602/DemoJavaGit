/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import javax.swing.table.DefaultTableModel;
import Controller.ProductController;
import java.sql.SQLException;
import java.util.List;
import Model.Product;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

/**
 *
 * @author Manh
 */
public class frmProduct extends javax.swing.JPanel {

    private boolean cothem = true;
    private final ProductController pd = new ProductController();
    private final DefaultTableModel tableModel = new DefaultTableModel();

    /**
     * Creates new form Customer
     */
    public frmProduct() throws SQLException {
        initComponents();
        String[] colsName = {"Id", "Name Product", "Price", "Description"}; // Đặt tiêu đề cột cho tableModel
        tableModel.setColumnIdentifiers(colsName);
        tblProduct.setModel(tableModel); // Kết nối jtable với tableModel
        ShowData();
        setNull(); // Gọi hàm xóa trắng các JTextField
        setKhoa(true); // Gọi hàm khóa các TextField
        setButton(true); // Gọi vô hiệu 2 button Lưu, K.Lưu. Mở khóa 4 button còn lại

    }

    public void ShowData() throws SQLException {
        List<Product> lst = pd.getProductById(); // Lấy danh sách loại sản phẩm từ cơ sở dữ liệu
        try {
            // Xóa dữ liệu cũ trước khi thêm mới
            tableModel.setRowCount(0);
            for (Product item : lst) { // Duyệt qua từng dòng của lst
                Object[] rows = {item.getPdId(), item.getPdName(), item.getPdPrice(), item.getPdDescription()};
                // Giả sử các phương thức getId, getTenLoai, getMoTa đã được định nghĩa trong lớp LoaiSP
                tableModel.addRow(rows); // Đưa dòng dữ liệu vào tableModel
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNameProduct = new javax.swing.JTextField();
        txtPriceProduct = new javax.swing.JTextField();
        btnEditCustomer = new javax.swing.JButton();
        btnAddCustomer = new javax.swing.JButton();
        btnDeleteCustomer = new javax.swing.JButton();
        btnSaveCustomer = new javax.swing.JButton();
        btnResetCustomer = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtADescriptionProduct = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Price:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Name Product:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Description:");

        btnEditCustomer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditCustomer.setText("Edit");
        btnEditCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCustomerActionPerformed(evt);
            }
        });

        btnAddCustomer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/8666718_plus_circle_icon.png"))); // NOI18N
        btnAddCustomer.setText("Add");
        btnAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCustomerActionPerformed(evt);
            }
        });

        btnDeleteCustomer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDeleteCustomer.setText("Delete");
        btnDeleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCustomerActionPerformed(evt);
            }
        });

        btnSaveCustomer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSaveCustomer.setText("Save");
        btnSaveCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveCustomerActionPerformed(evt);
            }
        });

        btnResetCustomer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnResetCustomer.setText("Reset");
        btnResetCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetCustomerActionPerformed(evt);
            }
        });

        txtADescriptionProduct.setColumns(20);
        txtADescriptionProduct.setRows(5);
        jScrollPane2.setViewportView(txtADescriptionProduct);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAddCustomer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditCustomer)
                        .addGap(18, 18, 18)
                        .addComponent(btnSaveCustomer)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteCustomer)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPriceProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(btnResetCustomer)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNameProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPriceProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditCustomer)
                    .addComponent(btnAddCustomer)
                    .addComponent(btnSaveCustomer)
                    .addComponent(btnDeleteCustomer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetCustomer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Name Pruct", "Price", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduct);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Product");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Search:");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCustomerActionPerformed
        setNull();//Xoa trang TextField
        setKhoa(false);//Mo khoa TextField
        setButton(false);//Goi ham khoa cac Button
        cothem = true;//Gan cothem = true de ghi nhan trang thai them moi
    }//GEN-LAST:event_btnAddCustomerActionPerformed

    private void btnSaveCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveCustomerActionPerformed
        String namepd = txtNameProduct.getText();
        BigDecimal pricepd = new BigDecimal(txtPriceProduct.getText());
        String descriptionpd = txtADescriptionProduct.getText();
        // Kiểm tra thông tin nhập vào
        if (namepd.length() == 0 || descriptionpd.length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Kiểm tra độ dài
        try {
            // Tạo đối tượng Customer
            Product obj = new Product();
            obj.setPdName(namepd);
            obj.setPdPrice(pricepd);
            obj.setPdDescription(descriptionpd);
            if (!cothem) { // Nếu không phải thêm mới
                int row = this.tblProduct.getSelectedRow(); // Lấy hàng đã chọn
                int id = Integer.parseInt(this.tblProduct.getModel().getValueAt(row, 0).toString()); // Lấy ID từ bảng
                obj.setPdId(id); // Thiết lập ID cho đối tượng
            }
            // Thực hiện thêm hoặc sửa dữ liệu
            if (cothem) { // Thêm mới
                pd.addProduct(obj);
                JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else { // Sửa
                pd.EditProduct(obj);
                JOptionPane.showMessageDialog(null, "Sửa loại sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

            // Cập nhật lại dữ liệu hiển thị
            ClearData(); // Xóa dữ liệu trong tableModel
            ShowData(); // Đưa lại dữ liệu vào Table Model
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại: " + ex.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
        }

        // Khóa các trường và bật lại các nút
        setKhoa(true);
        setButton(true);
    }//GEN-LAST:event_btnSaveCustomerActionPerformed

    private void tblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseClicked
        try {
            int row = this.tblProduct.getSelectedRow();
            int id = Integer.parseInt(this.tblProduct.getModel().getValueAt(row, 0).toString());
            Product obj = pd.getProductById(id);

            if (obj != null) {
                txtNameProduct.setText(obj.getPdName());
                txtPriceProduct.setText(obj.getPdPrice().toString());
                txtADescriptionProduct.setText(obj.getPdDescription());
                setKhoa(false); // Mở khóa các TextField
                setButton(true); // Khóa các nút Thêm, Sửa, Xóa
                btnSaveCustomer.setEnabled(false);
                btnResetCustomer.setEnabled(true); // Kích hoạt nút Xóa
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblProductMouseClicked

    private void btnEditCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCustomerActionPerformed
        String name = txtNameProduct.getText();
        if (name.length() == 0) //Chua chon Ma loai
            JOptionPane.showMessageDialog(null, "Vui long chon loi can sua",
                    "Thong bao", 1);
        else {
            setKhoa(false);//Mo khoa cac TextField
            setButton(false); //Khoa cac Button
            cothem = false; //Gan cothem=false de ghi nhan trang thai la sua
        }
    }//GEN-LAST:event_btnEditCustomerActionPerformed

    private void btnDeleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCustomerActionPerformed
        int row = this.tblProduct.getSelectedRow();
        int id = Integer.parseInt(this.tblProduct.getModel().getValueAt(row, 0).toString());
        try {
            if (id == 0) {
                JOptionPane.showMessageDialog(null, "Chon 1 loai SP de xoa", "Thong bao", 1);
            } else {
                if (JOptionPane.showConfirmDialog(null, "Ban muon xoa loai " + id + "nay hay khong ? ", "Thong bao", 2) == 0) {
                    pd.DeleteData(id);//goi ham xoa du lieu theo ma loai
                    ClearData();//Xoa du lieu trong tableModel
                    ShowData();//Do du lieu vao table Model
                    setNull();//Xoa trang Textfield
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Xóa thất bại", "Thong bao", 1);
        }
    }//GEN-LAST:event_btnDeleteCustomerActionPerformed

    private void btnResetCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetCustomerActionPerformed
        setNull();
        setKhoa(true);
        setButton(true);
    }//GEN-LAST:event_btnResetCustomerActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        String keyword = txtSearch.getText();
        try {
            List<Product> lst;
            if (keyword.isEmpty()) {
                // Nếu ô tìm kiếm trống, hiển thị tất cả khách hàng
                lst = pd.getProductById();
            } else {
                // Gọi phương thức tìm kiếm
                lst = pd.searchCustomers(keyword);
            }

            // Cập nhật lại dữ liệu trong bảng
            ClearData(); // Xóa dữ liệu cũ trong tableModel
            for (Product item : lst) {
                Object[] rows = {item.getPdId(), item.getPdName(), item.getPdPrice(), item.getPdDescription()};
                tableModel.addRow(rows); // Thêm dòng dữ liệu mới vào tableModel
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtSearchKeyReleased
    public void ClearData() throws SQLException {
//Lay chi so dong cuoi cung
        int n = tableModel.getRowCount() - 1;
        for (int i = n; i >= 0; i--) {
            tableModel.removeRow(i);//Remove tung dong
        }
    }

    private void setNull() {
        this.txtNameProduct.setText(null);
        this.txtPriceProduct.setText(null);
        this.txtADescriptionProduct.setText(null);
        
        this.txtNameProduct.requestFocus();
    }

    private void setKhoa(boolean a) {
        this.txtNameProduct.setEnabled(!a);
        this.txtPriceProduct.setEnabled(!a);
        this.txtADescriptionProduct.setEnabled(!a);
        
    }

    private void setButton(boolean a) {
        this.btnAddCustomer.setEnabled(a);
        this.btnDeleteCustomer.setEnabled(a);
        this.btnEditCustomer.setEnabled(a);
        this.btnSaveCustomer.setEnabled(!a);
        this.btnResetCustomer.setEnabled(!a);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCustomer;
    private javax.swing.JButton btnDeleteCustomer;
    private javax.swing.JButton btnEditCustomer;
    private javax.swing.JButton btnResetCustomer;
    private javax.swing.JButton btnSaveCustomer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTextArea txtADescriptionProduct;
    private javax.swing.JTextField txtNameProduct;
    private javax.swing.JTextField txtPriceProduct;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}