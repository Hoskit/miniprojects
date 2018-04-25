import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageCropping {
    public static void main(String[] args) {
        final int correctPicture = 12;

        final int height = 20;
        final int width  = 82;

        final File          img = new File("/Users/tonyye/Desktop/Stuff/download.gif");
        final BufferedImage actual;

        try {
            actual = ImageIO.read(img);
            for (int i = 0; i < 80; i++) {
                if (i == correctPicture - 1) {
                    final int x = i / 10 * width;
                    final int y = i % 10 * height;

                    BufferedImage cropped = actual.getSubimage(x, y, width, height);
                    System.out.println(cropped);
                    File output = new File("/Users/tonyye/Desktop/Stuff/" + (i + 1) + ".gif");
                    ImageIO.write(cropped, "gif", output);
                }
            }
        }
        catch (IOException ignored) {
        }
    }
}
