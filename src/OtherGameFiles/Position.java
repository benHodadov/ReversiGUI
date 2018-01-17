package OtherGameFiles;

/**
 * Created by Ben and Barak on 08-Jan-18.
 */
public class Position {
    private int row;
    private int col;

    // constructor
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // getters
    int getRow() {
        return this.row;
    }
    int getCol() {
        return this.col;
    }

    /**
     * The method returns true if they are equal and false otherwise.
     * @param other OtherGameFiles.Position
     * @return isEqual
     */
    public boolean isEqual(Position other) {
        if ((this.getRow() == other.getRow()) && (this.getCol() == other.getCol())) {
            return true;
        }
        return false;
    }
}
