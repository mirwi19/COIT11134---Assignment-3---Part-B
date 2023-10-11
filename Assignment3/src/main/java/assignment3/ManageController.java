package assignment3;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller class for managing products and user interactions related to
 * product management
 *
 * This controller handles the management of products, including adding and
 * searching for products. It also provides functionality to edit and display
 * product details, and it communicates with the `ProductHandler` to interact
 * with the product data.
 *
 * @author Matthew Hay
 * @author Matthew Irwin
 * @author Matthew Wallis
 */
public class ManageController implements Initializable {

    private AccountHandler accountHandler;
    private ProductHandler productHandler;
    private ArrayList<Product> productList;

    @FXML
    private Button btnBrowseProds;
    @FXML
    private MenuItem btnEditAccount;
    @FXML
    private MenuItem btnLogout;
    @FXML
    private TextField txtProductName;
    @FXML
    private TextField txtProductPrice;
    @FXML
    private TextField txtProductStock;
    @FXML
    private TextField txtProductID;
    @FXML
    private Button btnAutoID;
    @FXML
    private Button btnAddProduct;
    @FXML
    private ScrollPane paneManageProdDisplay;
    @FXML
    private Label txtProductNameAlert;
    @FXML
    private Label txtProductPriceAlert;
    @FXML
    private Label txtProductStockAlert;
    @FXML
    private Label txtProductIDAlert;
    @FXML
    private Button btnManageProducts;
    @FXML
    private TextField txtSearchField;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnCart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountHandler = App.getAccountHandler();
        productHandler = App.getProductHandler();
        productList = productHandler.getProductList();
    }

    // To be called when switching scenes to populate fields
    public void populateScene() {
        displayProducts(productList);
        clearAlerts();
        clearFields();
    }

    // Populate product display with existing products
    private void displayProducts(ArrayList<Product> productList) {
        // Create VBox to stack products
        VBox vBox = new VBox();
        vBox.setSpacing(10);

        // Create productTile
        for (int i = 0; i < productList.size(); i += 2) {
            HBox hBox = new HBox();
            hBox.setSpacing(10);

            for (int j = i; j < Math.min(i + 2, productList.size()); j++) { // Math.min to prevent index out of bounds
                Product product = productList.get(j);

                // Create productTile
                try {
                    // Create FXMLLoader
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("manageTile.fxml"));

                    // Load tile into AnchorPane
                    AnchorPane productTilePane = loader.load();

                    // Get Controller
                    ManageTileController manageTileController = loader.getController();

                    // Set Product details
                    manageTileController.setProduct(product);

                    // Add productTilePane to VBox
                    hBox.getChildren().add(productTilePane);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
            // Add HBox to VBox
            vBox.getChildren().add(hBox);
        }
        // Add VBox to scene
        paneManageProdDisplay.setContent(vBox);
    }

    // Populate fields with product data to be edited
    public void displayEditProduct(Product productToEdit) {
        Product product = productToEdit;
        txtProductName.setText(product.getProductName());
        txtProductPrice.setText(String.format("%.2f", product.getPrice()));
        txtProductStock.setText(String.format("%d", product.getStock()));
        txtProductID.setText(product.getProductID());
    }

    // Search existing product list returning products based on search input
    private ArrayList<Product> searchProducts(String searchString) {
        ArrayList<Product> searchResults = new ArrayList<>();

        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(searchString.toLowerCase())) {
                searchResults.add(product);
            }
        }

        if (searchString.isBlank()) {
            return productList;
        } else {
            return searchResults;
        }
    }

    @FXML
    private void searchAction(ActionEvent event) {
        String searchString = txtSearchField.getText().trim();
        displayProducts(searchProducts(searchString));
    }

    @FXML
    private void autoIDAction(ActionEvent event) {
        int nextID = 1; // Lowest possible productID

        // Begin searching for unused ID
        while (nextID <= 9999) {
            boolean isIDUsed = false; // Flag to track if ID is used
            for (Product product : productList) {
                if (Integer.parseInt(product.getProductID()) == nextID) {
                    isIDUsed = true; // ID is used, set flag true
                    break;
                }
            }
            // If nextID is not used, set ID field to nextID
            if (!isIDUsed) {
                txtProductID.setText(String.format("%04d", nextID));
                break;
            }
            nextID++;
        }
    }

    @FXML
    private void addProductAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        String productIDInput = txtProductID.getText().trim();
        int validProductID = 0;
        boolean validID = false;
        String productNameInput = txtProductName.getText().trim();
        boolean validName = false;
        String productPriceInput = txtProductPrice.getText().trim();
        double validProductPrice = 0;
        boolean validPrice = false;
        String productStockInput = txtProductStock.getText().trim();
        int validProductStock = 0;
        boolean validStock = false;
        boolean validProduct = true;

        // Validating Product ID
        if (!productIDInput.isEmpty() && productIDInput.length() == 4) {
            try {
                validProductID = Integer.parseInt(productIDInput);
                if (validProductID >= 1 && validProductID <= 9999) {
                    validID = true;
                    txtProductIDAlert.setVisible(false);
                }
            } catch (NumberFormatException ex) {
                // alert invalid number
                System.out.println("ID: Invalid number");
                txtProductIDAlert.setVisible(true);
            }
        } else {
            // alert invalid ID
            txtProductIDAlert.setVisible(true);
        }

        // Validating Product Name
        if (!productNameInput.isEmpty()) {
            validName = true;
            txtProductNameAlert.setVisible(false);
        } else {
            // alert invalid name
            System.out.println("Name empty");
            txtProductNameAlert.setVisible(true);
        }

        // Validating Product Price
        if (!productPriceInput.isEmpty()) {
            try {
                validProductPrice = Double.parseDouble(productPriceInput);
                if (validProductPrice > 0) {
                    validPrice = true;
                    txtProductPriceAlert.setVisible(false);
                } else {
                    // alert invalid price
                    System.out.println("Negative Price");
                    txtProductPriceAlert.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                // alert invalid number
                System.out.println("Price: Invalid number");
                txtProductPriceAlert.setVisible(true);
            }
        } else {
            // alert invalid price
            System.out.println("Invalid Price");
            txtProductPriceAlert.setVisible(true);
        }

        // Validating Product Stock 
        if (!productStockInput.isEmpty()) {
            try {
                validProductStock = Integer.parseInt(productStockInput);
                if (validProductStock > 0) {
                    validStock = true;
                    txtProductStockAlert.setVisible(false);
                } else {
                    // alert invalid sotck
                    System.out.println("Negative Stock");
                    txtProductStockAlert.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                // alert invalid number
                System.out.println("Stock: Invalid number");
                txtProductStockAlert.setVisible(true);
            }
        } else {
            // alert invalid sotck
            System.out.println("Invalid Stock");
            txtProductStockAlert.setVisible(true);
        }

        if (!validID || !validName || !validPrice || !validStock) {
            validProduct = false;
        }

        if (validProduct) {
            // Clear input fields
            clearFields();
            // Create new Product object with valid data and add to productList
            Product productToAdd = new Product(productIDInput, productNameInput, Double.parseDouble(productPriceInput), Integer.parseInt(productStockInput));
            productHandler.addProduct(productToAdd);
            populateScene(); // Called to "refresh" scene
            productHandler.saveProductData(); // Store product to file
        }

    }

    // Clear all triggered alerts
    private void clearAlerts() {
        txtProductNameAlert.setVisible(false);
        txtProductPriceAlert.setVisible(false);
        txtProductStockAlert.setVisible(false);
        txtProductIDAlert.setVisible(false);
    }

    // Clear all input fields
    private void clearFields() {
        txtProductName.setText("");
        txtProductPrice.setText("");
        txtProductStock.setText("");
        txtProductID.setText("");
        txtSearchField.setText("");
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

}
