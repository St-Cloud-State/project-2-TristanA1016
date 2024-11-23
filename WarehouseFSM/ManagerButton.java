import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerButton extends JButton implements ActionListener {
    public ManagerButton() {
        super("Manager"); // Set the button label
        this.addActionListener(this); // Add action listener
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Transition to ManagerMenuState
        WarehouseContext.instance().setRole(WarehouseContext.MANAGER);
        WarehouseContext.instance().changeState(WarehouseContext.MANAGER_MENU_STATE);
    }
}
