package draw;

import texture.Texture;
import texture.TextureFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenDrawer {

    public static int screenWidth = 512;
    public static int screenHeight = 512;

    public static int width = 512;
    public static int height = 512;

    public static int whiteColor = new Color(255, 255, 255, 255).getRGB();
    public static int blueColor = new Color(0, 155, 255, 255).getRGB();
    public static int brownColor = new Color(92, 67, 7, 255).getRGB();

    private TextureFactory textureFactory;

    private int[] screenMap;

    private BufferedImage textures;

    public ScreenDrawer(BufferedImage textures, int[] screenMap) {

        this.screenMap = screenMap;
        textureFactory = new TextureFactory();
        this.textures = textures;
    }

    public void drawLine (int x, double lineHeight, float mapX, float mapY, char mapChar) {

        Texture texture = textureFactory.getTextureBySign(mapChar);

        for (int i = 0; i < lineHeight; i++) {
            int coordY = Math.max(screenHeight / 2 - (int)(lineHeight / 2) + i, 0);

            if (coordY >= screenHeight) {
                break;
            }

            screenMap[x + coordY * screenWidth] = texture.getPixelColor(textures, ((double) i / lineHeight), mapX, mapY);
        }

    }

}
