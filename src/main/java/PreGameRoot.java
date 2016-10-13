import javafx.scene.layout.Pane;

//панель подсказое перед началом игры
public class PreGameRoot extends Pane {
    //надпись (Get Ready!)
    private GetReady getReady;
    //подсказка (клик по окну)
    private Tap tap;

    //координаты елементов
    private static final double GET_READY_Y = 130;
    private static final double TAP_Y = 250;

    //создаем и добавляем елементы
    public PreGameRoot(Bird bird) {
        getReady = new GetReady();
        getReady.setTranslateX(bird.velocity.getX()+(Game.getStageWidth()-GetReady.getFitWidth())/2);
        getReady.setTranslateY(GET_READY_Y);

        tap = new Tap();
        tap.setTranslateX(bird.velocity.getX()+(Game.getStageWidth()-Tap.getFitWidth())/2);
        tap.setTranslateY(TAP_Y);

        getChildren().addAll(getReady, tap);
    }
}
