import java.io.*;

public class HighScoreManager {
    HighScore highScore = new HighScore();
    private static final String HIGHSCORE_FILE = "high_score.dat";
    File scoreFile;
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;


    public void createFile(){
        scoreFile = new File("src/main/resources/" + HIGHSCORE_FILE);

        if (!scoreFile.exists()){
            try {
                scoreFile.createNewFile();
                System.out.println(scoreFile.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("[Create] IO Error: " + e.getMessage());
            }
        }
    }

    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream("src/main/resources/" + HIGHSCORE_FILE));
            highScore = (HighScore) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[Load] FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Load] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Load] CNF Error: " + e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Load] IO Error: " + e.getMessage());
            }
        }
    }

    public void updateScoreFile(int score) {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/" + HIGHSCORE_FILE));
            highScore.setHighScore(score);
            outputStream.writeObject(highScore);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }


}
