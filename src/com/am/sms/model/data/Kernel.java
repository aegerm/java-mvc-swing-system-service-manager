package com.am.sms.model.data;

/**
 *
 * @author Alexandre Marques
 * @param <T>
 */
public abstract class Kernel<T>
{
    public static final String STATE_ACTIVE     = "A";
    public static final String STATE_INATIVE    = "I";
    
    protected int id;

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }
}
