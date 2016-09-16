import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class RestartButton extends Pane{
    Image restartButtonImg;
    ImageView restartButtonImgV;
    static final double WIDTH = 106;
    static final double HEIGHT = 60;


    public RestartButton() {                                                             //общее изображение
        restartButtonImg = new Image(getClass().getResourceAsStream("images/RestartButton.png"));
        restartButtonImgV = new ImageView(restartButtonImg);
        restartButtonImgV.setViewport(new Rectangle2D(0,0,106,60));

        restartButtonImgV.setFitWidth(WIDTH);
        restartButtonImgV.setFitHeight(HEIGHT);

        getChildren().add(restartButtonImgV);
    }
}
