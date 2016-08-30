import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Сергей on 16.08.2016.
 */
public class Test {
    public static void main(String[] args) {
        int x = 123456;
        int y;
        ArrayList<Integer> numbList =  new ArrayList();
        while(x > 0) {
            y = x % 10;
            numbList.add(y);
            x /= 10;
        }
        Collections.reverse(numbList);
        System.out.println(numbList);
    }
}
