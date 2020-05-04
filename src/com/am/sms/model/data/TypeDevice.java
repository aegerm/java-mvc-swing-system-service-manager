package com.am.sms.model.data;

/**
 *
 * @author Alexandre Marques
 */
public class TypeDevice
    extends 
        Kernel<TypeDevice>
{
    private String device;
    private String state;

    public String getDevice()
    {
        return device;
    }

    public void setDevice( String device )
    {
        this.device = device;
    }

    public String getState()
    {
        return state;
    }

    public void setState( String state )
    {
        this.state = state;
    }
    
    @Override
    public String toString()
    {
        return getDevice();
    }
}
