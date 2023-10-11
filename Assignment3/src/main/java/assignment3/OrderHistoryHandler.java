package assignment3;

import assignment3.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * This class handles the loading and saving of order history data to/from a
 * text file. It provides methods to load order history from a text file and
 * save order history to a text file. Additionally, it ensures the existence of
 * the order history file.
 *
 * @author Matthew Hay
 * @author Matthew Irwin
 * @author Matthew Wallis
 */
public class OrderHistoryHandler {

    public static ObservableList<Order> loadOrderHistoryFromTextFile() {
        ObservableList<Order> observableOrderHistory = FXCollections.observableArrayList();

        ensureOrderHistoryFileExists();

        try ( BufferedReader reader = new BufferedReader(new FileReader("order_history.txt"))) {
            String line; // Read each line of the file

            // Skip the header row
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");

                //check record length
                if (record.length == 6) {
                    String orderNum = record[0];
                    String date = record[1];
                    String shippedTo = record[2];

                    double orderTotal;
                    try {
                        orderTotal = Double.parseDouble(record[3]);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing order total. Invalid format.");
                        continue;
                    }
                    String status = record[4];
                    String userEmail = record[5];

                    // Retrieves logged in email
                    LoginController loginController = App.getLoginController();
                    String LoggedInEmail = loginController.getLoggedEmail();

                    // Retrieves orders which match the user's email.
                    if (userEmail.equals(LoggedInEmail)) {
                        System.out.println("Loaded Order: " + orderNum); // Debug print
                        Order order = new Order(orderNum, date, shippedTo, orderTotal, status, userEmail);
                        observableOrderHistory.add(order);
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading order history from file.");
        }

        return observableOrderHistory;
    }

    // Method to save order history to file
    public static void saveOrderHistoryToTextFile(ArrayList<Order> orderHistory) {
        try ( PrintWriter writer = new PrintWriter(new FileWriter("order_history.txt", true))) {

            // Write each order record
            for (Order order : orderHistory) {
                String orderNum = order.getOrderNum();
                String date = order.getDate();
                String shippedTo = order.getShippedTo();
                double orderTotal = order.getOrderTotal();
                String status = order.getStatus();
                String userEmail = order.getUserEmail();

                // Format and write the order record
                String record = String.format("%s,%s,%s,%.2f,%s,%s", orderNum, date, shippedTo, orderTotal, status, userEmail);
                writer.println(record);

                System.out.println("Saved Order: " + orderNum); // Debug print
            }

            System.out.println("Order history saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving order history to file.");
        }
    }

    // Method to ensure the orderHistory file exists
    private static void ensureOrderHistoryFileExists() {
        try {
            File file = new File("order_history.txt");
            if (!file.exists()) {
                // Create the file if it doesn't exist
                if (file.createNewFile()) {
                    System.out.println("Order history file created.");
                } else {
                    System.err.println("Error creating order history file.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error checking/creating order history file.");
        }
    }
}
