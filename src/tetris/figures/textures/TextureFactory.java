package tetris.figures.textures;

import tetris.figures.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by younotimba on 25.10.2016.
 */
public class TextureFactory {

    private static final TextureFactory ourInstance = new TextureFactory();

    public static BufferedImage TEXTURE1;
    public static BufferedImage TEXTURE2;
    public static BufferedImage TEXTURE3;
    public static BufferedImage TEXTURE4;
    public static ImageIcon TEXTURE5;
    public static ImageIcon TEXTURE6;
    public static ImageIcon TEXTURE7;
    public static ImageIcon TEXTURE8;
    public static ImageIcon TEXTURE9;

    public static BufferedImage getTexture(int color){
        switch (color){
            case 1: return TEXTURE1;
            case 2: return TEXTURE2;
            case 3: return TEXTURE3;
            case 4: return TEXTURE4;
            default: return null;
        }
    }

    public static TextureFactory getOurInstance(){
        return ourInstance;
    }

    public static ImageIcon getIcon(Figure figure){
        if(figure instanceof L)
            return TEXTURE6;
        if(figure instanceof Line)
            return TEXTURE5;
        if(figure instanceof Rectangle)
            return TEXTURE7;
        if(figure instanceof T)
            return TEXTURE8;
        if(figure instanceof Z)
            return TEXTURE9;
        return null;
    }


    private TextureFactory(){
        try {
            TEXTURE1 = ImageIO.read(getClass().getResourceAsStream("TatrisRect2.jpg"));
            TEXTURE2 = ImageIO.read(getClass().getResourceAsStream("TatrisRect3.jpg"));
            TEXTURE3 = ImageIO.read(getClass().getResourceAsStream("TatrisRect4.jpg"));
            TEXTURE4 = ImageIO.read(getClass().getResourceAsStream("TatrisRect5.jpg"));
            TEXTURE5 = new ImageIcon(getClass().getResource("line.jpg"));
            TEXTURE6 = new ImageIcon(getClass().getResource("L.jpg"));
            TEXTURE7 = new ImageIcon(getClass().getResource("rect.jpg"));
            TEXTURE8 = new ImageIcon(getClass().getResource("T.jpg"));
            TEXTURE9 = new ImageIcon(getClass().getResource("Z.jpg"));
        } catch (IOException e) {

        }

    }
}
