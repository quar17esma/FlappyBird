import java.io.*;

//Лучший счет
public class HighScore implements Serializable {

    private int highScore;

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
