import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

/**
 * Created by Barak on 08-Jan-18.
 */
public class Board {
    private char[][] board;
    private int size;

    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
        int x = size / 2;

        put(x, x, 'O');
        put(x, x + 1, 'X');
        put(x + 1, x, 'X');
        put(x + 1, x + 1, 'O');
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
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " | ");
        }
        System.out.println();
        System.out.println("----------------------------------");

        for (int i = 0; i < size; i++) {
            System.out.print((i+1) + "| ");
            for (int j = 0; j < size; j++) {
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

    public int getSize() {
        return size;
    }
}
