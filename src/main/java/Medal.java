import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

//абстрактная медаль за рекорд
public abstract class Medal extends Pane {
    private static Image medalImg;
    protected ImageView medalImgV;
    private static final String MEDAL_FILE = "images/Medal.png";

    //размер изображения медали
    protected static final double WIDTH = 44;
    protected static final double HEIGHT = 44;

    //конструктор
    public Medal() {
        medalImg = new Image(getClass().getResourceAsStream(MEDAL_FILE));
        medalImgV = new ImageView(medalImg);

        medalImgV.setFitWidth(WIDTH);
        medalImgV.setFitHeight(HEIGHT);

        getChildren().add(medalImgV);
    }
}
