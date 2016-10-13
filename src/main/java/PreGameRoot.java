import javafx.scene.layout.Pane;

//панель подсказое перед началом игры
public class PreGameRoot extends Pane {
    //надпись (Get Ready!)
    private GetReady getReady;
    //подсказка (клик по окну)
    private Tap tap;

    //создаем и добавляем елементы
    public PreGameRoot(Bird bird) {
        getReady = new GetReady();
        getReady.setTranslateX(bird.velocity.getX()+(Game.getStageWidth()-GetReady.getFitWidth())/2);
        getReady.setTranslateY(130);

        tap = new Tap();
        tap.setTranslateX(bird.velocity.getX()+(Game.getStageWidth()-Tap.getFitWidth())/2);
        tap.setTranslateY(250);

        getChildren().addAll(getReady, tap);
    }
}
