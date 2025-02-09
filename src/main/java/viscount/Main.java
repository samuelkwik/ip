package viscount;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Viscount using FXML.
 */
public class Main extends Application {

    private Viscount viscount = new Viscount();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinWidth(417);
            stage.setMinHeight(220);
            stage.setTitle("Viscount");
            fxmlLoader.<MainWindow>getController().setViscount(viscount);  // inject the Viscount instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
