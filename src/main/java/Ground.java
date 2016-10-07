import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

//отвечает за землю
public class Ground extends Pane{
    //фон панели
    BackgroundImage myBI;
    //размер фона
    private static final int WIDTH = 324;
    private static final int HEIGHT = 90;
    //файл изображения фона
    private static final String GROUND_FILE = "images/ground.png";

    //устанавливает фоновое изображение для панели
    public Ground() {
        myBI = new BackgroundImage(
            new Image(getClass().getResourceAsStream(GROUND_FILE), WIDTH, HEIGHT, false, true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
            new BackgroundPosition(Side.LEFT,0,true,Side.TOP,0,true),
            BackgroundSize.DEFAULT);

        setBackground(new Background(myBI));
        setTranslateX(0);
        setTranslateY(Game.getStageHeight()-HEIGHT);
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}
