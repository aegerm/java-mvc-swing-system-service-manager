package com.am.sms.model.data;

import java.math.BigDecimal;

/**
 *
 * @author Alexandre Marques
 */
public class Service
    extends 
        Kernel<Service>
{
    private double      cost;
    private String      service;
    private String      description;
    private String      state;

    public double getCost()
    {
        return cost;
    }

    public void setCost( double cost )
    {
        this.cost = cost;
    }

    public String getService()
    {
        return service;
    }

    public void setService( String service )
    {
        this.service = service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return getService();
    }
}
