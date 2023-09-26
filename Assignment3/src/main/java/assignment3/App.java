package assignment3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private static Scene sceneLogin;
    private static Scene sceneBrowse;
    private static Scene sceneAccount;
    private static Scene sceneCheckout;
    private static Stage mainStage;
    
    private static LoginController loginController;
    private static BrowseController browseController;
    private static AccountController accountController;
    private static CheckoutController checkoutController;
    
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
        
        // Get Controllers from FXMLLoaders
        loginController = loginLoader.getController();
        browseController = browseLoader.getController();
        accountController = accountLoader.getController();
        checkoutController = checkoutLoader.getController();
        
        // Create scenes
        sceneLogin = new Scene(loginLoader.getRoot());
        sceneBrowse = new Scene(browseLoader.getRoot());
        sceneAccount = new Scene(accountLoader.getRoot());
        sceneCheckout = new Scene(checkoutLoader.getRoot());
        
        // Set stage
        mainStage = stage;
        mainStage.setScene(sceneLogin);
        mainStage.setTitle("Application name here... - login");
        mainStage.show();
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
                mainStage.setTitle("Application name here... - loign");
                break;
            case 1:
                mainStage.setScene(sceneBrowse);
                mainStage.setTitle("Application name here... - browse");
                browseController.displayProducts();
                break;
            case 2:
                mainStage.setScene(sceneAccount);
                mainStage.setTitle("Application name here... = account");
                accountController.displayUserDetails();
                break;
            case 3:
                mainStage.setScene(sceneCheckout);
                mainStage.setTitle("Application name here... - checkout");
                checkoutController.displayCart(shoppingCart);
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