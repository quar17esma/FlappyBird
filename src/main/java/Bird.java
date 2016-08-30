import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


public class Bird extends Pane {
    Image BirdImg = new Image(getClass().getResourceAsStream("Bird.png"));  //Картинка Птички
    ImageView imageView = new ImageView(BirdImg);

    int count = 3;                                          //кол-во кадров

    int offsetX = 0;                                       //координаты первого кадра
    int offsetY = 0;

    int width = 34;                                         //размер кадра
    int height = 24;

    public SpriteAnimation animation;

    public Point2D velocity;

    public boolean isAlive = true;

    public Bird() {
        imageView.setFitHeight(24);                         //изменяем размер Птички
        imageView.setFitWidth(34);

        imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));  //устанавливаем кадр
        //создаем анимацию Марио
        animation = new SpriteAnimation(this.imageView, Duration.millis(200),count,offsetX,offsetY,width,height);
        getChildren().addAll(this.imageView);                                   //связываем картинку с персонажем Марио


        velocity  = new Point2D(0, 0);
        setTranslateX(100);
        setTranslateY(300);
    }

    public void moveX(int value){
        for (int i = 0; i < value; i++) {
            for (Wall wall: Game.walls) {
                if (getBoundsInParent().intersects(wall.getBoundsInParent())){
                    if (getTranslateX()+34 == wall.getTranslateX()) {
                        setTranslateX(getTranslateX()-1);
                        die();
                        return;
                    }
                }
                if (getTranslateX()+34 == wall.getTranslateX()){
                    Game.wallCounter++;
                    if (Game.wallCounter%2==0){
                        Game.score++;
                        Score.showScore(Game.score);
                    }
                }

            }
            setTranslateX(getTranslateX()+1);
        }
    }

    public void moveY(int value){
        boolean moveDown = value > 0 ? true : false;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Wall wall:Game.walls) {
                if (this.getBoundsInParent().intersects(wall.getBoundsInParent())){
                    if (moveDown){
                        setTranslateY(getTranslateY()-1);
                        die();
                        return;
                    } else {
                        setTranslateY(getTranslateY()+1);
                        return;
                    }
                }
            }

            if (getTranslateY()<0)
                setTranslateX(0);
            if (getTranslateY()>550-112+20-24-1)
                setTranslateY(550-112+20-24-1);
            setTranslateY(getTranslateY() + (moveDown?1:-1));
        }
    }

    public void jump(){
        velocity = new Point2D(3, -9);
        Game.music.jumpSound();
    }

    public void die(){
        getTransforms().add(new Rotate(90,0,0));
        setTranslateY(550-112+20-34-1);
        isAlive = false;
    }
}
