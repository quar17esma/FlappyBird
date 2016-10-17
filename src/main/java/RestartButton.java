import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

//кнопка перезапуска игры
public class RestartButton extends Pane{
    //размер изображения кнопки
    private static final double WIDTH = 106;
    private static final double HEIGHT = 60;
    //файл изображения кнопки
    private static final String RESTART_BUTTON_FILE = "images/RestartButton.png";

    private Image restartButtonImg;
    private ImageView restartButtonImgV;


    //устанавливает изображение и его размер
    public RestartButton() {
        restartButtonImg = new Image(getClass().getResourceAsStream(RESTART_BUTTON_FILE));

        restartButtonImgV = new ImageView(restartButtonImg);
        restartButtonImgV.setViewport(new Rectangle2D(0,0,106,60));
        restartButtonImgV.setFitWidth(WIDTH);
        restartButtonImgV.setFitHeight(HEIGHT);

        getChildren().add(restartButtonImgV);

        setCursor(Cursor.HAND);
    }

    public static double getWIDTH() {
        return WIDTH;
    }
}
