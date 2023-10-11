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
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller class for browsing and displaying products
 *
 * This class handles product display, search functionality, and scene
 * navigation. It interacts with the `ProductHandler` to retrieve the list of
 * products and provides methods for populating the product display and
 * performing searches.
 *
 * @author Matthew Hay
 * @author Matthew Irwin
 * @author Matthew Wallis
 */
public class BrowseController implements Initializable {

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
    private ScrollPane paneProdDisplay;
    @FXML
    private TextField txtSearchField;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnManageProducts;
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

        // Check if current user is an admin
        if (accountHandler.getCurrentUser() != null) {
            btnManageProducts.setVisible(accountHandler.getCurrentUser().getIsAdmin()); // Display/Hide manage products based on isAdmin
        }
    }

    // Populate product display
    private void displayProducts(ArrayList<Product> productList) {
        // Creat VBox to stack products
        VBox vBox = new VBox();
        vBox.setSpacing(10);

        // Create productTile
        for (int i = 0; i < productList.size(); i += 4) {
            HBox hBox = new HBox();
            hBox.setSpacing(10);

            for (int j = i; j < Math.min(i + 4, productList.size()); j++) { // Main.min to prevent index out of bounds
                Product product = productList.get(j);

                try {
                    // Create FXMLLoader for product
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("productTile.fxml"));

                    // Load productTile into AnchorPane
                    AnchorPane productTilePane = loader.load();

                    // Get Controller
                    ProductTileController productTileController = loader.getController();

                    // Set product details
                    productTileController.setProduct(product);

                    // Add productTile to HBox
                    hBox.getChildren().add(productTilePane);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
            // Add HBox to VBox
            vBox.getChildren().add(hBox);
        }
        // Add VBox to scene
        paneProdDisplay.setContent(vBox);
    }

    // Search productList and return products based on search input
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
