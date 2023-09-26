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
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author Matth
 */
public class CheckoutTileController implements Initializable {
    
    private Product product;
    private int quantity;
    private ShoppingCart cart;
    
    private CheckoutController checkoutController;


    @FXML
    private Label txtPrice;
    @FXML
    private Label txtName;
    @FXML
    private Button btnRemoveFromCart;
    @FXML
    private TextField txtQuantity;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cart = App.getShoppingCart();
        checkoutController = App.getCheckoutController();
    }    
    
    @FXML
    private void removeFromCart(ActionEvent event) {
        removeFromCart();
    }
    
    public void removeFromCart() {
        cart.removeFromCart(product);
        checkoutController.displayCart(cart);
    }

    @FXML
    private void updateQuantity(ActionEvent event) {
        String qtyText = this.txtQuantity.getText().trim();
        
        try {
            int qty = Integer.parseInt(qtyText);
            cart.updateQuantity(product, qty);
            checkoutController.displayCart(cart);
        } catch (NumberFormatException e) {
            // Handle invalid input
            System.err.println("Incalid qty...");
        }
    }
    
    public void setProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.txtName.setText(product.getProductName());
        this.txtPrice.setText(String.format("$%.2f", product.getPrice()));
        this.txtQuantity.setText("" + quantity);
    }

}
