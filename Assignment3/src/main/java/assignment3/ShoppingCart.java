package assignment3;

import java.util.ArrayList;

/**
 * This class represents a shopping cart that allows users to add, remove, and
 * update products. It keeps track of the products in the cart and their
 * respective quantities.
 *
 * @author Matthew Hay
 * @author Matthew Irwin
 * @author Matthew Wallis
 */
public class ShoppingCart {

    private ArrayList<Product> cartProducts;
    private ArrayList<Integer> cartProductQty;

    public ShoppingCart() {
        cartProducts = new ArrayList<>();
        cartProductQty = new ArrayList<>();
    }

    // Add a product to the cart
    public void addToCart(Product productToAdd, int qty) {
        // Check if product is in cart
        for (int i = 0; i < cartProducts.size(); i++) {
            if (cartProducts.get(i).getProductID().equals(productToAdd.getProductID())) {
                // If product in cart, update quantity
                cartProductQty.set(i, cartProductQty.get(i) + qty);
                // Debug: Printing change to cart
                System.out.println("Prod updated: " + cartProducts.get(i).getProductID() + ", Qty: " + cartProductQty.get(i));
                return;
            }
        }

        // Product not found in cart, add new entry
        cartProducts.add(productToAdd);
        cartProductQty.add(qty);
        // Debug: Printing new cart entry
        System.out.println("Prod added: " + productToAdd.getProductID() + ", Qty: " + qty);
    }

    // Remove a product from the cart
    public void removeFromCart(Product productToRemove) {
        // Find product index
        for (int i = 0; i < cartProducts.size(); i++) {
            if (cartProducts.get(i).getProductID().equals(productToRemove.getProductID())) {
                // Remove index in cart
                cartProducts.remove(i);
                // Remove index in qty
                cartProductQty.remove(i);
                return; // Product found and removed
            } else {
                System.err.println("Product not found"); // Shouldn't be reachable...
            }
        }
    }

    // Update quantity of current product in cart
    public void updateQuantity(Product productToUpdate, int qtyToSet) {
        for (int i = 0; i < cartProducts.size(); i++) {
            if (cartProducts.get(i).getProductID().equals(productToUpdate.getProductID())) {
                // Update quantity
                cartProductQty.set(i, qtyToSet);
                return; // Product updated
            } else {
                System.err.println("Product no found"); // Shouldn't be reachable...
            }
        }
    }

    public ArrayList<Product> getCartProducts() {
        return cartProducts;
    }

    public ArrayList<Integer> getCartProductQty() {
        return cartProductQty;
    }

    //Method to clear shopping cart
    public void clearCart() {
        cartProducts.clear();
        cartProductQty.clear();
    }
}
