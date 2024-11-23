import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ClerkMenuState extends WarehouseState {
    private static ClerkMenuState instance;
    private ClerkMenuState() {}

    public static ClerkMenuState instance(WarehouseContext context) {
        if (instance == null) {
            instance = new ClerkMenuState();
            setContext(context);
        }
        return instance;
    }

    @Override
    public void run() {
        JFrame frame = context.getFrame();
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(6, 1));

        JLabel label = new JLabel("Clerk Menu", JLabel.CENTER);
        frame.add(label);

        JButton addClientButton = new JButton("Add Client");
        JButton viewProductsButton = new JButton("View Products");
        JButton viewClientsButton = new JButton("View Clients");
        JButton viewClientsBalanceButton = new JButton("View Clients with outstanding balance");
        JButton makePaymentButton = new JButton("Make payment from client");
        JButton becomeClientButton = new JButton("Become a client");
        JButton logoutButton = new JButton("Logout");
        //
        addClientButton.addActionListener(new ActionListener() { //example of working GUI only, works with case 1 in WMS.java
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputBridge.inputQueue.put(1);
                    String input = JOptionPane.showInputDialog("Enter Client Name:");
                    InputBridgeS.inputQueue.put(input);
                    input = JOptionPane.showInputDialog("Enter Client ID:");
                    int clientID = Integer.parseInt(input);
                    InputBridge.inputQueue.put(clientID);
                    input = JOptionPane.showInputDialog("Enter Client Address:");
                    InputBridgeS.inputQueue.put(input);
                    input = JOptionPane.showInputDialog("Enter Client Contact:");
                    InputBridgeS.inputQueue.put(input);
                    showMessage("Client added.");
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
        viewClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputBridge.inputQueue.put(7);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        viewClientsBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputBridge.inputQueue.put(20);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        makePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputBridge.inputQueue.put(17);
                    String input = JOptionPane.showInputDialog("Enter Client ID:");
                    int clientID = Integer.parseInt(input);
                    InputBridge.inputQueue.put(clientID);
                    input = JOptionPane.showInputDialog("Enter payment amount:");
                    int payment = Integer.parseInt(input);
                    InputBridge.inputQueue.put(payment);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        becomeClientButton.addActionListener(e -> {
            // Prompt the user to enter a client ID
            String input = JOptionPane.showInputDialog("Enter Client ID:");

            if (input != null && !input.isEmpty()) {
                try {
                    int clientID = Integer.parseInt(input);  
                    // Transition to CLIENT_MENU_STATE
                    context.changeState(WarehouseContext.CLIENT_MENU_STATE, clientID);  // This will handle the state change
                } catch (NumberFormatException ex) {
        
                    System.out.println("Invalid input. No valid client ID entered.");
                }
            }
        });
        logoutButton.addActionListener(e -> context.changeState(WarehouseContext.LOGIN_STATE));

        frame.add(addClientButton);
        frame.add(viewProductsButton);
        frame.add(viewClientsButton);
        frame.add(viewClientsBalanceButton);
        frame.add(makePaymentButton);
        frame.add(becomeClientButton);
        frame.add(logoutButton);

        frame.repaint();
        frame.setVisible(true);
    }
}
