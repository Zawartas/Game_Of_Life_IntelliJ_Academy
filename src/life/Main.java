package life;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller();
        SwingUtilities.invokeLater(controller::createAndShowGUI);
        controller.updateUI();
    }
}

