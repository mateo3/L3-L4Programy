import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
public class Negatyw {
    public static void main(String[] args) {
        int width=1;
        int height=1;
        BufferedImage image;
        File zdj = new File("ratusz.jpg");
        try {
            image = ImageIO.read(zdj);
            width = image.getWidth();
            height = image.getHeight();
            new NegatywWatek(1,1,width,480, image).run();
            new NegatywWatek(1,479,width,height, image).run();
            File ouptut = new File("negatyw.jpg");
            ImageIO.write(image, "jpg", ouptut);
        } catch (Exception e) {}


    }
}