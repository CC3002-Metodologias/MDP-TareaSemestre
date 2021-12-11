package aventurasMarcoyLuis.view;
import aventurasMarcoyLuis.controller.GameController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameView extends Application{

    private static final String RESOURCE_PATH = "src/main/resources/";
    private final GameController controller = new GameController();
    private final Group root = new Group();
    private Button start;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Las aventuras de Marco y Luis");
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 1280, 700);

    }
}
