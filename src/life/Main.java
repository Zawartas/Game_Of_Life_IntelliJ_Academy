package life;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int iterations = 10; //scanner.nextInt();
        int sizeOfUniverse = 30;
        LifeGrid lifeGrid = new LifeGrid(sizeOfUniverse);
        lifeGrid.fillWithRandomValues();
        lifeGrid.setGenerationAlgorithm(new DefaultAlgorithm());

//        LifeGrid nextGen = lifeGrid.generateGeneration(iterations);

        new View(lifeGrid);

    }
}

