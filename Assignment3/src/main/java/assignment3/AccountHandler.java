/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package assignment3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Formatter;
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
    
    // Read User data from file and create User objects
    public void readUserData() {
        Scanner dataInput = null;
        
        try {
            dataInput = new Scanner(new FileReader(accountListFile));
            String dataEntry;
            User currentUser = null;
            
            while (dataInput.hasNextLine()) {
                dataEntry = dataInput.nextLine().trim();              
                // Tokenize user details
                StringTokenizer userTokenizer = new StringTokenizer(dataEntry, ",");
                if (userTokenizer.hasMoreTokens()) {
                    int uniqueID = Integer.parseInt(userTokenizer.nextToken().trim());
                    String password = userTokenizer.nextToken().trim();
                    String firstName = userTokenizer.nextToken().trim();
                    String lastName = userTokenizer.nextToken().trim();
                    String email = userTokenizer.nextToken().trim();
                    String address = userTokenizer.nextToken().trim();
                    int postcode = Integer.parseInt(userTokenizer.nextToken().trim());
                    String state = userTokenizer.nextToken().trim();
                    String phoneNum = userTokenizer.nextToken().trim();
                    boolean isAdmin = Boolean.parseBoolean(userTokenizer.nextToken().trim());

                    // Create User
                    currentUser = new User(uniqueID, password, firstName, lastName, email, address, postcode, state, phoneNum, isAdmin);
                    // Debug: Printing currentUser
                    System.out.println("User added: " + currentUser.getUniqueID() + ", " + currentUser.getFirstName() + ", " + currentUser.getLastName() + ", " + currentUser.getEmail());
                    addUser(currentUser);                  
                } 
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File Not Found:\n" + ex);
        } finally {
            if (dataInput != null) {
                dataInput.close();
            }
        }
    }
    
    // Add User object to userList
    public void addUser(User userToAdd) {
	//Add new user to arraylist
        this.userList.add(userToAdd);
	//Call save to file method
	saveUserData();
    }
    
    // Remove User object from userList
    public void removeUser() {
	//Remove current user from arraylist
	this.userList.remove(currentUser);
	//Call save to file method
	saveUserData();
	//Change to login/registration scene
	App.changeScene(0);
    }
    
    // Authenticate login attempt
    public boolean authenticateUser(String emailInput, String passwordInput) {
        LoginController loginController = App.getLoginController();
        boolean isEmptyEmail = emailInput == null || emailInput.isEmpty();
        boolean isEmptyPassword = passwordInput == null || passwordInput.isEmpty();
        boolean isMatchingEmail = false;
        boolean isMatchingPassword = false;
        
        for (User user : userList) {
            if (!isEmptyEmail && emailInput.equals(user.getEmail())) {
                isMatchingEmail = true;
                if (!isEmptyPassword && passwordInput.equals(user.getPassword())) {
                    isMatchingPassword = true;
                    currentUser = user;
                }
            }
        }
        
        if (isMatchingEmail && isMatchingPassword) {
            // Valid login
            loginController.setLoginEmailAlert(false);
            loginController.setLoginPasswordAlert(false);
            return true;
        } else {
            // Handle alerts based on booleans
            if (isEmptyEmail && isEmptyPassword) {
                System.out.println("Empty email & password");
                loginController.setLoginEmailAlert(true);
                loginController.setLoginPasswordAlert(true);
            } else if (isEmptyEmail) {
                System.out.println("Empty email");
                loginController.setLoginEmailAlert(true);
                loginController.setLoginPasswordAlert(false);
            } else if (isEmptyPassword) {
                System.out.println("Empty password");
                loginController.setLoginEmailAlert(false);
                loginController.setLoginPasswordAlert(true);
            } else if (isMatchingEmail) {
                System.out.println("Invalid/Empty password");
                loginController.setLoginEmailAlert(false);
                loginController.setLoginPasswordAlert(true);
            }
            return false;
        }
    }
    
    // Get the current logged in user
    public User getCurrentUser() {
        return currentUser;
    }
    
    // Log current User out and return to login
    public void logout() {
        // Reset current user
        currentUser = null;
        // Reset shopping cart
        ShoppingCart shoppingCart = App.getShoppingCart();
        shoppingCart.clearCart();
        // Save user list
        saveUserData();
        // Return to login scene
        App.changeScene(0);        
    }
    
    public int generateID() {
	//Minimum userID
	int id = 1;
	//Check if id exists using findID() method
	int index = findID(id); 
	
	//If id does exist continue incrementing and checking until it doesn't
	while (index != -1) {
	    id++;
	    index = findID(id);
	}
	
	return id;
    }
    
    public int findID(int input) {
	//Variable to store index of id initialised to -1 
	int index = -1;
	
	//Cycle through each user to check if id exists
	for(int i = 0; i < userList.size(); i++) {
	    if (input == userList.get(i).getUniqueID()) {
		index = i;	    
	    }
	}
	
	return index;
    }
    
    // Save current userList to file
    public void saveUserData(){
        try {
            Formatter userOutput = new Formatter(accountListFile); // Open output file
            int totalUsers = userList.size();
            
            for (int i = 0; i < totalUsers; i++) {
                User currentUser = userList.get(i);                
                userOutput.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n", currentUser.getUniqueID(), currentUser.getPassword(), currentUser.getFirstName(), currentUser.getLastName(), currentUser.getEmail(), currentUser.getAddress(), currentUser.getPostcode(), currentUser.getState(), currentUser.getPhoneNum(), currentUser.getIsAdmin());
            }
            userOutput.close(); // Close output file
        } catch (SecurityException ex) {
            System.err.print("Security Exception:\n" + ex);
        } catch (FileNotFoundException ex) {
            System.err.print("File Not Found:\n" + ex);
        }
    }
}
