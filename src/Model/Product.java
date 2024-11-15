/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;

/**
 *
 * @author Manh
 */
public class Product {
    private int PdId;
    private String PdName;
    private BigDecimal PdPrice; 
    private String PdDescription;

    public Product() {
    }

    public Product(int PdId, String PdName, BigDecimal PdPrice, String PdDescription) {
        this.PdId = PdId;
        this.PdName = PdName;
        this.PdPrice = PdPrice;
        this.PdDescription = PdDescription;
    }

    public int getPdId() {
        return PdId;
    }

    public void setPdId(int PdId) {
        this.PdId = PdId;
    }

    public String getPdName() {
        return PdName;
    }

    public void setPdName(String PdName) {
        this.PdName = PdName;
    }

    public BigDecimal getPdPrice() {
        return PdPrice;
    }

    public void setPdPrice(BigDecimal PdPrice) {
        this.PdPrice = PdPrice;
    }

    public String getPdDescription() {
        return PdDescription;
    }

    public void setPdDescription(String PdDescription) {
        this.PdDescription = PdDescription;
    }
    
}
