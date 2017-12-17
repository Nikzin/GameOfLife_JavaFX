import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

  private static final int CELL_SIZE = 20;//size of a each cell, pixels
    private static final int FIELD_SIZE_WIDE = 30;//size of scene: breadth, cells:
    private static final int FIELD_SIZE_HEIGHT = 20;//size of scene: height, cells
    private static final int TIME_ITER_MS=200;//iteration time, ms

    private Pane root = new Pane();

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Game of Life");
        Scene scene = new Scene(root, FIELD_SIZE_WIDE * CELL_SIZE, FIELD_SIZE_HEIGHT * CELL_SIZE);

        SceneController gameOfLife = new SceneController(root, CELL_SIZE, FIELD_SIZE_WIDE, FIELD_SIZE_HEIGHT, TIME_ITER_MS);

        gameOfLife.run();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}




