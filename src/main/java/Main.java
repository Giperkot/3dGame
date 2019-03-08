import controller.KeyController;
import draw.MainPanel;
import draw.ScreenDrawer;
import logic.GameLogic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static draw.ScreenDrawer.*;

public class Main {







    public static void main (String[] args) throws IOException {


        GameLogic gameLogic = new GameLogic();

//        BufferedImage locationImg = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
//        BufferedImage textureImg = new BufferedImage(384, 64, BufferedImage.TYPE_INT_ARGB);




        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("My3dGame");
        mainFrame.setSize(ScreenDrawer.screenWidth + width + 10, ScreenDrawer.screenHeight + 30);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        KeyController keyController = new KeyController(gameLogic);
        mainFrame.addKeyListener(keyController);

        MainPanel mainPanel = new MainPanel();

        mainFrame.add(mainPanel);
        gameLogic.init(mainPanel);

        mainPanel.setVisible(true);
    }



}
