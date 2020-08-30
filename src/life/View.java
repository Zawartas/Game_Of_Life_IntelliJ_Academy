package life;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class View extends JFrame {

    public static final int BOX_SIZE = 12;

    private JPanel gridPanel = new JPanel();
    private JLabel GenerationLabel = new JLabel();
    private JLabel AliveLabel = new JLabel();
    private JToggleButton jToggleButton;

    public JButton getjButtonRestart() {
        return jButtonRestart;
    }

    private JButton jButtonRestart;

    public void setjSliderValue(int value) {
        jSlider.setValue(value);
    }

    private JSlider jSlider;

    public JToggleButton getjToggleButton() {
        return jToggleButton;
    }

    public JSlider getjSlider() {
        return jSlider;
    }

    public View(LifeGrid lifeGrid) {
        super("Game of Life");
        int amountOfGrids = lifeGrid.getSize();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(BOX_SIZE *amountOfGrids, BOX_SIZE *amountOfGrids);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        createGridPanel(amountOfGrids);
        updateGridPanel(lifeGrid.getGrid());

        this.add(createNorthPanel(), BorderLayout.NORTH);
        this.add(gridPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void setGenerationLabel(int value) {
        GenerationLabel.setText(String.format("Generation #%d", value));
    }

    public void setAliveLabel(int value) {
        AliveLabel.setText(String.format("Alive: %d", value));
    }

    private void createGridPanel(int size) {
        gridPanel.removeAll();
        gridPanel.setLayout(new GridLayout(size, size));
    }

    public void updateGridPanel(boolean[][] grid) {
        gridPanel.removeAll();
        for (boolean[] booleans : grid) {
            for (int j = 0; j < grid.length; j++) {
                JLabel jLabel = new JLabel();
                jLabel.setBorder(new LineBorder(Color.DARK_GRAY, 1));
                if (booleans[j]) {
                    jLabel.setOpaque(true);
                    jLabel.setBackground(Color.BLACK);
                }
                gridPanel.add(jLabel);
            }
        }
    }

    private JPanel createNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        JPanel settingsPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

        jToggleButton = new JToggleButton("Start/Pause");
        settingsPanel.add(jToggleButton);

        jButtonRestart = new JButton("Restart");
        settingsPanel.add(jButtonRestart);

        jSlider = new JSlider(JSlider.HORIZONTAL,
                500, 5000, 1000);
        jSlider.setMinorTickSpacing(500);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);
        settingsPanel.add(jSlider);

        setGenerationLabel(0);
        setAliveLabel(0);

        JPanel labelsPanelChild = new JPanel();
        labelsPanelChild.setLayout(new BoxLayout(labelsPanelChild, BoxLayout.Y_AXIS));

        labelsPanelChild.add(Box.createRigidArea(new Dimension(5,5)));
        labelsPanelChild.add(GenerationLabel);
        labelsPanelChild.add(AliveLabel);
        labelsPanelChild.add(Box.createRigidArea(new Dimension(5,5)));

        JPanel labelsPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        labelsPanel.add(labelsPanelChild);

        northPanel.add(settingsPanel);
        northPanel.add(labelsPanel);
        return northPanel;
    }
}
