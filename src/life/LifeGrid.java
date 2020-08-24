package life;

import java.io.IOException;
import java.util.Random;

public final class LifeGrid {

    private boolean[][] grid;
    GenerationAlgorithm generationAlgorithm;

    public LifeGrid(int size) {
        grid = new boolean[size][size];
    }

    public LifeGrid(boolean[][] grid) {
        this.grid = grid;
    }

    public LifeGrid clone() {
        boolean[][] newGrid = new boolean[grid.length][grid.length];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid.length; ++j) {
                newGrid[i][j] = grid[i][j];
            }
        }
        return new LifeGrid(newGrid);
    }

    public void fillWithValuesFrom(LifeGrid source) {
        if (this.getSize() != source.getSize()) {
            throw new IllegalArgumentException("Different size of source grid");
        }
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid.length; ++j) {
                this.grid[i][j] = source.grid[i][j];
            }
        }
    }

    public void setGenerationAlgorithm(GenerationAlgorithm generationAlgorithm) {
        this.generationAlgorithm = generationAlgorithm;
    }

    public int getSize() {
        return grid.length;
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

    public int getAliveCellsCount(int iterations) {
        StringBuilder sb = new StringBuilder();

        int aliveCellsCounter = 0;
        for (int i = 0; i < getSize(); ++i) {
            for (int j = 0; j < getSize(); ++j) {
                if (getCell(i,j)) {
                    sb.append('O');
                    aliveCellsCounter++;
                } else {
                    sb.append(' ');
                }
            }
        }

    }

    public LifeGrid generateGeneration(int iterations) {
        return generationAlgorithm.createNewGenerations(this, iterations);
    }
}
