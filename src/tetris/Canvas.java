package tetris;

import tetris.figures.textures.TextureFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by younotimba on 23.10.2016.
 */
public class Canvas {
    public static int[][] matrix = new int[20][10];
    private ArrayList<Integer> lineForRemove  = new ArrayList<>();
    private int[][] matrixForRemoveEffect = new int[20][10];

    public Canvas() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {

                 matrix[i][j] = 0;
            }
        }
    }

    public ArrayList<Integer> getLineForRemove() {
        return lineForRemove;
    }

    public int[][] getMatrixForRemoveEffect() {
        return matrixForRemoveEffect;
    }

    public void setMatrixForRemoveEffect(int[][] matrixForRemoveEffect) {
        this.matrixForRemoveEffect = matrixForRemoveEffect;
    }

    public static int[][] getMatrix() {
        return matrix;
    }

    public static void setMatrix(int[][] matrix) {
        Canvas.matrix = matrix;
    }

    public void draw(Graphics g) throws IOException {
       // removeLineDrawEffect(g);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if(matrix[i][j] != 0) {
                    g.drawImage(TextureFactory.getTexture(matrix[i][j]), j * 40, i * 40 - 20, null);
                }
            }
        }
    }

    public int[][] removeLineDrawEffect(){

        int [] [] temp = matrix.clone();
        matrix = matrixForRemoveEffect.clone();
            for (Integer z : lineForRemove) {
                matrix[z] = new int[10];
            }

        return temp;
    }

    public int removeFullLines(){
        int score = 0;
        ArrayList<Integer> fullLines = new ArrayList<>();
        matrixForRemoveEffect = matrix.clone();
        //Создаем список для хранения линий
        ArrayList<int[]> lines = new ArrayList<int[]>();

        //Копируем все непустые линии в список.
        for (int i = 0; i <20; i++)
        {
            //подсчитываем количество единиц в строке - просто суммируем все ее значения
            int count = 0;
            for (int j = 0; j < 10; j++)
            {
                if(matrix[i][j]!=0)
                    count ++;
            }

            //Если сумма строки не равно ее ширине - добавляем в список
            if (count != 10)
                lines.add(matrix[i]);
            else {
                score++;
                lineForRemove.add(i);
            }
        }

        //Добавляем недостающие строки в начало списка.
        while (lines.size()<20)
        {
            lines.add(0,new int[10]);
        }

        //Преобразуем список обратно в матрицу
        matrix = lines.toArray(new int[20][10]);
        switch(score){
            case 1: return 100;

            case 2: return 300;

            case 3: return 500;

            case 4: return 1000;

            default:return 0;
        }

    }

}
