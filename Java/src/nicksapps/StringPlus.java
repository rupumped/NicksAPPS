package nicksapps;

import java.io.*;
import java.util.*;
import java.net.*;

public class StringPlus
{
    public static String lastToken(StringTokenizer tokenizer)
    {
        String token = null;
        while (tokenizer.hasMoreTokens())
            token = tokenizer.nextToken();
        return token;
    }

    public static boolean isFormat(String str, String... format)
    {
        if (str.length() == 0)
            return format.length == 1 && format[0].equals("");
        char[] charset = str.toCharArray();
        int c = 0;
        for (int i = 0; i < format.length; i++)
        {
            String form = format[i];
            if (form.equals(NUMBERS))
            {
                if (c >= charset.length)
                    return false;
                if (!Character.isDigit(charset[c]))
                {
                    if (charset[c] == '.')
                    {
                        if (c + 1 >= charset.length || !Character.isDigit(charset[c + 1]))
                            return false;
                    }
                    else
                        return false;
                }
                while (c < charset.length - 1 && (Character.isDigit(charset[c + 1]) || charset[c + 1] == '.'))
                    c++;
                c++;
            }
            else if (form.equals(LETTERS))
            {
                if (c >= charset.length || !Character.isLetter(charset[c]))
                    return false;
                while (c < charset.length - 1 && Character.isLetter(charset[c + 1]))
                    c++;
                c++;
            }
            else
            {
                char[] formchars = form.toCharArray();
                if (formchars.length > charset.length - c)
                    return false;
                for (int ch = 0; ch < formchars.length; ch++)
                {
                    if (formchars[ch] != charset[c])
                        return false;
                    c++;
                }
            }
        }
        return c == charset.length;
    }

    public static boolean isOfficialFormat(String str, byte... format)                              //isFormat on a char by char basis using the unicode byte constants of class Character
    {
        throw new UnsupportedOperationException();
    }

    public static String removeHTML(String code)
    {
        int i;
        String str = code;
        while(str.indexOf("<style>") != -1)
            str = str.replace(str.substring(str.indexOf("<style>"), str.indexOf("</style>") + 8), "");
        while(str.indexOf("<") != -1)
            str = str.replace(str.substring(str.indexOf("<"), str.indexOf(">") + 1), "");
        str = str.replace("&nbsp;", " ");
        for(i = 0; i < LINES.length(); i++)
            str = str.replace(LINES.substring(i), "\n");
        for(i = 0; i < SPACES.length() - 1; i++)
            str = str.replace(SPACES.substring(i), " ");
        str = str.replace("\n ", "\n");
        while(str.startsWith("\n"))
            str = str.substring(1);
        return str;
    }

    public static String[] split(String s, char regex1, char regex2)
    {
        StringList strList = new StringList();
        while(true)
        {
            try
            {
                int index = s.indexOf(regex1);
                if(index == -1)
                    break;
                s = s.substring(index + 1);
                String e = s.substring(0, s.indexOf(regex2));
                strList.add(e);
                s = s.substring(e.length() + 1);
            }
            catch(StringIndexOutOfBoundsException e)
            {
                break;
            }
        }
        return strList.toArray(new String[strList.size()]);
    }

    public static String getContent(File file) throws FileNotFoundException
    {
        return getContent(new Scanner(file));
    }
    
    public static String getContent(Scanner scanner)
    {
        String content = "";
        do
        {
            content += scanner.nextLine() + System.getProperty("line.separator");
        } while(scanner.hasNextLine());
        scanner.close();
        return content;
    }

    public static String getContent(URL url) throws IOException
    {
        String content = "";
        InputStream       inst = url.openStream();
        InputStreamReader insr = new InputStreamReader(inst);
        BufferedReader    buff = new BufferedReader(insr);
        while (true)
        {
            String line = buff.readLine();
            if (line != null)
                content += line + "\n";
            else
                break;
        }
        inst.close();
        insr.close();
        buff.close();
        return content;
    }

//    public static String getContent(PDFParser parser) throws IOException
//    {
//        parser.parse();
//        COSDocument cosDoc = parser.getDocument();
//        PDFTextStripper pdfStripper = new PDFTextStripper();
//        PDDocument pdDoc = new PDDocument(cosDoc);
//        String content = pdfStripper.getText(pdDoc);
//        cosDoc.close();
//        pdDoc.close();
//        return content;
//    }
    
    public static String getLetters(String s)
    {
        String str = "";
        for(int i = 0; i < s.length(); i++)
        {
            if (Character.isLetter(s.charAt(i)))
                str += s.charAt(i);
        }
        return str;
    }
    
    public static String getDigits(String s)
    {
        String str = "";
        for (int i = 0; i < s.length(); i++)
        {
            if (Character.isDigit(s.charAt(i)))
                str += s.charAt(i);
        }
        return str;
    }

    public static boolean isLetters(String s)
    {
        for (int i = 0; i < s.length(); i++)
        {
            if(!Character.isLetter(s.charAt(i)))
                return false;
        }
        return true;
    }

    public static boolean isDigits(String s)
    {
        for (int i = 0; i < s.length(); i++)
        {
            if(!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }

    private static final String LINES  = "\n\n\n\n\n\n\n\n\n\n\n";
    private static final String SPACES = "            ";
    
    public static final String NUMBERS = "#~#";
    public static final String LETTERS = "A~A";
}