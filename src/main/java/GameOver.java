import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

//Надпись об окончании игры (GAMEOVER)
public class GameOver extends Pane {

    //размер надписи
    private static final double WIDTH = 192;
    private static final double HEIGHT = 42;
    //коофициент увеличения размера изображения надписи
    private static final double SIZE_MULTIPLIER = 1.5;
    //исходная точка изображения
    private static final double START_X = 790;
    private static final double START_Y = 118;
    //файл изображения
    private static final String GAMEOVER_FILE = "images/Atlas.png";

    private static Image gameOverImg;
    private ImageView gameOverImgV;

    //устанавливает изображение и его размер
    public GameOver() {
        gameOverImg = new Image(getClass().getResourceAsStream(GAMEOVER_FILE));

        gameOverImgV = new ImageView(gameOverImg);
        gameOverImgV.setViewport(new Rectangle2D(START_X,START_Y,WIDTH,HEIGHT));
        gameOverImgV.setFitWidth(WIDTH*SIZE_MULTIPLIER);
        gameOverImgV.setFitHeight(HEIGHT*SIZE_MULTIPLIER);

        getChildren().add(gameOverImgV);
    }

    public static double getFitWidth() {
        return WIDTH*SIZE_MULTIPLIER;
    }
}
