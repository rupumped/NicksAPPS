package nicksapps;

import java.util.*;

public class ArraysPlus
{
   /**
    * Sort a list using multiple comparators
    * 
    * @param list        list to sort
    * @param comparators comparators to use for sorting, in order of use
    */
    public static void sort(List list, Comparator... comparators)
    {
        for (int i = comparators.length - 1; i >= 0; i--)
            Collections.sort(list, comparators[i]);
    }

   /**
    * Whether or not one list contains any elements of a second list
    * 
    * @param l1 a list
    * @param l2 another list
    * @return   true if the first list contains any of the elements in the
    *           second list
    */
    public static boolean containsAny(List l1, List l2)
    {
        for(int i = 0; i < l2.size(); i++)
        {
            if(l1.contains(l2.get(i)))
                return true;
        }
        return false;
    }

   /**
    * Whether or not one StringList contains any Strings of a second StringList,
    * ignoring case
    * 
    * @param l1 a list
    * @param l2 another list
    * @return   true if the first list contains any of the elements in the
    *           second list, ignoring case
    */
    public static boolean containsAnyIgnoreCase(StringList l1, StringList l2)
    {
        for(int i = 0; i < l2.size(); i++)
        {
            if(l1.containsIgnoreCase(l2.get(i)))
                return true;
        }
        return false;
    }

   /**
    * Returns the number of copies of a given element in an array
    * 
    * @param array an array of Objects
    * @param o     the object of which to count the occurances
    * @return      the number of copies of o which exist is array
    */
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

   /**
    * Compiles all elements of several lists into a single list
    * 
    * @param <T> the class of the objects contained in the lists
    * @param c   the lists to combine
    * @return    an ArrayList containing every object in the given lists
    */
    public static <T> List<T> asElementList(Collection<T>... c)
    {
        List<T> list = new ArrayList<T>();
        for(int i = 0; i < c.length; i++)
            list.addAll(c[i]);
        return list;
    }

   /**
    * Returns a reference array from an array of primitives
    * 
    * @param src array of primitives
    * @return    reference array to primitives in input
    */
    public static Byte[] toReferenceArray(byte[] src)
    {
        Byte[] dest = new Byte[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

   /**
    * Returns a reference array from an array of primitives
    * 
    * @param src array of primitives
    * @return    reference array to primitives in input
    */
    public static Short[] toReferenceArray(short[] src)
    {
        Short[] dest = new Short[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

   /**
    * Returns a reference array from an array of primitives
    * 
    * @param src array of primitives
    * @return    reference array to primitives in input
    */
    public static Integer[] toReferenceArray(int[] src)
    {
        Integer[] dest = new Integer[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

   /**
    * Returns a reference array from an array of primitives
    * 
    * @param src array of primitives
    * @return    reference array to primitives in input
    */
    public static Long[] toReferenceArray(long[] src)
    {
        Long[] dest = new Long[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

   /**
    * Returns a reference array from an array of primitives
    * 
    * @param src array of primitives
    * @return    reference array to primitives in input
    */
    public static Float[] toReferenceArray(float[] src)
    {
        Float[] dest = new Float[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

   /**
    * Returns a reference array from an array of primitives
    * 
    * @param src array of primitives
    * @return    reference array to primitives in input
    */
    public static Double[] toReferenceArray(double[] src)
    {
        Double[] dest = new Double[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

   /**
    * Returns a reference array from an array of primitives
    * 
    * @param src array of primitives
    * @return    reference array to primitives in input
    */
    public static Boolean[] toReferenceArray(boolean[] src)
    {
        Boolean[] dest = new Boolean[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

   /**
    * Returns a reference array from an array of primitives
    * 
    * @param src array of primitives
    * @return    reference array to primitives in input
    */
    public static Character[] toReferenceArray(char[] src)
    {
        Character[] dest = new Character[src.length];
        for(int i = 0; i < src.length; i++)
            dest[i] = src[i];
        return dest;
    }

   /**
    * Removes all nulls from a list
    * 
    * @param list list to trim
    */
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

   /**
    * Null-pads or deletes list to a given size.
    * 
    * @param list    list to resize
    * @param newSize new size for the list
    */
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