import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class ScoreBoard  extends Pane {
    Image scoreBoardImg;
    ImageView scoreBoardImgV;
    static final double WIDTH = 229;
    static final double HEIGHT = 118;


    public ScoreBoard() {                                                             //общее изображение
        scoreBoardImg = new Image(getClass().getResourceAsStream("images/ScoreBoard.png"));
        scoreBoardImgV = new ImageView(scoreBoardImg);
        scoreBoardImgV.setViewport(new Rectangle2D(0,0,229,118));

        scoreBoardImgV.setFitWidth(WIDTH);
        scoreBoardImgV.setFitHeight(HEIGHT);

        getChildren().add(scoreBoardImgV);
    }
}
