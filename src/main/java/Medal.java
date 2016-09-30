import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Medal extends Pane {
    Image medalImg;
    ImageView medalImgV;
    static final double WIDTH = 44;
    static final double HEIGHT = 44;

    public Medal() {                                                             //общее изображение
        medalImg = new Image(getClass().getResourceAsStream("images/Medal.png"));
        medalImgV = new ImageView(medalImg);


        medalImgV.setFitWidth(WIDTH);
        medalImgV.setFitHeight(HEIGHT);

        getChildren().add(medalImgV);
    }
}
