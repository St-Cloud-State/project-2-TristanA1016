import javax.swing.*;

public abstract class WarehouseState {
    protected static WarehouseContext context; // Shared FSM context

    /**
     * Set the shared context for all states.
     *
     * @param warehouseContext The shared context for the FSM.
     */
    public static void setContext(WarehouseContext warehouseContext) {
        context = warehouseContext;
    }

    
    public abstract void run();

    /**
     * Helper method to display an informational message.
     *
     * @param message The message to display.
     */
    protected void showMessage(String message) {
        JOptionPane.showMessageDialog(context.getFrame(), message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Helper method to display an error message.
     *
     * @param message The error message to display.
     */
    protected void showError(String message) {
        JOptionPane.showMessageDialog(context.getFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Helper method to get user input through a dialog box.
     *
     * @param prompt The prompt to display to the user.
     * @return The user's input, or null if canceled.
     */
    protected String getInput(String prompt) {
        return JOptionPane.showInputDialog(context.getFrame(), prompt);
    }

    /**
     * Helper method to confirm an action with the user.
     *
     * @param message The confirmation message to display.
     * @return true if the user selects "Yes", false otherwise.
     */
    protected boolean confirmAction(String message) {
        int result = JOptionPane.showConfirmDialog(context.getFrame(), message, "Confirm", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
}
