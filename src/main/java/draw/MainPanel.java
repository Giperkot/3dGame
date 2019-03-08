package draw;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static draw.ScreenDrawer.*;


public class MainPanel extends JPanel {

    private int[] screenMap;

    private int[] map;

    BufferedImage locationImg = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
    BufferedImage mapImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        locationImg.getGraphics().setColor(new Color(255, 0, 0));
        /*locationImg.getGraphics().fillRect(0, 0, locationImg.getWidth(), locationImg.getHeight());
        mapImage.getGraphics().drawRect(0, 0, mapImage.getWidth(), mapImage.getHeight());*/
/*
        locationImg = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        mapImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);*/

        /*if (true)
        return;*/

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                if (screenMap[i + screenWidth * j] != 0) {
                    locationImg.setRGB(i, j, screenMap[i + screenWidth * j]);
                } else {
                    if (j < height / 2) {
                        locationImg.setRGB(i, j, blueColor);
                    } else {
                        locationImg.setRGB(i, j, brownColor);
                    }

                }


                if (map[i + width * j] == whiteColor || map[i + width * j] == blueColor) {
                    mapImage.setRGB(i, j, map[i + width * j]);
                    continue;
                }

                int red = 255 * j / height;
                int green = 255 * i / width;
                int blue = 0;

                map[i + width * j] = new Color(red, green, blue, 255).getRGB();

                mapImage.setRGB(i, j, map[i + width * j]);
            }
        }

        g.drawImage(locationImg, 0, 0, null);
        g.drawImage(mapImage, screenWidth, 0, null);
/*
        File file = new File("out.png");
        File file2 = new File("out2.png");
        try {
            ImageIO.write(mapImage, "png", file);
            ImageIO.write(locationImg, "png", file2);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void clearAll () {

    }

    public int[] getScreenMap() {
        return screenMap;
    }

    public void setScreenMap(int[] screenMap) {
        this.screenMap = screenMap;
    }

    public int[] getMap() {
        return map;
    }

    public void setMap(int[] map) {
        this.map = map;
    }
}
