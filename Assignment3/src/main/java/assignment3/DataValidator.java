/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment3;

/**
 *
 * @author Beast
 */
public class DataValidator {
    
    //Method to validate all inputs for user registration
    public static boolean validateAll(String firstName, String lastName, String address, String postcode, String state, String phone, String email, String confEmail, String password, String confPassword) {
	
	//Return true if all valid or false if any not valid
	if (validateName(firstName) 
		&& validateName(lastName) 
		&& validateAddress(address) 
		&& validatePostcode(postcode) 
		&& validateState(state) 
		&& validatePhoneNum(phone) 
		&& validateEmail(email) 
		&& validateConfEmail(email, confEmail)
		&& validatePassword(password)
		&& validateConfPassword(password, confPassword)) {
	    return true;
	}
	else {
	    return false;
	}
    }
    
    //Method to validate names
    public static boolean validateName(String name) {
	
	//Check if name is an empty string return false if empty
	if (name.isEmpty()) {
	    System.err.println("Invalid Name - Empty");
	    return false;
	}
	
	//Loop to cycle through each character
	for (int i = 0; i < name.length(); i++) {
	    //If any characters are digits return false
	    if (Character.isDigit(name.charAt(i))) {
		System.err.println("Invalid Name - Contains Number");
		return false;
	    }
	}
	
	return true;
    }
    
    //Method to validate address
    public static boolean validateAddress(String address) {
	
	//Check if address is an empty string return false if empty
	if (address.isEmpty()) {
	    System.err.println("Invalid Address - Empty");
	    return false;
	}
	
	return true;
    }
    
    //Method to validate postcode
    public static boolean validatePostcode(String postcode) {
	
	//Try block to catch NumberFormatException return false if exception thrown
	try {
	    //Parse int to check if exception thrown
	    int test = Integer.parseInt(postcode);
	    
	    //Check if postcode is a length of 4 return true
	    if (postcode.length() == 4) {
		return true;
	    }

	    System.err.println("Invalid Postcode - Not 4 digits");
	    return false;
	} catch (NumberFormatException ex) {
	    System.err.println("Invalid Postcode - Not a number");
	    return false;
	}
    }
	
    //Method to validate state
    public static boolean validateState(String state) {
	
	//Check if state is an empty string return false if empty
	if (state.isEmpty()) {
	    System.err.println("Invalid State - Empty");
	    return false;
	}
	
	//Loop to cycle through each character
	for (int i = 0; i < state.length(); i++) {
	    //If any characters are digits return false
	    if (Character.isDigit(state.charAt(i))) {
		System.err.println("Invalid State - Contains Numbers");
		return false;
	    }
	}
	
	return true;
    }
    
    //Method to validate phone number
    public static boolean validatePhoneNum(String num) {
	
	//Check if string is empty if empty return true 
	if (num.isEmpty()) {
	    return true;
	}
	
	//Loop to cycle through each character
	for (int i = 0; i < num.length(); i++) {
	    //If any characters are digits return false
	    if (!Character.isDigit(num.charAt(i))) {
		System.err.println("Invalid Numbers - Contains non-number");
		return false;
	    }
	}
	
	return true;
    }
    
    
    //Method to validate email
    public static boolean validateEmail(String email) {
	//Pattern for valid email
	String pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	
	//Check if string is empty if empty return false 
	if (email.isEmpty()) {
	    System.err.println("Invalid Email - Empty");
	    return false;
	}
	//If string doesn't match pattern return false
	else if (!email.matches(pattern)) {
	    System.err.println("Invalid Email - Doesn't match pattern");
	    return false;
	}
	else{
	    return true;
	}
    }
    
    
    //Method to validate confirmation email
    public static boolean validateConfEmail(String email, String confEmail) {
	
	//Check if string is empty if empty return false 
	if (confEmail.isEmpty()) {
	    System.err.println("Invalid Email - confEmail empty");
	    return false;
	}
	//Check if emails match if they do return true
	else if (email.equals(confEmail)) {
	    return true;
	}
	else{
	    System.err.println("Invalid Email - Emails don't match");
	    return false;
	}
    }
    
    //Method to validate password
    public static boolean validatePassword(String pass) {
	
	//Check if string is empty if empty return false
	if (pass.isEmpty()) 
	{
	    System.err.println("Invalid Password - Password empty");
	    return false;
	}
	else {
	    return true;
	}
    }
    
    //Method to validate confirmation password
    public static boolean validateConfPassword(String pass, String confPass) {
	
	//Check if string is empty if empty return false
	if (confPass.isEmpty()) {
	    System.err.println("Invalid Password - confPass empty");
	    return false;
	}
	//Check if passwords match if they do return true
	else if (pass.equals(confPass)) 
	{
	    return true;
	}
	else {
	    System.err.println("Invalid Password - Passwords don't match");
	    return false;
	}
    }
}
