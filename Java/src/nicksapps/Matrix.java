package nicksapps;

import java.util.*;

public class Matrix<E>
{
    public Matrix(int rows, int columns)
    {
        r = rows;
        c = columns;
        array = new ArrayList<ArrayList<E>>(rows);                                                  //row vectors
        for(int i = 0; i < rows; i++)
        {
            ArrayList<E> tmpRow = new ArrayList<E>();
            for(int j = 0; j < columns; j++)
                tmpRow.add(null);
            array.add(tmpRow);
        }
    }

    public int getRows()
    {
        return r;
    }

    public int getColumns()
    {
        return c;
    }

    public void setRow(int row, ArrayList vector)
    {
        if(vector.size() != c)
            throw new MatrixException("Vector size: " + vector.size() + ". Columns: " + c);
        array.set(row, vector);
    }

    public void setColumn(int column, ArrayList<E> vector)
    {
        if(vector.size() != r)
            throw new MatrixException("Vector size: " + vector.size() + ". Rows: " + r);
        for(int i = 0; i < r; i++)
            set(i, column, vector.get(i));
    }

    public void set(int row, int column, E element)
    {
        array.get(row).set(column, element);
    }

    public void swap(int r1, int c1, int r2, int c2)
    {
        E tmp = get(r1, c1);
        set(r1, c1, get(r2, c2));
        set(r2, c2, tmp);
    }

    public E get(int row, int column)
    {
        return array.get(row).get(column);
    }

    public ArrayList<E> getRow(int row)
    {
        return array.get(row);
    }

    public ArrayList<E> getColumn(int column)
    {
        ArrayList<E> colVec = new ArrayList<E>(r);
        for(int i = 0; i < r; i++)
            colVec.add(get(i, column));
        return colVec;
    }

    @Override
    public String toString()
    {
        String content = "";
        for(int i = 0; i < r; i++)
        {
            content += array.get(i);
            if(i != r - 1)
                content += "\n ";
        }
        return "[" + content + "]";
    }

    int r;
    int c;
    List<ArrayList<E>> array;
}