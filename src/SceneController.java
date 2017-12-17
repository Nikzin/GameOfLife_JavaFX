import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.ArrayList;

public class SceneController {
    private int CELL_SIZE;
   private int FIELD_SIZE_WIDE;
 private int FIELD_SIZE_HEIGHT;

    private  int TIME_ITER_MS;
    private static final double INIT_DENSITY=0.2;
    private Pane root;
    private AbstractCellsHandler abstractCells;
    private ArrayList<ScreenCell> sceneCells =new ArrayList<ScreenCell>();
    private String check;

//
    public SceneController(Pane root, int cellSize, int fieldSizeWide, int fieldSizeHeight, int timeIterMs) {
        this.root=root;
        this.CELL_SIZE=cellSize;
        this.FIELD_SIZE_WIDE=fieldSizeWide;
        this.FIELD_SIZE_HEIGHT=fieldSizeHeight;
        this.TIME_ITER_MS=timeIterMs;
        //this.abstractCells= new AbstractCellsHandler(fieldSizeWide, fieldSizeHeight);
    }

    public void run() {
      //  System.out.println();

        //init scene with empty cells and init cells with random values
        initSceneWithCells(root);
        this.abstractCells= new AbstractCellsHandler(FIELD_SIZE_WIDE, FIELD_SIZE_HEIGHT);
        abstractCells.initCellsDensity(INIT_DENSITY);

        //play game with iteration every TIME_ITER_MS)
        timelinePlay();
    }

    private void timelinePlay() {  final Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler() {
        @Override
        public void handle(Event event) {
            abstractCells.nextPopulation();
            drawSceneCells();
        }}), new KeyFrame(Duration.millis(TIME_ITER_MS)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void initSceneWithCells(Pane root) {

        // Create a abstractCells with all cells (rectangles) and initiate Pane and array ArrayList sceneCells with these rectangles.
        for (int x = 0; x < FIELD_SIZE_WIDE*CELL_SIZE; x = x + CELL_SIZE) {
            for (int y = 0; y < FIELD_SIZE_HEIGHT*CELL_SIZE; y = y + CELL_SIZE) {
                Rectangle rectangeleCell = new Rectangle(x, y, CELL_SIZE, CELL_SIZE);
                rectangeleCell.setStroke(Color.LIGHTGREY);
                rectangeleCell.setFill(Color.WHITE);
                root.getChildren().add(rectangeleCell);
                ScreenCell fieldCell= new ScreenCell(x, y, CELL_SIZE, rectangeleCell);
                sceneCells.add(fieldCell); } }
    }

    //draws iterated cells on the scene
    private void drawSceneCells() {

        for (int x = 0; x < abstractCells.getWide(); x++) {
            for (int y = 0; y < abstractCells.getHeight(); y++) {
                Rectangle cell = findCell(x* CELL_SIZE, y* CELL_SIZE, sceneCells);
                // If cell is "alive" draw filled square (grey), otherwise empty square (white).
                if (abstractCells.getCellValue(x, y, abstractCells.getAbstractCells()) == 1) {
                    cell.setStroke(Color.LIGHTGREY);
                    cell.setFill(Color.GRAY);
                } else {
                    cell.setStroke(Color.LIGHTGREY);
                    cell.setFill(Color.WHITE); } } }
    }

    private Rectangle findCell(int x, int y, ArrayList<ScreenCell> fieldCells) {

        Rectangle cell = null;
        for (ScreenCell c:fieldCells){
            if (c.getCoordinateX()==x && c.getCoordinateY()==y){
                cell=c.getSquare();
            } }
        return cell;
    }
}
