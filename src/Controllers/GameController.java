package Controllers;

import OtherGameFiles.Game;
import OtherGameFiles.Player;
import OtherGameFiles.Settings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by ben hodadov on 13/01/2018.
 */
public class GameController implements Initializable {
    private Game game;
    @FXML
    private GridPane boardPane;
    @FXML
    private Button goMenuButton;
    @FXML
    private Label firstPlayer;
    @FXML
    private Label secondPlayer;
    @FXML
    public Label currentPlayer;
    @FXML
    public Label score1;
    @FXML
    public Label score2;

    @FXML
    void goToMenu() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You are going to leave the game");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                Stage stage = (Stage) goMenuButton.getScene().getWindow();
                //stage.close();
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../fxmlFiles/Menu.fxml"));
                Scene scene = new Scene(root,600,400);
                //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                //stage.setTitle("Reversi OtherGameFiles.Game");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                System.out.print("ERROR");
            }
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
        /*game.getBoard().addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            OtherGameFiles.Position p = game.getBoard().getClicked();
        });

        //board resize
            /*game.getBoard().widthProperty().addListener((observable, oldValue, newValue) -> {
                double boardNewWidth = newValue.doubleValue() - 120;
                game.getBoard().setPrefWidth(boardNewWidth);
                game.getBoard().draw();
            });

            /*game.getBoard().heightProperty().addListener((observable, oldValue, newValue) -> {
                game.getBoard().setPrefHeight(newValue.doubleValue());
                game.getBoard().draw();
            });*/

        // when finished
        runGame();
    }

    public void runGame() {
        final Player[] playing = {game.getP1()};
        Settings s = new Settings();

        this.game.getBoard().setGridLinesVisible(true);
        this.game.getBoard().setOnMouseClicked(e -> {
            if (!game.endGame()) {
            boolean isPlayed = this.game.playOneTurn(game.getGl(), game.getBoard(), playing[0]);
            score1.setText(String.valueOf(game.getScore(game.getP1())));
            score2.setText(String.valueOf(game.getScore(game.getP2())));
            if (isPlayed) {
                playing[0] = this.game.otherPlayer(playing[0]);
                String playerColor = (playing[0].getSign() == 'X')? s.player_1_color : s.player_2_color;
                currentPlayer.setText(playerColor);
            }
        } else {
            // game over
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Game finished!");
            alert.setHeaderText(game.findWinner());
            alert.setContentText(s.player_1_color + " player score = " + game.getScore(game.getP1()) +
                    "\n" + s.player_2_color + " player score = " + game.getScore(game.getP2()));
            alert.showAndWait();

            // now go to the menu
                try {
                    Stage stage = (Stage) goMenuButton.getScene().getWindow();
                    AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../fxmlFiles/Menu.fxml"));
                    Scene scene = new Scene(root,600,400);
                    //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                    //stage.setTitle("Reversi OtherGameFiles.Game");
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception c) {
                    System.out.print("ERROR");
                }
        }
        });
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
