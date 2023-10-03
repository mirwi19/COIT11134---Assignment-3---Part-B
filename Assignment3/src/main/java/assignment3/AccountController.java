/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Matth
 */
public class AccountController implements Initializable {
    private AccountHandler accountHandler;

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TableView<?> tblOrderHistory;
    @FXML
    private Button btnBrowseProds;
    @FXML
    private MenuItem btnEditAccount;
    @FXML
    private MenuItem btnLogout;
    @FXML
    private Button btnManageProducts;
    @FXML
    private Label txtFirstNameAlert;
    @FXML
    private Label txtLastNameAlert;
    @FXML
    private Label txtEmailAlert;
    @FXML
    private Label txtPhoneNumAlert;
    @FXML
    private Label txtCurrentPassAlert;
    @FXML
    private Label txtNewPassAlert;
    @FXML
    private Label txtConfNewPassAlert;
    @FXML
    private Label txtAddressAlert;
    @FXML
    private Label txtPostcodeAlert;
    @FXML
    private Label txtStateAlert;
    @FXML
    private Button btnCart;
    @FXML
    private Button btnDeleteAccount;
    @FXML
    private Button btnUpdateName;
    @FXML
    private Button btnUpdateEmail;
    @FXML
    private TextField txtPhoneNum;
    @FXML
    private Button btnUpdatePhoneNum;
    @FXML
    private PasswordField txtCurrentPass;
    @FXML
    private PasswordField txtNewPass;
    @FXML
    private PasswordField txtConfNewPass;
    @FXML
    private Button btnUpdatePass;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPostcode;
    @FXML
    private TextField txtState;
    @FXML
    private Button btnUpdateAddress;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountHandler = App.getAccountHandler();
    }
    
    public void displayUserDetails() {
        this.txtFirstName.setText(accountHandler.getCurrentUser().getFirstName());
        this.txtLastName.setText(accountHandler.getCurrentUser().getLastName());
        this.txtEmail.setText(accountHandler.getCurrentUser().getEmail());
	this.txtPhoneNum.setText(accountHandler.getCurrentUser().getPhoneNum());
	this.txtAddress.setText(accountHandler.getCurrentUser().getAddress());
	this.txtPostcode.setText(Integer.toString(accountHandler.getCurrentUser().getPostcode()));
	this.txtState.setText(accountHandler.getCurrentUser().getState());
	this.txtCurrentPass.setText("");
	this.txtNewPass.setText("");
	this.txtConfNewPass.setText("");
    }

    @FXML
    private void browseProdsAction(ActionEvent event) {
        App.changeScene(1);
    }

    @FXML
    private void editAccountAction(ActionEvent event) {
        App.changeScene(2);
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        accountHandler.logout();
    }

    @FXML
    private void viewCartAction(ActionEvent event) {
        App.changeScene(3);
    }
    
    @FXML
    private void manageProductsAction(ActionEvent event) {
        App.changeScene(4);
    }

    @FXML
    private void deleteAccountAction(ActionEvent event) {
	accountHandler.removeUser();
    }

    @FXML
    private void updateNameAction(ActionEvent event) {
	//Set name inputs to variables
	String firstName = txtFirstName.getText().trim();
	String lastName = txtLastName.getText().trim();
	
	//Run validators for name fields display alerts if invalid
	txtFirstNameAlert.setVisible(!DataValidator.validateName(firstName));
	txtLastNameAlert.setVisible(!DataValidator.validateName(lastName));
	
	//Check if names are valid
	if (DataValidator.validateName(firstName) && DataValidator.validateName(lastName)) {
	    //Set users name to new names
	    accountHandler.getCurrentUser().setFirstName(firstName);
	    accountHandler.getCurrentUser().setLastName(lastName);
	    System.out.println("Name updated");
	}
    }

    @FXML
    private void updateEmailAction(ActionEvent event) {
	//Set email input to variable
	String email = txtEmail.getText().trim();
	
	//Run validator for email display alert if invalid
	txtEmailAlert.setVisible(!DataValidator.validateEmail(email));
	
	//Check if email valid
	if (DataValidator.validateEmail(email)) {
	    //Set users email to new email
	    accountHandler.getCurrentUser().setEmail(email);
	    System.out.println("Email updated");
	}
    }

    @FXML
    private void updatePhoneNumAction(ActionEvent event) {
	//Set phone number input to variable
	String phoneNum = txtPhoneNum.getText().trim();
	
	//Run validator for phone number display alert if invalid
	txtPhoneNumAlert.setVisible(!DataValidator.validatePhoneNum(phoneNum));
	
	//Check if phone number valid
	if (DataValidator.validatePhoneNum(phoneNum)) {
	    //Set users phone number to new phone number
	    accountHandler.getCurrentUser().setPhoneNum(phoneNum);
	    System.out.println("Phone number updated");
	}
    }

    @FXML
    private void updatePassAction(ActionEvent event) {
	//Set password inputs to variables
	String currentPassword = accountHandler.getCurrentUser().getPassword();
	String currentConfPassword = txtCurrentPass.getText().trim();
	String newPassword = txtNewPass.getText().trim();
	String newConfPass = txtConfNewPass.getText().trim();
	
	//Run validators for password fields display alert if invalid
	txtCurrentPassAlert.setVisible(!DataValidator.validateConfPassword(currentPassword, currentConfPassword));
	txtNewPassAlert.setVisible(!DataValidator.validatePassword(newPassword));
	txtConfNewPassAlert.setVisible(!DataValidator.validateConfPassword(newPassword, newConfPass));
	
	//Check if password fields are valid
	if (DataValidator.validateConfPassword(currentPassword, currentConfPassword) && DataValidator.validatePassword(newPassword) && DataValidator.validateConfPassword(newPassword, newConfPass)) {
	    //Set users password to new password
	    accountHandler.getCurrentUser().setPassword(newPassword);
	    System.out.println("Password updated");

	    //Set all password fields blank
	    this.txtCurrentPass.setText("");
	    this.txtNewPass.setText("");
	    this.txtConfNewPass.setText("");
	}
    }

    //not done
    @FXML
    private void updateAddressAction(ActionEvent event) {
	//Set address inputs to variables
	String address = txtAddress.getText().trim();
	String postcode = txtPostcode.getText().trim();
	String state = txtState.getText().trim();
	
	//Run validators for address fields display alert if invalid
	txtAddressAlert.setVisible(!DataValidator.validateAddress(address));
	txtPostcodeAlert.setVisible(!DataValidator.validatePostcode(postcode));
	txtStateAlert.setVisible(!DataValidator.validateState(state));
	
	//Check if address fields are valid
	if (DataValidator.validateAddress(address) && DataValidator.validatePostcode(postcode) && DataValidator.validateState(state)) {
	    //Parse post into int
	    int postcodeInt = Integer.parseInt(postcode);
	    
	    //Set users address variables to new values
	    accountHandler.getCurrentUser().setAddress(address);
	    accountHandler.getCurrentUser().setPostcode(postcodeInt);
	    accountHandler.getCurrentUser().setState(state);
	    System.out.println("Email updated");
	}
    }
    
}
