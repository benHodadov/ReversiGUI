import java.util.List;
import java.util.Scanner;

/**
 * Created by benho on 08/01/2018.
 */
public class Game {
    Player p1;
    Player p2;
    Board b;
    GameLogic gl;

    Game() {
        this.p1 = new Player('X');
        this.p2 = new Player('O');
        this.b = new Board();
        this.gl = new GameLogic();
    }

    public void run() {
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

    public void playOneTurn(GameLogic gl, Board b, Player p) {
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
            for (int i = c + 1; i <= main.length; i++) {
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
            for (int i = r + 1; i <= main.length; i++) {
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
            for (int i = r - 1, j = c + 1; i > 0 && j <= main.length; i--, j++) {
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
            for (int i = r + 1, j = c - 1; i <= main.length && j > 0; i++, j--) {
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
            for (int i = r + 1, j = c + 1; i <= main.length && j <= main.length; i++, j++) {
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

    public void findWinner() {
        int countP1 = 0, countP2 = 0;
        for(int i = 1; i <= main.length; i ++) {
            for (int j = 1; j <= main.length; j++) {
                if (this.b.getSide(i, j) == 'X') {
                    countP1++;
                }
			else if (this.b.getSide(i, j) == 'O') {
                    countP2++;
                }
            }
        }

        if (countP1 > countP2) {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXX\nPlayer 1 is the winner !!\nXXXXXXXXXXXXXXXXXXXXXXXXX");
        }
        else if (countP1 < countP2) {
            System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOO\nPlayer 2 is the winner !!\nOOOOOOOOOOOOOOOOOOOOOOOOO");
        } else {
            System.out.println("-------------------------\nIt's a draw !!\n-------------------------");
        }
    }

    public Player otherPlayer(Player p) {
        if (p.getSign() == (this.p1).getSign()) {
            return this.p2;
        }
        return this.p1;
    }
}
