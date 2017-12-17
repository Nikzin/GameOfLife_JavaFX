
import javafx.scene.layout.Pane;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;



public class GameTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    //make 100 games with random parameters,
    // in each of them make random number (1-10) of iterations  and
    // for each of them check destiny of all cells in the next iteration
    public void gameTest() throws Exception {

//make 100 games with random parameters:
        for (int gameNr = 1; gameNr < 101; gameNr++) {

            //create a Cell field with random (10-60) breadth and height with randomly distributed random amount of alive cells
            int wide = (int) (Math.random() * 50) + 10;
           int  height = (int) (Math.random() * 50) + 10;
            AbstractCellsHandler abstractCellsHandler = new AbstractCellsHandler(wide, height);
            double randomDensity = Math.random();
            abstractCellsHandler.initCellsDensity(randomDensity);


           //make random (1-10) number of iterations and extract previous/next cell populations(generations)
            ArrayList<AbstractCell> previousGeneration = new ArrayList<>();
            ArrayList<AbstractCell> nextGeneration = new ArrayList<>();
            ArrayList<AbstractCell> tempAbsCells = new ArrayList<>();
            int iterationNumber = (int) (Math.random() * 10) + 1;
            for (int cycle = 0; cycle < iterationNumber; cycle++) {
                abstractCellsHandler.nextPopulation();
            }

            tempAbsCells = new ArrayList<>(abstractCellsHandler.getAbstractCells());

            for (AbstractCell aCell : tempAbsCells) {
                AbstractCell tempCell = new AbstractCell(aCell.getCoordinateX(), aCell.getCoordinateY(), aCell.getValue());
                previousGeneration.add(tempCell);
            }
            abstractCellsHandler.nextPopulation();
            tempAbsCells = new ArrayList<>(abstractCellsHandler.getAbstractCells());
            for (AbstractCell aCell : tempAbsCells) {
                AbstractCell tempCell = new AbstractCell(aCell.getCoordinateX(), aCell.getCoordinateY(), aCell.getValue());
                nextGeneration.add(tempCell);
            }

//Destiny test of all cells:
            for (int index = 0; index< previousGeneration.size(); index++) {

             //   int randomAbsCellNumber = (int) (Math.random() * previousGeneration.size());
                AbstractCell randomPreviousCell = previousGeneration.get(index);
                int prevNeuboursNumber = abstractCellsHandler.countNeighbours(randomPreviousCell.getCoordinateX(), randomPreviousCell.getCoordinateY(), previousGeneration);
                AbstractCell randomNextCell = nextGeneration.get(index);

                System.out.println("x "+ randomPreviousCell.getCoordinateX()+" y "+ randomPreviousCell.getCoordinateY()+ " neighbors " + prevNeuboursNumber +" previous "+randomPreviousCell.getValue() +  " next " + randomNextCell.getValue());


                if (randomPreviousCell.getValue() == 1 && ((prevNeuboursNumber < 2) || (prevNeuboursNumber > 3))) {
                    Assert.assertEquals(randomNextCell.getValue(), 0);
                }
                if (randomPreviousCell.getValue() == 1 && ((prevNeuboursNumber == 2) || (prevNeuboursNumber == 3))) {
                    Assert.assertEquals(randomNextCell.getValue(), 1);
                }
                if (randomPreviousCell.getValue() == 0 && prevNeuboursNumber == 3) {
                    Assert.assertEquals(randomNextCell.getValue(), 1);
                }
                if (randomPreviousCell.getValue() == 0 && ((prevNeuboursNumber < 3) || (prevNeuboursNumber > 3))) {
                    Assert.assertEquals(randomNextCell.getValue(), 0);
                }
            }

            randomDensity = Math.round(randomDensity * 100);
            randomDensity=randomDensity/100;
            System.out.println("Passed: game "+ gameNr+ " wide "+wide + " height " + height+ " initial density "+ randomDensity);
        }

    }
}