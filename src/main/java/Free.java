import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Free extends Pane {
    //размер надписи
    private static final double WIDTH = 332;
    private static final double HEIGHT = 48;
    //коофициент увеличения размера изображения надписи
    private static final double SIZE_MULTIPLIER = 1;
    //исходная точка изображения
    private static final double START_X = 0;
    private static final double START_Y = 0;
    //файл изображения
    private static final String FREE_FILE = "images/Free.png";

    private static Image freeImg;
    private ImageView freeImgV;

    //устанавливает изображение и его размер
    public Free() {
        freeImg = new Image(getClass().getResourceAsStream(FREE_FILE));

        freeImgV = new ImageView(freeImg);
        freeImgV.setViewport(new Rectangle2D(START_X, START_Y, WIDTH, HEIGHT));
        freeImgV.setFitWidth(WIDTH * SIZE_MULTIPLIER);
        freeImgV.setFitHeight(HEIGHT * SIZE_MULTIPLIER);

        getChildren().add(freeImgV);
    }

    public static double getFitWidth() {
        return WIDTH * SIZE_MULTIPLIER;
    }
}
