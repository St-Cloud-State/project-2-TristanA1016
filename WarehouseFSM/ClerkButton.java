import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClerkButton extends JButton implements ActionListener {
    public ClerkButton() {
        super("Clerk"); // Set the button label
        this.addActionListener(this); // Add action listener
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Set the role and transition to ClerkMenuState
        WarehouseContext.instance().setRole(WarehouseContext.CLERK);
        WarehouseContext.instance().changeState(WarehouseContext.CLERK_MENU_STATE);
    }
}

