import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

//Изобажение подсказки (TAP)
public class Tap extends Pane {
    //размер изображения
    private static final double WIDTH = 114;
    private static final double HEIGHT = 98;
    //коофициент увеличения размера изображения надписи
    private static final double SIZE_MULTIPLIER = 1.2;
    //исходная точка изображения
    private static final double START_X = 0;
    private static final double START_Y = 0;
    //файл изображения
    private static final String TAP_FILE = "images/Tap.png";

    private static Image tapImg;
    private ImageView tapImgV;

    //устанавливает изображение и его размер
    public Tap() {
        tapImg = new Image(getClass().getResourceAsStream(TAP_FILE));

        tapImgV = new ImageView(tapImg);
        tapImgV.setViewport(new Rectangle2D(START_X, START_Y, WIDTH, HEIGHT));
        tapImgV.setFitWidth(WIDTH * SIZE_MULTIPLIER);
        tapImgV.setFitHeight(HEIGHT * SIZE_MULTIPLIER);

        getChildren().add(tapImgV);
    }

    public static double getFitWidth() {
        return WIDTH * SIZE_MULTIPLIER;
    }
}
