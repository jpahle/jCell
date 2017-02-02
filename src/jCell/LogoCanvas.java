package jCell;
import jCell.*;
import java.awt.*;

public class LogoCanvas extends Canvas {

    private Image myImage;

    public LogoCanvas(Image logo) {
	myImage = logo;
    }


    public Dimension getPreferredSize() {
	return new Dimension(100, 100);
    }

    public void paint(Graphics g) {
	g.drawImage(myImage, 0, 0, 100, 100, this);
    }
}
					      
