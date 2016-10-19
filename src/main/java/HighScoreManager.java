import org.apache.log4j.Logger;

import java.io.*;

//класс управляет лучшим счетом
public class HighScoreManager {
    //логгер
    final static Logger logger = Logger.getLogger(HighScoreManager.class);

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
                logger.error("[Create HighScore File] IO Error: ", e);
            }
        }
    }

    //загружает лучший счет из файла
    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            highScore = (HighScore) inputStream.readObject();
        } catch (FileNotFoundException e) {
            logger.error("[Load HighScore File] FNF Error: ", e);
        } catch (IOException e) {
            logger.error("[Load HighScore File] IO Error: ", e);
        } catch (ClassNotFoundException e) {
            logger.error("[Load HighScore File] CNF Error: ", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error("[Load HighScore File] IO Error: ", e);
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
            logger.error("[Update HighScore File] FNF Error: ", e);
        } catch (IOException e) {
            logger.error("[Update HighScore File] IO Error: ", e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                logger.error("[Update HighScore File] IO Error: ", e);
            }
        }
    }

    public HighScoreManager() {
        highScore = new HighScore();
    }
}
