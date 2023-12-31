package assignment3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller class for the individual product tile displayed in the Checkout
 * scene
 *
 * This class handles interactions and actions related to a single product tile
 * in the shopping cart during the checkout process. It allows users to adjust
 * the quantity of a product, remove it from the cart, and updates the cart
 * display accordingly.
 *
 * @author Matthew Hay
 * @author Matthew Irwin
 * @author Matthew Wallis
 */
public class CheckoutTileController implements Initializable {

    private Product product;
    private int quantity;
    private ShoppingCart cart;

    private CheckoutController checkoutController;

    @FXML
    private Button btnRemoveFromCart;
    @FXML
    private TextField txtQuantity;
    @FXML
    private Button btnQtyDec;
    @FXML
    private Button btnQtyInc;
    @FXML
    private Text txtName;
    @FXML
    private Text txtPrice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cart = App.getShoppingCart();
        checkoutController = App.getCheckoutController();
    }

    // Populate tile with product details
    public void setProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.txtName.setText(product.getProductName());
        this.txtPrice.setText(String.format("$%.2f", product.getPrice()));
        this.txtQuantity.setText("" + quantity);
    }

    @FXML
    public void removeFromCart() {
        cart.removeFromCart(product);
        checkoutController.displayCart(cart);
    }

    private void removeFromCart(ActionEvent event) {
        removeFromCart();
    }

    @FXML
    private void qtyDecAction(ActionEvent event) {
        String currentQty = this.txtQuantity.getText();

        try {
            int qty = Integer.parseInt(currentQty);

            if (qty > 1) {
                qty -= 1;
                this.txtQuantity.setText(String.valueOf(qty));
                cart.updateQuantity(product, qty);
                checkoutController.displayCart(cart);
            }
        } catch (NumberFormatException ex) {
            System.err.println(ex);
        }
    }

    @FXML
    private void qtyIncAction(ActionEvent event) {
        String currentQty = this.txtQuantity.getText();

        try {
            int qty = Integer.parseInt(currentQty);

            if (qty >= 1) {
                qty += 1;
                this.txtQuantity.setText(String.valueOf(qty));
                cart.updateQuantity(product, qty);
                checkoutController.displayCart(cart);
            }
        } catch (NumberFormatException ex) {
            System.err.println(ex);
        }
    }

}
