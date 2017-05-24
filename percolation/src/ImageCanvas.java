import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
public class ImageCanvas extends Canvas {

        private BufferedImage img;

        public ImageCanvas() {
            try {
                img = ImageIO.read(new File("C:/Users/Rajiv Sarvepalli/Desktop/8puzzle1.jpg"));  //put in the location of 8puzzle1.jpg, for example C:/Users/Rajiv Sarvepalli/Desktop/School work/AP CS/8puzzle1.jpg
            } catch (IOException ex) {//exception catching 
                ex.printStackTrace();
				}
		  }      
		  @Override
        public void paint(Graphics g) {
            super.paint(g);//paint the image onto the canvas
            if (img != null) {
                int x = (getWidth() - img.getWidth()) / 2;//finding width
                int y = (getHeight() - img.getHeight()) / 2;//finding height
                g.drawImage(img, x, y, this);
            }
        }

    }
