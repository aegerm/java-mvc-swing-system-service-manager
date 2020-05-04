package com.am.sms.model.data;

import java.sql.Timestamp;

/**
 *
 * @author Alexandre Marques
 */
public class Os
    extends 
        Kernel<Os>
{
    public static final String STATE_OPEN       = "O";
    public static final String STATE_CANCEL     = "C";
    public static final String STATE_FINISHED   = "F";
    
    private Timestamp       dateRegister;
    private Collaborator    collaborator;
    private Client          client;
    private String          state;
    private double          value;

    public Timestamp getDateRegister()
    {
        return dateRegister;
    }

    public void setDateRegister( Timestamp dateRegister )
    {
        this.dateRegister = dateRegister;
    }

    public Collaborator getCollaborator()
    {
        return collaborator;
    }

    public void setCollaborator( Collaborator collaborator )
    {
        this.collaborator = collaborator;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient( Client client )
    {
        this.client = client;
    }

    public String getState() 
    {
        return state;
    }

    public void setState( String state )
    {
        this.state = state;
    }

    public double getValue() 
    {
        return value;
    }

    public void setValue( double value ) 
    {
        this.value = value;
    }
}
