/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package assignment3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Matthew Irwin
 */
public class ProductHandler {
    private String productListFile;
    private ArrayList<Product> productList;
    
    public ProductHandler(String fileName) {
        this.productListFile = fileName + ".txt";
        this.productList = new ArrayList<>();
        
        readProductData();
    }
    
    public void readProductData() {
        Scanner dataInput = null;
        
        try {
            dataInput = new Scanner(new FileReader(productListFile));
            String dataEntry;
            
            while (dataInput.hasNextLine()) {
                dataEntry = dataInput.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(dataEntry, ",");
                
                // Tokenize product
                if (tokenizer.hasMoreTokens()) {
                    String prodID = tokenizer.nextToken().trim();
                    String productName = tokenizer.nextToken().trim();
                    double price = Double.parseDouble(tokenizer.nextToken().trim());
                    int stock = Integer.parseInt(tokenizer.nextToken().trim());
                    
                    // Add product to productList
                    Product currentProduct = new Product(prodID, productName, price, stock);
                    // Debug: printing product
                    System.out.println("Product added: " + currentProduct.getProductID() + ", " + currentProduct.getProductName() + ", " + String.format("$%.2f", currentProduct.getPrice()) + ", " + currentProduct.getStock());
                    addProduct(currentProduct);
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } finally {
            if (dataInput != null) {
                dataInput.close();
            }
        }
    }
    
    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product productToAdd) {
        boolean productExists = false; // Flag for tracking if product exists
        Product existingProduct = null;

        for (int i = 0; i < productList.size(); i++) {
            if (productToAdd.getProductID().equals(productList.get(i).getProductID())) {
                productExists = true;
                existingProduct = productList.get(i);
                break; // Existing product found, no need to compare more
            }
        }

        if (productExists) {
            // Creating custom alert to "Confirm" an update
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Existing Product Found");
            alert.setContentText("Existing Product Found!\nDo you wish to update the following produt:\nProduct Name: " + existingProduct.getProductName() + "\nProduct Price: $" + String.format("%.2f", existingProduct.getPrice()) + "\nProduct Stock: " + existingProduct.getStock());
            
            // Creating buttons
            ButtonType updateButton = new ButtonType("Update", ButtonData.YES);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonData.NO);            
            alert.getButtonTypes().setAll(updateButton, cancelButton);      
            
            // Check response
            Optional<ButtonType> result = alert.showAndWait();            
            if (result.isPresent() && result.get() == updateButton) {              
                existingProduct.setProductName(productToAdd.getProductName());
                existingProduct.setPrice(productToAdd.getPrice());
                existingProduct.setStock(productToAdd.getStock());
            }            
        } else {
            productList.add(productToAdd);
        }
    }    
    
    public void removeProduct(Product productToRemove) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete: \"" + productToRemove.getProductName() + "\"?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    if (productList.remove(productToRemove)) {
                        System.out.println("Product removed.");
                    } else {
                        System.err.println("Product not found in list.");
                    }
                } catch (NoSuchElementException ex) {
                    System.err.println(ex);
                }
            }
        });
    }
    
    public void saveProductData() {
        
    }
}
