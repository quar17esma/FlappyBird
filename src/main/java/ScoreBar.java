import javafx.scene.layout.Pane;

public class ScoreBar {
    Number number1;
    Number number2;
    Number number3;
    String[] scoreStr = new String[3];

    ScoreBar(Pane appRoot){
        number1 = new Number();
        number2 = new Number();
        number3 = new Number();

        number1.setTranslateX((appRoot.getPrefWidth()- Number.WIDTH)/2+28);
        number1.setTranslateY(60);
        appRoot.getChildren().add(number1);
        number2.setTranslateX((appRoot.getPrefWidth()- Number.WIDTH)/2);
        number2.setTranslateY(60);
        appRoot.getChildren().add(number2);
        number3.setTranslateX((appRoot.getPrefWidth()- Number.WIDTH)/2-28);
        number3.setTranslateY(60);
        appRoot.getChildren().add(number3);

        scoreStr[0] = "0";
        scoreStr[1] = "0";
        scoreStr[2] = "0";
    }

    public void showScore(int score){
        if (score>=0&&score<1000) {
            String scoreString = "000";
            if (score < 10) {
                scoreString = "00" + score;
            }
            if (score < 100 && score >= 10) {
                scoreString = "0" + score;
            }
            scoreStr = scoreString.split("");
            number1.showNumber(scoreStr[2]);
            number2.showNumber(scoreStr[1]);
            number3.showNumber(scoreStr[0]);
        }
    }
}
