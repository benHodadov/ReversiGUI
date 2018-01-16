import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Scanner;

/**
 * Created by benho on 08/01/2018.
 */
public class Game {
    private Player p1;
    private Player p2;

    public Board getBoard() {
        return this.b;
    }

    private Board b;
    private GameLogic gl;

    Game(int boardSize) {
        this.p1 = new Player('X');
        this.p2 = new Player('O');
        this.b = new Board(boardSize);
        this.gl = new GameLogic();
    }


    public void run() {
        final Player[] playing = {p1};
        System.out.println("Start game:");
        System.out.println("player1: " + p1.getSign() + ", player2: " + p2.getSign() + "\n***********************");

        this.b.setOnMouseClicked(e -> {
        //this.b.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (!endGame()) {
                boolean isPlayed = this.playOneTurn(gl, b, playing[0]);
                if (isPlayed) {
                    playing[0] = this.otherPlayer(playing[0]);
                }
            } else {
                // game over
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Game finished!");
                alert.setHeaderText(findWinner());
                alert.setContentText("Player 1 score = " + getScore(p1) + "\nPlayer 2 score = " + getScore(p2));
                alert.showAndWait();
                b.print();
                this.findWinner();
                return;
            }
        });
    }



    public boolean playOneTurn(GameLogic gl, Board b, Player p) {
        System.out.println("Current board:");
        b.draw();
        b.setGridLinesVisible(true);
        b.print();

        System.out.println(p.getSign() + ": It's your move.\nYour possible moves: ");
        List<Position> v = gl.optionalMoves(b, p);

        // if any of the moves are legal return 0.
        if (v.size() == 0) {
            System.out.println("No possible moves. Play passes back to the other player. Press any key to continue.");
            //Scanner reader = new Scanner(System.in);
            //char c = reader.next().charAt(0);
            return true;
        }
        // print the options
        for (int i = 0; i < v.size(); i++) {
            Position pos = v.get(i);
            System.out.print("(" + pos.getRow() + "," + pos.getCol() + ")");
            if ((i + 1) != v.size()) {
                System.out.print(",");
            }
        }

        System.out.println("\nPlease enter your move row,col: ");

        final Position[] selectedPosition = {this.getPlace()};
        System.out.println("i=" + selectedPosition[0].getRow() + ",j=" + selectedPosition[0].getCol());
        //System.exit(0);

        final boolean[] isValid = {false};
        for (int i = 0; i < v.size(); i++) {
            if (selectedPosition[0].isEqual(v.get(i))) {
                isValid[0] = true;
                //play.

                this.putAndTurnOver(gl, b, selectedPosition[0].getRow(), selectedPosition[0].getCol(), p);
                b.draw();
                b.gridLinesVisibleProperty().set(true);
                return true;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Oops, not valid move");
        alert.setContentText("Please choose again");

        alert.showAndWait();
        return false;
    }

    public void putAndTurnOver(GameLogic gl,Board b, int r, int c, Player p) {
        b.put(r, c, p.getSign());

        this.turnDiscsFromLeft(gl, b, r, c, p);
        this.turnDiscsFromRight(gl, b, r, c, p);
        this.turnDiscsFromUp(gl, b, r, c, p);
        this.turnDiscsFromDown(gl, b, r, c, p);
        this.turnDiscsFromUpLeft(gl, b, r, c, p);
        this.turnDiscsFromUpRight(gl, b, r, c, p);
        this.turnDiscsFromDownLeft(gl, b, r, c, p);
        this.turnDiscsFromDownRight(gl, b, r, c, p);
    }

    public void turnDiscsFromLeft(GameLogic gl, Board b, int r, int c, Player p) {
        if (gl.checkLeft(b, r, c, p)) {
            for (int i = c - 1; i > 0; i--) {
                if (b.getSide(r, i) != ' ') {
                    if (b.getSide(r, i) != b.getSide(r, c)) {
                        b.put(r, i, p.getSign());
                    }
                }
                else {
                    return;
                }
            }
        }
    }

    public void turnDiscsFromRight(GameLogic gl, Board b, int r, int c, Player p) {
        if (gl.checkRight(b, r, c, p)) {
            for (int i = c + 1; i <= b.getSize(); i++) {
                if (b.getSide(r, i) != ' ') {
                    if (b.getSide(r, i) != b.getSide(r, c)) {
                        b.put(r, i, p.getSign());
                    }
                }
                else {
                    return;
                }
            }
        }
    }

    public void turnDiscsFromUp(GameLogic gl, Board b, int r, int c, Player p) {
        if (gl.checkUp(b, r, c, p)) {
            for (int i = r - 1; i > 0; i--) {
                if (b.getSide(i, c) != ' ') {
                    if (b.getSide(i, c) != b.getSide(r, c)) {
                        b.put(i, c, p.getSign());
                    }
                }
                else {
                    return;
                }
            }
        }
    }

    public void turnDiscsFromDown(GameLogic gl, Board b, int r, int c, Player p) {
        if (gl.checkDown(b, r, c, p)) {
            for (int i = r + 1; i <= b.getSize(); i++) {
                if (b.getSide(i, c) != ' ') {
                    if (b.getSide(i, c) != b.getSide(r, c)) {
                        b.put(i, c, p.getSign());
                    }
                }
                else {
                    return;
                }
            }
        }
    }

    public void turnDiscsFromUpLeft(GameLogic gl, Board b, int r, int c, Player p) {
        if (gl.checkUpLeft(b, r, c, p)) {
            for (int i = r - 1, j = c - 1; i > 0 && j > 0; i--, j--) {
                if (b.getSide(i, j) != ' ') {
                    if (b.getSide(i, j) != b.getSide(r, c)) {
                        b.put(i, j, p.getSign());
                    }
                }
                else {
                    return;
                }
            }
        }
    }

    public void turnDiscsFromUpRight(GameLogic gl, Board b, int r, int c, Player p) {
        if (gl.checkUpRight(b, r, c, p)) {
            for (int i = r - 1, j = c + 1; i > 0 && j <= b.getSize(); i--, j++) {
                if (b.getSide(i, j) != ' ') {
                    if (b.getSide(i, j) != b.getSide(r, c)) {
                        b.put(i, j, p.getSign());
                    }
                }
                else {
                    return;
                }
            }
        }
    }

    public void turnDiscsFromDownLeft(GameLogic gl, Board b, int r, int c, Player p) {
        if (gl.checkDownLeft(b, r, c, p)) {
            for (int i = r + 1, j = c - 1; i <= b.getSize() && j > 0; i++, j--) {
                if (b.getSide(i, j) != ' ') {
                    if (b.getSide(i, j) != b.getSide(r, c)) {
                        b.put(i, j, p.getSign());
                    }
                }
                else {
                    return;
                }
            }
        }
    }

    public void turnDiscsFromDownRight(GameLogic gl, Board b, int r, int c, Player p) {
        if (gl.checkDownRight(b, r, c, p)) {
            for (int i = r + 1, j = c + 1; i <= b.getSize() && j <= b.getSize(); i++, j++) {
                if (b.getSide(i, j) != ' ') {
                    if (b.getSide(i, j) != b.getSide(r, c)) {
                        b.put(i, j, p.getSign());
                    }
                }
                else {
                    return;
                }
            }
        }
    }

    public boolean endGame() {
        return ((this.gl.optionalMoves(this.b, this.p1).size() == 0)
                && (this.gl.optionalMoves(this.b, this.p2).size() == 0));
    }

    public int getScore(Player p) {
        int count = 0;
        for(int i = 1; i <= b.getSize(); i ++) {
            for (int j = 1; j <= b.getSize(); j++) {
                if (this.b.getSide(i, j) == p.getSign()) {
                    count++;
                }
            }
        }
        return count;
    }

    public String findWinner() {
        int countP1 = getScore(p1);
        int countP2 = getScore(p2);

        if (countP1 > countP2) {
            //System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXX\nPlayer 1 is the winner !!\nXXXXXXXXXXXXXXXXXXXXXXXXX");
            return "Player 1 is the winner !!";
        } else if (countP1 < countP2) {
            //System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOO\nPlayer 2 is the winner !!\nOOOOOOOOOOOOOOOOOOOOOOOOO");
            return "Player 2 is the winner !!";
        } else {
            //System.out.println("-------------------------\nIt's a draw !!\n-------------------------");
            return "It's a draw !!";
        }
    }

    public Player otherPlayer(Player p) {
        if (p.getSign() == this.p1.getSign()) {
            return this.p2;
        }
        return this.p1;
    }

    public Position getPlace() {
        return this.b.getClicked();
    }
}
