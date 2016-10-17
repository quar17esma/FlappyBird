import javafx.scene.layout.Pane;

public class ScoreBar extends Pane {
    //единицы
    Number number1;
    //десятки
    Number number2;
    //сотни
    Number number3;

    private String[] scoreStr = new String[3];

    ScoreBar(double size) {
        number1 = new Number();
        number2 = new Number();
        number3 = new Number();

        number1.setTranslateX(28*0*size);
        number1.setTranslateY(0);
        number2.setTranslateX(28*1*size);
        number2.setTranslateY(0);
        number3.setTranslateX(28*2*size);
        number3.setTranslateY(0);

        number1.scoreImgV.setFitWidth(Number.WIDTH*size);
        number1.scoreImgV.setFitHeight(Number.HEIGHT*size);
        number2.scoreImgV.setFitWidth(Number.WIDTH*size);
        number2.scoreImgV.setFitHeight(Number.HEIGHT*size);
        number3.scoreImgV.setFitWidth(Number.WIDTH*size);
        number3.scoreImgV.setFitHeight(Number.HEIGHT*size);

        getChildren().addAll(number1, number2, number3);
    }

    public void showScore(int score) {
        if (score >= 0 && score < 1000) {
            String scoreString = "000";

            if (score < 10) {
                scoreString = "00" + score;
            } else if (score < 100) {
                scoreString = "0" + score;
            }

            scoreStr = scoreString.split("");

            number1.showNumber(scoreStr[0]);
            number2.showNumber(scoreStr[1]);
            number3.showNumber(scoreStr[2]);
        }
    }
}
