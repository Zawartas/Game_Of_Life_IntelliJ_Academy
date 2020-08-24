package life;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class View extends JFrame {

    int generation;
    int alive;

    public View(LifeGrid lifeGrid) {
        super("Game of Life");
        int size = lifeGrid.getSize();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(25*size, 25*size);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(size, size));

        JLabel GenerationLabel = new JLabel("   Generation #" + generation);
        JLabel AliveLabel = new JLabel("   Alive: " + alive);

        northPanel.add(Box.createRigidArea(new Dimension(0,5)));
        northPanel.add(GenerationLabel);
        northPanel.add(AliveLabel);
        northPanel.add(Box.createRigidArea(new Dimension(0,5)));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel jLabel = new JLabel();
//                Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            jLabel.setBorder(new LineBorder(Color.DARK_GRAY, 1));
//                jLabel.setBorder(border);
                if (lifeGrid.getCell(i, j)) {
                    jLabel.setOpaque(true);
                    jLabel.setBackground(Color.BLACK);
//                jLabel.setVisible(false);
                }
                centerPanel.add(jLabel);
            }
        }
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
