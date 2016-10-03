import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

//класс отвечает за анимацию птички
public class SpriteAnimation  extends Transition{
    private final ImageView imageView;                      //изображение
    private final int count;                                //кол-во кадров

    private int offsetX;                                    //координаты первого кадра
    private int offsetY;

    private final int width;                                //размер кадра
    private final int height;

    public SpriteAnimation(                                 //конструктор с параметрами и временем

//параметры конструктора
       ImageView imageView,
       Duration duration,
       int count,
       int offsetX, int offsetY,
       int width, int height
    ){
        this.imageView = imageView;
        this.count = count;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
                                                                //устанавливаем:
        setCycleDuration(duration);                             //длительность одниго круга кадров,
        setCycleCount(Animation.INDEFINITE);                    //кол-во повторений анимации,
        setInterpolator(Interpolator.LINEAR);                   //скорость анимации (линейная),

                                                                //указываем кадр на картинке(расположение и размер)
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    }

    public void setOffsetX(int x){
        this.offsetX = x;
    }         //смена кадра
    public void setOffsetY(int y){
        this.offsetY = y;
    }         //смена колонки


//изменяем изображение, вызывается при каждом кадре анимации (заначение параметра изменяется от 0.00 до 1.00)
    protected void interpolate(double frac) {
        final int index = Math.min((int)Math.floor(count*frac), count-1);
        final int x = index*width+offsetX;            //столбец кадра (изменяется с изменением frac)

        imageView.setViewport(new Rectangle2D(x, offsetY, width, height));    //смена кадра с новыми координатами
    }
}