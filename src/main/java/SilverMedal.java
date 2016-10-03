import javafx.geometry.Rectangle2D;

//Медаль за повторение рекорда
public class SilverMedal extends Medal{

//утанавливаем соответстующее изображение
    public SilverMedal(){
        medalImgV.setViewport(new Rectangle2D(0, 0, WIDTH, HEIGHT));
    }
}
