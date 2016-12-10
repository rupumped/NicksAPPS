package nicksapps;

import java.io.*;
import jxl.Workbook;
import jxl.write.*;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class MainPlus
{
    public static void endByCatch(String message)
    {
        JOptionPane.showMessageDialog(null, message, UFE, JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }
    
    public static void endByCatch(String message, Object... a)                                      //ends the program and explains the error
    {
        JOptionPane.showMessageDialog(null, message, UFE, JOptionPane.ERROR_MESSAGE);
        for(int i = 0; i < a.length; i++)
        {
            Object ob = a[i];
            try
            {
                if(ob instanceof WritableWorkbook)
                {
                    WritableWorkbook wr = (WritableWorkbook)ob;
                    wr.write();
                    try
                    {
                        wr.close();
                    }
                    catch(WriteException e) {   }
                }
                if(ob instanceof Workbook)
                {
                    Workbook wb = (Workbook)ob;
                    wb.close();
                }
                if(ob instanceof Closeable)
                {
                    Closeable cl = (Closeable)ob;
                    cl.close();
                }
            }
            catch(IOException e) {  }
        }
        System.exit(0);
    }

    public static  void shouldIStop(String message)
    {
        int decision = JOptionPane.showConfirmDialog(null, "Should I stop?\n" + message, UFE, JOptionPane.YES_NO_OPTION);
        if (decision != 1)
            System.exit(0);
    }

    public static  void shouldIStop(String message, Object... a)
    {
        int decision = JOptionPane.showConfirmDialog(null, "Should I stop?\n" + message, UFE, JOptionPane.YES_NO_OPTION);
        if (decision != 1)
        {
            for(int i = 0; i < a.length; i++)
            {
                Object ob = a[i];
                try
                {
                    if(ob instanceof WritableWorkbook)
                    {
                        WritableWorkbook wr = (WritableWorkbook)ob;
                        wr.write();
                        try
                        {
                            wr.close();
                        }
                        catch(WriteException e) {   }
                    }
                    if(ob instanceof Workbook)
                    {
                        Workbook wb = (Workbook)ob;
                        wb.close();
                    }
                    if(ob instanceof Closeable)
                    {
                        Closeable cl = (Closeable)ob;
                        cl.close();
                    }
                }
                catch(IOException e) {  }
            }
            System.exit(0);
        }
    }
    
    public static void userStop()
    {
        System.out.print("Okay. Nevermind.");
        System.exit(0);
    }
    
    public static void userStop(Object... a)                                                        //the program ends by user request
    {
        System.out.print("Okay. Nevermind.");
        for(int i = 0; i < a.length; i++)
        {
            Object ob = a[i];
            try
            {
                if(ob instanceof WritableWorkbook)
                {
                    WritableWorkbook wr = (WritableWorkbook)ob;
                    wr.write();
                    try
                    {
                        wr.close();
                    }
                    catch(WriteException e) {   }
                }
                if(ob instanceof Workbook)
                {
                    Workbook wb = (Workbook)ob;
                    wb.close();
                }
                if(ob instanceof Closeable)
                {
                    Closeable cl = (Closeable)ob;
                    cl.close();
                }
            }
            catch(IOException e) {  }
        }
        System.exit(0);
    }
    
    public static void testStop()
    {
        System.err.println("\nCheckpoint");
        System.exit(0);
    }

    public static void testStop(Object o)
    {
        System.err.println(o);
        System.exit(0);
    }

    public static void testStop(Object o, Object... a)
    {
        System.err.println(o);
        for(int i = 0; i < a.length; i++)
        {
            Object ob = a[i];
            try
            {
                if(ob instanceof WritableWorkbook)
                {
                    WritableWorkbook wr = (WritableWorkbook)ob;
                    wr.write();
                    try
                    {
                        wr.close();
                    }
                    catch(WriteException e) {   }
                }
                if(ob instanceof Workbook)
                {
                    Workbook wb = (Workbook)ob;
                    wb.close();
                }
                if(ob instanceof Closeable)
                {
                    Closeable cl = (Closeable)ob;
                    cl.close();
                }
            }
            catch(IOException e) {  }
        }
        System.exit(0);
    }
    
    public static final String FNF = "Could not find file: ";                                       //generic FileNotFoundException
    public static final String FRE = "Error reading file: ";
    public static final String CLO = "Please close the output file before running me.";
    public static final String FMT = "Formatting error!";
    public static final String UNK = "Unknown Error!\n";
    public static final String UFE = "Unfixable Error!";
}