package tetris.view;

import tetris.Direction;
import tetris.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by younotimba on 23.10.2016.
 */
public class Field extends JPanel {
    public boolean shouldDrawFigure = false;

    public boolean isShouldDrawFigure() {
        return shouldDrawFigure;
    }

    public void setShouldDrawFigure(boolean shouldDrawFigure) {
        this.shouldDrawFigure = shouldDrawFigure;
    }

    public Field() {
        this.setSize(401, 805);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        Tetris.currentFigure.move(Direction.RIGHT);
                        repaint();
                        break;

                    case KeyEvent.VK_LEFT:
                        Tetris.currentFigure.move(Direction.LEFT);
                        repaint();
                        break;

                    case KeyEvent.VK_UP:
                        Tetris.currentFigure.rotate();
                        repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        Tetris.currentFigure.land();
                        repaint();
                        break;
                    case KeyEvent.VK_P:

                        try {
                            Tetris.game.sleep(1000000) ;
                        } catch (InterruptedException e1) {

                        }

                        break;
                    case KeyEvent.VK_N:
                        try {
                            Tetris.newGame();
                        } catch (InterruptedException e1) {

                        }
                        break;
                    case KeyEvent.VK_R:
                             Tetris.game.interrupt();
                        break;
                }
            }
        });
        setFocusable(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 401, 800);

       // g.setColor(Color.white);
        g.drawLine(401,0,401,800);
        try {

            Tetris.canvas.draw(g);

        } catch (IOException e) {

        }
        if(shouldDrawFigure)
            Tetris.currentFigure.draw(g);
    }
}
