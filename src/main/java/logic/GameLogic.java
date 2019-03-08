package logic;

import draw.MainPanel;
import draw.ScreenDrawer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static draw.ScreenDrawer.*;

public class GameLogic {

    private String wallMap = "0000222222220000" +
            "1              0" +
            "1      11111   0" +
            "1     0        0" +
            "0     0  1110000" +
            "0     3        0" +
            "0   10000      0" +
            "0   0   11100  0" +
            "0   0   0      0" +
            "0   0   1  00000" +
            "0       1      0" +
            "2       1      0" +
            "0       0      0" +
            "0 0000000      0" +
            "0              0" +
            "0002222222200000";

    private static int mapWidth = 16;
    private static int mapHeight = 16;

    private static int squareWidth = width / mapWidth;
    private static int squareHeight = height / mapHeight;

    private static float playerX = 3.4f;
    private static float playerY = 2.8f;
    private static float playerA = (float)Math.PI * 3 /4; //(float)Math.PI / 2;
    private static float sightAngle = (float)Math.PI / 3;

    int[] map;
    int[] screenMap;


    private ScreenDrawer screenDrawer;
    private MainPanel mainPanel;

    public void init(MainPanel mainPanel) throws IOException {
        map = new int[width * height];
        screenMap = new int[screenWidth * screenHeight];

        this.mainPanel = mainPanel;
        mainPanel.setScreenMap(screenMap);
        mainPanel.setMap(map);

        File textureFile = new File("walltext.png");
        BufferedImage textureImg  = ImageIO.read(textureFile);

        screenDrawer = new ScreenDrawer(textureImg, screenMap);
        render();
    }

    public void rotate (double angle) {
        playerA += angle;

        render();
    }

    public void goForward () {
        double step = 0.4;

        float newPlayerX = playerX + (float)(step * Math.cos(playerA));
        float newPlayerY = playerY + (float)(step * Math.sin(playerA));

        if (wallMap.charAt((int) newPlayerX + mapWidth * (int) newPlayerY) != ' ') {
            return;
        }

        playerX = newPlayerX;
        playerY = newPlayerY;
        System.out.println(playerX + " " + playerY);

        render();
    }

    public void goRight () {
        double step = 0.2;
        double angle2 = playerA + Math.PI / 2;

        float newPlayerX = playerX + (float)(step * Math.cos(angle2));
        float newPlayerY = playerY + (float)(step * Math.sin(angle2));

        if (wallMap.charAt((int) newPlayerX + mapWidth * (int) newPlayerY) != ' ') {
            return;
        }

        playerX = newPlayerX;
        playerY = newPlayerY;

        render();
    }

    public void goBackward () {
        double step = 0.3;

        float newPlayerX = playerX - (float)(step * Math.cos(playerA));
        float newPlayerY = playerY - (float)(step * Math.sin(playerA));

        if (wallMap.charAt((int) newPlayerX + mapWidth * (int) newPlayerY) != ' ') {
            return;
        }

        playerX = newPlayerX;
        playerY = newPlayerY;

        render();
    }

    public void goLeft () {
        double step = 0.2;
        double angle2 = playerA + Math.PI / 2;

        float newPlayerX = playerX - (float)(step * Math.cos(angle2));
        float newPlayerY = playerY - (float)(step * Math.sin(angle2));

        if (wallMap.charAt((int) newPlayerX + mapWidth * (int) newPlayerY) != ' ') {
            return;
        }

        playerX = newPlayerX;
        playerY = newPlayerY;

        render();

        render();
    }

    public void clearAll () {
        mainPanel.clearAll();
    }

    public void render () {

        for (int i = 0; i < screenWidth * screenHeight; i++) {
            map[i] = 0;
            screenMap[i] = 0;
        }

        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                if (' ' != wallMap.charAt(i * mapWidth + j)) {
                    drawRect(map, j, i, squareWidth, squareHeight, blueColor);
                }
            }
        }

        drawRect(map, playerX, playerY, 5, 5, whiteColor);

        float angleStep = sightAngle / screenWidth;

        int q = 0;
        for (float visible = 0; visible < sightAngle; visible += angleStep) {
            float visibleAngle = playerA + visible - sightAngle / 2;

            for (float k = 0; ; k += 0.05) {
                float sightX = (float) (k * Math.cos(visibleAngle) + playerX);
                float sightY = (float) (k * Math.sin(visibleAngle) + playerY);

                char mapChar = wallMap.charAt((int) sightX + mapWidth * ((int) sightY));
                if (mapChar != ' ') {
                    double lineHeight = 600 / k / Math.cos(visibleAngle - playerA);
                    /*locationImgGraphics.drawLine(q, screenHeight / 2 - (int)(200 / k / Math.cos(visibleAngle - playerA)),
                            q, screenHeight / 2 + (int)(200 / k / Math.cos(visibleAngle - playerA)));*/
                    screenDrawer.drawLine(q, lineHeight, sightX, sightY, mapChar);
                    break;
                }

//                drawPoint(screenMap, );

                drawPoint(map, sightX, sightY, whiteColor);
            }
            q++;
        }

        System.out.println("rendered");
//        mainPanel.repaint();
        mainPanel.updateUI();
    }

    private static void drawRect (int[] map, float x, float y, int sqWidth, int sqHeight, int color) {
        int startY = (int)(y * squareHeight);
        int startX = (int)(x * squareWidth);

        for (int i = startY ; i < startY + sqHeight; i++) {
            for (int j = startX; j < startX + sqWidth; j++) {
                map[i * width + j] = color;
            }
        }
    }

    private static void drawPoint (int[] map, float x, float y, int color) {
        int i = (int)(y * squareHeight);
        int j = (int)(x * squareWidth);

        map[i * width + j] = color;
    }
}
