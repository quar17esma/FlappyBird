import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

//Птичка
public class Bird extends Pane {
    //кол-во кадров анимации
    private static final int COUNT = 3;
    //размер птички
    private static final int WIDTH = 34;
    private static final int HEIGHT = 24;
    //начальные координаты
    private static final double START_POSITION_X = 100;
    private static final double START_POSITION_Y = 200;
    //файл изображений птички
    private static final String BIRD_FILE = "images/Bird.png";
    //координаты первого кадра
    int offsetX = 0;
    int offsetY = 0;
    //изображение птички
    Image birdImg;
    ImageView imageView;
    //анимация
    SpriteAnimation animation;

    Point2D velocity;

    //состояние птички(жива или нет)
    public boolean isAlive;

    public Bird() {
        isAlive = true;


        //изображение птички
        birdImg = new Image(getClass().getResourceAsStream(BIRD_FILE));

        imageView = new ImageView(birdImg);
        imageView.setFitHeight(HEIGHT);
        imageView.setFitWidth(WIDTH);
        imageView.setViewport(new Rectangle2D(offsetX,offsetY, WIDTH, HEIGHT));

        //создаем анимацию Птички
        animation = new SpriteAnimation(this.imageView,Duration.millis(200),COUNT,offsetX,offsetY, WIDTH, HEIGHT);
        getChildren().addAll(this.imageView);


        velocity  = new Point2D(0, 0);
        setTranslateX(START_POSITION_X);
        setTranslateY(START_POSITION_Y);
    }

    //перемещает птичку по горизонтали
    public void moveX(int value, ArrayList<Wall> walls, ScoreBar scoreBar){
        for (int i = 0; i < value; i++) {
            for (Wall wall: walls) {
                //при столкновении с препятствием - птичка упирается в него и умирает
                if (getBoundsInParent().intersects(wall.getBoundsInParent())){
                    if (getTranslateX()+WIDTH == wall.getTranslateX()) {
                        setTranslateX(getTranslateX()-1);
                        die();
                        return;
                    }
                }
                //при прохождении препятствия - увеличиваем счет
                if (getTranslateX()+WIDTH == wall.getTranslateX()){
                    Game.wallCounter++;
                    if (Game.wallCounter%2==0){
                        Game.score++;
                        scoreBar.showScore(Game.score);
                    }
                }
            }
            //перемещаем птичку вперед
            setTranslateX(getTranslateX()+1);
        }
    }

    //перемещает птичку по вертикали
    public void moveY(int value, ArrayList<Wall> walls){
        //положительная value указивает на перемещение птички вниз
        boolean moveDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Wall wall:walls) {
                if (this.getBoundsInParent().intersects(wall.getBoundsInParent())){
                    if (moveDown){
                        setTranslateY(getTranslateY()-1);
                    } else {
                        setTranslateY(getTranslateY()+1);
                    }
                    die();
                    return;
                }
            }

            if (getTranslateY()<0)
                setTranslateY(0);

            if (getTranslateY()>Game.getStageHeight()-112+20-HEIGHT-1){
                setTranslateY(Game.getStageHeight()-112+20-HEIGHT-1);
                die();
            }
            setTranslateY(getTranslateY() + (moveDown?1:-1));
        }
    }

    //перещает птичку(прыжок), запускает звук взмаха крыла
    public void jump(Music music){
        velocity = new Point2D(3, -9);
        music.jumpSound();
    }

    //убивает птичку(птичка наклоняется и падает)
    public void die(){
        getTransforms().add(new Rotate(90,0,0));
        setTranslateY(Game.getStageHeight()-112+20-WIDTH-1);
        isAlive = false;
    }
}
