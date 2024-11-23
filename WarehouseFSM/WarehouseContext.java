import javax.swing.*;
import java.util.HashMap;

public class WarehouseContext {
    // State constants
    public static final int LOGIN_STATE = 0;
    public static final int CLIENT_MENU_STATE = 1;
    public static final int CLERK_MENU_STATE = 2;
    public static final int MANAGER_MENU_STATE = 3;

    // Role constants
    public static final int CLIENT = 1;
    public static final int CLERK = 2;
    public static final int MANAGER = 3;

    private static WarehouseContext instance;
    private WarehouseState[] states; // Array to hold all states
    private int currentState; // ID of the current state
    private int currentRole; // ID of the current role (Client, Clerk, Manager)
    private JFrame frame; // Main application frame
    private HashMap<Integer, String> roleMap; // Optional: Map for role names (useful for debugging)

    private WarehouseContext() {
        // Initialize the main frame
        frame = new JFrame("Warehouse Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500); // Adjusted for a better UI experience
        frame.setLocationRelativeTo(null);

        // Initialize roleMap (optional for debugging)
        roleMap = new HashMap<>();
        roleMap.put(CLIENT, "Client");
        roleMap.put(CLERK, "Clerk");
        roleMap.put(MANAGER, "Manager");

        // Initialize states
        states = new WarehouseState[4];
        states[LOGIN_STATE] = LoginState.instance(this);
        states[CLIENT_MENU_STATE] = ClientMenuState.instance(this, -1);
        states[CLERK_MENU_STATE] = ClerkMenuState.instance(this);
        states[MANAGER_MENU_STATE] = ManagerMenuState.instance(this);

        currentState = LOGIN_STATE; // Default state
    }

    // Singleton instance method
    public static WarehouseContext instance() {
        if (instance == null) {
            instance = new WarehouseContext();
        }
        return instance;
    }

    // Change to a new state and run its logic
    public void changeState(int state) {
        currentState = state;
        states[currentState].run();
    }

    public void changeState(int state, int clientID) {
        currentState = state;
        if (state == WarehouseContext.CLIENT_MENU_STATE) {
            ClientMenuState.instance(this, clientID).run();  // Pass clientID to ClientMenuState
        } else {
            states[state].run();
        }
    }

    // Get the main application frame
    public JFrame getFrame() {
        return frame;
    }

    // Start the FSM by running the current state's logic
    public void start() {
        states[currentState].run();
        frame.setVisible(true);
    }

    // Set the role for the current user
    public void setRole(int role) {
        this.currentRole = role;
        System.out.println("Role set to: " + roleMap.get(role)); // Optional debugging
    }

    // Get the current role
    public int getRole() {
        return currentRole;
    }
}
