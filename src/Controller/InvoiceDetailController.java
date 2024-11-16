/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Invoice;
import Model.InvoiceDetail;
import Model.Product;
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
public class InvoiceDetailController {

    public List<InvoiceDetail> getInvoiceDetailById() throws SQLException {
        String sql = "SELECT InvoiceDetailID,I.InvoiceNumber, P.ProductName, Quantity, P.UnitPrice, (ID.Quantity * ID.UnitPrice) AS TotalAmount  FROM InvoiceDetail ID, Product P, Invoice I WHERE I.InvoiceID = ID.InvoiceID AND P.ProductID = ID.ProductID";
        List<InvoiceDetail> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                InvoiceDetail invoicedetail = new InvoiceDetail();
                invoicedetail.setInvoiceDetailID(rs.getInt("InvoiceDetailID"));

                Invoice invoice = new Invoice();
                invoice.setIvNumber(rs.getString("InvoiceNumber"));
                invoicedetail.setInvoice(invoice);

                Product product = new Product();
                product.setPdName(rs.getString("ProductName"));
                invoicedetail.setProduct(product);

                invoicedetail.setQuantity(rs.getInt("Quantity"));
                invoicedetail.setUnitPrice(rs.getBigDecimal("UnitPrice"));
                BigDecimal quantity = BigDecimal.valueOf(invoicedetail.getQuantity());
                BigDecimal unitPrice = invoicedetail.getUnitPrice();
                BigDecimal totalAmount = quantity.multiply(unitPrice);
                invoicedetail.setTotalAmount(totalAmount);
                list.add(invoicedetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    public List<InvoiceDetail> getInvoiceDetailById(int InvoiceDetailID) throws SQLException {
        String sql = "SELECT InvoiceDetailID,I.InvoiceNumber, P.ProductName, Quantity, P.UnitPrice, (ID.Quantity * ID.UnitPrice) AS TotalAmount  FROM InvoiceDetail ID, Product P, Invoice I WHERE I.InvoiceID = ID.InvoiceID AND P.ProductID = ID.ProductID";
        List<InvoiceDetail> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, InvoiceDetailID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                InvoiceDetail invoicedetail = new InvoiceDetail();
                invoicedetail.setInvoiceDetailID(rs.getInt("InvoiceDetailID"));

                Invoice invoice = new Invoice();
                invoice.setIvNumber(rs.getString("InvoiceNumber"));
                invoicedetail.setInvoice(invoice);

                Product product = new Product();
                product.setPdName(rs.getString("ProductName"));
                invoicedetail.setProduct(product);

                invoicedetail.setQuantity(rs.getInt("Quantity"));
                
                invoicedetail.setUnitPrice(rs.getBigDecimal("UnitPrice"));
                
                BigDecimal quantity = BigDecimal.valueOf(invoicedetail.getQuantity());
                BigDecimal unitPrice = invoicedetail.getUnitPrice();
                BigDecimal totalAmount = quantity.multiply(unitPrice);
                invoicedetail.setTotalAmount(totalAmount);
                list.add(invoicedetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    public boolean addInvoiceDetail(InvoiceDetail invoicedetail) throws SQLException {
        String sql = "INSERT INTO InvoiceDetail (InvoiceID, ProductID, Quantity, UnitPrice, TotalAmount) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, invoicedetail.getInvoice().getIvId());
            pstmt.setInt(2, invoicedetail.getProduct().getPdId());
            pstmt.setInt(3, invoicedetail.getQuantity());
            pstmt.setBigDecimal(4, invoicedetail.getUnitPrice());
            pstmt.setBigDecimal(5, invoicedetail.getTotalAmount());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public ResultSet showInvoiceDetail() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM Product";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }
    public int getProductIdByName(String productName) throws SQLException {
        String sql = "SELECT ProductID FROM Product WHERE ProductName = ?";
        int productId = -1; // Giá trị mặc định nếu không tìm thấy
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                productId = rs.getInt("CustomerID");
            }
        }
        return productId;
    }
}
