package tetris.view;



import com.sun.prism.Texture;
import tetris.Tetris;
import tetris.figures.FigureFactory;
import tetris.figures.textures.TextureFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by younotimba on 23.10.2016.
 */
public class View extends JFrame{
    private Field field;
    private JLabel score;
    private JLabel figure;
  //  private int width;
   // private int height;

    public View() throws HeadlessException {

        setBackground(Color.BLACK);
        setSize(530,805);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        field = new Field();
        field.setSize(401,800);
        getContentPane().add(field);
        score = new JLabel();
        score.setForeground(Color.GREEN);
        score.setText("SCORE");

        JLabel nextFigure = new JLabel();
        nextFigure.setForeground(Color.WHITE);
        nextFigure.setText("Next Figure");

        figure = new JLabel();
        figure.setIcon(TextureFactory.getIcon(Tetris.nextFigure));

        JLabel information = new JLabel("N = Start Game");
        information.setForeground(Color.WHITE);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(-650,10,0,10);
        rightPanel.add(score,c);
        c.gridy++;
        c.insets = new Insets(-400,10,0,10);
        rightPanel.add(nextFigure,c);
        c.gridy++;
        c.insets = new Insets(-200,10,0,10);
        rightPanel.add(figure,c);
        c.gridy++;
        c.insets = new Insets(0,0,0,0);
        rightPanel.add(information,c);

        rightPanel.setSize(100,800);

        rightPanel.setBackground(Color.BLACK);

        getContentPane().add(rightPanel,BorderLayout.EAST);

        setVisible(true);
    }

    public void updateScore(String text){
        score.setText(text);
        score.repaint();
        figure.repaint();
    }

    public Field getField() {
        return field;
    }

    public JLabel getFigure() {
        return figure;
    }

    public void setNextFigure(ImageIcon image){
        this.figure.setIcon(image);
    }

    public void setFigure(JLabel figure) {
        this.figure = figure;
    }

    public void update(){
        field.repaint();
    }

    public void gameOver(){JOptionPane.showMessageDialog(this,"Game Over !!\n Your "+score.getText());}
}
