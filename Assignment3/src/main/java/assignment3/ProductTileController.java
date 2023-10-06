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
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author Matth
 */
public class ProductTileController implements Initializable {
    
    private Product product;
    private ShoppingCart cart;


    @FXML
    private Text txtPrice;
    @FXML
    private Text txtName;
    @FXML
    private Button btnAddToCart;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        cart = App.getShoppingCart();
    }    
    
    @FXML
    private void addToCartAction(ActionEvent event) {
        addToCart();
    }

    // Populate tile with product details
    public void setProduct(Product productToSet) {
        this.product = productToSet;
        this.txtName.setText(productToSet.getProductName());
        this.txtPrice.setText(String.format("$%.2f",productToSet.getPrice()));
    }
    
    // Add product to cart
    public void addToCart() {
        cart.addToCart(product, 1);
    }
}
