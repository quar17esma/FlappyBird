import javafx.geometry.Rectangle2D;

//Медаль за побитый рекорд
public class GoldMedal extends Medal {

//утанавливаем соответстующее изображение
    public GoldMedal(){
        medalImgV.setViewport(new Rectangle2D(0, 48, WIDTH, HEIGHT));
    }
}
