/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import db.DatabaseConnection;
import Model.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manh
 */
public class CustomerDAO {
    public List<Customer> getCustomerById() throws SQLException {
        String sql = "SELECT * FROM Customer";
        List<Customer> list = new ArrayList<>();
        try (
            Connection conn = DatabaseConnection.getConnection(); 
            PreparedStatement pstmt = conn.prepareStatement(sql); 
            ResultSet rs = pstmt.executeQuery()) {

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
    public void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer (CustomerName, Email, Phone, Address, TaxCode) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPhone());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getTaxCode());
            stmt.executeUpdate();
        }
    }

    public Customer getCustomerById(int customerId) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE CustomerID = ?";
        Customer customer = null;

        try (
            Connection conn = DatabaseConnection.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {

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
        }
        return customer;
    }
}
