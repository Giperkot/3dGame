package texture;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TextureFactory {

    private List<Texture> textureList = new ArrayList<Texture>();

    public TextureFactory() {
        textureList.add(new Texture(0, 0, 64, 64));
        textureList.add(new Texture(64, 0, 64, 64));
        textureList.add(new Texture(128, 0, 64, 64));
        textureList.add(new Texture(192, 0, 64, 64));
        textureList.add(new Texture(256, 0, 64, 64));
        textureList.add(new Texture(320, 0, 64, 64));

    }

    public Texture getTextureBySign (char sign) {

        switch (sign) {
            case '0':
                return textureList.get(0);
            case '1':
                return textureList.get(1);
            case '2':
                return textureList.get(2);
            case '3':
                return textureList.get(3);
            case '4':
                return textureList.get(4);
            case '5':
            default:
                return textureList.get(5);
        }
    }
}
