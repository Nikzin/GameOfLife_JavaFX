import java.util.ArrayList;
import java.util.Random;

public class AbstractCellsHandler {
    private int wide;
    private int height;

    public ArrayList<AbstractCell> getAbstractCells() {
        return abstractCells;
    }

    private ArrayList<AbstractCell> abstractCells = new ArrayList<AbstractCell>();
    public ArrayList<AbstractCell> tempAbsCells = new ArrayList<>();


    public int getWide() {
        return wide;
    }

    public void setWide(int wide) {
        this.wide = wide;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AbstractCellsHandler(int wide, int height) {
        this.wide = wide;
        this.height = height;
    }

    public void nextPopulation() {

        tempAbsCells.clear();
        for (AbstractCell c1 : abstractCells) {
            lifeCheckAndChange(c1.getCoordinateX(), c1.getCoordinateY(), c1.getValue(), abstractCells);
        }
        abstractCells = new ArrayList<>(tempAbsCells);
    }

    private void lifeCheckAndChange(int coordinateX, int coordinateY, int value, ArrayList<AbstractCell> abstractCells) {

        int neighbours = 0;
        int tempValue = getCellValue(coordinateX, coordinateY, abstractCells);
        AbstractCell tempCell = new AbstractCell(coordinateX, coordinateY, value);
//lifeCycleNumber neighbours
        neighbours=countNeighbours(coordinateX, coordinateY, abstractCells);

//change Life status of cell according to Game of Life rules:
        if (tempValue == 0 && neighbours == 3) {
            tempCell.setValue(1);
        }

        if (tempValue == 1 && (neighbours < 2)) {
            tempCell.setValue(0);
        }

        if (tempValue == 1 && (neighbours > 3)) {
            tempCell.setValue(0);
        }
        tempAbsCells.add(tempCell);
    }

    public int countNeighbours(int coordinateX, int coordinateY, ArrayList<AbstractCell> abstractCells) {
        int neighbours=0;
        neighbours += getCellValue(coordinateX - 1, coordinateY + 1, abstractCells);
        neighbours += getCellValue(coordinateX, coordinateY + 1, abstractCells);
        neighbours += getCellValue(coordinateX + 1, coordinateY + 1, abstractCells);
        neighbours += getCellValue(coordinateX + 1, coordinateY, abstractCells);
        neighbours += getCellValue(coordinateX + 1, coordinateY - 1, abstractCells);
        neighbours += getCellValue(coordinateX, coordinateY - 1, abstractCells);
        neighbours += getCellValue(coordinateX - 1, coordinateY - 1, abstractCells);
        neighbours += getCellValue(coordinateX - 1, coordinateY, abstractCells);

        return neighbours;
    }

    public int getCellValue(int x, int y, ArrayList<AbstractCell> cells) {
        int value = 0;
        for (AbstractCell c1 : cells) {
            if (c1.getCoordinateX() == x && c1.getCoordinateY() == y) {
                value = c1.getValue(); }
        }
        return value; }

    public void initCellsDensity(double density) {
        Random random = new Random();
        for (int x = 0; x < wide; x++) {
            for (int y = 0; y < height; y++) {
                if (random.nextDouble() < density) {
                    AbstractCell cell1 = new AbstractCell(x, y, 1);
                    abstractCells.add(cell1);
                } else {
                    AbstractCell cell1 = new AbstractCell(x, y, 0);
                    abstractCells.add(cell1);
                }
            }
        }
    }
}