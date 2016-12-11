package nicksapps;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

public class RobotPlus extends Robot {
    private Keyboard board;
    
    public RobotPlus() throws AWTException {
        super();
        board = new Keyboard(this);
    }
    
    public RobotPlus(GraphicsDevice screen) throws AWTException {
        super(screen);
        board = new Keyboard(this);
    }
    
    public Keyboard getBoard() {
        return board;
    }
    
    public Point findInScreen(BufferedImage subimage) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();
        BufferedImage screen = this.createScreenCapture(new Rectangle(screenWidth,screenHeight));
        int subWidth = subimage.getWidth();
        int subHeight = subimage.getHeight();
        for (int screenRow = 0; screenRow < screenWidth - subWidth; screenRow++) {
            for (int screenCol = 0; screenCol < screenHeight - subHeight; screenCol++) {
                if (imgEqual(screen.getSubimage(screenRow, screenCol, subWidth, subHeight), subimage)) {
                    return new Point(screenRow, screenCol);
                }
            }
        }
        throw new RuntimeException("Subimage does not exist on screen");
    }
    
    public boolean screenContains(BufferedImage subimage) {
        try {
            findInScreen(subimage);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
    
    public void delayUntilLoad(BufferedImage img) {
        while (!screenContains(img)) {}
    }
    
    public static boolean imgEqual(BufferedImage image1, BufferedImage image2) {
        int width;
        int height;
        boolean imagesEqual = true;

        if( image1.getWidth()  == ( width  = image2.getWidth() ) && 
            image1.getHeight() == ( height = image2.getHeight() ) ){

            for(int x = 0;imagesEqual == true && x < width; x++){
                for(int y = 0;imagesEqual == true && y < height; y++){
                    if( image1.getRGB(x, y) != image2.getRGB(x, y) ){
                        imagesEqual = false;
                    }
                }
            }
        }else{
            imagesEqual = false;
        }
        return imagesEqual;
    }
    
    public void mouseMove(Point point) {
        mouseMove(point.x,point.y);
    }
    
    public void click(Point point, int period) {
        mouseMove(point);
        mousePress(InputEvent.BUTTON1_DOWN_MASK);
        mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        delay(period);
    }
    
    public void click(Point point, BufferedImage period) {
        mouseMove(point);
        mousePress(InputEvent.BUTTON1_DOWN_MASK);
        mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        delayUntilLoad(period);
    }
    
    public void click(BufferedImage img, String loc, int period) {
        Point pt = findInScreen(img);
        int w = img.getWidth();
        int h = img.getHeight();
        switch (loc) {
            case "TL": break;
            case "TR": pt.x += w; break;
            case "C":  pt.x += w/2;
                       pt.y += h/2; break;
            case "BL": pt.y += h; break;
            case "BR": pt.x += w;
                       pt.y += h; break;
        }
        click(pt, period);
    }
    
    public void type(CharSequence characters) {
        board.type(characters);
    }
}