/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package assignment3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

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
        productList.add(productToAdd);
    }
    
    public void removeProduct() {
        
    }
    
    public void saveProductData() {
        
    }
}
