package nicksapps;

import java.util.Scanner;

public class ScannerPlus
{
   /**
    * Skips lines
    * 
    * @param scanner in which to skip
    * @param lines   number of lines to skip
    */
    public static void skipLines(Scanner scanner, int lines)                                        //skips lines in a Scanner
    {
        for(int cl = 0; cl < lines; cl++) {
            scanner.nextLine();
        }
    }

   /**
    * Skips lines until a nonempty line is found
    * 
    * @param scanner in which to skip
    * @return        content of first non-empty line
    */
    public static String skipUntilContent(Scanner scanner)                                          //skips through scanner until line is not empty and returns line
    {
        String line = scanner.nextLine();
        while (line.equals(""))
            line = scanner.nextLine();
        return line;
    }

   /**
    * Skip lines until an empty line is found
    * 
    * @param scanner in which to skip
    */
    public static void skipUntilBlank(Scanner scanner)                                              //skips through scanner until line is empty
    {
        while (!scanner.nextLine().equals(""));
    }

   /**
    * Skip lines until a given format is encountered
    * 
    * @param scanner in which to skip
    * @param format  format for which to search
    * @return        content of first line with given format
    */
    public static String skipUntilFormat(Scanner scanner, String... format)                         //skips through scanner until line fits StringPlus format
    {
        String line = scanner.nextLine();
        while (!StringPlus.isFormat(line, format))
            line = scanner.nextLine();
        return line;
    }
}