import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

//препятствие на пути птички
public class Wall extends Pane {

//файл с изображением препятствия
    Image wallImg;
    ImageView wall;
    String WALL_IMAGE_FILE = "images/Atlas.png";

//типы препятсвий(вверху, внизу)
    public enum WallType {
        WALL_DOWN, WALL_UP
    }

//конструктор стены с параметрами(тип препятствия, высота препятствия)
    public Wall(WallType wallType, int height) {
        wallImg = new Image(getClass().getResourceAsStream(WALL_IMAGE_FILE));
        wall = new ImageView(wallImg);

//устанавливаем изображение в зависимости от типа препятствия
        switch (wallType) {
            case WALL_UP:
                wall.setViewport(new Rectangle2D(111, 646+320-height, 52, height));
                break;
            case WALL_DOWN:
                wall.setViewport(new Rectangle2D(168, 646, 52, height));
                break;
        }

//добавляем препятствие на уровень
        getChildren().add(wall);
    }
}
