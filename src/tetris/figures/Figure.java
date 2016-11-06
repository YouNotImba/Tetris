package tetris.figures;

import tetris.Direction;
import tetris.Tetris;
import tetris.figures.textures.TextureFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by younotimba on 23.10.2016.
 */
public class Figure {
    private int x;
    private int y;
    private int[][] matrix;
    private int currentRotation = -1;
    private int[][][] rotations;
    private int color;


    public Figure(int x, int y) {
        this.x = x;
        this.y = y;
        color = (int) (Math.random()*4+1);
    }

    public int getCurrentRotation() {
        return currentRotation;
    }


    public void setCurrentRotation(int currentRotation) {
        this.currentRotation = currentRotation;
    }

    public int[][][] getRotations() {
        return rotations;
    }

    public void setRotations(int[][][] rotations) {
        this.rotations = rotations;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void draw(Graphics g) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    g.drawImage(TextureFactory.getTexture(color),(j + x) * 40, (i + y) * 40 - 20,null);
                }

            }

        }
    }

    public boolean isCurrentPositionAvailable() {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    if (y + i >= 20 || x + j >= 10 || x < 0)
                        return false;

                    Integer value = Tetris.canvas.matrix[y + i][x + j];
                    if (value == null || value >= 1)
                        return false;
                }
            }
        }

        return true;
    }

    public void landed() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1)
                    Tetris.canvas.getMatrix()[i + y][j + x] = color;
                 //   Tetris.canvas.getMatrix()[i + y][j + x] = matrix[i][j];
            }
        }

    }

    public void move(Direction direction) {
        switch (direction) {
            case RIGHT:

                x++;
                if (!isCurrentPositionAvailable())
                    x--;

                break;
            case LEFT:

                x--;
                if (!isCurrentPositionAvailable())
                    x++;

                break;
            case UP:
                y -= 1;
                break;
            case DOWN:
                y += 1;
                break;
            case LAND: land();
                break;

        }
    }

    public void land(){
        while(isCurrentPositionAvailable()){
            y++;
        }
        y--;
    }

    public void rotate() {
        int [][] temp = matrix;
        int rotationtemp = currentRotation;
        if (currentRotation == rotations.length - 1)
            currentRotation = -1;
        currentRotation++;
        matrix = rotations[currentRotation];
        if(!isCurrentPositionAvailable()) {
            matrix = temp;
            currentRotation = rotationtemp;
        }
    }


}
