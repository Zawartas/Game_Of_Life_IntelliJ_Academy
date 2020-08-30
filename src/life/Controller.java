package life;

import javax.swing.*;

public class Controller {

    private LifeGrid lifeGrid;
    private View view;
    private Timer timer;
    private int delay = 2000;

    public View getView() {
        return view;
    }

    public Controller() {
        this.lifeGrid = new LifeGrid(60);
    }

    public void createAndShowGUI() {
        view = new View(lifeGrid);
        view.getStartStopButton().setSelected(false);
        view.setVisible();

        setActionOnStartStopButton(view.getStartStopButton(), timer);
        setActionOnSlider(view.getSlider());
        setActionOnRestart(view.getRestartButton());

        view.setSliderValue(delay);
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

    private void setActionOnStartStopButton(JButton startStopButton, Timer timer) {
        startStopButton.addActionListener(e -> {
            if (timer.isRunning()) {
                timer.stop();
                startStopButton.setText("START");
            } else if (!timer.isRunning()) {
                timer.start();
                startStopButton.setText("STOP");
            }
        });
    }

    private void setActionOnSlider(JSlider jSlider) {
        jSlider.addChangeListener(e -> timer.setDelay(jSlider.getValue()));
    }

    private void setActionOnRestart(JButton jButton) {
        jButton.addActionListener(e -> {
            timer.stop();
            view.getStartStopButton().setText("START");
            lifeGrid.reset();
            setGenerationLabel();
            setAliveLabel(lifeGrid.getAliveCellsCount());
            updateGridPanel(lifeGrid.getGrid());
        });
    }
}
