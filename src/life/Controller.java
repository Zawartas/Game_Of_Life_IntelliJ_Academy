package life;

import javax.swing.*;

public class Controller {

    private LifeGrid lifeGrid;
    private View view;
    private Timer timer;
    private int delay = 1000;

    public View getView() {
        return view;
    }

    public Controller() {
        this.lifeGrid = new LifeGrid(60);
    }

    public void createAndShowGUI() {
        view = new View(lifeGrid);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setActionOnStartStopButton(view.getjToggleButton(), timer);
        setActionOnSlider(view.getjSlider());
        setActionOnRestart(view.getjButtonRestart());
    }

    public void updateUI() {
        timer = new Timer(delay, e -> {
            this.generateGeneration();
            this.setGenerationLabel();
            this.setAliveLabel(lifeGrid.getAliveCellsCount());
            this.updateGridPanel(lifeGrid.getGrid());
            SwingUtilities.updateComponentTreeUI(this.getView());

            if (this.getGeneration() == 100) {
                timer.stop();
            }
        });

        timer.start();
    }

    public void setGenerationLabel() {
        view.setGenerationLabel(lifeGrid.getGeneration());
    }

    public void setAliveLabel(int aliveCellsCount) {
        view.setAliveLabel(aliveCellsCount);
    }

    public void updateGridPanel(boolean[][] grid) {
        view.updateGridPanel(grid);
    }

    public int getGeneration() {
        return lifeGrid.getGeneration();
    }

    public void generateGeneration() {
        lifeGrid.generateGeneration();
    }

    private void setActionOnStartStopButton(JToggleButton jToggleButton, Timer timer) {
        jToggleButton.addActionListener(e -> {
            if (jToggleButton.isSelected()) {
                timer.stop();
            } else {
                timer.start();
            }
        });
    }

    private void setActionOnSlider(JSlider jSlider) {
        jSlider.addChangeListener(e -> timer.setDelay(jSlider.getValue()));
    }

    private void setActionOnRestart(JButton jButton) {
        jButton.addActionListener(e -> {
            timer.stop();
            view.getjToggleButton().setSelected(true);
            lifeGrid.reset();
            setGenerationLabel();
            setAliveLabel(lifeGrid.getAliveCellsCount());
            updateGridPanel(lifeGrid.getGrid());
        });
    }
}
