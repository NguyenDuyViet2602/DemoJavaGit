/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.*;
import DAO.CustomerDAO;
import Model.Customer;
import View.frmCustomer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manh
 */
public class CustomerController {

    private final CustomerDAO cus = new CustomerDAO();
    private final DefaultTableModel tableModel = new DefaultTableModel();
    private frmCustomer view;    // View chứa giao diện người dùng
    private CustomerDAO model;    // DAO để thao tác với cơ sở dữ liệu
    // Constructor: khởi tạo View và Model

    public CustomerController(frmCustomer view, CustomerDAO model) {
        this.view = view;
        this.model = model;

        // Lắng nghe sự kiện nút "Thêm khách hàng"
        this.view.getBtnAddCustomer().addActionListener(e -> addCustomer());
    }
    
    private void addCustomer() {
        // Lấy thông tin khách hàng từ View
        String name = view.getName();
        String email = view.getEmail();
        String phone = view.getPhone();
        String address = view.getAddress();
        String taxCode = view.getTaxCode();

        // Tạo đối tượng Customer từ thông tin lấy được
        Customer customer = new Customer(name, email, phone, address, taxCode);

        // Gọi phương thức thêm khách hàng trong CustomerDAO
        try {
            model.addCustomer(customer);
            JOptionPane.showMessageDialog(view, "Khách hàng đã được thêm thành công!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm khách hàng!");
        }
    }
}
