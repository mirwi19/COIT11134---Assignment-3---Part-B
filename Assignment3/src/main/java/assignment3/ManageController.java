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
 * FXML Controller class
 *
 * @author Matth
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
    
    public void populateScene() {
        if(checkAdmin()) {
            btnManageProducts.setVisible(true);
        }
        displayProducts(productList);
        clearAlerts();
        clearFields();
    }
    
    private void displayProducts(ArrayList<Product> productList) {
        // Create VBox to stack products
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        
        // Create productTile
        for (int i = 0; i < productList.size(); i+= 2) {
            HBox hBox = new HBox();
            hBox.setSpacing(10);
            
            for (int j = i; j < Math.min(i +2, productList.size()); j++) { // Math.min to prevent index out of bounds
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
    
    public void displayEditProduct(Product productToEdit) {
        Product product = productToEdit;        
        txtProductName.setText(product.getProductName());
        txtProductPrice.setText(String.format("%.2f",product.getPrice()));
        txtProductStock.setText(String.format("%d", product.getStock()));
        txtProductID.setText(product.getProductID());
    }
    
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
        /* 
        Example implementation for furture data validation
        boolean validProductID = false;
        boolean validProductName = false;
        boolean validProductPrice = false;
        boolean validProductStock = false;
        
        String productIDInput = txtProductID.getText().trim();
        String productNameInput = txtProductName.getText().trim();
        String productPriceInput = txtProductPrice.getText().trim();
        String productStockInput = txtProductStock.getText().trim();
        
        
        if (dataValidator.validateProductID(productID)) {
            validProductID = true;
            String validatedID = productIDInput;
            txtProductIDAlert.setVisible(false);
        } else {
            txtProductIDAlert.setVisible(true);
        }
        
        ... other validation checks
        
        if (validProductID && validProductName && validProductPrice && validProductStock) {
            Product productToAdd = new Product(validatedID, validatedName, validatedPrice, validatedStock);
            productHandler.addProduct(productToAdd);
            populateScene(); // Used to "refresh" scene
        */
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        String productID = txtProductID.getText().trim();
        String productName = txtProductName.getText().trim();
        String productPrice = txtProductPrice.getText().trim();
        String productStock = txtProductStock.getText().trim();
        
        // Basic input validation - update when data validation is done...
        if (productID.isBlank()) {
            System.err.println("Invalid product ID.");
            alert.setTitle("ERROR - Invalid Product ID");
            alert.setContentText("Invalid input.\nProduct ID must not be empty AND between 1 - 9999");
            alert.showAndWait();
            
            // Update Alert fields
            clearAlerts();
            txtProductIDAlert.setVisible(true);
            return;
        }
        
        // Basic input validation - update when data validation is done...
        if (productName.isBlank()) {
            System.err.println("Invalid product name.");
            alert.setTitle("ERROR - Invalid Product Name");
            alert.setContentText("Invalid input.\nProduct Name must NOT be empty.");
            alert.showAndWait();
            
            // Update Alert fields
            clearAlerts();
            txtProductNameAlert.setVisible(true);
            return;
        }
        
        // Basic input validation - update when data validation is done...
        if (productPrice.isBlank()) {
            System.err.println("Invalid product price");
            alert.setTitle("ERROR - Invalid Price");
            alert.setContentText("Invalid input.\nProduct Price must NOT be empty.");
            alert.showAndWait();
            
            // Update Alert fields
            clearAlerts();
            txtProductPriceAlert.setVisible(true);
            return;
        }
        
        // Basic input validation - update when data validation is done...
        if (productStock.isBlank()) {
            System.err.println("Invalid product stock");
            alert.setTitle("ERROR - Invalid Stock");
            alert.setContentText("Invalid input.\nProduct Stock must NOT be empty.");
            alert.showAndWait();
            
            // Update Alert fields
            clearAlerts();
            txtProductStockAlert.setVisible(true);
            return;
        }
        
        try {
            Product productToAdd = new Product(productID, productName, Double.parseDouble(productPrice), Integer.parseInt(productStock));
            productHandler.addProduct(productToAdd);
            populateScene(); // Used to "refresh" scene
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    private void clearAlerts() {
        txtProductNameAlert.setVisible(false);
        txtProductPriceAlert.setVisible(false);
        txtProductStockAlert.setVisible(false);
        txtProductIDAlert.setVisible(false);
    }
    
    private void clearFields() {
        txtProductName.setText("");
        txtProductPrice.setText("");
        txtProductStock.setText("");
        txtProductID.setText("");
        txtSearchField.setText("");
    }
    
    // Move this method to AccountHandler???
    private boolean checkAdmin() {
        // TODO: Implement isAdmin check on currentUser when isAdmin is implemented
        // E.g., accountHandler.getCurrentUser().isAdmin();
        return false;
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
