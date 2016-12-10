package nicksapps;

import java.util.*;

public final class StringList extends ArrayList<String>
{
    public StringList()
    {
        super();
    }

    public StringList(Collection c)
    {
        super(c);
    }

    public StringList(int initialCapacity)
    {
        super(initialCapacity);
    }

    public String asOneString()
    {
        String s = "";
        for(int i = 0; i < size(); i++)
            s += get(i);
        return s;
    }

    public String asOneString(String delim)
    {
        String s = "";
        for(int i = 0; i < size(); i++)
            s += get(i) + delim;
        return s;
    }

    public boolean containsIgnoreCase(String s)
    {
        return indexOfIgnoreCase(s) >= 0;
    }

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

    public int indexContaining(String s)
    {
        for(int i = 0; i < size(); i++)
        {
            if(get(i).contains(s))
                return i;
        }
        return -1;
    }

    public boolean containsWithin(String s)
    {
        return indexContaining(s) >= -1;
    }
}