import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
public class WarehouseManagementSystem implements Serializable {
    private static final long serialVersionUID = 4L;
    private List<Warehouse> warehouseList;
    private List<Client> clientList;
    private int systemID;
    private LinkedList<String> receipts = new LinkedList<>();

    public WarehouseManagementSystem() {
        this.warehouseList = new ArrayList<>();
        this.clientList = new ArrayList<>();
    }

    public boolean addProduct(Warehouse warehouse, Product product) {
        return warehouse.addProduct(product);
    }

    public boolean addClient(Client client) {
        return clientList.add(client);
    }

    public boolean addWarehouse(Warehouse warehouse) {
        return warehouseList.add(warehouse);
    }

    public boolean removeClient(int clientID) {
        return clientList.removeIf(client -> client.getClientID() == clientID);
    }

    public boolean removeProduct(int productID) {
        for (Warehouse warehouse : warehouseList) {
            if (warehouse.removeProduct(productID)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeWarehouse(int warehouseID) {
        return warehouseList.removeIf(warehouse -> warehouse.getID() == warehouseID);
    }

    public boolean addToWishlist(Client client, int productID, int quantity) {
        return client.addProductToWishlist(productID, quantity);
    }
    
    public List<Client> getAllClients() {
        return clientList;
    }

   public Iterator<Product> getAllProducts(Warehouse warehouse) {
    return warehouse.getProductList(); // Return the iterator of products
    }

    public Product getProductById(int productID) {
        for (Warehouse warehouse : warehouseList) {
            Product product = warehouse.getProductById(productID);
            if (product != null) {
                return product;
            }
        }
        return null;
    }


    public List<Warehouse> getAllWarehouses() {
        return warehouseList;
    }

    public int getSystemID() {
        return systemID;
    }

    public void setSystemID(int systemID) {
        this.systemID = systemID;
    }


    // TERMINAL UI STUFF
    public void run() {
        Scanner scanner = new Scanner(System.in);
        BlockingQueue<Integer> inputQueue = InputBridge.inputQueue;
        BlockingQueue<String> inputQueueS = InputBridgeS.inputQueue;
        Warehouse warehouse = new Warehouse("temp1", "temp2");
        warehouse.setID(0);
        addWarehouse(warehouse);
        int idw = 1;
        while (true) {
            try {
                /* System.out.println("Warehouse Management System");
                System.out.println("1. Add Client");
                System.out.println("2. Add Warehouse");
                System.out.println("3. Add Product to Warehouse");
                System.out.println("4. Remove Client");
                System.out.println("5. Remove Product from Warehouse");
                System.out.println("6. Remove Warehouse");
                System.out.println("7. View All Clients");
                System.out.println("14. View Clients wishlist");
                System.out.println("15. Buy Clients wishlist");
                System.out.println("13. View all Products in Warehouse");
                System.out.println("16. View product waitlist");
                System.out.println("8. View All Warehouses");
                System.out.println("9. Add Product to Client Wishlist");
                System.out.println("11. Add multiple defualt clients");
                System.out.println("17. Add payment to client");
                System.out.println("18. Add Shipment of Product");
                System.out.println("19: View all Orders/Invoices for a Client");
                System.out.println("10. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline*/ 
                int choice = inputQueue.take();
                switch (choice) {
                    case 1:
                        String clientName = inputQueueS.take();
                        int clientID = inputQueue.take();
                        String clientAddress = inputQueueS.take();
                        String clientContact = inputQueueS.take();
                        Client client = new Client(clientID, clientName, clientAddress, clientContact);
                        addClient(client);
                        break;
        
                    case 2:
                        System.out.print("Enter warehouse location: ");
                        String location = scanner.nextLine();
                        System.out.print("Enter warehouse address: ");
                        String address = scanner.nextLine();
                        Warehouse warehouse2 = new Warehouse(location, address);
                        addWarehouse(warehouse2);
                        warehouse2.setID(idw+1);
                        idw = idw + 1;
                        System.out.println("Warehouse added.");
                        break;
        
                    case 3:
                        int wID = 0;
                        Warehouse wh = getWarehouseById(wID);
                        if (wh != null) {
                            int productID = inputQueue.take();  // Read and parse the product ID
                            String productName = inputQueueS.take();  // Now properly moves to product name input
                            String productDescription = inputQueueS.take();
                            double productPrice = Double.parseDouble(inputQueueS.take()); // Parse price as double
                            int stockQuantity = inputQueue.take(); // Parse quantity as integer
                            Product product = new Product(productID, productName, productDescription, productPrice, stockQuantity);
                            addProduct(wh, product);
                        } else {
                            System.out.println("Warehouse not found.");
                        }
                        break;
        
                    case 4:
                        System.out.print("Enter client ID to remove: ");
                        int cID = scanner.nextInt();
                        if (removeClient(cID)) {
                            System.out.println("Client removed.");
                        } else {
                            System.out.println("Client not found.");
                        }
                        break;
        
                    case 5:
                        System.out.print("Enter product ID to remove: ");
                        int pID = scanner.nextInt();
                        if (removeProduct(pID)) {
                            System.out.println("Product removed.");
                        } else {
                            System.out.println("Product not found.");
                        }
                        break;
        
                    case 6:
                        System.out.print("Enter warehouse ID to remove: ");
                        int whID = scanner.nextInt();
                        if (removeWarehouse(whID)) {
                            System.out.println("Warehouse removed.");
                        } else {
                            System.out.println("Warehouse not found.");
                        }
                        break;
        
                    case 7:
                        System.out.println("All Clients:");
                        for (Client c : getAllClients()) {
                            /*try{
                                InputBridgeS.inputQueue.put(c.toString());
                            }catch(InterruptedException ex) {
                                ex.printStackTrace();
                            }*/
                            System.out.println(c.toString());
                        }
                        break;
        
                    case 8:
                        System.out.println("All Warehouses:");
                        for (Warehouse w : getAllWarehouses()) {
                            System.out.println("Warehouse Location: " + w.getLocation() + ", Address: " + w.getAddress());
                        }
                        break;
        
                    case 9:
                        int clientWishlistID = inputQueue.take();
                        Client wishlistClient = getClientById(clientWishlistID);
                        if (wishlistClient != null) {
                            int prodID = inputQueue.take();
                            int quantity = inputQueue.take();
                            Product wishlistProduct = getProductById(prodID);
                            if (wishlistProduct != null) {
                                addToWishlist(wishlistClient, prodID, quantity);
                            } else {
                                System.out.println("Product not found.");
                            }
                        } else {
                            System.out.println("Client not found.");
                        }
                        break;
        
                    case 10:
                        System.out.println("Exiting system.");
                        scanner.close();
                        return;

                    case 11:
                        System.out.print("Enter the number of default clients to add: ");
                        int numClients = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        for (int i = 1; i <= numClients; i++) {
                            int defaultID = clientList.size() + i; // Unique client ID
                            String defaultName = "C" + i;          // Client name as C1, C2, ...
                            String defaultAddress = "Address_" + i;
                            String defaultContact = "Contact_" + i;
                            Client defaultClient = new Client(defaultID, defaultName, defaultAddress, defaultContact);
                            addClient(defaultClient);
                        }
                        System.out.println(numClients + " default clients added.");
                        break;
                    
                    case 12: // New case for printing all products in a warehouse
                        System.out.print("Enter warehouse ID to view products: ");
                        int warehouseID = scanner.nextInt();
                        Warehouse warehouse3 = getWarehouseById(warehouseID);
                        if (warehouse3 != null) {
                            System.out.println("Products in Warehouse " + warehouseID + ":");
                            warehouse3.displayAllProduct();
                        } else {
                            System.out.println("Warehouse not found.");
                        }
                        break;
                        
                    case 13: {
                        int warehouseID4 = 0;
                        
                        Warehouse warehouseT = getWarehouseById(warehouseID4); // Assumes you have a method to find warehouse by ID
                        if (warehouseT != null) {
                            System.out.println("Products in Warehouse " + warehouseID4 + ":");
                            warehouseT.displayAllProduct();
                        } else {
                            System.out.println("Warehouse with ID " + warehouseID4 + " not found.");
                        }
                        break;
                    }

                    case 16: {
                        int warehouseID4 = 0;           
                        Warehouse warehouseT = getWarehouseById(warehouseID4); // Assumes you have a method to find warehouse by ID
                        if (warehouseT != null) {
                            int productID = inputQueue.take();
                            warehouseT.getProductById(productID).printWaitlist();;
                        } else {
                            System.out.println("Warehouse with ID " + warehouseID4 + " not found.");
                        }
                        break;
                    }

                    case 17: { // New case for adding payment
                        int paymentClientID = inputQueue.take();
                        System.out.print("Enter payment amount: ");
                        double paymentAmount = Double.parseDouble(inputQueueS.take());
    
                        Client chargeClient = getClientById(paymentClientID);

                        if(chargeClient.getCreditAccount() > 0 && chargeClient.getCreditAccount() >= paymentAmount){
                            chargeClient.setCreditAccount(chargeClient.getCreditAccount() - paymentAmount);
                        }else if(chargeClient.getCreditAccount() > 0 && chargeClient.getCreditAccount() < paymentAmount){
                            paymentAmount = paymentAmount - chargeClient.getCreditAccount();
                            chargeClient.setCreditAccount(0);
                            chargeClient.setDebitAccount(paymentAmount + chargeClient.getDebitAccount());
                        }else{
                            chargeClient.setDebitAccount(paymentAmount + chargeClient.getDebitAccount());
                        }
                        receipts.add("Payment of $" + paymentAmount + " sent to Client: " + chargeClient.getName());
                        break;
                    }

                    case 14:{
                        int c1ID = inputQueue.take();
                        Client printClient = getClientById(c1ID);
                        if (printClient == null) {
                            System.out.println("Client not found.");
                        }else{
                            printClient.printWishlist();
                        }
                        break;

                    }

                    case 15:{
                        int clientOrderID = inputQueue.take();
                        Client orderClient = getClientById(clientOrderID);
                        if(orderClient == null){
                            System.out.println("Client not found.");
                            break;
                        }else{
                            double totalPrice = 0;
                            // Get wishlist items
                            HashMap<Integer, Integer> wishlistItems = orderClient.getWishlist().getWishlistItems();
                            HashMap<Integer, Integer> wishlistItemsCopy = new HashMap<>(wishlistItems);
                            //make and add the order
                            Order order1 = new Order(orderClient.getOrders().size() + 1, clientOrderID, wishlistItemsCopy);
                            orderClient.createOrder(order1);


                            //reduce stock, change account balances, and update waitlist if needed
                            for (Map.Entry<Integer, Integer> entry : wishlistItems.entrySet()) {
                                System.out.print("enter the loop\n");
                                int productID = entry.getKey(); // Get the product ID
                                int quantity = entry.getValue(); // Get the quantity
        
                                Product product = warehouse.getProductById(productID); // Method to retrieve the Product by ID
                                

                                if (product != null) {
                                    // Attempt to reduce the stock of the product
                                    boolean success = product.reduceStock(quantity);
                                    
                                    
                                    if (success) {
                                        System.out.println("Reduced stock for Product ID " + productID + " by " + quantity + ".");
                                        totalPrice = totalPrice + (product.getPrice() * quantity);
                                    } else {
                                        System.out.println("Failed to reduce stock for Product ID " + productID + ". Not enough stock.");
                                        product.addToWaitlist(orderClient, quantity);
                                        totalPrice = totalPrice + (product.getPrice() * quantity);
                                    }
                                } else {
                                    System.out.println("Product ID " + productID + " not found.");
                                }
                            }
                            if(orderClient.getDebitAccount() >= totalPrice){
                                orderClient.setDebitAccount(orderClient.getDebitAccount() - totalPrice);

                            }else{
                                totalPrice = totalPrice - orderClient.getDebitAccount();
                                orderClient.setDebitAccount(0.0);
                                orderClient.setCreditAccount(totalPrice + orderClient.getCreditAccount());
                            }
                        }
                        orderClient.clearWishlist();
                        
                        break;
                    }

                    case 18: {
                        int shipmentID = inputQueue.take();
                        Product shipmentProduct = getProductById(shipmentID);
                        
                        if (shipmentProduct != null) { // Check if the product exists
                            int amount = inputQueue.take();
                            
                            // Update stock first
                            shipmentProduct.updateStock(amount);
                            
                            // Check the waitlist after updating stock
                            if (!shipmentProduct.getWaitlist().isEmpty()) { // Ensure the waitlist is not empty
                                System.out.println("Checking waitlist for product: " + shipmentProduct.getProductName());
                                Iterator<Waitlist> iterator = shipmentProduct.getWaitlist().iterator();
                                while (iterator.hasNext()) {
                                    Waitlist entry = iterator.next();
                                    int waitlistQuantity = entry.getQuantity();
                                    
                                    // Compare shipment amount with waitlist entry
                                    if (shipmentProduct.getStockQuantity() >= waitlistQuantity) {
                                        System.out.println("Can fulfill waitlist for Client ID: " + entry.getClientID() +
                                                        ", Quantity: " + waitlistQuantity);
                                        shipmentProduct.reduceStock(waitlistQuantity); // Decrease the remaining amount
                                        iterator.remove(); // Remove the entry from the waitlist
                                    } else {
                                        System.out.println("Not enough stock to fulfill waitlist for Client ID: " + entry.getClientID() +
                                                        ", Requested: " + waitlistQuantity + ", Available: " + amount);
                                    }
                                }
                            }
                        } else {
                            System.out.println("Product not found.");
                        }
                        break;
                    }

                    case 19: {
                        int clientID2 = inputQueue.take();
                        Client clientO = getClientById(clientID2);
                        
                        if (clientO != null) { // Check if the client exists
                            clientO.viewOrders(); // Use the viewOrders method in Client class
                        } else {
                            System.out.println("Client not found.");
                        }
                        break;
                    }

                    case 20: {

                        System.out.println("All Clients:");
                        for (Client c : getAllClients()) {
                            if (c.getCreditAccount() > 0) {
                                System.out.println(c); 
                            }
                        }
                        break;
                    }
                    
                    
        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InterruptedException e) {
            // Handle interruption
            System.err.println("The program was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Re-interrupt the thread
            }
        }
    }
    
    // Helper methods to find warehouse and client by ID
    public Warehouse getWarehouseById(int warehouseID) {
        for (Warehouse warehouse : warehouseList) {
            if (warehouse.getID() == warehouseID) {
                return warehouse;
            }
        }
        return null;
    }
    
    public Client getClientById(int clientID) {
        for (Client client : clientList) {
            if (client.getClientID() == clientID) {
                return client;
            }
        }
        return null;
    }
}

