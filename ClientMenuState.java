import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ClientMenuState extends WarehouseState {
    private static ClientMenuState instance;
    public int ID;
    public String temp = "testing";
    private ClientMenuState() {}
   

    public static ClientMenuState instance(WarehouseContext context, int clientID) {
        if (instance == null) {
            instance = new ClientMenuState();
            setContext(context);
        }
        instance.setClientID(clientID);  // Set the clientID when the instance is created
        return instance;
    }
    public void setClientID(int clientID) {
        this.ID = clientID;
    }
    @Override
    public void run() {
        JFrame frame = context.getFrame();
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(6, 1));

        JLabel label = new JLabel("Client Menu", JLabel.CENTER);
        frame.add(label);

        JButton viewDetailsButton = new JButton("View Client Details");
        JButton viewProductsButton = new JButton("View Products");
        JButton viewOrdersButton = new JButton("View Transactions");
        JButton addToWishlistButton = new JButton("Add to Wishlist");
        JButton viewWishlistButton = new JButton("View Wishlist");
        JButton placeAnOrderButton = new JButton("Place an Order");
        JButton logoutButton = new JButton("Logout");

        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputBridge.inputQueue.put(7);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        viewProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputBridge.inputQueue.put(13);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        viewOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputBridge.inputQueue.put(19);
                    InputBridge.inputQueue.put(ID);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        addToWishlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputBridge.inputQueue.put(9);
                    InputBridge.inputQueue.put(ID);
                    String input = JOptionPane.showInputDialog("Enter Product ID:");
                    int ProductID = Integer.parseInt(input);
                    InputBridge.inputQueue.put(ProductID);
                    input = JOptionPane.showInputDialog("Enter Amount:");
                    int amount = Integer.parseInt(input);
                    InputBridge.inputQueue.put(amount);
                    showMessage("Product added to wishlist.");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        viewWishlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputBridge.inputQueue.put(14);
                    InputBridge.inputQueue.put(ID);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        placeAnOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputBridge.inputQueue.put(15);
                    InputBridge.inputQueue.put(ID);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        logoutButton.addActionListener(e -> context.changeState(WarehouseContext.LOGIN_STATE));

        frame.add(viewDetailsButton);
        frame.add(viewProductsButton);
        frame.add(viewOrdersButton);
        frame.add(addToWishlistButton);
        frame.add(viewWishlistButton);
        frame.add(placeAnOrderButton);
        frame.add(logoutButton);

        frame.repaint();
        frame.setVisible(true);
    }
}
