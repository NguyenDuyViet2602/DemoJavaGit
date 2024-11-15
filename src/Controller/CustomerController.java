/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.*;
import db.DatabaseConnection;
import Model.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manh
 */
public class CustomerController {

    public List<Customer> getCustomerById() throws SQLException {
        String sql = "SELECT * FROM Customer";
        List<Customer> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("CustomerID"));
                customer.setName(rs.getString("CustomerName"));
                customer.setEmail(rs.getString("Email"));
                customer.setPhone(rs.getString("Phone"));
                customer.setAddress(rs.getString("Address"));
                customer.setTaxCode(rs.getString("TaxCode"));
                list.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    public Customer getCustomerById(int customerId) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE CustomerID = ?";
        Customer customer = null;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("CustomerID"));
                customer.setName(rs.getString("CustomerName"));
                customer.setEmail(rs.getString("Email"));
                customer.setPhone(rs.getString("Phone"));
                customer.setAddress(rs.getString("Address"));
                customer.setTaxCode(rs.getString("TaxCode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return customer;
    }

    public boolean addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer (CustomerName, Email, Phone, Address, TaxCode) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPhone());
            pstmt.setString(4, customer.getAddress());
            pstmt.setString(5, customer.getTaxCode());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;

        }
    }

    public boolean EditCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE Customer SET CustomerName = ?, Email = ?, Phone = ?, Address = ?, TaxCode = ? WHERE CustomerID = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPhone());
            pstmt.setString(4, customer.getAddress());
            pstmt.setString(5, customer.getTaxCode());
            pstmt.setInt(6, customer.getId()); // Đảm bảo bạn đã set ID của customer

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có ít nhất một bản ghi được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean DeleteData(int id) throws SQLException {
        String sql = "DELETE FROM Customer WHERE CustomerID=?";
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

    public List<Customer> searchCustomers(String keyword) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE CustomerName LIKE ? OR Email LIKE ? OR Phone LIKE ? OR Address LIKE ? OR TaxCode LIKE ?";
        List<Customer> list = new ArrayList<>();

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
                Customer customer = new Customer();
                customer.setId(rs.getInt("CustomerID"));
                customer.setName(rs.getString("CustomerName"));
                customer.setEmail(rs.getString("Email"));
                customer.setPhone(rs.getString("Phone"));
                customer.setAddress(rs.getString("Address"));
                customer.setTaxCode(rs.getString("TaxCode"));
                list.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }
}
