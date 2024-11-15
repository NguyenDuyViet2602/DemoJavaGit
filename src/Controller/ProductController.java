/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Product;
import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manh
 */
public class ProductController {

    public List<Product> getProductById() throws SQLException {
        String sql = "SELECT * FROM Product";
        List<Product> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setPdId(rs.getInt("ProductID"));
                product.setPdName(rs.getString("ProductName"));
                product.setPdPrice(rs.getBigDecimal("UnitPrice"));
                product.setPdDescription(rs.getString("Description"));
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    public Product getProductById(int productId) throws SQLException {
        String sql = "SELECT * FROM Product WHERE ProductID = ?";
        Product product = null;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setPdId(rs.getInt("ProductID"));
                product.setPdName(rs.getString("ProductName"));
                product.setPdPrice(rs.getBigDecimal("UnitPrice"));
                product.setPdDescription(rs.getString("Description"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return product;
    }

    public boolean addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Product (ProductName, UnitPrice, Description) VALUES (?, ?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, product.getPdName());
            pstmt.setBigDecimal(2, product.getPdPrice());
            pstmt.setString(3, product.getPdDescription());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;

        }
    }

    public boolean EditProduct(Product product) throws SQLException {
        String sql = "UPDATE Product SET ProductName = ?, UnitPrice = ?, Description = ? WHERE ProductID = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, product.getPdName());
            pstmt.setBigDecimal(2, product.getPdPrice());
            pstmt.setString(3, product.getPdDescription());
            pstmt.setInt(4, product.getPdId()); // Đảm bảo bạn đã set ID của customer

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có ít nhất một bản ghi được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean DeleteData(int id) throws SQLException {
        String sql = "DELETE FROM Product WHERE ProductID=?";
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

    public List<Product> searchCustomers(String keyword) throws SQLException {
        String sql = "SELECT ProductID, ProductName, UnitPrice, Description FROM Product WHERE ProductName LIKE ? OR UnitPrice LIKE ? OR Description LIKE ?";
        List<Product> list = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Sử dụng wildcard '%' để tìm kiếm
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            pstmt.setString(3, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setPdId(rs.getInt("ProductID"));
                product.setPdName(rs.getString("ProductName"));
                product.setPdPrice(rs.getBigDecimal("UnitPrice"));
                product.setPdDescription(rs.getString("Description"));
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }
}
