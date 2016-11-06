package tetris.figures;

/**
 * Created by younotimba on 23.10.2016.
 */
public class Rectangle extends Figure {
    private int[][] currentMatrix = {
            {1, 1},
            {1, 1},

            };

    public Rectangle(int x, int y) {
        super(x, y);
        super.setMatrix(currentMatrix);
        super.setRotations(rotations);
    }

    public int[][] getCurrentMatrix() {
        return currentMatrix;
    }

    private static int [][][] rotations = {{

            {1, 1},
            {1, 1}

            }};
}
