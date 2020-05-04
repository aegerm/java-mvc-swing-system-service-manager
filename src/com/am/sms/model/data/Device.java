package com.am.sms.model.data;

/**
 *
 * @author Alexandre Marques
 */
public class Device
    extends 
        Kernel<Device>
{
    private String      model;
    private String      maker;
    private String      serialNumber;
    private TypeDevice  typeDevice;
    private String      state;

    public String getModel()
    {
        return model;
    }

    public void setModel( String model )
    {
        this.model = model;
    }

    public String getMaker()
    {
        return maker;
    }

    public void setMaker( String maker )
    {
        this.maker = maker;
    }
    
    public String getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber( String serialNumber )
    {
        this.serialNumber = serialNumber;
    }

    public TypeDevice getTypeDevice()
    {
        return typeDevice;
    }

    public void setTypeDevice( TypeDevice typeDevice )
    {
        this.typeDevice = typeDevice;
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
        return getModel();
    }
}
