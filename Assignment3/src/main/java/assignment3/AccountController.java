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
    }

    @FXML
    private void updateNameAction(ActionEvent event) {
    }

    @FXML
    private void updateEmailAction(ActionEvent event) {
    }

    @FXML
    private void updatePhoneNumAction(ActionEvent event) {
    }

    @FXML
    private void updatePassAction(ActionEvent event) {
    }

    @FXML
    private void updateAddressAction(ActionEvent event) {
    }
    
}
