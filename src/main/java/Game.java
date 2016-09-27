import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
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
 * Created by Quar17esma on 04.08.2016.
 */
public class Game extends Application {
    AnimationTimer timer;

    Image backgroundImg = new Image(getClass().getResourceAsStream("images/background.png"));

    public Pane appRoot;
    public Pane gameRoot;
    public Pane groundRoot;

    Ground ground;

    public Music music;

    public ArrayList<Wall> walls = new ArrayList<>();
    Bird bird = new Bird();
    public static int score = 0;
    public static int wallCounter = 0;
    public Label scoreLabel = new Label("Number: " + score);
    public ScoreBar scoreBar;
    Scene mainScene;
    ImageView background;
    Stage primaryStage;

    public Parent createContent(){
        appRoot = new Pane();
        gameRoot = new Pane();
        groundRoot = new Pane();

        background = new ImageView(backgroundImg);
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

        gameRoot.getChildren().addAll(bird);
        appRoot.getChildren().addAll(background, gameRoot, groundRoot);

        scoreBar = new ScoreBar(appRoot);


        return appRoot;
    }

    public void update() {
        if (bird.isAlive) {

            if (bird.velocity.getY() < 5)
                bird.velocity = bird.velocity.add(0, 1);

            bird.moveX((int) bird.velocity.getX(), walls, scoreBar);
            bird.moveY((int) bird.velocity.getY(), walls);
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
        timer.stop();

        GameOver gameOver = new GameOver();
        gameOver.setTranslateX(bird.velocity.getX()+(appRoot.getPrefWidth()-GameOver.WIDTH)/2);
        gameOver.setTranslateY(bird.velocity.getY()+130);
        appRoot.getChildren().add(gameOver);

        ScoreBoard scoreBoard = new ScoreBoard(score);
        scoreBoard.setTranslateX(bird.velocity.getX()+(appRoot.getPrefWidth()-ScoreBoard.WIDTH)/2);
        scoreBoard.setTranslateY(bird.velocity.getY()+230);
        appRoot.getChildren().add(scoreBoard);

        RestartButton playButton = new RestartButton();
        playButton.setTranslateX(bird.velocity.getX()+(appRoot.getPrefWidth()-RestartButton.WIDTH)/2);
        playButton.setTranslateY(bird.velocity.getY()+360);
        appRoot.getChildren().add(playButton);
        playButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            Game.score = 0;
            Game.wallCounter = 0;

            primaryStage.close();
            Platform.runLater(() -> {
                try {
                    new Game().start(new Stage());
                } catch (Exception e) {e.printStackTrace();}
            });
        });

        music.musicBackgroundStop();
        music.gameOverSound();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        mainScene = new Scene(createContent());
        mainScene.setOnMouseClicked(event->{
            bird.jump(music);
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
