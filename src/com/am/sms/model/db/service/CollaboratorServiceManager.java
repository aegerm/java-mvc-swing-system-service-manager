package com.am.sms.model.db.service;

import com.am.sms.model.data.Collaborator;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.transaction.CollaboratorManagerTransaction;

/**
 *
 * @author Alexandre Marques
 */
public class CollaboratorServiceManager
    implements 
        ServiceManager<Collaborator>
{
    private static CollaboratorServiceManager instance;
    private static CollaboratorManagerTransaction transaction;
    
    public static CollaboratorServiceManager getInstance()
    {
        if( instance == null )
        {
            instance = new CollaboratorServiceManager();
        }
        
        return instance;
    }
    
    public CollaboratorServiceManager()
    {
        transaction = new CollaboratorManagerTransaction();
    }

    @Override
    public void addItem( Collaborator item ) throws Exception
    {
        ConnectionFactory factory =  ConnectionFactory.getInstance();
        
        try
        {
            transaction.addItem( factory, item );
        }
        
        finally
        {
            factory.closeConnection();
        }
    }

    @Override
    public void updateItem( Collaborator item ) throws Exception
    {
        ConnectionFactory cf = ConnectionFactory.getInstance();
        
        try
        {
            transaction.updateItem( cf, item );
        }
        
        finally
        {
            cf.closeConnection();
        }
    }

    @Override
    public void deleteItem( Collaborator item ) throws Exception
    {
        ConnectionFactory cf = ConnectionFactory.getInstance();
        
        try
        {
            transaction.deleteItem( cf, item );
        }
        
        finally
        {
            cf.closeConnection();
        }
    }
    
    public Collaborator getCollaboratorLogin( String login, String password ) throws Exception
    {
        ConnectionFactory cf = ConnectionFactory.getInstance();
        
        try
        {
            return transaction.getCollaboratorLogin( cf, login, password );
        }
        
        finally
        {
            cf.closeConnection();
        }
    }
}
