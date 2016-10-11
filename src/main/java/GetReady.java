import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


//Надпись о подготовке к игре (GET READY!)
public class GetReady extends Pane {
    //размер надписи
    private static final double WIDTH = 184;
    private static final double HEIGHT = 49;
    //коофициент увеличения размера изображения надписи
    private static final double SIZE_MULTIPLIER = 1.5;
    //исходная точка изображения
    private static final double START_X = 0;
    private static final double START_Y = 0;
    //файл изображения
    private static final String GET_READY_FILE = "images/GetReady.png";

    private static Image getReadyImg;
    private ImageView getReadyImgV;

    //устанавливает изображение и его размер
    public GetReady() {
        getReadyImg = new Image(getClass().getResourceAsStream(GET_READY_FILE));

        getReadyImgV = new ImageView(getReadyImg);
        getReadyImgV.setViewport(new Rectangle2D(START_X, START_Y, WIDTH, HEIGHT));
        getReadyImgV.setFitWidth(WIDTH * SIZE_MULTIPLIER);
        getReadyImgV.setFitHeight(HEIGHT * SIZE_MULTIPLIER);

        getChildren().add(getReadyImgV);
    }

    public static double getFitWidth() {
        return WIDTH * SIZE_MULTIPLIER;
    }
}
