import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Score extends Pane {
    Image scoreImg;
    static ImageView scoreImgV;
    static final double WIDTH = 32;
    static final double HEIGHT = 36;

    public enum Score_Numbers {
        ONE, TWO, TREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, ZERO
    }

    public Score() {                                                             //общее изображение
        scoreImg = new Image(getClass().getResourceAsStream("ScoreNumbers.png"));
        scoreImgV = new ImageView(scoreImg);
        scoreImgV.setViewport(new Rectangle2D(0, 0, WIDTH, HEIGHT));

        scoreImgV.setFitWidth(WIDTH);
        scoreImgV.setFitHeight(HEIGHT);

        getChildren().add(scoreImgV);
    }

    public static void showScore(int score) {
//        Score_Numbers number = score;
//        scoreImgV =
//        switch (number) {
//            case ZERO:
//                scoreImgV.setViewport(new Rectangle2D(111, 646 + 320 - height, 52, height));
//                break;
//            case ONE:
//                scoreImgV.setViewport(new Rectangle2D(168, 646, 52, height));
//                break;
//            case TWO:
//                scoreImgV.setViewport(new Rectangle2D(168, 646, 52, height));
//                break;
//            case TREE:
//                scoreImgV.setViewport(new Rectangle2D(168, 646, 52, height));
//                break;
//            case FOUR:
//                scoreImgV.setViewport(new Rectangle2D(168, 646, 52, height));
//                break;
//            case FIVE:
//                scoreImgV.setViewport(new Rectangle2D(168, 646, 52, height));
//                break;
//            case SIX:
//                scoreImgV.setViewport(new Rectangle2D(168, 646, 52, height));
//                break;
//            case SEVEN:
//                scoreImgV.setViewport(new Rectangle2D(168, 646, 52, height));
//                break;
//            case EIGHT:
//                scoreImgV.setViewport(new Rectangle2D(168, 646, 52, height));
//                break;
//            case NINE:
//                scoreImgV.setViewport(new Rectangle2D(168, 646, 52, height));
//                break;
//        }
    }
}
