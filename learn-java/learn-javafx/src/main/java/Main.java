import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by mazhibin on 17/3/11
 */
public class Main extends Application{


    public void start(Stage primaryStage) throws Exception {
        Button button = new Button();
        button.setText("button");
        button.setOnAction(event -> {
            System.out.println("hello");
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);
        Scene scene = new Scene(root,300,200);
        
        primaryStage.setTitle("hello");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
