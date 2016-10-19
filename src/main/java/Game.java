import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Quar17esma on 04.08.2016.
 */
public class Game extends Application {
    //логгер
    final static Logger logger = Logger.getLogger(Game.class);

    //главное окно и сцена главного окна
    private Stage primaryStage;
    private Scene mainScene;

    //анимация
    private AnimationTimer timer;

    //фон главной сцены
    private BackgroundImage backgroundImg;

    //главная панель
    private Pane appRoot;
    //панель игры
    private Pane gameRoot;
    //панель с подсказкой перед началом игры
    private PreGameRoot preGameRoot;
    //панель о проигрыше или прохождении всех препятствий
    private PostGameRoot postGameRoot;

    //обьекты - панели игровой панели
    private Bird bird;
    private Ground ground;
    private ScoreBar scoreBar;
    private ArrayList<Wall> walls = new ArrayList<>();

    //отвечает за музыку
    private Music music;
    //текущий счет
    static int score = 0;
    //пройденые препятствия
    static int wallCounter = 0;

    //началась ли игра(начинается с первым кликом)
    boolean isGameStarted;

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
        ground.setPrefSize((Wall.getQUANTITY()+1)*Wall.getGAP()+STAGE_WIDTH, Ground.getHEIGHT());
        gameRoot.getChildren().add(ground);

        //птичка
        bird = new Bird();
        gameRoot.getChildren().add(bird);

        //текущий счет
        scoreBar = new ScoreBar(1);
        scoreBar.setTranslateX((appRoot.getPrefWidth() - Number.WIDTH*3)/2);
        scoreBar.setTranslateY(60);
        appRoot.getChildren().add(scoreBar);

        isGameStarted = false;

        //музыка
        music = new Music();

        //панель подсказок перед началом игры
        preGameRoot = new PreGameRoot(bird);
        appRoot.getChildren().add(preGameRoot);


        return appRoot;
    }

    //производит необходимые действия при проигрыше или прохождении всех препятствий
    public void gameOver(){
        //остановка анимаци
        timer.stop();
        bird.animation.stop();

        //панель при проигрыше (Счет, GameOver, кнопка рестарта)
        postGameRoot = new PostGameRoot(bird, score, walls, music, primaryStage);
        appRoot.getChildren().add(postGameRoot);

        //отановка фоновой музыки и запуск звука проигрыща
        music.musicBackgroundStop();

        if (bird.isFree()) {
            music.freeSoundPlay();
        } else {
            music.gameOverSoundPlay();
        }

        mainScene.setCursor(Cursor.DEFAULT);
    }

    //создает препятствия случайных размеров
    public void createWalls(){
        for (int i = 0; i < Wall.getQUANTITY(); i++) {
            int enter = Wall.ENTER_MIN + new Random().nextInt(120);    //80-230
//            int enter = 230;
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
            if (bird.isAlive()&&!bird.isFree()) {
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

        if(logger.isInfoEnabled()){
            logger.info("App is started");
        }

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
            appRoot.getChildren().remove(preGameRoot);

            isGameStarted = true;

            if (bird.isAlive()&&!bird.isFree()){
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
