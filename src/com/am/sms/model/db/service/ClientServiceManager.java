package com.am.sms.model.db.service;

import com.am.sms.model.data.Client;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.transaction.ClientManagerTransaction;

/**
 *
 * @author Alexandre Marques
 */
public class ClientServiceManager
    implements 
        ServiceManager<Client>
{
    private static ClientServiceManager instance;
    private static ClientManagerTransaction transaction;
    
    public static ClientServiceManager getInstance()
    {
        if( instance == null )
        {
            instance = new ClientServiceManager();
        }
        
        return instance;
    }
    
    public ClientServiceManager()
    {
        transaction = new ClientManagerTransaction();
    }
    
    @Override
    public void addItem( Client item ) throws Exception
    {
        ConnectionFactory cf = ConnectionFactory.getInstance();
        
        try
        {
            transaction.addItem( cf, item );
        }
        
        finally
        {
            cf.closeConnection();
        }
    }

    @Override
    public void updateItem( Client item ) throws Exception
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
    public void deleteItem( Client item ) throws Exception
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
}
