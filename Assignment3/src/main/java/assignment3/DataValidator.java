/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment3;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Beast
 */
public class DataValidator {
    
    public static boolean validateAll(String firstName, String lastName, String address, String postcode, String state, String phone, String email, String confEmail, String password, String confPassword) {
	
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
    
    public static boolean validateName(String name) {
	if (name.isEmpty()) {
	    System.err.println("Invalid Name - Empty");
	    return false;
	}
	
	for (int i = 0; i < name.length(); i++) {
	    
	    if (Character.isDigit(name.charAt(i))) {
		System.err.println("Invalid Name - Contains Number");
		return false;
	    }
	}
	
	return true;
    }
    
    public static boolean validateAddress(String address) {
	if (address.isEmpty()) {
	    System.err.println("Invalid Address - Empty");
	    return false;
	}
	
	return true;
    }
    
    public static boolean validatePostcode(String postcode) {
	try {
	    int test = Integer.parseInt(postcode);
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
	
    public static boolean validateState(String state) {
	if (state.isEmpty()) {
	    System.err.println("Invalid State - Empty");
	    return false;
	}
	
	for (int i = 0; i < state.length(); i++) {
	    if (Character.isDigit(state.charAt(i))) {
		System.err.println("Invalid State - Contains Numbers");
		return false;
	    }
	}
	
	return true;
    }
    
    public static boolean validatePhoneNum(String num) {
	
	if (num.isEmpty()) {
	    return true;
	}
	
	for (int i = 0; i < num.length(); i++) {
	    if (!Character.isDigit(num.charAt(i))) {
		System.err.println("Invalid Numbers - Contains non-number");
		return false;
	    }
	}
	
	return true;
    }
    
    public static boolean validateEmail(String email) {
	if (email.isEmpty()) {
	    System.err.println("Invalid Email - Empty");
	    return false;
	}
	else{
	    return true;
	}
    }
    
    public static boolean validateConfEmail(String email, String confEmail) {
	
	if (confEmail.isEmpty()) {
	    System.err.println("Invalid Email - confEmail empty");
	    return false;
	}
	else if (email.equals(confEmail)) {
	    return true;
	}
	else{
	    System.err.println("Invalid Email - Emails don't match");
	    return false;
	}
    }
    
    public static boolean validatePassword(String pass) {
	if (pass.isEmpty()) 
	{
	    System.err.println("Invalid Password - Password empty");
	    return false;
	}
	else {
	    return false;
	}
    }
    
    public static boolean validateConfPassword(String pass, String confPass) {
	
	if (confPass.isEmpty()) {
	    System.err.println("Invalid Password - confPass empty");
	    return false;
	}
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
