package assignment3;

/**
 * Represents an order Each order contains information such as order number,
 * date, shipping details, order total, status, and the user's email.
 *
 * This class provides methods to access and manipulate order data.
 *
 * @author Matthew Hay
 * @author Matthew Irwin
 * @author Matthew Wallis
 */
public class Order {

    private String orderNum;
    private String date;
    private String shippedTo;
    private double orderTotal;
    private String status;
    private String userEmail;

    public Order(String orderNum, String date, String shippedTo, double orderTotal, String status, String userEmail) {
        this.orderNum = orderNum;
        this.date = date;
        this.shippedTo = shippedTo;
        this.orderTotal = orderTotal;
        this.status = status;
        this.userEmail = userEmail;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
