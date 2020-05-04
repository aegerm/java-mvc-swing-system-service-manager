package com.am.sms.model.data;

import java.math.BigDecimal;

/**
 *
 * @author Alexandre Marques
 */
public class ItemOs
    extends 
        Kernel<ItemOs>
{
    public static final String STATE_FINISHED = "F";
    public static final String STATE_OPEN     = "O";
    
    private Os          os;
    private Device      device;
    private Service     service;
    private BigDecimal  valueFull;
    private String      descriptionClient;
    private String      descriptionTecnical;
    private String      state;

    public Os getOs()
    {
        return os;
    }

    public void setOs( Os os )
    {
        this.os = os;
    }

    public Device getDevice()
    {
        return device;
    }

    public void setDevice( Device device )
    {
        this.device = device;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    
    public BigDecimal getValueFull()
    {
        return valueFull;
    }

    public void setValueFull( BigDecimal valueFull )
    {
        this.valueFull = valueFull;
    }

    public String getDescriptionClient()
    {
        return descriptionClient;
    }

    public void setDescriptionClient( String descriptionClient )
    {
        this.descriptionClient = descriptionClient;
    }

    public String getDescriptionTecnical()
    {
        return descriptionTecnical;
    }

    public void setDescriptionTecnical( String descriptionTecnical )
    {
        this.descriptionTecnical = descriptionTecnical;
    }

    public String getState()
    {
        return state;
    }

    public void setState( String state )
    {
        this.state = state;
    }
}
