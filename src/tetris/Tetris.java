package tetris;

import tetris.figures.Figure;
import tetris.figures.FigureFactory;
import tetris.figures.textures.TextureFactory;
import tetris.view.View;

/**
 * Created by younotimba on 23.10.2016.
 */
public class Tetris {
    public static Figure currentFigure;
    public static Figure nextFigure;
    public static Canvas canvas;
    private static boolean isGameOver = false;
    private static int speed = 500;
    private static int score = 0;
    public static View view;
    public static Thread game;
    public static boolean isGamePaused = false;

    public static void main(String[] args) throws InterruptedException {
        canvas = new Canvas();
        currentFigure = FigureFactory.getFigure(5,0);
        nextFigure = FigureFactory.getFigure(5,0);
        view = new View();
        /*while(!isGameOver){
            step();
            speedUp();
            view.updateScore("SCORE : "+score+"");
            view.setNextFigure(TextureFactory.getIcon(nextFigure));
            Thread.sleep(speed);
            view.update();
        }
        view.gameOver();*/
    }

    public static void newGame() throws InterruptedException {
        //currentFigure = FigureFactory.getFigure(5,0);
        game = new Thread(new Runnable() {
            @Override
            public void run() {
                currentFigure = FigureFactory.getFigure(5,0);
                nextFigure = FigureFactory.getFigure(5,0);
                view.getField().setShouldDrawFigure(true);
                while(!isGameOver){

                        step();
                        speedUp();
                        view.updateScore("SCORE : "+score+"");
                        view.setNextFigure(TextureFactory.getIcon(nextFigure));
                        try {
                            Thread.sleep(speed);
                        } catch (InterruptedException e) {

                        }
                        view.update();

                }
                view.gameOver();
            }
        });
        game.start();

    }

    public static void step(){
        currentFigure.move(Direction.DOWN);
        if(!currentFigure.isCurrentPositionAvailable()){
            currentFigure.move(Direction.UP);
            currentFigure.landed();
            isGameOver = currentFigure.getY() <= 1;

            score+=canvas.removeFullLines();
            if(!canvas.getLineForRemove().isEmpty()){
                view.getField().setShouldDrawFigure(false);
                int [][] origin = canvas.getMatrix().clone();
                for (int i = 0; i < 2 ; i++) {
                    canvas.removeLineDrawEffect();
                    view.update();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    canvas.setMatrix(canvas.getMatrixForRemoveEffect());
                    view.update();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                }
                canvas.getLineForRemove().clear();
                canvas.setMatrix(origin);
                view.getField().setShouldDrawFigure(true);
            }

            currentFigure = nextFigure;
            nextFigure = FigureFactory.getFigure(5,0);
        }
    }

    public static void speedUp(){
        if(score >= 500 && score < 2000)
            speed = 350;
        if(score>= 2000 && score < 4000)
            speed = 200;
        if(score >=4000 && score < 6000)
            speed = 100;
        if(score >=6000 && score < 8000)
            speed = 50;
        if(score >= 8000 && score < 10000)
            speed = 20;
        if(score >= 10000)
            speed = 3;
    }
}
