import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

//Надпись об окончании игра (GAMEOVER)
public class GameOver extends Pane {

    //размер надписи
    private static final double WIDTH = 192*1.5;
    private static final double HEIGHT = 42*1.5;
    //файл изображения
    private static final String GAMEOVER_FILE = "images/Atlas.png";

    Image gameOverImg;
    ImageView gameOverImgV;

    //устанавливает изображение и его размер
    public GameOver() {
        gameOverImg = new Image(getClass().getResourceAsStream(GAMEOVER_FILE));

        gameOverImgV = new ImageView(gameOverImg);
        gameOverImgV.setViewport(new Rectangle2D(790,118,192,42));
        gameOverImgV.setFitWidth(WIDTH);
        gameOverImgV.setFitHeight(HEIGHT);

        getChildren().add(gameOverImgV);
    }

    public static double getWIDTH() {
        return WIDTH;
    }
}
