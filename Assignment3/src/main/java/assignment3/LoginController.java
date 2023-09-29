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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author Matth
 */
public class LoginController implements Initializable {
    private AccountHandler accountHandler;


    @FXML
    private TextField txtLoginEmail;
    @FXML
    private PasswordField txtLoginPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label txtLoginEmailAlert;
    @FXML
    private Label txtLoginPasswordAlert;
    @FXML
    private TextField txtRegFirstName;
    @FXML
    private Label txtRegFirstNameAlert;
    @FXML
    private TextField txtRegLastName;
    @FXML
    private Label txtRegLastNameAlert;
    @FXML
    private TextField txtRegAddress;
    @FXML
    private Label txtRegAddressAlert;
    @FXML
    private TextField txtRegPostcode;
    @FXML
    private Label txtRegPostcodeAlert;
    @FXML
    private TextField txtRegState;
    @FXML
    private Label txtRegStateAlert;
    @FXML
    private TextField txtRegPhoneNum;
    @FXML
    private Label txtRegPhoneNumAlert;
    @FXML
    private TextField txtRegEmail;
    @FXML
    private Label txtRegEmailAlert;
    @FXML
    private TextField txtRegConfEmail;
    @FXML
    private Label txtRegConfEmailAlert;
    @FXML
    private PasswordField txtRegPass;
    @FXML
    private Label txtRegPassAlert;
    @FXML
    private PasswordField txtRegConfPass;
    @FXML
    private Button btnRegister;
    @FXML
    private Label txtRegConfPassAlert;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountHandler = App.getAccountHandler();
    }        

    @FXML
    private void loginAction(ActionEvent event) {
        String emailInput = txtLoginEmail.getText().trim();
        String passwordInput = txtLoginPassword.getText().trim();
        if (accountHandler.authenticateUser(emailInput, passwordInput)) {
            txtLoginEmail.setText("");
            txtLoginPassword.setText("");
            App.changeScene(1);
        }
    }

    @FXML
    private void registerAction(ActionEvent event) {
	String firstName = txtRegFirstName.getText();
	String lastName = txtRegLastName.getText();
	String address = txtRegAddress.getText();
	String postcodeString = txtRegPostcode.getText();
	String state = txtRegState.getText();
	String phoneNumber = txtRegPhoneNum.getText();
	String email = txtRegEmail.getText();
	String confEmail = txtRegConfEmail.getText();
	String password = txtRegPass.getText();
	String confPassword = txtRegConfPass.getText();
	int postcodeInt;
	boolean inputValid = DataValidator.validateAll(firstName, lastName, address, postcodeString, state, phoneNumber, email, confEmail, password, confPassword);
	
	txtRegFirstNameAlert.setVisible(!DataValidator.validateName(firstName));
	txtRegLastNameAlert.setVisible(!DataValidator.validateName(lastName));
	txtRegAddressAlert.setVisible(!DataValidator.validateAddress(address));
	txtRegPostcodeAlert.setVisible(!DataValidator.validatePostcode(postcodeString));
	txtRegStateAlert.setVisible(!DataValidator.validateState(state));
	txtRegPhoneNumAlert.setVisible(!DataValidator.validatePhoneNum(phoneNumber));
	txtRegEmailAlert.setVisible(!DataValidator.validateEmail(email));
	txtRegConfEmailAlert.setVisible(!DataValidator.validateConfEmail(email, confEmail));
	txtRegPassAlert.setVisible(!DataValidator.validatePassword(password));
	txtRegConfPassAlert.setVisible(!DataValidator.validateConfPassword(password, confPassword));

	if (inputValid) {
	    postcodeInt = Integer.parseInt(postcodeString);
	    
	    
	    System.out.println("User created");
	}
    }

}
