import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class Ground extends Pane{
    BackgroundImage myBI;
    int height = 90;
//    public Ground() {
//        myBI = new BackgroundImage(
//            new Image(getClass().getResourceAsStream("ground_test.png"), 110, height, false, true),
//            BackgroundRepeat.ROUND, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//            BackgroundSize.DEFAULT);
//    }
    Image groundImg = new Image(getClass().getResourceAsStream("images/Atlas.png"));
    ImageView ground;

    public Ground() {
                myBI = new BackgroundImage(
            new Image(getClass().getResourceAsStream("images/ground.png"), 324, height, false, true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
            new BackgroundPosition(Side.LEFT,0,true,Side.BOTTOM,0,true),
//                        BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
//        //конструктор блока
//        ground = new ImageView(groundImg);
//        ground.setViewport(new Rectangle2D(584,0,336,112));
//
//        ground.setFitWidth(800);                                 //размер блока
//        ground.setFitHeight(112);
//
//        setTranslateX(0);                                      //координаты блока
//        setTranslateY(550-112+20);
////        setTranslateY(600-200);
//
//        //добавляем блок на уровень
//        getChildren().add(ground);
    }
}
