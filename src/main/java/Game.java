import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Quar17esma on 04.08.2016.
 */
public class Game extends Application {
    private AnimationTimer timer;

    private BackgroundImage backgroundImg;

    Pane appRoot;
    Pane gameRoot;
    Pane groundRoot;
    Ground ground;
    Music music;

    ArrayList<Wall> walls = new ArrayList<>();
    Bird bird;
    static int score = 0;
    static int wallCounter = 0;
    ScoreBar scoreBar;
    Scene mainScene;
    ImageView background;
    Stage primaryStage;
    private static final double STAGE_WIDTH = 600;
    private static final double STAGE_HEIGHT = 550;
    private static final String BACKGROUND_FILE = "images/background.png";

    private Parent createContent(){
        //корневая панель
        appRoot = new Pane();
        appRoot.setPrefSize(STAGE_WIDTH,STAGE_HEIGHT);
        appRoot.setMaxSize(STAGE_WIDTH,STAGE_HEIGHT);
        appRoot.setBackground(createMainBackground());

        //игровая панель
        gameRoot = new Pane();
        gameRoot.setPrefSize(STAGE_WIDTH,STAGE_HEIGHT);

        //земля
        groundRoot = new Pane();
        groundRoot.setPrefSize(Wall.getQUANTITY()*Wall.getGAP()+STAGE_WIDTH,STAGE_HEIGHT);
        ground = new Ground();
        groundRoot.setBackground(new Background(ground.myBI));

        //музыка
        music = new Music();

        //препятствия
        createWalls();

        //птичка
        bird = new Bird();
        gameRoot.getChildren().add(bird);

        appRoot.getChildren().addAll(gameRoot, groundRoot);

        //текущий счет
        scoreBar = new ScoreBar(appRoot);
        appRoot.getChildren().addAll(scoreBar.number1, scoreBar.number2, scoreBar.number3);


        return appRoot;
    }



    //производит необходимые действия при проигрыше
    public void gameOver(){
        //остановка анимаци
        timer.stop();

        //выводим надпись ГеймОвер
        GameOver gameOver = new GameOver();
        gameOver.setTranslateX(bird.velocity.getX()+(appRoot.getPrefWidth()-GameOver.getWIDTH())/2);
        gameOver.setTranslateY(130);
        appRoot.getChildren().add(gameOver);

        //выводим табло счета
        ScoreBoard scoreBoard = new ScoreBoard(score);
        scoreBoard.createScoreBoard();
        scoreBoard.setTranslateX(bird.velocity.getX()+(appRoot.getPrefWidth()-ScoreBoard.getWIDTH())/2);
        scoreBoard.setTranslateY(230);
        appRoot.getChildren().add(scoreBoard);

        //выводим кнопку рестарта
        RestartButton restartButton = new RestartButton();
        restartButton.setTranslateX(bird.velocity.getX()+(appRoot.getPrefWidth()-RestartButton.getWIDTH())/2);
        restartButton.setTranslateY(360);
        restartButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            score = 0;
            wallCounter = 0;

            primaryStage.close();
            Platform.runLater(() -> {
                try {
                    new Game().start(new Stage());
                } catch (Exception e) {e.printStackTrace();}
            });
        });
        appRoot.getChildren().add(restartButton);

        //отановка фоновой музыки и запуск звука проигрыща
        music.musicBackgroundStop();
        music.gameOverSound();
    }

    public void createWalls(){
        for (int i = 0; i < Wall.getQUANTITY(); i++) {
            int enter = (int)(Math.random()*150+80);    //80-230
            int heightWallUp = new Random().nextInt((int)STAGE_HEIGHT-enter-70-60) + 70;
//            int enter = 80;
//            int height = 70;

            Wall wallUp = new Wall(Wall.WallType.WALL_UP, heightWallUp);
            wallUp.setTranslateX(i*Wall.getGAP()+STAGE_WIDTH);
            wallUp.setTranslateY(0);
            walls.add(wallUp);

            Wall wallDown = new Wall(Wall.WallType.WALL_DOWN, (int)STAGE_HEIGHT-heightWallUp-enter);
            wallDown.setTranslateX(i*Wall.getGAP()+STAGE_WIDTH);
            wallDown.setTranslateY(heightWallUp+enter);
            walls.add(wallDown);

            gameRoot.getChildren().addAll(wallUp, wallDown);
        }
    }

    //создает и возвращает фон для корневой панели
    public Background createMainBackground(){
        //фон главной панели
        backgroundImg = new BackgroundImage(
                new Image(getClass().getResourceAsStream(BACKGROUND_FILE),
                        STAGE_WIDTH,
                        STAGE_HEIGHT,
                        false,
                        false),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                new BackgroundPosition(Side.LEFT,0,true,Side.TOP,0,true),
                BackgroundSize.DEFAULT);

        return new Background(backgroundImg);
    }

    public void update() {
        if (bird.isAlive) {
            if (bird.velocity.getY() < 5)
                bird.velocity = bird.velocity.add(0, 1);

            bird.moveX((int) bird.velocity.getX(), walls, scoreBar);
            bird.moveY((int) bird.velocity.getY(), walls);

            bird.translateXProperty().addListener((ov, oldValue, newValue) -> {
                int offset = newValue.intValue();
                if (offset > 200) {
                    gameRoot.setLayoutX(-(offset - 200));
                    groundRoot.setLayoutX(-(offset - 200));
                }
            });
        } else
            gameOver();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //главоное окно
        this.primaryStage = primaryStage;

        //главная сцена в окне
        mainScene = new Scene(createContent());
        mainScene.setOnMouseClicked(event->{
            bird.jump(music);
            bird.animation.play();
        });
        primaryStage.setScene(mainScene);

        primaryStage.show();

        //запуск анимации
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();

        //запуск фоновой музыки
        music.musicBackgroundPlay();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static double getStageWidth() {
        return STAGE_WIDTH;
    }

    public static double getStageHeight() {
        return STAGE_HEIGHT;
    }
}
