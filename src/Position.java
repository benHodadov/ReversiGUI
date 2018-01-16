/**
 * Created by Barak on 08-Jan-18.
 */
public class Position {
    int row;
    int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    int getRow() {
        return this.row;
    }
    int getCol() {
        return this.col;
    }

    public void print() {
        System.out.print("(" + this.row + "," + this.col + ")");
    }

    public boolean isEqual(Position other) {
        if ((this.getRow() == other.getRow()) && (this.getCol() == other.getCol())) {
            return true;
        }
        return false;
    }
}
