/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Manh
 */
public class Invoice {

    private int IvId;
    private String IvNumber;
    private int CusId;
    private BigDecimal TotalAmount;
    private BigDecimal TaxAmount;
    private Date IvDate;
    private Date DueDate;
    private String Status;
    private String CusName;

    public Invoice() {
    }

    public Invoice(int IvId, String IvNumber, int CusId, BigDecimal TotalAmount, BigDecimal TaxAmount, Date IvDate, Date DueDate, String Status) {
        this.IvId = IvId;
        this.IvNumber = IvNumber;
        this.CusId = CusId;
        this.TotalAmount = TotalAmount;
        this.TaxAmount = TaxAmount;
        this.IvDate = IvDate;
        this.DueDate = DueDate;
        this.Status = Status;
    }

    public int getIvId() {
        return IvId;
    }

    public void setIvId(int IvId) {
        this.IvId = IvId;
    }

    public String getIvNumber() {
        return IvNumber;
    }

    public void setIvNumber(String IvNumber) {
        this.IvNumber = IvNumber;
    }

    public int getCusId() {
        return CusId;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String CusName) {
        this.CusName = CusName;
    }

    public void setCusId(int CusId) {
        this.CusId = CusId;
    }

    public BigDecimal getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(BigDecimal TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public BigDecimal getTaxAmount() {
        return TaxAmount;
    }

    public void setTaxAmount(BigDecimal TaxAmount) {
        this.TaxAmount = TaxAmount;
    }

    public Date getIvDate() {
        return IvDate;
    }

    public void setIvDate(Date IvDate) {
        this.IvDate = IvDate;
    }

    public Date getDueDate() {
        return DueDate;
    }

    public void setDueDate(Date DueDate) {
        this.DueDate = DueDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
}
