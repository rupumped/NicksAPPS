package nicksapps;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Storyteller {
    public static void main(String[] args) throws AWTException, FileNotFoundException {
        Random r = new Random();
        RobotPlus writer = new RobotPlus();
        Scanner story = new Scanner(new File("story.txt"));
        writer.delay(2000);
//        NumberList test = new NumberList();
        while (story.hasNextLine()) {
            String line = story.nextLine();
            writer.type(line);
            writer.keyPress(KeyEvent.VK_ENTER);
            writer.keyRelease(KeyEvent.VK_ENTER);
            int del = (int)Math.min((int)Math.abs(2000*line.split(" ").length + 1000) + ((line.contains("."))?5000:0),40000);
//            test.add(del);
            writer.delay(del);
        }
//        System.out.println(test.sum().doubleValue()/60000);
        story.close();
    }
}