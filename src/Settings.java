import java.io.*;

/**
 * Created by Barak on 11-Jan-18.
 */
public class Settings {
    String player_1_color;
    String player_2_color;
    int    boardSize;

    public Settings() {
        BufferedReader is = null;

        try {
            File settings = new File("settings");
            is = new BufferedReader(new FileReader(settings));

            String data = is.readLine();
            String[] parts = data.split(":") ;
            this.player_1_color = parts[1];
            //System.out.println("Player 1 color is -" + this.player_1_color);

            data = is.readLine();
            parts = data.split(":") ;
            this.player_2_color = parts[1];
            //System.out.println("Player 2 color is -" + this.player_2_color);

            data = is.readLine();
            parts = data.split(":") ;
            this.boardSize = Integer.parseInt(parts[1]);
            //System.out.println("The size of the board is -" + this.boardSize);

        } catch (Exception e) {
            System.out.println("ERROR 404: file not found !");
        } finally {
            if (is != null) {
                // Exception might have happened at constructor.
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("Failed closing the file!");
                }
            }
        }
    }

    public void setPlayer_1_color(String player_1_color) {
        this.player_1_color = player_1_color;
    }
    public void setPlayer_2_color(String player_2_color) {
        this.player_2_color = player_2_color;
    }
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void save() {
        OutputStreamWriter os = null;

        try {
            File settings = new File("settings");
            os = new OutputStreamWriter(new FileOutputStream(settings));

            //os.write("Starting player:" + this.starting_player + "\n");
            os.write("Player 1 color:" + this.player_1_color + "\n");
            os.write("Player 2 color:" + this.player_2_color + "\n");
            os.write("Board size:" + this.boardSize + "\n");

        } catch (IOException e) {
            System.out.println("Something went wrong while writing");
        }  finally {
            if (os != null) {
                // Exception might have happened at constructor.
                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println("Failed closing the file!");
                }
            }
        }
    }
}
