package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Ben and Barak on 10/01/2018.
 */
public class MenuController implements Initializable {
    @FXML
    private Hyperlink quitButton;
    @FXML
    private Hyperlink startHyperlink;
    @FXML
    private Hyperlink settingsHyperlink;

    @FXML
    void Quit() {
        // quits the game
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Your are going to quit the game");
        alert.setContentText("Are you sure? once you go you can't go back...");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
                Stage stage = (Stage) quitButton.getScene().getWindow();
                stage.close();
        }
    }

    @FXML
    void goSettings() {
        // go to settings page
        try {
            Stage stage = (Stage) settingsHyperlink.getScene().getWindow();
            //stage.close();
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../fxmlFiles/Settings.fxml"));
            Scene scene = new Scene(root,600,400);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            //stage.setTitle("Reversi OtherGameFiles.Game");
            stage.setScene(scene);
            stage.show();
        } catch (Exception c) {
            System.out.print("ERROR");
        }
    }

    @FXML
    void startGame() {
        // go to the game page and start the game!
        try {
            Stage stage = (Stage) startHyperlink.getScene().getWindow();
            //stage.close();
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../fxmlFiles/Game.fxml"));
            Scene scene = new Scene(root,600,400);
            stage.setScene(scene);
            stage.show();
        } catch (Exception c) {
            System.out.print("ERROR");
        }
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

