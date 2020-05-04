package com.am.sms.model.db.service;

import com.am.sms.model.data.Service;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.transaction.ServiceManagerTransaction;

/**
 *
 * @author Alexandre Marques
 */
public class ServiceServiceManager
    implements 
        ServiceManager<Service>
{
    private static ServiceServiceManager instance;
    private static  ServiceManagerTransaction transaction;
    
    public static ServiceServiceManager getInstance()
    {
        if( instance == null )
        {
            instance = new ServiceServiceManager();
        }
        
        return instance;
    }
    
    public ServiceServiceManager()
    {
        transaction = new ServiceManagerTransaction();
    }
    
    @Override
    public void addItem( Service item ) throws Exception
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
    public void updateItem( Service item ) throws Exception
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
    public void deleteItem( Service item ) throws Exception
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
