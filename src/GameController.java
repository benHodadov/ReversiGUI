import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ben hodadov on 13/01/2018.
 */
public class GameController implements Initializable {
    private Game game;
    @FXML
    GridPane boardPane;
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
        game = new Game(settings.boardSize);
        firstPlayer.setText(settings.player_1_color + " player score:");
        secondPlayer.setText(settings.player_2_color + " player score:");

        currentPlayer.setText(settings.player_1_color);
        score1.setText("2");
        score2.setText("2");

        //double height = board.getScene().getWindow().getHeight();
        //double width  = board.getScene().getWindow().getWidth();
        //double cellSize = Math.min(height, width) / settings.boardSize;
        game.getBoard().setPrefWidth(350);
        game.getBoard().setPrefHeight(350);
        double h = 350 / settings.boardSize;
        double w = 350 / settings.boardSize;
        for (int i = 0; i < settings.boardSize; i++) {
            RowConstraints row = new RowConstraints(h);
            ColumnConstraints col = new ColumnConstraints(w);
            game.getBoard().getRowConstraints().add(row);
            game.getBoard().getColumnConstraints().add(col);
        }
        //game.getBoard().getColumnConstraints().remove(0);
        boardPane.getChildren().add(0, game.getBoard());

        // draw board
        game.getBoard().draw();


        //get the place pressed
        game.getBoard().addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            Position p = game.getBoard().getClicked();
        });

        /* board resize
            game.getBoard().widthProperty().addListener((observable, oldValue, newValue) -> {
                double boardNewWidth = newValue.doubleValue() - 120;
                game.getBoard().setPrefWidth(boardNewWidth);
                game.getBoard().draw(c1, c2, bg);
            });

            game.getBoard().heightProperty().addListener((observable, oldValue, newValue) -> {
                game.getBoard().setPrefHeight(newValue.doubleValue());
                game.getBoard().draw(c1, c2, bg);
            });*/


            // when finished
            runGame();
    }

    public void runGame() {
        this.game.run();
    }

    public static Color getColorByName(String name) {
        switch (name) {
            case "black":
                return Color.BLACK;
            case "blue":
                return Color.BLUE;
            case "cyan":
                return Color.CYAN;
            case "darkGray":
                return Color.DARKGRAY;
            case "gray":
                return Color.GRAY;
            case "green":
                return Color.GREEN;
            case "lightGray":
                return Color.LIGHTGRAY;
            case "magenta":
                return Color.MAGENTA;
            case "orange":
                return Color.ORANGE;
            case "pink":
                return Color.PINK;
            case "red":
                return Color.RED;
            case "white":
                return Color.WHITE;
            case "yellow":
                return Color.YELLOW;
            default:
                return Color.BLACK;
        }
    }
}
