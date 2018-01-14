import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by benho on 13/01/2018.
 */
public class GameController implements Initializable {
    //@FXML
    //private GridPane board;
    @FXML
    private Button goMenuButton;
    @FXML
    private Label firstPlayer;
    @FXML
    private Label secondPlayer;
    @FXML
    private Label currentPlayer;
    @FXML
    private Label score1;
    @FXML
    private Label score2;


    @FXML
    void goToMenu() {
        try {
            Stage stage = (Stage) goMenuButton.getScene().getWindow();
            //stage.close();
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root,600,400);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            //stage.setTitle("Reversi Game");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
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
        Settings settings = new Settings();
        firstPlayer.setText(settings.player_1_color + " player score:");
        secondPlayer.setText(settings.player_2_color + " player score:");

        currentPlayer.setText(settings.player_1_color);
        score1.setText("0");
        score2.setText("0");
    }
}
