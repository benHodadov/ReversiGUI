/**
 * Created by benho on 09/01/2018.
 */
package myapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.stage.Stage;



public class HelloWorld extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        /*
        primaryStage.setTitle("Rom is the king");

        Label l = new Label("barak");
        l.setFont(new Font("Arial", 30));

        Button b = new Button("click me");
        TextArea t = new TextArea("write text here");

        b.setOnAction(event -> {
            t.setText("Button clicked");
        });


        VBox root = new VBox();
        root.getChildren().add(l);
        root.getChildren().add(b);
        root.getChildren().add(t);

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
        */
        try {
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("myFXML.fxml"));
            Scene scene = new Scene(root,400,350);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Reversi Game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }

    }
}
