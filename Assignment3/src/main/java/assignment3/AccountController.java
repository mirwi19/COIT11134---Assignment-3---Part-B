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
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
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
    @FXML
    private TableView<Order> orderHistoryTable;
    @FXML
    private TableColumn<Order, String> orderNumberColumn;
    @FXML
    private TableColumn<Order, String> dateColumn;
    @FXML
    private TableColumn<Order, String> shippedToColumn;
    @FXML
    private TableColumn<Order, Double> orderTotalColumn;
    @FXML
    private TableColumn<Order, String> statusColumn;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountHandler = App.getAccountHandler();
        initializeOrderHistoryTable();
    }
    
    // Load the order history from the text file
    public void loadOrderHistory() {
        ObservableList<Order> loadedOrderHistory = OrderHistoryHandler.loadOrderHistoryFromTextFile();
        orderHistoryTable.setItems(loadedOrderHistory);
    }
    
    // Initialize the order history table
    private void initializeOrderHistoryTable() {
        orderNumberColumn.setCellValueFactory(new PropertyValueFactory<>("orderNum"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        shippedToColumn.setCellValueFactory(new PropertyValueFactory<>("shippedTo"));
        orderTotalColumn.setCellValueFactory(new PropertyValueFactory<>("orderTotal"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    
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
	
	txtFirstNameAlert.setVisible(false);
	txtLastNameAlert.setVisible(false);
	txtEmailAlert.setVisible(false);
	txtPhoneNumAlert.setVisible(false);
	txtCurrentPassAlert.setVisible(false);
	txtNewPassAlert.setVisible(false);
	txtConfNewPassAlert.setVisible(false);
	txtAddressAlert.setVisible(false);
	txtPostcodeAlert.setVisible(false);
	txtStateAlert.setVisible(false);
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
	Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete your account?");
	alert.setTitle("Account Deletion");
	alert.setHeaderText(null);
	alert.showAndWait().ifPresent(response -> {
	    if (response == ButtonType.OK) {
		accountHandler.removeUser();
	    }
	});
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
	    
	    //Alert for name change
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Name Changed");
            alert.setHeaderText(null);
            alert.setContentText("Name has been changed!");
            alert.showAndWait(); 
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
	    
	    //Alert for email change
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Email Changed");
            alert.setHeaderText(null);
            alert.setContentText("Email has been changed!");
            alert.showAndWait(); 
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
	    
	    //Alert for phone number change
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Phone Number Changed");
            alert.setHeaderText(null);
            alert.setContentText("Phone number has been changed!");
            alert.showAndWait(); 
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
	    
	    //Alert for password change
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password Changed");
            alert.setHeaderText(null);
            alert.setContentText("Password has been changed!");
            alert.showAndWait(); 
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
	    System.out.println("Address updated");
	    
	    //Alert for address change
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Address Changed");
            alert.setHeaderText(null);
            alert.setContentText("Address has been changed!");
            alert.showAndWait(); 
	}
    }
    
}
