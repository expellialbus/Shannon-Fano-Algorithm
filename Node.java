import java.io.Serializable ;

public class Node <K , V> implements Serializable
{
    private static final long serialVersionUID = 1L ;

    private K __key = null ;
    private V __value = null ;
    private String __code = "" ;
    private double __possibility = 0d ;

    public Node (K key , V value)
    {
        this.__key = key ;
        this.__value = value ;
    }

    public K getKey ()
    {
        return this.__key ;
    }

    public V getValue ()
    {
        return this.__value ;
    }

    public void setValue (V value)
    {
        this.__value = value ;
    }

    public String getCode ()
    {
        return this.__code ;
    }

    public void setCode (String code)
    {
        this.__code = code ;
    }

    public double getPossibility ()
    {
        return this.__possibility ;
    }

    public void setPossibility (double possibility)
    {
        this.__possibility = possibility ;
    }

    @Override
    public String toString ()
    {
        return "Key ---> " + this.__key + " --- value ---> " + this.__value ;
    }
}