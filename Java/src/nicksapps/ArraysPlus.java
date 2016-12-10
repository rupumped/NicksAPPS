package nicksapps;

import java.util.*;

public class ArraysPlus
{
    public static void sort(List list, Comparator... comparators)
    {
        for (int i = comparators.length - 1; i >= 0; i--)
            Collections.sort(list, comparators[i]);
    }

    public static boolean containsAny(List l1, List l2)
    {
        for(int i = 0; i < l2.size(); i++)
        {
            if(l1.contains(l2.get(i)))
                return true;
        }
        return false;
    }

    public static boolean containsAnyIgnoreCase(StringList l1, StringList l2)
    {
        for(int i = 0; i < l2.size(); i++)
        {
            if(l1.containsIgnoreCase(l2.get(i)))
                return true;
        }
        return false;
    }

    public static int quantityOf(Object[] array, Object o)
    {
        int q = 0;
        for(int i = 0; i < array.length; i++)
        {
            if(array[i].equals(o))
                q++;
        }
        return q;
    }

    public static <T> List<T> asElementList(Collection<T>... c)
    {
        List<T> list = new ArrayList<T>();
        for(int i = 0; i < c.length; i++)
            list.addAll(c[i]);
        return list;
    }

    public static Byte[] toReferenceArray(byte[] src)
    {
        Byte[] dest = new Byte[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

    public static Short[] toReferenceArray(short[] src)
    {
        Short[] dest = new Short[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

    public static Integer[] toReferenceArray(int[] src)
    {
        Integer[] dest = new Integer[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

    public static Long[] toReferenceArray(long[] src)
    {
        Long[] dest = new Long[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

    public static Float[] toReferenceArray(float[] src)
    {
        Float[] dest = new Float[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

    public static Double[] toReferenceArray(double[] src)
    {
        Double[] dest = new Double[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

    public static Boolean[] toReferenceArray(boolean[] src)
    {
        Boolean[] dest = new Boolean[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

    public static Character[] toReferenceArray(char[] src)
    {
        Character[] dest = new Character[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

    public static void trim(List list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i) == null)
            {
                list.remove(i);
                i--;
            }
        }
    }

    public static void resize(List list, int newSize)
    {
        if(list.size() < newSize)
        {
            for(int i = list.size(); i < newSize; i++)
                list.add(null);
        }
        else if(list.size() > newSize)
        {
            for(int i = list.size() - 1; i >= newSize; i--)
                list.remove(i);
        }
    }
}