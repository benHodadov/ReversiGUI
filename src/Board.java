import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

/**
 * Created by Barak on 08-Jan-18.
 */
public class Board {
    private char[][] board;

    public Board() {
        board = new char[main.length][main.length];
        for (int i = 0; i < main.length; i++) {
            for (int j = 0; j < main.length; j++) {
                board[i][j] = ' ';
            }
        }

        put(4, 4, 'O');
        put(4, 5, 'X');
        put(5, 4, 'X');
        put(5, 5, 'O');
    }

    public void put(int row, int col, char sign) {
        board[row - 1][col - 1] = sign;
    }

    public char getSide(int row, int col) {
        return board[row - 1][col - 1];
    }

    public void print() {
        System.out.println();
        System.out.print(" | ");
        for (int i = 1; i <= main.length; i++) {
            System.out.print(i + " | ");
        }
        System.out.println();
        System.out.println("----------------------------------");

        for (int i = 0; i < main.length; i++) {
            System.out.print((i+1) + "| ");
            for (int j = 0; j < main.length; j++) {
                if (board[i][j] == 'O') {
                    System.out.print("O | ");
                } else if (board[i][j] == 'X') {
                    System.out.print("X | ");
                } else {
                    System.out.print("  | ");
                }
            }
            System.out.println("\n----------------------------------");
        }
    }
}
