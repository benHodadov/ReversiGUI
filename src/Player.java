/**
 * Created by Ben and Barak on 08-Jan-18.
 */
public class Player {
    char sign;

    /**
     * A constructor.
     * @param s char
     */
    public Player(char s) {
        this.sign = s;
    }

    /**
     * The method get for the player's sign.
     * @return sign
     */
    public char getSign() {
        return this.sign;
    }

    /**
     * Print the player.
     */
    public void print() {
        System.out.print(this.sign);
    }
}
