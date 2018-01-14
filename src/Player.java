/**
 * Created by Barak on 08-Jan-18.
 */
public class Player {
    char sign;

    public Player(char s) {
        this.sign = s;
    }

    public char getSign() {
        return this.sign;
    }

    public void print() {
        System.out.print(this.sign);
    }
}
