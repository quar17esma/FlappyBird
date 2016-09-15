import javafx.animation.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Сергей on 04.08.2016.
 */
public class Game extends Application {
    static AnimationTimer timer;

    Image backgroundImg = new Image(getClass().getResourceAsStream("background.png"));

    public static Pane appRoot = new Pane();
    public static Pane gameRoot = new Pane();
    public static Pane groundRoot = new Pane();

    Ground ground;

    public static Music music;

    public static ArrayList<Wall> walls = new ArrayList<>();
    Bird bird = new Bird();
    public static int score = 0;
    public static int wallCounter = 0;
    public Label scoreLabel = new Label("Number: " + score);
    public static ScoreBar scoreBar;
    Scene mainScene;

    public Parent createContent(){
        ImageView background = new ImageView(backgroundImg);
        background.setFitWidth(600);
        background.setFitHeight(550);

        ground = new Ground();
        music = new Music();

        appRoot.setPrefSize(600,550);
        appRoot.setMaxSize(600,550);

        groundRoot.setPrefSize(15*350+600,550);

        gameRoot.setPrefSize(600,550);

        for (int i = 0; i < 15; i++) {
            int enter = (int)(Math.random()*150+110);    //80-230
//            int height = new Random().nextInt(550-enter);
//            int enter = 80;
            int height = 70;


            Wall wallUp = new Wall(Wall.WallType.WALL_UP, height);
            wallUp.setTranslateX(i*350+600);
            wallUp.setTranslateY(0);
            walls.add(wallUp);

            Wall wallDown = new Wall(Wall.WallType.WALL_DOWN, 600-height-enter-100);
            wallDown.setTranslateX(i*350+600);
            wallDown.setTranslateY(height+enter);
            walls.add(wallDown);

            gameRoot.getChildren().addAll(wallUp, wallDown);
        }

        groundRoot.setBackground(new Background(ground.myBI));
//        groundRoot.getChildren().add(gameRoot);
        gameRoot.getChildren().addAll(bird);
        appRoot.getChildren().addAll(background, gameRoot, groundRoot);

        scoreBar = new ScoreBar();

//        gameRoot.setBackground(new Background(ground.myBI));
//
//        gameRoot.getChildren().add(bird);
//        appRoot.getChildren().addAll(background, gameRoot, ground);
        return appRoot;
    }

    public void update() {
        if (bird.isAlive) {

            if (bird.velocity.getY() < 5)
                bird.velocity = bird.velocity.add(0, 1);

            bird.moveX((int) bird.velocity.getX());
            bird.moveY((int) bird.velocity.getY());
            scoreLabel.setText("Number: " + score);

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

    public void gameOver(){
        Game.timer.stop();

        GameOver gameOver = new GameOver();
        gameOver.setTranslateX(bird.velocity.getX()+(Game.appRoot.getPrefWidth()-GameOver.WIDTH)/2);
        gameOver.setTranslateY(bird.velocity.getY()+130);
        Game.appRoot.getChildren().add(gameOver);

        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.setTranslateX(bird.velocity.getX()+(Game.appRoot.getPrefWidth()-ScoreBoard.WIDTH)/2);
        scoreBoard.setTranslateY(bird.velocity.getY()+230);
        Game.appRoot.getChildren().add(scoreBoard);

        PlayButton playButton = new PlayButton();
        playButton.setTranslateX(bird.velocity.getX()+(Game.appRoot.getPrefWidth()-PlayButton.WIDTH)/2);
        playButton.setTranslateY(bird.velocity.getY()+360);
        Game.appRoot.getChildren().add(playButton);
        playButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            mainScene = new Scene(createContent());
            mainScene.setOnMouseClicked(event1->{
                bird.jump();
                bird.animation.play();
            });
            timer.start();
            music.musicBackgroundPlay();
        });

        Game.music.musicBackgroundStop();
        Game.music.gameOverSound();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainScene = new Scene(createContent());
        mainScene.setOnMouseClicked(event->{
            bird.jump();
            bird.animation.play();
        });

        primaryStage.setScene(mainScene);
        primaryStage.show();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();

        music.musicBackgroundPlay();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
