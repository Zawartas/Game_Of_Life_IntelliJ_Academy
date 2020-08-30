package life;

import java.util.Random;

public final class LifeGrid {

    private boolean[][] grid;
    private int generation;
    private GenerationAlgorithm generationAlgorithm;

    public LifeGrid(int size) {
        grid = new boolean[size][size];
        generation = 0;
        this.fillWithRandomValues();
        this.setGenerationAlgorithm(new DefaultAlgorithm());
    }

    public LifeGrid(boolean[][] grid, int generation) {
        this.grid = grid;
        this.generation = generation;
    }

    public boolean[][] getGrid() {
        return grid;
    }

    public int getGeneration() {
        return generation;
    }

    public int getSize() {
        return grid.length;
    }

    private void setGenerationAlgorithm(GenerationAlgorithm generationAlgorithm) {
        this.generationAlgorithm = generationAlgorithm;
    }

    public LifeGrid clone() {
        boolean[][] newGrid = new boolean[grid.length][grid.length];
        for (int i = 0; i < grid.length; ++i) {
            System.arraycopy(grid[i], 0, newGrid[i], 0, grid.length);
        }
        return new LifeGrid(newGrid, getGeneration());
    }

    public void fillWithValuesFrom(LifeGrid source) {
        if (this.getSize() != source.getSize()) {
            throw new IllegalArgumentException("Different size of source grid");
        }
        for (int i = 0; i < grid.length; ++i) {
            System.arraycopy(source.grid[i], 0, this.grid[i], 0, grid.length);
        }
    }

    public void fillWithRandomValues() {
        Random random = new Random();
        for (int row = 0; row < getSize(); ++row) {
            for (int column = 0; column < getSize(); ++column) {
                grid[row][column] = random.nextBoolean();
            }
        }
    }

    public boolean getCell(int x, int y) {
        return grid[x][y];
    }

    public void setCell(int x, int y, boolean status) {
        grid[x][y] = status;
    }

    public int getAliveCellsCount() {
        int aliveCellsCounter = 0;
        for (int i = 0; i < getSize(); ++i) {
            for (int j = 0; j < getSize(); ++j) {
                if (getCell(i,j)) {
                    aliveCellsCounter++;
                }
            }
        }
        return aliveCellsCounter;
    }

    public void generateGeneration() {
        generationAlgorithm.createNewGenerations(this);
        generation++;
    }

    public void reset() {
        fillWithRandomValues();
        generation = 0;
    }
}
