package life;

import static java.lang.Thread.*;

public class DefaultAlgorithm implements GenerationAlgorithm {

    @Override
    public LifeGrid createNewGenerations(LifeGrid baseLifeGrid, int iterations) {
        LifeGrid tempLifeGrid = baseLifeGrid.clone();
        LifeGrid a = new LifeGrid(baseLifeGrid.getSize());

        for (int k = 1; k <= iterations; ++k) {
            for (int x = 0; x < baseLifeGrid.getSize(); ++x) {
                for (int y = 0; y < baseLifeGrid.getSize(); ++y) {
                    boolean state = tempLifeGrid.getCell(x, y);
                    int aliveNeighbors = checkNeighbors(tempLifeGrid, x, y);
                    boolean newState = cellStateBasedOnPreviousGeneration(state, aliveNeighbors);
                    a.setCell(x, y, newState);
                }
            }
            tempLifeGrid.fillWithValuesFrom(a);
            waitFor(800);

            a.showGrid(k);
        }
        return tempLifeGrid;
    }

    private void waitFor(int timeInMiliseconds) {
        try {
            sleep(timeInMiliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean cellStateBasedOnPreviousGeneration(boolean currentState, int aliveNeighbors) {
        if (currentState && !(aliveNeighbors == 2 || aliveNeighbors == 3)) {
            return false;
        } else if (!currentState && aliveNeighbors == 3) {
            return true;
        } else {
            return currentState;
        }
    }

    private int checkNeighbors(LifeGrid lifeGrid, int x, int y) {
        int count = 0;
        final int[][] neighbours = {
                {-1, -1}, {-1, 0}, {-1, +1},
                { 0, -1}, /*CELL*/ { 0, +1},
                {+1, -1}, {+1, 0}, {+1, +1}};

        for (int[] offset : neighbours) {
//            System.out.println("neigbour:");
//            System.out.println("(" + ((x + offset[0]) % grid.getSize() + grid.getSize()) % grid.getSize()
//            + ", " + ((y + offset[1]) % grid.getSize() + grid.getSize())% grid.getSize() + ")");
//            System.out.println(grid.getCell(((x + offset[0]) % grid.getSize() + grid.getSize()) % grid.getSize(),
//                    ((y + offset[1]) % grid.getSize() + grid.getSize())% grid.getSize()));
            if (lifeGrid.getCell(((x + offset[0]) % lifeGrid.getSize() + lifeGrid.getSize()) % lifeGrid.getSize(),
                                ((y + offset[1]) % lifeGrid.getSize() + lifeGrid.getSize())% lifeGrid.getSize())) {
//                System.out.print(" HERE\n");
                count++;
            }
        }
        return count;
    }
}
