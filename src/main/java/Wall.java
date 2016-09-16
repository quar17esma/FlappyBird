import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Wall extends Pane {
                                                                    //общее изображение
    Image wallImg = new Image(getClass().getResourceAsStream("images/Atlas.png"));
    ImageView wall;                                                //стена

    public enum WallType {                                         //типы стен
        WALL_DOWN, WALL_UP
    }

    public Wall(WallType wallType, int height) {               //конструктор блока
        wall = new ImageView(wallImg);

//        wall.setFitWidth(52);                         //размер блока
//        wall.setFitHeight(height);

//        setTranslateX(x);                                           //координаты блока
//        setTranslateY(y);

        switch (wallType) {                                        //изображение блока в зависимости от типа
            case WALL_UP:
                wall.setViewport(new Rectangle2D(111, 646+320-height, 52, height));
                break;
            case WALL_DOWN:
                wall.setViewport(new Rectangle2D(168, 646, 52, height));
                break;
        }
        getChildren().add(wall);    //добавляем блок на уровень
    }
}
