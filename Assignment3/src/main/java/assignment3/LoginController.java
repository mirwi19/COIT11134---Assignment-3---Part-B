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
    }

}
