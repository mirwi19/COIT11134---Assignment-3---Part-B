package assignment3;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The main class of the application
 *
 * This class serves as the entry point for the application and manages various
 * scenes such as login, browsing, account management, checkout, and product
 * management. It initializes controllers, handlers, and the shopping cart.
 *
 * @author Matthew Hay
 * @author Matthew Irwin
 * @author Matthew Wallis
 */
public class App extends Application {

    private static Scene sceneLogin;
    private static Scene sceneBrowse;
    private static Scene sceneAccount;
    private static Scene sceneCheckout;
    private static Scene sceneManage;
    private static Stage mainStage;

    private static LoginController loginController;
    private static BrowseController browseController;
    private static AccountController accountController;
    private static CheckoutController checkoutController;
    private static ManageController manageController;

    private static AccountHandler accountHandler;
    private static ProductHandler productHandler;

    private static ShoppingCart shoppingCart;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Initialise handlers
        accountHandler = new AccountHandler("accountList");
        productHandler = new ProductHandler("productList");
        shoppingCart = new ShoppingCart();

        // Load FXML files and their Controllers
        FXMLLoader loginLoader = loadFXML("login");
        FXMLLoader browseLoader = loadFXML("browse");
        FXMLLoader accountLoader = loadFXML("account");
        FXMLLoader checkoutLoader = loadFXML("checkout");
        FXMLLoader manageLoader = loadFXML("manage");

        // Get Controllers from FXMLLoaders
        loginController = loginLoader.getController();
        browseController = browseLoader.getController();
        accountController = accountLoader.getController();
        checkoutController = checkoutLoader.getController();
        manageController = manageLoader.getController();

        // Create scenes
        sceneLogin = new Scene(loginLoader.getRoot());
        sceneBrowse = new Scene(browseLoader.getRoot());
        sceneAccount = new Scene(accountLoader.getRoot());
        sceneCheckout = new Scene(checkoutLoader.getRoot());
        sceneManage = new Scene(manageLoader.getRoot());

        // Set stage
        mainStage = stage;
        mainStage.setScene(sceneLogin);
        mainStage.setTitle("E-commerce App - Login");
        mainStage.show();

        // Save data on close
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("Application closed, saving data...");
                accountHandler.saveUserData();
            }
        });
    }

    public FXMLLoader loadFXML(String fileName) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName + ".fxml"));
        loader.load();
        return loader;
    }

    public static void changeScene(int sc) {
        switch (sc) {
            case 0:
                mainStage.setScene(sceneLogin);
                mainStage.setTitle("E-commerce App - Loign");
                break;
            case 1:
                mainStage.setScene(sceneBrowse);
                mainStage.setTitle("E-commerce App - Browse");
                browseController.populateScene();
                break;
            case 2:
                mainStage.setScene(sceneAccount);
                mainStage.setTitle("E-commerce App - Manage Account");
                accountController.populateScene();
                break;
            case 3:
                mainStage.setScene(sceneCheckout);
                mainStage.setTitle("E-commerce App - Checkout");
                checkoutController.populateScene(shoppingCart);
                break;
            case 4:
                mainStage.setScene(sceneManage);
                mainStage.setTitle("E-commerece App - Manage Products");
                manageController.populateScene();
                break;
        }
    }

    public static LoginController getLoginController() {
        return loginController;
    }

    public static BrowseController getBrowseController() {
        return browseController;
    }

    public static AccountController getAccountController() {
        return accountController;
    }

    public static CheckoutController getCheckoutController() {
        return checkoutController;
    }

    public static ManageController getManageController() {
        return manageController;
    }

    public static AccountHandler getAccountHandler() {
        return accountHandler;
    }

    public static ProductHandler getProductHandler() {
        return productHandler;
    }

    public static ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
