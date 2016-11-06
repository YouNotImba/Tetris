package tetris.figures;

/**
 * Created by younotimba on 23.10.2016.
 */
public class FigureFactory {

    public static Figure getFigure (int x,int y){
        int index =(int) (Math.random()*5);
        switch(index){
            case 0 : return new L(x,y);

            case 1: return new Line(x,y);

            case 2: return new Rectangle(x,y);

            case 3: return new T(x,y);

            case 4: return new Z(x,y);

            default: return null;
        }

    }
}
