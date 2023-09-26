/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package assignment3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Matthew Irwin
 */
public class AccountHandler {
    private String accountListFile;
    private ArrayList<User> userList;
    private User currentUser;

    public AccountHandler(String fileName) {
        this.accountListFile = fileName + ".txt";
        this.userList = new ArrayList<>();
        
        readUserData();
    }
    
    public void readUserData() {
        Scanner dataInput = null;
        
        try {
            dataInput = new Scanner(new FileReader(accountListFile));
            String dataEntry;
            User currentUser = null;
            boolean readingOrderHistory = false; // Flag to indicate if reading order history
            
            while (dataInput.hasNextLine()) {
                dataEntry = dataInput.nextLine().trim();
                // If line ends with ':' read user information
                if (dataEntry.endsWith(":")) {
                    // Set readingOrderHistory to false on user read
                    if (currentUser != null && readingOrderHistory) {
                        readingOrderHistory = false;
                    }
                    
                    // Tokenize user details
                    StringTokenizer userTokenizer = new StringTokenizer(dataEntry, ",");
                    if (userTokenizer.hasMoreTokens()) {
                        String uniqueID = userTokenizer.nextToken().trim();
                        String password = userTokenizer.nextToken().trim();
                        String firstName = userTokenizer.nextToken().trim();
                        String lastName = userTokenizer.nextToken().trim();
                        String email = userTokenizer.nextToken().trim();
                        
                        // Removing ':' separator
                        if (email.endsWith(":")) { // Email is currently the last token, change to match last token
                            email = email.substring(0, email.length() - 1);
                        }
                        
                        // Create User
                        currentUser = new User(uniqueID, password, firstName, lastName, email);
                        // Debug: Printing currentUser
                        System.out.println("User added: " + currentUser.getUniqueID() + ", " + currentUser.getFirstName() + ", " + currentUser.getLastName() + ", " + currentUser.getEmail());
                        addUser(currentUser);
                    }
                } else if (currentUser != null) { // Checking if there's an initial user before reading order history
                    // Set readingOrderHistory
                    if (!readingOrderHistory) {
                        readingOrderHistory = true;
                    }
                    
                    // Tokenize order details
                    StringTokenizer orderTokenizer = new StringTokenizer(dataEntry, ",");
                    String orderNum = orderTokenizer.nextToken().trim();
                    String date = orderTokenizer.nextToken().trim();
                    String shippedTo = orderTokenizer.nextToken().trim();
                    double orderTotal = Double.parseDouble(orderTokenizer.nextToken().trim());
                    String status = orderTokenizer.nextToken().trim();
                    
                    // Add order to currentUser
                    Order currentOrder = new Order(orderNum, date, shippedTo, orderTotal, status);
                    // Debug: Printin currentOrder
                    System.out.println("Order added to: " + currentUser.getUniqueID() + ", Order details:" + ", " + currentOrder.getOrderNum() + ", " +  currentOrder.getDate() + ", " + currentOrder.getShippedTo() + ", " + currentOrder.getOrderTotal() + ", " + currentOrder.getStatus());
                    currentUser.getOrderHistory().add(currentOrder);
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } finally {
            if (dataInput != null) {
                dataInput.close();
            }
        }
    }
    
    public void addUser(User userToAdd) {
        this.userList.add(userToAdd);
    }
    
    public void removeUser() {
        
    }
    
    public boolean authenticateUser(String emailInput, String passwordInput) {
        if (emailInput == null || emailInput.isEmpty()) {
            // Invalid Email - empty
            System.err.println("Invalid Email - Empty");
            return false;
        }
        
        if (passwordInput == null || passwordInput.isEmpty()) {
            // Invalid Password - Empty
            System.err.println("Invalid Password - Empty");
            return false;
        }
        
        for (User user : userList) {
            if (emailInput.equals(user.getEmail())) {
                // Matching Email
                if (passwordInput.equals(user.getPassword())) {
                    // Matching Password
                    currentUser = user;
                    return true;
                }
            }
        }
        
        // No matching user found
        System.err.println("Invalid email or password");
        return false;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void logout() {
        currentUser = null;
        App.changeScene(0);
        
    }
    
    public void saveUserData(){
        
    }
}