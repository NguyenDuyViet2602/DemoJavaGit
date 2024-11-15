/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Customer;
import Model.Invoice;
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
public class InvoiceController {

    public List<Invoice> getInvoiceById() throws SQLException {
        String sql = "SELECT InvoiceID, InvoiceNumber, CustomerName, TotalAmount, TaxAmount, InvoiceDate, DueDate, Status FROM Invoice I, Customer C WHERE I.CustomerID = C.CustomerID";
        List<Invoice> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setIvId(rs.getInt("InvoiceID"));
                invoice.setIvNumber(rs.getString("InvoiceNumber"));
                invoice.setCusName(rs.getString("CustomerName"));
                invoice.setTotalAmount(rs.getBigDecimal("TotalAmount"));
                invoice.setTaxAmount(rs.getBigDecimal("TaxAmount"));
                invoice.setIvDate(rs.getDate("InvoiceDate"));
                invoice.setDueDate(rs.getDate("DueDate"));
                invoice.setStatus(rs.getString("Status"));
                list.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    public Invoice getInvoiceById(int invoiceId) throws SQLException {
        String sql = "SELECT InvoiceID, InvoiceNumber, CustomerName, TotalAmount, TaxAmount, InvoiceDate, DueDate, Status FROM Invoice I, Customer C WHERE I.CustomerID = C.CustomerID";
        Invoice invoice = null;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, invoiceId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                invoice = new Invoice();
                invoice.setIvId(rs.getInt("InvoiceID"));
                invoice.setIvNumber(rs.getString("InvoiceNumber"));
                invoice.setCusName(rs.getString("CustomerName"));
                invoice.setTotalAmount(rs.getBigDecimal("TotalAmount"));
                invoice.setTaxAmount(rs.getBigDecimal("TaxAmount"));
                invoice.setIvDate(rs.getDate("InvoiceDate"));
                invoice.setDueDate(rs.getDate("DueDate"));
                invoice.setStatus(rs.getString("Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return invoice;
    }

    public boolean addInvoice(Invoice invoice) throws SQLException {
        String sql = "INSERT INTO Invoice (InvoiceNumber, CustomerID, TotalAmount, TaxAmount, InvoiceDate, DueDate, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, invoice.getIvNumber());
            pstmt.setInt(2, invoice.getCusId());
            pstmt.setBigDecimal(3, invoice.getTotalAmount());
            pstmt.setBigDecimal(4, invoice.getTaxAmount());

            // Chuyển đổi từ java.util.Date sang java.sql.Date
            pstmt.setDate(5, new Date(invoice.getIvDate().getTime()));  // InvoiceDate
            pstmt.setDate(6, new Date(invoice.getDueDate().getTime()));  // DueDate

            pstmt.setString(7, invoice.getStatus());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean EditInvoice(Invoice invoice) throws SQLException {
        String sql = "UPDATE Invoice SET InvoiceNumber = ?, CustomerID = ?, TotalAmount = ?, TaxAmount = ?, InvoiceDate = ?, DueDate = ?, Status = ? WHERE InvoiceID = ?";

        // Sử dụng try-with-resources để tự động đóng PreparedStatement và Connection
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, invoice.getIvNumber());
            pstmt.setInt(2, invoice.getCusId());
            pstmt.setBigDecimal(3, invoice.getTotalAmount());
            pstmt.setBigDecimal(4, invoice.getTaxAmount());
            // Chuyển đổi từ java.util.Date sang java.sql.Date
            pstmt.setDate(5, new java.sql.Date(invoice.getIvDate().getTime())); // InvoiceDate
            pstmt.setDate(6, new java.sql.Date(invoice.getDueDate().getTime())); // DueDate
            pstmt.setString(7, invoice.getStatus());
            pstmt.setInt(8, invoice.getIvId());

            // Thực hiện cập nhật
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có ít nhất một bản ghi được cập nhật

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật hóa đơn: " + e.getMessage());
            throw e;
        }
    }

    public boolean DeleteData(int id) throws SQLException {
        String sql = "DELETE FROM Invoice WHERE InvoiceID=?";
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

    public int getCustomerIdByName(String customerName) throws SQLException {
        String sql = "SELECT CustomerID FROM Customer WHERE CustomerName = ?";
        int customerId = -1; // Giá trị mặc định nếu không tìm thấy
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                customerId = rs.getInt("CustomerID");
            }
        }
        return customerId;
    }

    public ResultSet showInvoice() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM Customer";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

//    public ResultSet getAllInvoice() throws SQLException {
//        Connection conn = DatabaseConnection.getConnection();
//        String sql = "SELECT InvoiceID, InvoiceNumber, CustomerName, TotalAmount, TaxAmount, InvoiceDate, DueDate, Status FROM Invoice I, Customer C WHERE I.CustomerID = C.CustomerID";
//        PreparedStatement preparedStatement = conn.prepareStatement(sql);
//        return preparedStatement.executeQuery();
//    }
    public List<Invoice> searchInvoice(String keyword) throws SQLException {
        String sql = "SELECT I.InvoiceID, I.InvoiceNumber, C.CustomerName, I.TotalAmount, I.TaxAmount, I.InvoiceDate, I.DueDate, I.Status "
                + "FROM Invoice I JOIN Customer C ON I.CustomerID = C.CustomerID "
                + "WHERE I.InvoiceNumber LIKE ? OR C.CustomerName LIKE ? OR "
                + "CAST(I.TotalAmount AS VARCHAR) LIKE ? OR "
                + "CAST(I.TaxAmount AS VARCHAR) LIKE ? OR "
                + "CAST(I.InvoiceDate AS VARCHAR) LIKE ? OR "
                + "CAST(I.DueDate AS VARCHAR) LIKE ? OR "
                + "I.Status LIKE ?";
        List<Invoice> list = new ArrayList<>();

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
                Invoice invoice = new Invoice();
                invoice.setIvId(rs.getInt("InvoiceID"));
                invoice.setIvNumber(rs.getString("InvoiceNumber"));
                invoice.setCusName(rs.getString("CustomerName"));
                invoice.setTotalAmount(rs.getBigDecimal("TotalAmount"));
                invoice.setTaxAmount(rs.getBigDecimal("TaxAmount"));
                invoice.setIvDate(rs.getDate("InvoiceDate"));
                invoice.setDueDate(rs.getDate("DueDate"));
                invoice.setStatus(rs.getString("Status"));
                list.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }
}
