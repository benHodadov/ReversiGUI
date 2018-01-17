import javafx.scene.control.Alert;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Ben and Barak on 08/01/2018.
 */
public class Game {
    private Player p1;
    private Player p2;
    private Board b;
    private GameLogic gl;

    /**
     * A constructor.
     * @param boardSize int
     */
    Game(int boardSize) {
        this.p1 = new Player('X');
        this.p2 = new Player('O');
        this.b = new Board(boardSize);
        this.gl = new GameLogic();
    }

    /**
     * The method runs the game. later copied to the gameController.
     */
    public void run() {
        final Player[] playing = {p1};
        System.out.println("Start game:");
        System.out.println("player1: " + p1.getSign() + ", player2: " + p2.getSign() + "\n***********************");

        //this.b.setOnMouseClicked(e -> {
            if (!endGame()) {
                boolean isPlayed = this.playOneTurn(gl, b, playing[0]);
                if (isPlayed) {
                    //GameController.score1.setText(String.valueOf(getScore(playing[0])));
                    //GameController.score2.setText(String.valueOf(getScore(otherPlayer(playing[0]))));
                    playing[0] = this.otherPlayer(playing[0]);

                }
            } else {
                // game over
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Game finished!");
                alert.setHeaderText(findWinner());
                alert.setContentText("Player 1 score = " + getScore(p1) + "\nPlayer 2 score = " + getScore(p2));
                alert.showAndWait();
                this.findWinner();
            }
        //});
    }

    /**
     * The method plays one turn for a player. returns true if he played and false otherwise.
     * @param gl GameLogic
     * @param b Board
     * @param p Player
     * @return isPlayed
     */
    public boolean playOneTurn(GameLogic gl, Board b, Player p) {
        b.draw();
        List<Position> v = gl.optionalMoves(b, p);

        // if any of the moves are legal return 0.
        if (v.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Oops, there is no valid moves for you");
            alert.setContentText("Hope your opponent will make a mistake");

            alert.showAndWait();
            // the player has no moves sp we need to change the playing player. return true,
            return true;
        }
        final Position[] selectedPosition = {this.getPlace()};
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
        this.b.setGridLinesVisible(false);
        this.b.setGridLinesVisible(true);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Oops, the chosen move is not valid");
        alert.setContentText("Please choose again");

        alert.showAndWait();
        return false;
    }


    // the following methods turn the disks if needed.
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

    /**
     * The method returns true if the game is over and false otherwise.
     * @return isOver
     */
    public boolean endGame() {
        return ((this.gl.optionalMoves(this.b, this.p1).size() == 0)
                && (this.gl.optionalMoves(this.b, this.p2).size() == 0));
    }

    /**
     * The method gets a player and returns his score.
     * @param p Player
     * @return scoreP
     */
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

    /**
     * The method returns a string with the winning player. ready to be printed.
     * @return winnerMSG
     */
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

    /**
     * The method gets a player and return the other one.
     * @param p Player
     * @return otherPlayer
     */
    public Player otherPlayer(Player p) {
        if (p.getSign() == this.p1.getSign()) {
            return this.p2;
        }
        return this.p1;
    }

    public Position getPlace() {
        return this.b.getClicked();
    }

    // getters
    public Board getBoard() {
        return this.b;
    }
    public Player getP1() {
        return p1;
    }
    public Player getP2() {
        return p2;
    }
    public GameLogic getGl() {
        return gl;
    }





    // the next 2 methods worked in the console. not important for this task.
    public void consoleRun() {
        Player playing = p1;
        System.out.println("Start game:");
        System.out.println("player1: " + p1.getSign() + ", player2: " + p2.getSign() + "\n***********************");

        while (!this.endGame()) {
            this.playOneTurn(gl, b, playing);
            playing = this.otherPlayer(playing);
        }
        b.print();
        this.findWinner();
    }

    public void consolePlayOneTurn(GameLogic gl, Board b, Player p) {
        System.out.println("Current board:");
        b.print();

        System.out.println(p.getSign() + ": It's your move.\nYour possible moves: ");
        List<Position> v = gl.optionalMoves(b, p);

        // if any of the moves are legal return 0.
        if (v.size() == 0) {
            System.out.println("No possible moves. Play passes back to the other player. Press any key to continue.");
            Scanner reader = new Scanner(System.in);
            char c = reader.next().charAt(0);
            return;
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
        int r, c;
        Scanner scn = new Scanner(System.in);
        r = scn.nextInt();
        c = scn.nextInt();
        Position selectedPosition = new Position(r, c);


        boolean isValid = false;
        for (int i = 0; i < v.size(); i++) {
            if (selectedPosition.isEqual(v.get(i))) {
                isValid = true;
                //play.
                this.putAndTurnOver(gl, b, r, c, p);
            }
        }

        while (!isValid) {
            System.out.print("The selected position is not valid.\nPlease enter your move row,col: ");
            scn = new Scanner(System.in);
            r = scn.nextInt();
            c = scn.nextInt();
            selectedPosition = new Position(r, c);
            for (int i = 0; i < v.size(); i++) {
                if (selectedPosition.isEqual(v.get(i))) {
                    isValid = true;
                    //play.
                    this.putAndTurnOver(gl, b, r, c, p);
                    break;
                }
            }
        }
    }
}
