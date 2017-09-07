package nicksapps;

import java.util.*;

public final class StringList extends ArrayList<String>
{
   /**
    * Primary constructor
    */
    public StringList()
    {
        super();
    }

   /**
    * Initializes StringList with list of Strings
    * 
    * @param c list of Strings
    */
    public StringList(Collection c)
    {
        super(c);
    }

   /**
    * Allocates capacity for StringList
    * 
    * @param initialCapacity number of elements for which to allocate
    */
    public StringList(int initialCapacity)
    {
        super(initialCapacity);
    }

   /**
    * Concatenates all Strings in list into one String
    * 
    * @return concatenated String
    */
    public String asOneString()
    {
        String s = "";
        for(int i = 0; i < size(); i++)
            s += get(i);
        return s;
    }

   /**
    * Concatenates all Strings in list into one String separated by a delimeter
    * 
    * @param delim delimeter
    * @return      concatenated String
    */
    public String asOneString(String delim)
    {
        String s = "";
        for(int i = 0; i < size(); i++)
            s += get(i) + delim;
        return s;
    }

   /**
    * Whether or not the StringList contains a given string, case-insensitive
    * 
    * @param s String for which to search
    * @return  true if and only if s is contained in StringList in any case
    */
    public boolean containsIgnoreCase(String s)
    {
        return indexOfIgnoreCase(s) >= 0;
    }

   /**
    * The index of a given String in the StringList
    * 
    * @param s String for which to search
    * @return  index of s
    */
    public int indexOfIgnoreCase(String s)
    {
        if (s == null) {
	    for (int i = 0; i < size(); i++)
		if (get(i)==null)
		    return i;
	} else {
	    for (int i = 0; i < size(); i++)
		if (s.equalsIgnoreCase(get(i)))
		    return i;
	}
	return -1;
    }

   /**
    * The index of the String containing a given substring
    * 
    * @param s substring for which to search
    * @return  index of String containing s
    */
    public int indexContaining(String s)
    {
        for(int i = 0; i < size(); i++)
        {
            if(get(i).contains(s))
                return i;
        }
        return -1;
    }

   /**
    * Whether or not the StringList contains a string containing a specific
    * substring
    * 
    * @param s substring for which to search
    * @return  true if and only if s is contained in a String contained in
    *          StringList
    */
    public boolean containsWithin(String s)
    {
        return indexContaining(s) >= -1;
    }
}