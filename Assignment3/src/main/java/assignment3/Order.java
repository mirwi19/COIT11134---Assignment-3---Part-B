/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package assignment3;

/**
 *
 * @author Matthew Irwin
 */
public class Order {
    private String orderNum;
    private String date;
    private String shippedTo;
    private double orderTotal;
    private String status;

    public Order(String orderNum, String date, String shippedTo, double orderTotal, String status) {
        this.orderNum = orderNum;
        this.date = date;
        this.shippedTo = shippedTo;
        this.orderTotal = orderTotal;
        this.status = status;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShippedTo() {
        return shippedTo;
    }

    public void setShippedTo(String shippedTo) {
        this.shippedTo = shippedTo;
    }
    
    public double getOrderTotal() {
        return orderTotal;
    }
    
    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

}
