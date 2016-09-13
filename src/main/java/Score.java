import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Score extends Pane {
    Image scoreImg;
    ImageView scoreImgV;
    static final double WIDTH = 28;
    static final double HEIGHT = 36;

    public Score() {                                                             //общее изображение
        scoreImg = new Image(getClass().getResourceAsStream("ScoreNumbers.png"));
        scoreImgV = new ImageView(scoreImg);
        scoreImgV.setViewport(new Rectangle2D(0, 0, WIDTH, HEIGHT));

        scoreImgV.setFitWidth(WIDTH);
        scoreImgV.setFitHeight(HEIGHT);

        getChildren().add(scoreImgV);
    }

    public void showNumber(String score) {

            switch (score) {
                case "0":
                    scoreImgV.setViewport(new Rectangle2D(WIDTH*0, 0, WIDTH, HEIGHT));
                    break;
                case "1":
                    scoreImgV.setViewport(new Rectangle2D(WIDTH*1, 0, WIDTH, HEIGHT));
                    break;
                case "2":
                    scoreImgV.setViewport(new Rectangle2D(WIDTH*2, 0, WIDTH, HEIGHT));
                    break;
                case "3":
                    scoreImgV.setViewport(new Rectangle2D(WIDTH*3, 0, WIDTH, HEIGHT));
                    break;
                case "4":
                    scoreImgV.setViewport(new Rectangle2D(WIDTH*4, 0, WIDTH, HEIGHT));
                    break;
                case "5":
                    scoreImgV.setViewport(new Rectangle2D(WIDTH*5, 0, WIDTH, HEIGHT));
                    break;
                case "6":
                    scoreImgV.setViewport(new Rectangle2D(WIDTH*6, 0, WIDTH, HEIGHT));
                    break;
                case "7":
                    scoreImgV.setViewport(new Rectangle2D(WIDTH*7, 0, WIDTH, HEIGHT));
                    break;
                case "8":
                    scoreImgV.setViewport(new Rectangle2D(WIDTH*8, 0, WIDTH, HEIGHT));
                    break;
                case "9":
                    scoreImgV.setViewport(new Rectangle2D(WIDTH*9, 0, WIDTH, HEIGHT));
                    break;
            }
    }
}
