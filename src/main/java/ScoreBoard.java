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

    //изображение табло
    private Image scoreBoardImg;
    private ImageView scoreBoardImgV;

    //текущий счет
    private ScoreBar scoreBar;
    private int score;

    //лучший счет
    private ScoreBar highScoreBar;
    private HighScoreManager highScoreManager;

    //медаль
    private Medal medal;

    public ScoreBoard(int score) {
        this.score = score;
    }

    //создает табло и добавляет в него соответствующие обькты
    public void createScoreBoard(){
        addScoreBoardImg();
        addScoreBar();
        addHighScoreBar();
        addMedal();
    }

    //задает изображение табло
    private void addScoreBoardImg(){
        scoreBoardImg = new Image(getClass().getResourceAsStream(SCOREBOARD_FILE));

        scoreBoardImgV = new ImageView(scoreBoardImg);
        scoreBoardImgV.setViewport(new Rectangle2D(0, 0, 229, 118));
        scoreBoardImgV.setFitWidth(WIDTH);
        scoreBoardImgV.setFitHeight(HEIGHT);

        getChildren().add(scoreBoardImgV);
    }

    //добавляет текущий счет на табло
    private void addScoreBar(){
        scoreBar = new ScoreBar(this, 175, 35);

        scoreBar.number1.scoreImgV.setFitWidth(Number.WIDTH * 0.5);
        scoreBar.number1.scoreImgV.setFitHeight(Number.HEIGHT * 0.5);
        scoreBar.number2.scoreImgV.setFitWidth(Number.WIDTH * 0.5);
        scoreBar.number2.scoreImgV.setFitHeight(Number.HEIGHT * 0.5);
        scoreBar.number3.scoreImgV.setFitWidth(Number.WIDTH * 0.5);
        scoreBar.number3.scoreImgV.setFitHeight(Number.HEIGHT * 0.5);

        getChildren().addAll(scoreBar.number1, scoreBar.number2, scoreBar.number3);

        scoreBar.showScore(score);
    }

    //добавляет лучший счет на табло
    private void addHighScoreBar(){
        highScoreBar = new ScoreBar(this, 175, 75);

        highScoreBar.number1.scoreImgV.setFitWidth(Number.WIDTH * 0.5);
        highScoreBar.number1.scoreImgV.setFitHeight(Number.HEIGHT * 0.5);
        highScoreBar.number2.scoreImgV.setFitWidth(Number.WIDTH * 0.5);
        highScoreBar.number2.scoreImgV.setFitHeight(Number.HEIGHT * 0.5);
        highScoreBar.number3.scoreImgV.setFitWidth(Number.WIDTH * 0.5);
        highScoreBar.number3.scoreImgV.setFitHeight(Number.HEIGHT * 0.5);

        getChildren().addAll(highScoreBar.number1, highScoreBar.number2, highScoreBar.number3);

        highScoreManager = new HighScoreManager();
        highScoreManager.createFile();
        highScoreManager.loadScoreFile();

        highScoreBar.showScore(highScoreManager.highScore.getHighScore());
    }

    //добавляет медаль на табло
    private void addMedal(){
        if (score >= highScoreManager.highScore.getHighScore()) {
            //серебряная медаль за повторения лучшего счета
            if (score == highScoreManager.highScore.getHighScore()) {
                medal = new SilverMedal();
            }
            //золотая медаль за побитый лучший счет
            if (score > highScoreManager.highScore.getHighScore()) {
                highScoreManager.updateScoreFile(score);
                medal = new GoldMedal();
            }

            medal.setTranslateX(27);
            medal.setTranslateY(43);

            getChildren().add(medal);
        }
    }

    public static double getWIDTH() {
        return WIDTH;
    }
}
