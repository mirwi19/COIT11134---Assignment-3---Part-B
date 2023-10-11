package assignment3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller class for handling user login and registration
 *
 * This controller manages user interactions related to logging in and
 * registering for an account in the E-commerce Application. It handles the
 * validation of user input and communicates with the `AccountHandler` to
 * authenticate users and create new accounts.
 *
 * @author Matthew Hay
 * @author Matthew Irwin
 * @author Matthew Wallis
 */
public class LoginController implements Initializable {

    private AccountHandler accountHandler;
    private String loggedEmail;

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

    public String getLoggedEmail() {
        return loggedEmail;
    }

    @FXML
    private void loginAction(ActionEvent event) {
        String emailInput = txtLoginEmail.getText().trim();
        String passwordInput = txtLoginPassword.getText().trim();
        if (accountHandler.authenticateUser(emailInput, passwordInput)) {
            txtLoginEmail.setText("");
            txtLoginPassword.setText("");
            App.changeScene(1);

            // Store the logged in email address
            loggedEmail = emailInput;
            System.out.println("Email logged in: " + loggedEmail);

            //Loads User Order History
            AccountController accountController = App.getAccountController();
            accountController.loadOrderHistory();
        }
    }

    @FXML
    private void registerAction(ActionEvent event) {
        //Set input fields to variables trim to remove spaces before and after string 
        String firstName = txtRegFirstName.getText().trim();
        String lastName = txtRegLastName.getText().trim();
        String address = txtRegAddress.getText().trim();
        String postcodeString = txtRegPostcode.getText().trim();
        String state = txtRegState.getText().trim();
        String phoneNumber = txtRegPhoneNum.getText().trim();
        String email = txtRegEmail.getText().trim();
        String confEmail = txtRegConfEmail.getText().trim();
        String password = txtRegPass.getText().trim();
        String confPassword = txtRegConfPass.getText().trim();
        //Variable to store integer of postcode
        int postcodeInt;
        //Variable to store whether all inputs are valid
        boolean inputValid = DataValidator.validateAll(firstName, lastName, address, postcodeString, state, phoneNumber, email, confEmail, password, confPassword);

        //Run validators for each field to display alert messages
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

        //If all inputs valid
        if (inputValid) {

            //Parse postcode into int
            postcodeInt = Integer.parseInt(postcodeString);
            //Use generateID() method to get a new uniqueID
            int id = App.getAccountHandler().generateID();
            //Set isAdmin for new accounts false
            boolean isAdmin = false;
            //Display userID
            System.out.println("UserID: " + id);

            //Create new user object
            User user = new User(id, password, firstName, lastName, email, address, postcodeInt, state, phoneNumber, isAdmin);
            //Add new user arraylist using addUser() method
            App.getAccountHandler().addUser(user);
            System.out.println("User created");

            //Set all fields blank
            txtRegFirstName.setText("");
            txtRegLastName.setText("");
            txtRegAddress.setText("");
            txtRegPostcode.setText("");
            txtRegState.setText("");
            txtRegPhoneNum.setText("");
            txtRegEmail.setText("");
            txtRegConfEmail.setText("");
            txtRegPass.setText("");
            txtRegConfPass.setText("");

            //Alert box for account created
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Account Created");
            alert.setHeaderText(null);
            alert.setContentText("Account has been created!");
            alert.showAndWait();
        }
    }

    @FXML
    public void setLoginEmailAlert(boolean alertStatus) {
        // Update alert status for login email field
        txtLoginEmailAlert.setVisible(alertStatus);
    }

    @FXML
    public void setLoginPasswordAlert(boolean alertStatus) {
        // Update alert status for login password field
        txtLoginPasswordAlert.setVisible(alertStatus);
    }

}
