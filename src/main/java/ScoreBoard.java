import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

//класс - табло, на котором находятся текущий и лучший счет, медаль
public class ScoreBoard extends Pane {

    //размер табло
    private static final double WIDTH = 229;
    private static final double HEIGHT = 118;
    //файл изображения табло
    private static final String SCOREBOARD_FILE = "images/ScoreBoard.png";

    Image scoreBoardImg;
    ImageView scoreBoardImgV;

    //текущий счет
    ScoreBar scoreBar;
    //лучший счет
    ScoreBar highScoreBar;

    HighScoreManager highScoreManager = new HighScoreManager();
    Medal medal;


    public ScoreBoard(int score) {
        scoreBoardImg = new Image(getClass().getResourceAsStream(SCOREBOARD_FILE));
        scoreBoardImgV = new ImageView(scoreBoardImg);

        scoreBoardImgV.setViewport(new Rectangle2D(0, 0, 229, 118));

        scoreBoardImgV.setFitWidth(WIDTH);
        scoreBoardImgV.setFitHeight(HEIGHT);

        getChildren().add(scoreBoardImgV);


        scoreBar = new ScoreBar(this, 175, 35);

        scoreBar.number1.scoreImgV.setFitWidth(Number.WIDTH * 0.5);
        scoreBar.number1.scoreImgV.setFitHeight(Number.HEIGHT * 0.5);
        scoreBar.number2.scoreImgV.setFitWidth(Number.WIDTH * 0.5);
        scoreBar.number2.scoreImgV.setFitHeight(Number.HEIGHT * 0.5);
        scoreBar.number3.scoreImgV.setFitWidth(Number.WIDTH * 0.5);
        scoreBar.number3.scoreImgV.setFitHeight(Number.HEIGHT * 0.5);

        scoreBar.showScore(score);


        highScoreBar = new ScoreBar(this, 175, 75);

        highScoreBar.number1.scoreImgV.setFitWidth(Number.WIDTH * 0.5);
        highScoreBar.number1.scoreImgV.setFitHeight(Number.HEIGHT * 0.5);
        highScoreBar.number2.scoreImgV.setFitWidth(Number.WIDTH * 0.5);
        highScoreBar.number2.scoreImgV.setFitHeight(Number.HEIGHT * 0.5);
        highScoreBar.number3.scoreImgV.setFitWidth(Number.WIDTH * 0.5);
        highScoreBar.number3.scoreImgV.setFitHeight(Number.HEIGHT * 0.5);

        highScoreManager.createFile();
        highScoreManager.loadScoreFile();
        highScoreBar.showScore(highScoreManager.highScore.getHighScore());

        if (score >= highScoreManager.highScore.getHighScore()) {
            if (score == highScoreManager.highScore.getHighScore()) {
                medal = new SilverMedal();
            }
            if (score > highScoreManager.highScore.getHighScore()) {
                highScoreManager.updateScoreFile(score);
                medal = new GoldMedal();
            }

            medal.setTranslateX(27);
            medal.setTranslateY(43);
            this.getChildren().add(medal);
        }
    }

    public static double getWIDTH() {
        return WIDTH;
    }
}
