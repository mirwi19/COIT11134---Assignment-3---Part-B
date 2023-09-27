/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment3;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author Matth
 */
public class CheckoutController implements Initializable {
    private AccountHandler accountHandler;


    @FXML
    private ScrollPane paneCartDisplay;
    @FXML
    private Label txtTotal;
    @FXML
    private Button btnBrowseProds;
    @FXML
    private MenuItem btnEditAccount;
    @FXML
    private MenuItem btnLogout;
    @FXML
    private Button btnManageProducts;
    @FXML
    private Button btnCart;
    @FXML
    private TextField txtAddress;
    @FXML
    private Label txtAddressAlert;
    @FXML
    private TextField txtPostcode;
    @FXML
    private Label txtPostcodeAlert;
    @FXML
    private TextField txtState;
    @FXML
    private Label txtStateAlert;
    @FXML
    private Button btnUpdateAddress;
    @FXML
    private TextField txtNameOnCard;
    @FXML
    private Label txtNameOnCardAlert;
    @FXML
    private TextField txtCardNum;
    @FXML
    private Label txtCardNumAlert;
    @FXML
    private DatePicker txtExpireDate;
    @FXML
    private Label txtExpireDateAlert;
    @FXML
    private TextField txtCVV;
    @FXML
    private Label txtCVVAlert;
    @FXML
    private Button btnCheckout;
    @FXML
    private Button btnContinueShopping;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountHandler = App.getAccountHandler();
    }    
        
    public void displayCart(ShoppingCart cart) {
        ArrayList<Product> products = cart.getCartProducts();
        ArrayList<Integer> quantities = cart.getCartProductQty();
        
        double cartTotal = 0;
        
        // Create VBox to stack cart
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        
        // Process cart
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int quantity = quantities.get(i);
            
            // Update carTotal
            cartTotal += product.getPrice() * quantity;
            
            // Create carTile
            try {
                // Create FXMLLoader
                FXMLLoader loader = new FXMLLoader(getClass().getResource("checkoutTile.fxml"));
                
                // Load tile into AnchorPane
                AnchorPane cartTilePane = loader.load();
                
                // Get Controller
                CheckoutTileController cartController = loader.getController();
                
                // Set Product details
                cartController.setProduct(product, quantity);
                
                // Add cartTilePane to VBox
                vBox.getChildren().add(cartTilePane);
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        // Add VBox to Checkout scene
        paneCartDisplay.setContent(vBox);
        
        // Display carTotal
        this.txtTotal.setText(String.format("Total: $%.2f", cartTotal));
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
    private void updateAddressAction(ActionEvent event) {
    }

    @FXML
    private void checkoutAction(ActionEvent event) {
    }

}
