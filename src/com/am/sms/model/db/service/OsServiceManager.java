package com.am.sms.model.db.service;

import com.am.sms.model.data.Os;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.transaction.OsManagerTransaction;

/**
 *
 * @author Alexandre Marques
 */
public class OsServiceManager
    implements 
        ServiceManager<Os>
{
    private static OsServiceManager instance;
    private static OsManagerTransaction transaction;
    
    public static OsServiceManager getInstance()
    {
        if( instance == null )
        {
            instance = new OsServiceManager();
        }
        
        return instance;
    }
    
    public OsServiceManager()
    {
        transaction = new OsManagerTransaction();
    }

    @Override
    public void addItem( Os item ) throws Exception
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
    public void updateItem( Os item ) throws Exception
    {
         ConnectionFactory factory =  ConnectionFactory.getInstance();
        
        try
        {
            transaction.updateItem( factory, item );
        }
        
        finally
        {
            factory.closeConnection();
        }
    }

    @Override
    public void deleteItem( Os item ) throws Exception
    {
         ConnectionFactory factory =  ConnectionFactory.getInstance();
        
        try
        {
            transaction.deleteItem( factory, item );
        }
        
        finally
        {
            factory.closeConnection();
        }
    }
}
