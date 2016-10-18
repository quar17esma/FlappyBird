import java.io.*;

//класс управляет лучшим счетом
public class HighScoreManager {

    //файл хранения лучшего счета
    private static final String HIGHSCORE_FILE = "high_score.dat";

    private File scoreFile;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    HighScore highScore;

    //метод создает файл с лучшим счетом если он не создан
    public void createFile(){
        scoreFile = new File(HIGHSCORE_FILE);

        if (!scoreFile.exists()){
            try {
                scoreFile.createNewFile();
            } catch (IOException e) {
                System.out.println("[Create] IO Error: " + e.getMessage());
            }
        }
    }

    //загружает лучший счет из файла
    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
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

//перезаписывает лучший счет в файл
    public void updateScoreFile(int score) {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
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

    public HighScoreManager() {
        highScore = new HighScore();
    }
}
