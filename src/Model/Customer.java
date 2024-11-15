/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Manh
 */
public class Customer {
    private int CusId;          // ID của khách hàng
    private String name;      // Tên khách hàng
    private String email;     // Email của khách hàng
    private String phone;     // Số điện thoại
    private String address;   // Địa chỉ
    private String taxCode;   // Mã số thuế

    public Customer() {
    }

    public Customer(int CusId, String name, String email, String phone, String address, String taxCode) {
        this.CusId = CusId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.taxCode = taxCode;
    }

    public int getId() {
        return CusId;
    }

    public void setId(int CusId) {
        this.CusId = CusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }
    
}
