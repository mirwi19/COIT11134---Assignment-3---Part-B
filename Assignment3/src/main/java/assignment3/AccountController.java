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
import javafx.scene.control.MenuItem;
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
    private MenuItem btnViewCart;
    @FXML
    private MenuItem btnCheckout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountHandler = App.getAccountHandler();
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
    private void checkoutAction(ActionEvent event) {
        App.changeScene(3);
    }
    
    public void displayUserDetails() {
        this.txtFirstName.setText(accountHandler.getCurrentUser().getFirstName());
        this.txtLastName.setText(accountHandler.getCurrentUser().getLastName());
        this.txtEmail.setText(accountHandler.getCurrentUser().getEmail());
    }
    
}
