

public class AbstractCell {
    private int coordinateX;
    private int coordinateY;
    private int value;

    public AbstractCell(int coordinateX, int coordinateY, int value) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.value = value;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

