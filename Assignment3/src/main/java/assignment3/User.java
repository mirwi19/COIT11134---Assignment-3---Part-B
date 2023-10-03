/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package assignment3;

import java.util.ArrayList;

/**
 *
 * @author Matthew Irwin
 */
public class User {
    private int uniqueID;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private int postcode;
    private String state;
    private String phoneNum;
    private ArrayList<Order> orderHistory;
    private boolean isAdmin;
    
    public User(int uniqueID, String password, String firstName, String lastName, String email, String address, int postcode, String state, String phoneNum, boolean isAdmin) {
        this.uniqueID = uniqueID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
	this.address = address;
	this.postcode = postcode;
	this.state = state;
	this.phoneNum = phoneNum;
	this.isAdmin = isAdmin;
        this.orderHistory = new ArrayList<>();
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }    

}
