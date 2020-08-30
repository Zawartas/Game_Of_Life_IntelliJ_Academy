package life;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class View extends JFrame {

    public static final int BOX_SIZE = 12;

    private JPanel gridPanel = new JPanel();
    private JLabel GenerationLabel = new JLabel();
    private JLabel AliveLabel = new JLabel();
    private JButton startStopButton = new JButton("START");
    private JButton restartButton = new JButton("RESTART");
    private JSlider slider;

    public JButton getRestartButton() {
        return restartButton;
    }

    public JButton getStartStopButton() {
        return startStopButton;
    }

    public JSlider getSlider() {
        return slider;
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
    }

    public void setVisible() {
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

        startStopButton.setPreferredSize(new Dimension(100, 30));
        restartButton.setPreferredSize(new Dimension(100, 30));
        settingsPanel.add(startStopButton);
        settingsPanel.add(restartButton);

        slider = new JSlider(JSlider.HORIZONTAL,
                1000, 5000, 1000);
        slider.setPreferredSize(new Dimension(300, 50));
        slider.setMinorTickSpacing(1000);

        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(1000, new JLabel("1 sec"));
        labels.put(2000, new JLabel("2 sec"));
        labels.put(3000, new JLabel("3 sec"));
        labels.put(4000, new JLabel("4 sec"));
        labels.put(5000, new JLabel("5 sec"));
        slider.setLabelTable(labels);

        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        settingsPanel.add(slider);

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

    public void setSliderValue(int delay) {
        this.slider.setValue(delay);
    }
}
