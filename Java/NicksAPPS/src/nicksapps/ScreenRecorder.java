package nicksapps;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.api.awt.AWTSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Rational;

public class ScreenRecorder {
    static final int DELAY_SECONDS = 5;     // seconds
    static final int FPS = 8;               // frames per second
    static final int RECORD_LENGTH = 5;     // seconds
    
    public static void main(String[] args) throws AWTException, IOException {
        Robot r = new Robot();
        
        // Get bounding rectangle for screen recording
        System.out.println("Move your mouse to the top left corner of the rectangle you want to record.\n"
                + "System will record the point in...");
        for (int s=DELAY_SECONDS;s>0;s--) {
            System.out.println(s);
            r.delay(1000);
        }
        Point topLeft = MouseInfo.getPointerInfo().getLocation();
        System.out.println("Top Left Corner: (" + topLeft.x + "," + topLeft.y + ")");
        
        System.out.println("Move your mouse to the bottom right corner of the rectangle you want to record.\n"
                + "System will record the point in...");
        for (int s=DELAY_SECONDS;s>0;s--) {
            System.out.println(s);
            r.delay(1000);
        }
        Point botRight = MouseInfo.getPointerInfo().getLocation();
        System.out.println("Bottom Right Corner: (" + botRight.x + "," + botRight.y + ")");
        
        // Adjust width and height to be multiples of 2 for color space
        int width = botRight.x-topLeft.x;
        width+= ((width%2==0)?0:1);
        int height = botRight.y-topLeft.y;
        height+= ((height%2==0)?0:1);
        
        // Record screenshots
        BufferedImage[] imgArr = new BufferedImage[FPS*RECORD_LENGTH];
        System.out.println("Will begin recording in...");
        for (int s=DELAY_SECONDS;s>0;s--) {
            System.out.println(s);
            r.delay(1000);
        }
        for (int f=0;f<imgArr.length;f++) {
            imgArr[f] = r.createScreenCapture(new Rectangle(topLeft.x,topLeft.y,width,height));
            r.delay(1000/FPS);
        }
        
        // Create video from screenshots
        SeekableByteChannel out = null;
        try {
            out = NIOUtils.writableFileChannel("output.mp4");
            // for Android use: AndroidSequenceEncoder
            AWTSequenceEncoder encoder = new AWTSequenceEncoder(out, Rational.R(FPS, 1));
            for (BufferedImage img : imgArr) {
                // Encode the image
                encoder.encodeImage(img);
            }
            // Finalize the encoding, i.e. clear the buffers, write the header, etc.
            encoder.finish();
        } finally {
            NIOUtils.closeQuietly(out);
        }
    }
}