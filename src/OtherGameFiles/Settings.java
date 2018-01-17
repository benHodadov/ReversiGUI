package OtherGameFiles;

import java.io.*;

/**
 * Created by Ben and Barak on 11-Jan-18.
 */
public class Settings {
    public String player_1_color;
    public String player_2_color;
    public int    boardSize;

    /**
     * A constructor.
     */
    public Settings() {
        BufferedReader is = null;

        try {
            File settings = new File("settings");
            is = new BufferedReader(new FileReader(settings));

            String data = is.readLine();
            String[] parts = data.split(":") ;
            this.player_1_color = parts[1];

            data = is.readLine();
            parts = data.split(":") ;
            this.player_2_color = parts[1];

            data = is.readLine();
            parts = data.split(":") ;
            this.boardSize = Integer.parseInt(parts[1]);

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

    // setters
    public void setPlayer_1_color(String player_1_color) {
        this.player_1_color = player_1_color;
    }
    public void setPlayer_2_color(String player_2_color) {
        this.player_2_color = player_2_color;
    }
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    /**
     * The method save's the settings in the file.
     * means that writes them in the file.
     */
    public void save() {
        OutputStreamWriter os = null;

        try {
            File settings = new File("settings");
            os = new OutputStreamWriter(new FileOutputStream(settings));

            //os.write("Starting player:" + this.starting_player + "\n");
            os.write("OtherGameFiles.Player 1 color:" + this.player_1_color + "\n");
            os.write("OtherGameFiles.Player 2 color:" + this.player_2_color + "\n");
            os.write("OtherGameFiles.Board size:" + this.boardSize + "\n");

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
