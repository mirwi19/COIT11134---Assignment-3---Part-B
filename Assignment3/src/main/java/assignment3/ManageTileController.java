package assignment3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * Controller class for managing product tiles and user interactions related to
 * individual product items
 *
 * This controller handles the display of product details within a tile,
 * including name, price, ID, and stock. It provides functionality to edit and
 * delete individual products, communicating with the `ProductHandler` and the
 * parent `ManageController`.
 *
 * @author Matthew Hay
 * @author Matthew Irwin
 * @author Matthew Wallis
 */
public class ManageTileController implements Initializable {

    private ProductHandler productHandler;
    private ManageController manageController;

    private Product product;

    @FXML
    private Text txtPrice;
    @FXML
    private Text txtName;
    @FXML
    private Text txtID;
    @FXML
    private Text txtStock;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productHandler = App.getProductHandler();
        manageController = App.getManageController();
    }

    // Populate tile with product details
    public void setProduct(Product productToSet) {
        this.product = productToSet;
        this.txtName.setText(productToSet.getProductName());
        this.txtPrice.setText(String.format("$%.2f", productToSet.getPrice()));
        this.txtID.setText(productToSet.getProductID());
        this.txtStock.setText(String.format("%d", productToSet.getStock()));
    }

    @FXML
    private void editAction(ActionEvent event) {
        manageController.displayEditProduct(product);
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        productHandler.removeProduct(product);
        manageController.populateScene();
    }

}
