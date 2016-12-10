package nicksapps;

import java.util.*;

public final class NumberList extends ArrayList<Number>
{
    public NumberList()
    {
        super();
    }

    public NumberList(Collection c)
    {
        super(c);
    }

    public NumberList(int initialCapacity)
    {
        super(initialCapacity);
    }

    public Number sum()
    {
        Double sum = 0.0;
        for(int i = 0; i < size(); i++)
            sum += get(i).doubleValue();
        return sum;
    }

    public Number reciprocal()
    {
        Double recip = 0.0;
        for(int i = 0; i < size(); i++)
            recip += 1 / get(i).doubleValue();
        return recip;
    }

    public Number product()
    {
        Double product = 1.0;
        for(int i = 0; i < size(); i++)
            product *= get(i).doubleValue();
        return product;
    }

    public Number average()
    {
        return sum().doubleValue() / size();
    }

    public Number mean()
    {
        return sum().doubleValue() / size();
    }

    public Number median()
    {
        List numList = new NumberList(this);
        Collections.sort(numList);
        if(numList.size() % 2 == 0)
            return (((Number)numList.get(numList.size() / 2 - 1)).doubleValue() + ((Number)numList.get(numList.size() / 2)).doubleValue()) / 2;
        else
            return (Number)numList.get(numList.size() / 2);
    }

    public Number min()
    {
        Double min = Double.MAX_VALUE;
        for(int i = 0; i < size(); i++)
            min = Math.min(get(i).doubleValue(), min);
        return min;
    }

    public Number max()
    {
        double max = Double.MIN_VALUE;
        for(int i = 0; i < size(); i++)
            max = Math.max(get(i).doubleValue(), max);
        return max;
    }

    public void fill(Number value)
    {
        Collections.fill(this, value);
    }

    public NumberList cumSum()
    {
        NumberList numelementArray = new NumberList();
        Double sum = 0.0;
        for(int i = 0; i < size(); i++)
        {
            sum += get(i).doubleValue();
            numelementArray.add(sum);
        }
        return numelementArray;
    }

    public NumberList cumProd()
    {
        NumberList numelementArray = new NumberList();
        Double prod = 0.0;
        for(int i = 0; i < size(); i++)
        {
            prod *= get(i).doubleValue();
            numelementArray.add(prod);
        }
        return numelementArray;
    }

    public NumberList deltaList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 1; i < size(); i++)
            numelementArray.add(get(i).doubleValue() - get(i - 1).doubleValue());
        return numelementArray;
    }

    public NumberList roundedList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.round(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList floorList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.floor(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList ceilList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.ceil(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList absList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.abs(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList oppList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(-get(i).doubleValue());
        return numelementArray;
    }

    public NumberList invList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(1 / get(i).doubleValue());
        return numelementArray;
    }

    public NumberList sinList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.sin(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList cosList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.cos(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList tanList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.tan(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList cscList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(MathPlus.csc(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList secList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(MathPlus.sec(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList cotList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(MathPlus.cot(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList asinList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.asin(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList acosList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.acos(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList atanList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.atan(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList sinhList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.sinh(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList coshList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.cosh(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList tanhList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.tanh(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList sqrtList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.sqrt(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList cbrtList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.cbrt(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList copySignList(double sign)
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.copySign(get(i).doubleValue(), sign));
        return numelementArray;
    }

    public NumberList copySignList(float sign)
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.copySign(get(i).floatValue(), sign));
        return numelementArray;
    }

    public NumberList copySignList(NumberList elementArray2)
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.copySign(get(i).doubleValue(), elementArray2.get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList expList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.exp(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList expm1List()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.expm1(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList getExponentList()
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.getExponent(get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList hypotList(double y)
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.hypot(get(i).doubleValue(), y));
        return numelementArray;
    }

    public NumberList hypotList(NumberList elementArray2)
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(Math.hypot(get(i).doubleValue(), elementArray2.get(i).doubleValue()));
        return numelementArray;
    }

    public NumberList sumList(Number num)
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(get(i).doubleValue() + num.doubleValue());
        return numelementArray;
    }

    public NumberList diffList(Number num)
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(get(i).doubleValue() - num.doubleValue());
        return numelementArray;
    }

    public NumberList prodList(Number num)
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(get(i).doubleValue() * num.doubleValue());
        return numelementArray;
    }

    public NumberList divList(Number num)
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(get(i).doubleValue() / num.doubleValue());
        return numelementArray;
    }

    public NumberList remList(Number num)
    {
        NumberList numelementArray = new NumberList();
        for(int i = 0; i < size(); i++)
            numelementArray.add(get(i).doubleValue() % num.doubleValue());
        return numelementArray;
    }
}