import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class NegatywWatek implements Runnable {
     Thread watek;
     int odwidth;
     int odheight;
     int dowidth;
     int doheight;
     BufferedImage image;

    NegatywWatek(int w, int h,int wDo, int hDo,BufferedImage image) {
       odwidth = w;
       odheight = h;
       dowidth = wDo;
       doheight= hDo;
       this.image=image;
    }
    public void run() {
        for(int i=odheight; i<doheight-1; i++){
            for(int j=odwidth; j<dowidth-1; j++){
                Color c = new Color(image.getRGB(j, i));
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();
                int final_red, final_green, final_blue;
                final_red = 255-red;
                final_green = 255-green;
                final_blue = 255-blue;
                Color newColor = new Color(final_red, final_green, final_blue);
                image.setRGB(j,i,newColor.getRGB());
            }
        }
//        File ouptut = new File("negatyw.jpg");
//        try {
//            ImageIO.write(image, "jpg", ouptut);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}