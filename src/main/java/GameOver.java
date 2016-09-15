import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class GameOver extends Pane {
    Image gameOverImg;
    ImageView gameOverImgV;
    static final double WIDTH = 192*1.5;
    static final double HEIGHT = 42*1.5;


    public GameOver() {                                                             //общее изображение
        gameOverImg = new Image(getClass().getResourceAsStream("Atlas.png"));
        gameOverImgV = new ImageView(gameOverImg);                                  //стена
        gameOverImgV.setViewport(new Rectangle2D(790,118,192,42));

        gameOverImgV.setFitWidth(WIDTH);
        gameOverImgV.setFitHeight(HEIGHT);

        getChildren().add(gameOverImgV);
    }
}
