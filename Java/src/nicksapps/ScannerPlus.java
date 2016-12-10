package nicksapps;

import java.util.Scanner;

public class ScannerPlus
{
    public static void skipLines(Scanner scanner, int lines)                                        //skips lines in a Scanner
    {
        for(int cl = 0; cl < lines; cl++)
        {
            scanner.nextLine();
        }
    }

    public static String skipUntilContent(Scanner scanner)                                          //skips through scanner until line is not empty and returns line
    {
        String line = scanner.nextLine();
        while (line.equals(""))
            line = scanner.nextLine();
        return line;
    }

    public static void skipUntilBlank(Scanner scanner)                                              //skips through scanner until line is empty
    {
        while (!scanner.nextLine().equals(""));
    }

    public static String skipUntilFormat(Scanner scanner, String... format)                         //skips through scanner until line fits StringPlus format
    {
        String line = scanner.nextLine();
        while (!StringPlus.isFormat(line, format))
            line = scanner.nextLine();
        return line;
    }
}