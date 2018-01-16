import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

import static java.awt.Color.*;

/**
 * Created by benho on 11/01/2018.
 */
public class SettingsController implements Initializable {
    private ObservableList<Integer> sizesList = FXCollections.observableArrayList
            (4, 6, 8, 10, 12, 14, 16, 18, 20);
    private ObservableList<String> colorsList = FXCollections.observableArrayList
            ("black", "blue", "cyan", "darkGray", "gray", "green",
                    "lightGray", "magenta", "orange", "pink", "red", "white", "yellow");

    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;
    @FXML
    private ChoiceBox<Integer> boardSizeBox;
    @FXML
    private ChoiceBox<String> firstPlayerColorBox;
    @FXML
    private ChoiceBox<String> secondPlayerColorBox;

    private Settings settings = new Settings();

    @FXML
    void save() {
        try {
            // save the changes
            settings.setBoardSize(boardSizeBox.getValue());
            settings.setPlayer_1_color(firstPlayerColorBox.getValue());
            settings.setPlayer_2_color(secondPlayerColorBox.getValue());
            settings.save();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Settings successfully saved");
            alert.setContentText("Let's play!");

            alert.showAndWait();

            Stage stage = (Stage) saveButton.getScene().getWindow();
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root,600,400);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            //stage.setTitle("Reversi Game");
            stage.setScene(scene);
            stage.show();
        } catch (Exception c) {
            System.out.print("ERROR");
        }
    }

    @FXML
    void back() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root,600,400);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            //stage.setTitle("Reversi Game");
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
        // set the values to the choice boxes
        boardSizeBox.setItems(sizesList);
        firstPlayerColorBox.setItems(colorsList);
        secondPlayerColorBox.setItems(colorsList);


        // set the default value tot the choice boxes
        boardSizeBox.setValue(settings.boardSize);
        firstPlayerColorBox.setValue(settings.player_1_color);
        secondPlayerColorBox.setValue(settings.player_2_color);
    }
}

