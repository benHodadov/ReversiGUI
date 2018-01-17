import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Ben and Barak on 08-Jan-18.
 */
public class Board extends GridPane {
    private char[][] board;
    private int size;
    private Position clicked;

    /**
     * A constructor.
     * gets the board's size
     * @param size int
     */
    public Board(int size) {
        // fill the board with ' '
        this.size = size;
        board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
        int x = size / 2;

        // put x/O in the middle
        put(x, x, 'O');
        put(x, x + 1, 'X');
        put(x + 1, x, 'X');
        put(x + 1, x + 1, 'O');

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Board.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        // load the board
        try {
            fxmlLoader.load();
        } catch (Exception exception) {
            System.out.println("Error on creating board");
        }
    }

    /**
     * The method puts the sign in the position given.
     * @param row int
     * @param col int
     * @param sign char
     */
    public void put(int row, int col, char sign) {
        board[row - 1][col - 1] = sign;
    }

    /**
     * The method returns the char in a position given.
     * @param row int
     * @param col int
     * @return charAtIJ
     */
    public char getSide(int row, int col) {
        return board[row - 1][col - 1];
    }

    /**
     * The method get for the board's size
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * The method draws the board in the gui.
     */
    public void draw() {
        // get the wanted colors
        Settings settings = new Settings();
        Color c1 = GameController.getColorByName(settings.player_1_color);
        Color c2 = GameController.getColorByName(settings.player_2_color);
        Color bg = GameController.getColorByName("lightGray");

        this.getChildren().clear();
        // calculate the cell's size
        double height = this.getPrefHeight();
        double width  = this.getPrefWidth();
        double cellHeight = height / board.length;
        double cellWidth  = width  / board.length;

        // paint the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                Rectangle r;
                if (board[i][j] == 'X') {
                    r = new Rectangle(i * cellHeight, j * cellWidth, cellWidth, cellHeight);
                    this.add(r, j, i);
                    r.setFill(c1);
                } else if (board[i][j] == 'O') {
                    r = new Rectangle(i * cellHeight, j * cellWidth, cellWidth, cellHeight);
                    this.add(r, j, i);
                    r.setFill(c2);
                } else {
                    r = new Rectangle(i * cellHeight, j * cellWidth, cellWidth, cellHeight);
                    this.add(r, j, i);
                    r.setFill(bg);
                }
                final int finalI = i;
                final int finalJ = j;
                r.addEventHandler(MouseEvent.MOUSE_CLICKED, b -> { this.clicked = new Position(finalI, finalJ); });
            }
        }
        this.setGridLinesVisible(true);
        this.setGridLinesVisible(false);
    }

    /**
     * The method returns the position clicked
     * @return positionClicked
     */
    public Position getClicked() {
        Position temp = this.clicked;
        return new Position(temp.getRow() + 1, temp.getCol() + 1);
    }

    /**
     * The method paints the board in the console.
     */
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
}
