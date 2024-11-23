import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ManagerMenuState extends WarehouseState {
    private static ManagerMenuState instance;

    private ManagerMenuState() {}

    public static ManagerMenuState instance(WarehouseContext context) {
        if (instance == null) {
            instance = new ManagerMenuState();
            setContext(context);
        }
        return instance;
    }

    @Override
    public void run() {
        JFrame frame = context.getFrame();
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(6, 1));

        JLabel label = new JLabel("Manager Menu", JLabel.CENTER);
        frame.add(label);

        JButton addProductButton = new JButton("Add Product");
        JButton viewWaitlistButton = new JButton("View Waitlist");
        JButton receiveShipmentButton = new JButton("Receive Shipment");
        JButton becomeClerkButton = new JButton("Become Clerk");
        JButton logoutButton = new JButton("Logout");

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputBridge.inputQueue.put(3);
                    String input = JOptionPane.showInputDialog("Enter Product ID:");
                    int productID = Integer.parseInt(input);
                    InputBridge.inputQueue.put(productID);
                    input = JOptionPane.showInputDialog("Enter Product Name:");
                    InputBridgeS.inputQueue.put(input);
                    input = JOptionPane.showInputDialog("Enter Product Description:");
                    InputBridgeS.inputQueue.put(input);
                    input = JOptionPane.showInputDialog("Enter Product Price:");
                    InputBridgeS.inputQueue.put(input);
                    input = JOptionPane.showInputDialog("Enter Product Amount:");
                    int productCount = Integer.parseInt(input);
                    InputBridge.inputQueue.put(productCount);
                    showMessage("Product added to warehouse.");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        viewWaitlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputBridge.inputQueue.put(16);
                    String input = JOptionPane.showInputDialog("Enter Product ID:");
                    int productID = Integer.parseInt(input);
                    InputBridge.inputQueue.put(productID);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        receiveShipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputBridge.inputQueue.put(18);
                    String input = JOptionPane.showInputDialog("Enter Product ID:");
                    int productID = Integer.parseInt(input);
                    InputBridge.inputQueue.put(productID);
                    input = JOptionPane.showInputDialog("Enter Product amount:");
                    int amount = Integer.parseInt(input);
                    InputBridge.inputQueue.put(amount);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        becomeClerkButton.addActionListener(e -> context.changeState(WarehouseContext.CLERK_MENU_STATE));
        logoutButton.addActionListener(e -> context.changeState(WarehouseContext.LOGIN_STATE));

        frame.add(addProductButton);
        frame.add(viewWaitlistButton);
        frame.add(receiveShipmentButton);
        frame.add(becomeClerkButton);
        frame.add(logoutButton);

        frame.repaint();
        frame.setVisible(true);
    }
}
