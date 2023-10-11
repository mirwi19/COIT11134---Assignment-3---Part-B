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

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Controller class for the Checkout functionality
 *
 * This class handles the checkout process, including validating input fields,
 * generating order numbers, calculating order totals, and saving order history.
 * It interacts with the shopping cart, user account, and order history
 * components of the application.
 *
 * @author Matthew Hay
 * @author Matthew Irwin
 * @author Matthew Wallis
 */
public class CheckoutController implements Initializable {

    private AccountHandler accountHandler;
    private ArrayList<Order> orderHistory = new ArrayList<>();

    public CheckoutController() {
    }

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

    private double cartTotal = 0;

    // To be called when switching scenes to populate fields
    public void populateScene(ShoppingCart cart) {
        displayCart(cart);

        // Check if current user is an admin
        if (accountHandler.getCurrentUser() != null) {
            btnManageProducts.setVisible(accountHandler.getCurrentUser().getIsAdmin()); // Display/Hide manage products based on isAdmin
        }
    }

    // Populate cart display with current cart
    public void displayCart(ShoppingCart cart) {
        ArrayList<Product> products = cart.getCartProducts();
        ArrayList<Integer> quantities = cart.getCartProductQty();

        cartTotal = 0;

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

        // Validate the input fields
        String address = txtAddress.getText().trim();
        String postcode = txtPostcode.getText().trim();
        String state = txtState.getText().trim();

        if (cartTotal == 0.0) {
            //display alert for when cart is empty
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Cart is Empty");
            alert.setHeaderText(null);
            alert.setContentText("Cannot Checkout an empty cart!");
            alert.showAndWait();
            return;
        }

        if (address.isEmpty() || postcode.isEmpty() || state.isEmpty()) {
            // Show an error message and prevent checkout if any address fields are empty
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Checkout Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all address details before proceeding with the checkout.");
            alert.showAndWait();
            return;
        }

        String orderNum = generateOrderNumber(); // Generate order number
        String date = getCurrentDate(); // Get current date
        String shippedTo = txtAddress.getText() + ". " + txtState.getText() + ". " + txtPostcode.getText();
        double orderTotal = calculateOrderTotal(); // Calculate the order total
        String status = "Pending"; // Set the initial status as "Pending"

        //retrieve logged in user email
        LoginController loginController = App.getLoginController();
        String userEmail = loginController.getLoggedEmail();

        // Create an Order instance
        Order order = new Order(orderNum, date, shippedTo, orderTotal, status, userEmail);

        // Add the order to the order history
        orderHistory.add(order);

        // Save the order history to a file
        OrderHistoryHandler.saveOrderHistoryToTextFile(orderHistory);

        // Clear the shopping cart
        ShoppingCart shoppingCart = App.getShoppingCart();
        shoppingCart.clearCart();
        VBox vBox = (VBox) paneCartDisplay.getContent();
        vBox.getChildren().clear();
        cartTotal = 0;
        this.txtTotal.setText("$0.00");

        // Displays an Alert to show the checkout was successful
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Checkout Successful");
        alert.setHeaderText(null);
        alert.setContentText("Your checkout was successful!");
        alert.showAndWait();

        //Re-Load order history to include newly placed order.
        AccountController accountController = App.getAccountController();
        accountController.loadOrderHistory();

        //Debugging
        System.out.println("Size of orderHistory before adding: " + orderHistory.size());
        orderHistory.add(order);
        System.out.println("Size of orderHistory after adding: " + orderHistory.size());

    }

    // Generate a random order number
    private String generateOrderNumber() {
        Set<String> existingOrderNumbers = loadExistingOrderNumbers();
        String orderNumber;
        do {
            orderNumber = generateRandomOrderNumber();
        } while (existingOrderNumbers.contains(orderNumber));

        return orderNumber;
    }

    private String generateRandomOrderNumber() {
        // Generate a random 5-digit order number
        Random random = new Random();
        int randomNumber = 10000 + random.nextInt(90000);
        return String.valueOf(randomNumber);
    }

    // Check if newly generated orderNum exists
    private Set<String> loadExistingOrderNumbers() {
        Set<String> existingOrderNumbers = new HashSet<>();

        try ( BufferedReader reader = new BufferedReader(new FileReader("order_history.txt"))) {
            String line;
            reader.readLine(); // Skip header row
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                if (record.length == 5) {
                    String orderNumber = record[0];
                    existingOrderNumbers.add(orderNumber);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading existing order numbers from file.");
        }

        return existingOrderNumbers;
    }

    private String getCurrentDate() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        //Format into dd/MM/yyyy
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Format the current date as a string
        String formattedDate = currentDate.format(dateFormat);

        return formattedDate;
    }

    //calcuate total order
    private double calculateOrderTotal() {
        return cartTotal;
    }
}
