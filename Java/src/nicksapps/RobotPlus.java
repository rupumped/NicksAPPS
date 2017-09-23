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
    
   /**
    * Basic constructor.
    */
    public RobotPlus() throws AWTException {
        super();
        board = new Keyboard(this);
    }
    
   /**
    * If you've already initialized a Robot, you can incorporate it into this
    * RobotPlus as an input to the constructor.
    */
    public RobotPlus(GraphicsDevice screen) throws AWTException {
        super(screen);
        board = new Keyboard(this);
    }
    
   /**
    * Returns the Keyboard used by this RobotPlus to automate typing.
    * 
    * @return the Keyboard used by the RobotPlus
    */
    public Keyboard getBoard() {
        return board;
    }
    
   /**
    * Locates an image in the current screenshot. If the image does not exist in
    * the current screenshot, returns the Point (-1,-1
    * 
    * @param subimage BufferedImage you want to locate in the current
    *                 screenshot
    * @return         Point in screen where the top-right corner of the subimage
    *                 exists
    */
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
        return DUMMY;
    }
    
   /**
    * Whether or not the screen contains a particular image.
    * 
    * @param subimage BufferedImage you want to test for in the current
    * screenshot
    * @return         boolean true if the image does exist in the current
    *                 screenshot, false otherwise
    */
    public boolean screenContains(BufferedImage subimage) {
        return !DUMMY.equals(findInScreen(subimage));
    }
    
   /**
    * Delays processing until a given image appears on screen
    * 
    * @param img BufferedImage to wait for before allowing processing to
    *            continue
    */
    public void delayUntilLoad(BufferedImage img) {
        while (!screenContains(img)) {}
    }
    
   /**
    * Whether or not two images contain the same pixels. I feel like this should
    * be implemented in standard libraries, but I couldn't find it. Maybe I'm
    * just bad at searching?
    * 
    * @param image1 the first image
    * @param image2 the second image
    * @return       true if image1 is identical to image2, false otherwise
    */
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
    
   /**
    * Moves the mouse to a given point on the screen
    * 
    * @param point the point to which to move the mouse
    */
    public void mouseMove(Point point) {
        mouseMove(point.x,point.y);
    }
    
   /**
    * Click the mouse
    * 
    * @param button_mask the button of the mouse to press, typically 
    *                    InputEvent.BUTTON1_MASK for left click
    */
    public void click(int button_mask) {
        mousePress(button_mask);
        mouseRelease(button_mask);
    }
    
   /**
    * Click and hold the mouse at a certain point on the screen for a fixed
    * length of time. Length of time can be 0 if you want just a basic click,
    * but it is recommended to make it some small number, like 10, because some
    * applications struggle with immediate mouse button releases.
    * 
    * @param point  the point on the screen at which to click
    * @param period how long to wait before releasing the mouse button
    */
    public void click(Point point, int period) {
        mouseMove(point);
        click(LEFT);
        delay(period);
    }
    
   /**
    * Click and hold the mouse at a certain point on the screen until a specific
    * image appears.
    * 
    * @param point  the point on the screen at which to click
    * @param period the image to wait for before releasing the mouse
    */
    public void click(Point point, BufferedImage period) {
        mouseMove(point);
        click(LEFT);
        delayUntilLoad(period);
    }
    
   /**
    * Click and hold the mouse on a certain image on the screen. Choose in what
    * part of the image you want to click. Length of time can be 0 if you want
    * just a basic click, but it is recommended to make it some small number,
    * like 10, because some applications struggle with immediate mouse button
    * releases.
    * 
    * @param img    the image on which to click
    * @param loc    where you want to click on the image. "TL" => top-left, "TR"
    *               => top-right, "C" => center, "BL" => bottom-left, "BR" =>
    *               bottom-right
    * @param period how long to wait before releasing the mouse button
    */
    public void click(BufferedImage img, String loc, int period) {
        Point pt = findInScreen(img);
        if (pt.equals(DUMMY))
            throw new RuntimeException("Image does not exist.");
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
    
   /**
    * Type a character sequence with the keyboard
    * 
    * @param characters the character sequence to type
    */
    public void type(CharSequence characters) {
        board.type(characters);
    }
    
   /**
    * Type a character sequence with the keyboard, then wait for a fixed period
    * 
    * @param characters the character sequence to type
    * @param period     how long to wait after typing to allow application to
    *                   catch up
    */
    public void type(CharSequence characters, int period) {
        type(characters);
        delay(period);
    }
    
    public static Point DUMMY = new Point(-1,-1);
    public static int LEFT   = InputEvent.BUTTON1_DOWN_MASK;
    public static int MIDDLE = InputEvent.BUTTON2_DOWN_MASK;
    public static int RIGHT  = InputEvent.BUTTON3_DOWN_MASK;
}