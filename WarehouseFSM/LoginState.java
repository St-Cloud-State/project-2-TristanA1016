import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginState extends WarehouseState {
    private static LoginState instance;

    private LoginState() {}

    public static LoginState instance(WarehouseContext context) {
        if (instance == null) {
            instance = new LoginState();
            setContext(context);
        }
        return instance;
    }

    @Override
    public void run() {
        JFrame frame = context.getFrame();
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Welcome to the Warehouse Management System", JLabel.CENTER);
        frame.add(label);

        JButton clientButton = new JButton("Client Menu");
        JButton clerkButton = new JButton("Clerk Menu");
        JButton managerButton = new JButton("Manager Menu");

        clientButton.addActionListener(e -> {
            // Prompt the user to enter a client ID
            String input = JOptionPane.showInputDialog("Enter Client ID:");

            if (input != null && !input.isEmpty()) {
                try {
                    int clientID = Integer.parseInt(input);  // Convert input to an integer
                    // Transition to CLIENT_MENU_STATE
                    context.changeState(WarehouseContext.CLIENT_MENU_STATE, clientID);  // This will handle the state change
                } catch (NumberFormatException ex) {
                    // Just ignore the exception as we don't need to check validity
                    System.out.println("Invalid input. No valid client ID entered.");
                }
            }
        });
        clerkButton.addActionListener(e -> context.changeState(WarehouseContext.CLERK_MENU_STATE));
        managerButton.addActionListener(e -> context.changeState(WarehouseContext.MANAGER_MENU_STATE));

        frame.add(clientButton);
        frame.add(clerkButton);
        frame.add(managerButton);

        frame.repaint();
        frame.setVisible(true);
    }
}
