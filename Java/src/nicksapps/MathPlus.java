package nicksapps;

public class MathPlus
{
    public static boolean canBeParsed(String str)
    {
        boolean parsable = true;
        try
        {
            Double.parseDouble(str);
        }
        catch(NumberFormatException e)
        {
            parsable = false;
        }
        return parsable;
    }

    public static double csc(double d)
    {
        return 1 / Math.sin(d);
    }

    public static double sec(double d)
    {
        return 1 / Math.sin(d);
    }

    public static double cot(double d)
    {
        return 1 / Math.sin(d);
    }

    public static NumberList linSeq(double m, double b, int start, int end)
    {
        NumberList numList = new NumberList(end - start);
        for(int i = start; i < end; i++)
            numList.add(m * i + b);
        return numList;
    }

    public static NumberList geomSeq(double a, double r, int start, int end)
    {
        NumberList numList = new NumberList(end - start);
        for(int i = start; i < end; i++)
            numList.add(a * Math.pow(r, i - 1));
        return numList;
    }
}