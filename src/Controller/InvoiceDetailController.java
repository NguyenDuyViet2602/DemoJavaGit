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
}
