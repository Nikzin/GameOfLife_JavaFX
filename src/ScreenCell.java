

import javafx.scene.shape.Rectangle;

public class ScreenCell {
    private int coordinateX;
    private int coordinateY;
    private int squareSize;
    private Rectangle square;

    public ScreenCell(int coordinateX, int coordinateY, int squareSize, Rectangle square) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.squareSize = squareSize;
        this.square = square;
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

    public int getSquareSize() {
        return squareSize;
    }

    public void setSquareSize(int squareSize) {
        this.squareSize = squareSize;
    }

    public Rectangle getSquare() {
        return square;
    }

    public void setSquare(Rectangle square) {
        this.square = square;
    }
}

