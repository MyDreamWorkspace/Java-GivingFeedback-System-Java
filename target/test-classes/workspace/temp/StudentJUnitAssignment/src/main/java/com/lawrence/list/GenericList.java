package com.lawrence.list;

/**
 * A class to provide a simple list.
 * List resizes automatically. Used to illustrate
 * various design and implementation details of
 * a class in Java.
 *
 *
 */
public class GenericList
{
    // Default capacity
    private static final int DEFAULT_CAP = 10;

    // iValues store the elements of the list and may have extra capacity
    private Object[] iValues;
    private int iSize;

    /**
     * Creates an empty list.
     */
    public GenericList()
    {
        //redirect to single int constructor
        this(DEFAULT_CAP);
    }

    /**
     * Creates an empty list. Specify initial capacity.
     * @param initialCap > 0
     */
    public GenericList(int initialCap)
    {
        if (initialCap <= 0)
        {
            throw new IllegalArgumentException("Must be a positive integer");
        }
        iValues = new Object[initialCap];
        iSize = 0;
    }

    /**
     * Get list size.
     * @return size of list.
     */
    public int size()
    {
        return iSize;
    }

    /**
     * Get list element.
     * @param pos of the element in the list.
     * @return the list element.
     */
    public Object get(int pos)
    {
        return iValues[pos];
    }

    /**
     * Add element to the end of the list.
     * Size of the list goes up by 1.
     * @param element value to add.
     */
    public void add(Object element)
    {
        insert(iSize, element);
    }

    /**
     * Insert element at position.
     * @param pos of the element.
     * @param element to add.
     */
    public void insert(int pos, Object element)
    {
        ensureCapcity();
        for(int i = iSize; i > pos; i--)
        {
            iValues[i] = iValues[i - 1];
        }
        iValues[pos] = element;
        iSize++;
    }

    /**
     * Remove element at position.
     * @param pos of the element to remove.
     * @return removed element.
     */
    public Object remove(int pos)
    {
        Object removedElement = iValues[pos];
        for(int i = pos; i < iSize - 1; i++)
        {
            iValues[i] = iValues[i + 1];
        }
        iValues[iSize - 1] = null;
        iSize--;
        return removedElement;
    }

    /**
     * Return a String representation of list.
     * Size and elements included.
     * @return string representation of element.
     */
    public String toString()
    {
        String result = "size: " + iSize + ", elements: [";
        for(int i = 0; i < iSize - 1; i++)
        {
            result += iValues[i].toString() + ", ";
        }
        if(iSize > 0 )
        {
            result += iValues[iSize - 1];
        }
        result += "]";
        return result;
    }

    /**
     * Return a String representation of list.
     * Size and elements included.
     * @return string representation of element.
     */
    public String toStringUsingStringBuffer()
    {
        StringBuffer result = new StringBuffer();
        result.append( "size: " );
        result.append( iSize );
        result.append(", elements: [");
        for(int i = 0; i < iSize - 1; i++)
        {
            result.append(iValues[i]);
            result.append(", ");
        }
        if( iSize > 0 )
        {
            result.append(iValues[iSize - 1]);
        }
        result.append("]");
        return result.toString();
    }

    /**
     * Check if this list is equal to another.
     * @param other The object to comapre to.
     * @return true if other is a non null, IntList object
     * that is the same size as this IntList and has the
     * same elements in the same order, false otherwise.
     */
    public boolean equals(Object other)
    {
        boolean result;
        if(other == null)
        {
            // we know this is not null so can't be equal
            result = false;
        }
        else if(this == other)
        {
            // quick check if this and other refer to same IntList object
            result = true;
        }
        else if(this.getClass() != other.getClass())
        {
            // other is not an IntList they can't be equal
            result = false;
        }
        else
        {
            // other is not null and refers to an IntList
            GenericList otherList = (GenericList)other;
            result = this.size() == otherList.size();
            int i = 0;
            while(i < iSize && result)
            {
                result = this.iValues[i].equals( otherList.iValues[i] );
                i++;
            }
        }
        return result;
    }

    /**
     * Resize internal storage container by a factor of 2, if needed.
     */
    private void ensureCapcity()
    {
        if(iSize == iValues.length)
        {
            Object[] temp = new Object[iValues.length * 2];
            System.arraycopy(iValues, 0, temp, 0, iValues.length);
            iValues = temp;
        }
    }
}