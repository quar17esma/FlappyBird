import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.ArrayList;


public class PostGameRoot extends Pane {
    //логгер
    final static Logger logger = Logger.getLogger(PostGameRoot.class);

    private GameOver gameOver;
    private Free free;
    private ScoreBoard scoreBoard;
    private RestartButton restartButton;

    private static final double GAME_OVER_Y = 130;
    private static final double FREE_Y = 120;
    private static final double SCORE_BOARD_Y = 230;
    private static final double RESTART_BUTTON_Y = 360;


    public PostGameRoot(Bird bird, int score, ArrayList<Wall> walls, Music music, Stage primaryStage) {
        //если прошли все препятствия
        if (bird.isFree()) {
            //выводим надпись (FlappyBird is Free)
            free = new Free();
            free.setTranslateX(bird.velocity.getX() + (Game.getStageWidth() - Free.getFitWidth()) / 2);
            free.setTranslateY(FREE_Y);
            getChildren().add(free);
        } else  {
            //выводим надпись ГеймОвер
            gameOver = new GameOver();
            gameOver.setTranslateX(bird.velocity.getX()+(Game.getStageWidth()-GameOver.getFitWidth())/2);
            gameOver.setTranslateY(GAME_OVER_Y);
            getChildren().add(gameOver);
        }

        //выводим табло счета
        scoreBoard = new ScoreBoard(score);
        scoreBoard.createScoreBoard();
        scoreBoard.setTranslateX(bird.velocity.getX()+(Game.getStageWidth()-ScoreBoard.getWIDTH())/2);
        scoreBoard.setTranslateY(SCORE_BOARD_Y);
        getChildren().add(scoreBoard);

        //выводим кнопку рестарта
        restartButton = new RestartButton();
        restartButton.setTranslateX(bird.velocity.getX()+(Game.getStageWidth()-RestartButton.getWIDTH())/2);
        restartButton.setTranslateY(RESTART_BUTTON_Y);
        restartButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            Game.score = 0;
            Game.wallCounter = 0;
            walls.clear();

            music.allSoundsStop();

            primaryStage.close();

            Platform.runLater(() -> {
                try {
                    new Game().start(new Stage());
                } catch (Exception e) {
                    logger.error("[ReRun] E Error: ", e);
                }
            });
        });
        getChildren().add(restartButton);
    }
}
