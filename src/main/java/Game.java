import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Quar17esma on 04.08.2016.
 */
public class Game extends Application {
    private Stage primaryStage;
    private Scene mainScene;

    private AnimationTimer timer;

    private BackgroundImage backgroundImg;

    private Pane appRoot;
    private Pane gameRoot;

    private Bird bird;
    private Ground ground;
    private ScoreBar scoreBar;
    private ArrayList<Wall> walls = new ArrayList<>();

    private Music music;

    static int score = 0;
    static int wallCounter = 0;

    boolean isGameStarted;

    GetReady getReady;
    Tap tap;

    //размер окна
    private static final double STAGE_WIDTH = 600;
    private static final double STAGE_HEIGHT = 550;
    //файл фона
    private static final String BACKGROUND_FILE = "images/background.png";
    //файл иконки окна
    private static final String PRIMARY_STAGE_ICON_FILE = "images/BirdTitleBar.png";
    private static final String STAGE_TITLE_TEXT = "Flappy Bird by Quar17esma";


    private Parent createContent(){
        //корневая панель
        appRoot = new Pane();
        appRoot.setPrefSize(STAGE_WIDTH,STAGE_HEIGHT);
        appRoot.setMaxSize(STAGE_WIDTH,STAGE_HEIGHT);
        appRoot.setBackground(createMainBackground());

        //игровая панель
        gameRoot = new Pane();
        gameRoot.setPrefSize(STAGE_WIDTH,STAGE_HEIGHT);
        appRoot.getChildren().add(gameRoot);

        //препятствия
        createWalls();

        //земля
        ground = new Ground();
        ground.setPrefSize(Wall.getQUANTITY()*Wall.getGAP()+STAGE_WIDTH, Ground.getHEIGHT());
        gameRoot.getChildren().add(ground);

        //музыка
        music = new Music();

        //птичка
        bird = new Bird();
        gameRoot.getChildren().add(bird);

        //текущий счет
        scoreBar = new ScoreBar(gameRoot);
        appRoot.getChildren().addAll(scoreBar.number1, scoreBar.number2, scoreBar.number3);

        isGameStarted = false;

        getReady = new GetReady();
        getReady.setTranslateX(bird.velocity.getX()+(STAGE_WIDTH-GetReady.getFitWidth())/2);
        getReady.setTranslateY(130);
        gameRoot.getChildren().add(getReady);

        tap = new Tap();
        tap.setTranslateX(bird.velocity.getX()+(STAGE_WIDTH-Tap.getFitWidth())/2);
        tap.setTranslateY(250);
        gameRoot.getChildren().add(tap);

        return appRoot;
    }

    //производит необходимые действия при проигрыше
    public void gameOver(){
        //остановка анимаци
        timer.stop();
        bird.animation.stop();

        //выводим надпись ГеймОвер
        GameOver gameOver = new GameOver();
        gameOver.setTranslateX(bird.velocity.getX()+(STAGE_WIDTH-GameOver.getFitWidth())/2);
        gameOver.setTranslateY(130);
        appRoot.getChildren().add(gameOver);

        //выводим табло счета
        ScoreBoard scoreBoard = new ScoreBoard(score);
        scoreBoard.createScoreBoard();
        scoreBoard.setTranslateX(bird.velocity.getX()+(STAGE_WIDTH-ScoreBoard.getWIDTH())/2);
        scoreBoard.setTranslateY(230);
        appRoot.getChildren().add(scoreBoard);

        //выводим кнопку рестарта
        RestartButton restartButton = new RestartButton();
        restartButton.setTranslateX(bird.velocity.getX()+(STAGE_WIDTH-RestartButton.getWIDTH())/2);
        restartButton.setTranslateY(360);
        restartButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            score = 0;
            wallCounter = 0;
            walls = null;

            music.gameOverSoundStop();

            primaryStage.close();

            Platform.runLater(() -> {
                try {
                    new Game().start(new Stage());
                } catch (Exception e) {
                    System.out.println("[ReRun] E Error: " + e.getMessage());
                }
            });
        });
        appRoot.getChildren().add(restartButton);

        //отановка фоновой музыки и запуск звука проигрыща
        music.musicBackgroundStop();
        music.gameOverSoundPlay();

        mainScene.setCursor(Cursor.DEFAULT);
    }

    //создает препятствия случайных размеров
    public void createWalls(){
        for (int i = 0; i < Wall.getQUANTITY(); i++) {
            int enter = Wall.ENTER_MIN + new Random().nextInt(120);    //80-230
            int heightWallUp = Wall.WALL_HEIGHT_MIN
                                + new Random().nextInt((int)STAGE_HEIGHT
                                                        - Ground.getHEIGHT()
                                                        - Wall.WALL_HEIGHT_MIN
                                                        - enter-Wall.WALL_HEIGHT_MIN);

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
        if (isGameStarted()) {
            if (bird.isAlive()) {
                if (bird.velocity.getY() < 5)
                    bird.velocity = bird.velocity.add(0, 1);

                bird.moveX((int) bird.velocity.getX(), walls, scoreBar);
                bird.moveY((int) bird.velocity.getY(), walls);

                bird.translateXProperty().addListener((ov, oldValue, newValue) -> {
                    int offset = newValue.intValue();
                    if (offset > 200) {
                        gameRoot.setLayoutX(-(offset - 200));
                    }
                });
            } else {
                gameOver();
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //главоное окно
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(PRIMARY_STAGE_ICON_FILE)));
        primaryStage.setTitle(STAGE_TITLE_TEXT);
        primaryStage.setWidth(STAGE_WIDTH);
        primaryStage.setHeight(STAGE_HEIGHT);
        primaryStage.setResizable(false);

        //главная сцена в окне
        mainScene = new Scene(createContent());
        mainScene.setCursor(Cursor.HAND);
        mainScene.setOnMouseClicked(event->{
            gameRoot.getChildren().remove(getReady);
            gameRoot.getChildren().remove(tap);

            isGameStarted = true;

            if (bird.isAlive()){
                bird.jump(music);
                bird.animation.play();
            }
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

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public boolean isGameStarted(){
        return isGameStarted;
    }
}
