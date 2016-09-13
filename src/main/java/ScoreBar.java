
public class ScoreBar {
    Score score1;
    Score score2;
    Score score3;
    String[] scoreStr = new String[3];

    ScoreBar(){
        score1 = new Score();
        score2 = new Score();
        score3 = new Score();

        score1.setTranslateX((Game.appRoot.getPrefWidth()-Score.WIDTH)/2);
        score1.setTranslateY(60);
        Game.appRoot.getChildren().add(score1);
        score2.setTranslateX((Game.appRoot.getPrefWidth()-Score.WIDTH)/2-28);
        score2.setTranslateY(60);
        Game.appRoot.getChildren().add(score2);
        score3.setTranslateX((Game.appRoot.getPrefWidth()-Score.WIDTH)/2-28*2);
        score3.setTranslateY(60);
        Game.appRoot.getChildren().add(score3);

        scoreStr[0] = "0";
        scoreStr[1] = "0";
        scoreStr[2] = "0";
    }

    public void showScore(int score){
        String scoreString = "000";
        if (score<10){
            scoreString = "00" + score;
        }
        if (score<100&&score>=10){
            scoreString = "0" + score;
        }
        scoreStr = scoreString.split("");
        score1.showNumber(scoreStr[2]);
        score2.showNumber(scoreStr[1]);
        score3.showNumber(scoreStr[0]);
    }
}
