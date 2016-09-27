import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class ScoreBoard  extends Pane {
    Image scoreBoardImg;
    ImageView scoreBoardImgV;
    static final double WIDTH = 229;
    static final double HEIGHT = 118;
    ScoreBar scoreBar;
    ScoreBar highScoreBar;
    HighScoreManager highScoreManager = new HighScoreManager();


    public ScoreBoard(int score) {                                                             //общее изображение
        scoreBoardImg = new Image(getClass().getResourceAsStream("images/ScoreBoard.png"));
        scoreBoardImgV = new ImageView(scoreBoardImg);
        scoreBoardImgV.setViewport(new Rectangle2D(0,0,229,118));

        scoreBoardImgV.setFitWidth(WIDTH);
        scoreBoardImgV.setFitHeight(HEIGHT);

        getChildren().add(scoreBoardImgV);

        scoreBar = new ScoreBar(this, 175,35);

        scoreBar.number1.scoreImgV.setFitWidth(Number.WIDTH*0.5);
        scoreBar.number1.scoreImgV.setFitHeight(Number.HEIGHT*0.5);
        scoreBar.number2.scoreImgV.setFitWidth(Number.WIDTH*0.5);
        scoreBar.number2.scoreImgV.setFitHeight(Number.HEIGHT*0.5);
        scoreBar.number3.scoreImgV.setFitWidth(Number.WIDTH*0.5);
        scoreBar.number3.scoreImgV.setFitHeight(Number.HEIGHT*0.5);

        scoreBar.showScore(score);

        highScoreBar= new ScoreBar(this, 175,75);

        highScoreBar.number1.scoreImgV.setFitWidth(Number.WIDTH*0.5);
        highScoreBar.number1.scoreImgV.setFitHeight(Number.HEIGHT*0.5);
        highScoreBar.number2.scoreImgV.setFitWidth(Number.WIDTH*0.5);
        highScoreBar.number2.scoreImgV.setFitHeight(Number.HEIGHT*0.5);
        highScoreBar.number3.scoreImgV.setFitWidth(Number.WIDTH*0.5);
        highScoreBar.number3.scoreImgV.setFitHeight(Number.HEIGHT*0.5);

        highScoreManager.createFile();
        highScoreManager.loadScoreFile();
        highScoreBar.showScore(highScoreManager.highScore.getHighScore());

        if (score> highScoreManager.highScore.getHighScore()){
            highScoreManager.updateScoreFile(score);
        }

    }
}
