package OtherGameFiles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben and Barak on 08-Jan-18.
 */
public class GameLogic {
    public GameLogic() {

    }

    /**
     * The method returns the player's optional moves in a list.
     * @param b OtherGameFiles.Board
     * @param p OtherGameFiles.Player
     * @return optionalMoves
     */
    public List<Position> optionalMoves(Board b, Player p)  {
        List<Position> om = new ArrayList<Position>();

        for (int i = 1; i <= b.getSize(); i++) {
            for(int j =1; j <= b.getSize(); j++) {
                if (b.getSide(i, j) != ' ') {
                    continue;
                }
                if (this.isValidMove(b, i, j, p)) {
                    Position pos = new Position(i, j);
                    om.add(pos);
                }
            }
        }
        return om;
    }

    // the method checks from all sides.
    public boolean isValidMove(Board b, int row, int col, Player p)  {
        return this.checkLeft(b, row, col, p) || this.checkRight(b, row, col, p) ||
                this.checkUp(b, row, col, p) || this.checkDown(b, row, col, p) ||
                this.checkUpLeft(b, row, col, p) || this.checkUpRight(b, row, col, p) ||
                this.checkDownLeft(b, row, col, p) || this.checkDownRight(b, row, col, p);
    }

    // the following methods check from every direction.
    public boolean checkLeft(Board b, int row, int col, Player p){
        if (col != 1) {
            // if (col == 1) then return false.
            // if (b.getSide(row, col - 1) == b.getSide(row, col)) return false.
            if (b.getSide(row, col -1) != ' ') {
                if (b.getSide(row, col - 1) != p.getSign()) {
                    for (int i = col - 2; i > 0; i--) {
                        if (b.getSide(row, i) != b.getSide(row, i + 1)) {
                            if (b.getSide(row, i) != ' ') {
                                //cout << "left" << endl;
                                return true;
                            }
                            //else - exit for loop and return false.
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkRight(Board b, int row, int col, Player p) {
        if (col != b.getSize()) {
            // if (col == LENGTH) then return false.
            // if (b.getSide(row, col + 1) == b.getSide(row, col)) return false.
            if (b.getSide(row, col + 1) != ' ') {
                if (b.getSide(row, col + 1) != p.getSign()) {
                    for (int i = col + 2; i <= b.getSize(); i++) {
                        if (b.getSide(row, i) != b.getSide(row, i - 1)) {
                            if (b.getSide(row, i) != ' ') {
                                //cout << "right" << endl;
                                return true;
                            }
                            //else - exit for loop and return false.
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkUp(Board b, int row, int col, Player p) {
        if (row != 1) {
            // if (row == 1) then return false.
            // if (b.getSide(row - 1, col) == b.getSide(row, col)) return false.
            if (b.getSide(row - 1, col) != ' ') {
                if (b.getSide(row - 1, col) != p.getSign()) {
                    for (int i = row - 2; i > 0; i--) {
                        if (b.getSide(i, col) != b.getSide(i + 1, col)) {
                            if (b.getSide(i, col) != ' ') {
                                //cout << "up" << endl;
                                return true;
                            }
                            //else - exit for loop and return false.
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkDown(Board b, int row, int col, Player p) {
        if (row != b.getSize()) {
            // if (row == LENGTH) then return false.
            // if (b.getSide(row + 1, col) == b.getSide(row, col)) return false.
            if (b.getSide(row + 1, col) != ' ') {
                if (b.getSide(row + 1, col) != p.getSign()) {
                    for (int i = row + 2; i <= b.getSize(); i++) {
                        if (b.getSide(i, col) != b.getSide(i - 1, col)) {
                            if (b.getSide(i, col) != ' ') {
                                //cout << "down" << endl;
                                return true;
                            }
                            //else - exit for loop and return false.
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkUpLeft(Board b, int row, int col, Player p) {
        if ((row != 1) && (col != 1)) {
            // if ((row == 1) && (col == 1)) then return false.
            // if (b.getSide(row, col - 1) == b.getSide(row, col)) return false.
            if (b.getSide(row - 1, col - 1) != ' ') {
                if (b.getSide(row - 1, col - 1) != p.getSign()) {
                    for (int i = row -2, j = col - 2; i > 0 && j > 0; i--, j--) {
                        if (b.getSide(i, j) != b.getSide(i + 1, j + 1)) {
                            if (b.getSide(i, j) != ' ') {
                                return true;
                            }
                            //else - exit for loop and return false.
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkUpRight(Board b, int row, int col, Player p)  {
        if ((row != 1) && (col != b.getSize())) {
            // if (row != 1) && (col != LENGTH) then return false.
            // if (b.getSide(row - 1, col + 1) == b.getSide(row, col)) return false.
            if (b.getSide(row - 1, col + 1) != ' ') {
                if (b.getSide(row - 1, col + 1) != p.getSign()) {
                    for (int i = row - 2, j = col + 2; i > 0 && j <= b.getSize(); i--, j++) {
                        if (b.getSide(i, j) != b.getSide(i + 1, j -1)) {
                            if (b.getSide(i, j) != ' ') {
                                //cout << "up-right" << endl;
                                return true;
                            }
                            //else - exit for loop and return false.
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkDownLeft(Board b, int row, int col, Player p)  {
        if ((row != b.getSize()) && (col != 1)) {
            // if (row != LENGTH) && (col != 1) then return false.
            // if (b.getSide(row + 1, col - 1) == b.getSide(row, col)) return false.
            if (b.getSide(row + 1, col -1) != ' ') {
                if (b.getSide(row + 1, col - 1) != p.getSign()) {
                    for (int i = row + 2, j = col - 2; i <= b.getSize() && j > 0; i++, j--) {
                        if (b.getSide(i, j) != b.getSide(i - 1, j + 1)) {
                            if (b.getSide(i, j) != ' ') {
                                //cout << "down-left" << endl;
                                return true;
                            }
                            //else - exit for loop and return false.
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkDownRight(Board b, int row, int col, Player p) {
        if ((row != b.getSize()) && (col != b.getSize())) {
            // if ((row == LENGTH) && (col == LENGTH)) then return false.
            // if (b.getSide(row + 1, col + 1) == b.getSide(row, col)) return false.
            if (b.getSide(row + 1, col + 1) != ' ') {
                if (b.getSide(row + 1, col + 1) != p.getSign()) {
                    for (int i = row + 2, j = col + 2 ; i <= b.getSize() && j <= b.getSize(); i++, j++) {
                        if (b.getSide(i, j) != b.getSide(i - 1, j - 1)) {
                            if (b.getSide(i, j) != ' ') {
                                //cout << "down-right" << endl;
                                return true;
                            }
                            //else - exit for loop and return false.
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }
}
