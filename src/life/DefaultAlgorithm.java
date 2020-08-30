package life;

public class DefaultAlgorithm implements GenerationAlgorithm {

    @Override
    public void createNewGenerations(LifeGrid baseLifeGrid) {
        LifeGrid targetLifeGrid = new LifeGrid(baseLifeGrid.getSize());
            for (int x = 0; x < baseLifeGrid.getSize(); ++x) {
                for (int y = 0; y < baseLifeGrid.getSize(); ++y) {
                    boolean oldState = baseLifeGrid.getCell(x, y);
                    int aliveNeighbors = checkNeighbors(baseLifeGrid, x, y);
                    boolean newState = cellStateBasedOnPreviousGeneration(oldState, aliveNeighbors);
                    targetLifeGrid.setCell(x, y, newState);
                }
            }
        baseLifeGrid.fillWithValuesFrom(targetLifeGrid);
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
