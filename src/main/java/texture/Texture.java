package texture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Texture {

    private int textureStartX;

    private int textureStartY;

    private int height;

    private int width;

    public Texture(int textureStartX, int textureStartY, int height, int width) {
        this.textureStartX = textureStartX;
        this.textureStartY = textureStartY;
        this.height = height;
        this.width = width;
    }

    public int getPixelColor (BufferedImage image, double heightPercent, float mapX, float mapY) {

        float noIntCoord = 0;

        float w = mapX % 1;
        w = Math.min(w, 1 - w);
        float q = mapY % 1;
        q = Math.min(q, 1 - q);

        if (w < q) {
            noIntCoord = q;
        } else {
            noIntCoord = w;
        }

//        System.out.println(noIntCoord);

        int coordY = (int)(textureStartY + height * heightPercent);
        int coordX = (int)(textureStartX + noIntCoord * width);
        return image.getRGB(coordX, coordY);
    }

}
