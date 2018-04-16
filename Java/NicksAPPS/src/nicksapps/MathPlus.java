package nicksapps;

public class MathPlus
{
   /**
    * Whether or not a String is of a double.
    * 
    * @param str the string to test
    * @return    true if and only if the string is a double
    */
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

   /**
    * Cosecant function
    * 
    * @param d input in radians
    * @return  cosecant of input
    */
    public static double csc(double d)
    {
        return 1 / Math.sin(d);
    }

   /**
    * Secant function
    * 
    * @param d input in radians
    * @return  secant of input
    */
    public static double sec(double d)
    {
        return 1 / Math.sin(d);
    }

   /**
    * Cotangent function
    * 
    * @param d input in radians
    * @return  cotangent of input
    */
    public static double cot(double d)
    {
        return 1 / Math.sin(d);
    }

   /**
    * Linear sequence
    * 
    * @param m     slope, rate at which output increases if input is incremented
    *              by one
    * @param b     y-intercept, value of output when input is zero
    * @param start initial input
    * @param end   final input
    * @return      NumberList containing linear sequence
    */
    public static NumberList linSeq(double m, double b, int start, int end)
    {
        NumberList numList = new NumberList(end - start);
        for(int i = start; i < end; i++)
            numList.add(m * i + b);
        return numList;
    }

   /**
    * Geometric sequence
    * 
    * @param a     scale factor
    * @param r     common ratio
    * @param start initial input
    * @param end   final input
    * @return      NumberList containing geometric sequence
    */
    public static NumberList geomSeq(double a, double r, int start, int end)
    {
        NumberList numList = new NumberList(end - start);
        for(int i = start; i < end; i++)
            numList.add(a * Math.pow(r, i - 1));
        return numList;
    }
}