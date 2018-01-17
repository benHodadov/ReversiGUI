package OtherGameFiles; /**
 * Created by Ben and Barak on 08-Jan-18.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // run the opening menu.
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../fxmlFiles/Menu.fxml"));
            Scene scene = new Scene(root,600,400);
            primaryStage.setTitle("Reversi Game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
}
