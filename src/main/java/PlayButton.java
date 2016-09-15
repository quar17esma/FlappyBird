import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayButton extends Pane{
    Image playButtonImg;
    ImageView playButtonImgV;
    static final double WIDTH = 106;
    static final double HEIGHT = 60;


    public PlayButton() {                                                             //общее изображение
        playButtonImg = new Image(getClass().getResourceAsStream("PlayButton.png"));
        playButtonImgV = new ImageView(playButtonImg);
        playButtonImgV.setViewport(new Rectangle2D(0,0,106,60));

        playButtonImgV.setFitWidth(WIDTH);
        playButtonImgV.setFitHeight(HEIGHT);

        getChildren().add(playButtonImgV);
    }
}
