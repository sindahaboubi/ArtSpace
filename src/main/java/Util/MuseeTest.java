
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class MuseeTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/AjoutMusee.fxml"));
        Parent root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root);
        primaryStage.setTitle("ArtSpace");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


}
