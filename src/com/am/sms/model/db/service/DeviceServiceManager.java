package com.am.sms.model.db.service;

import com.am.sms.model.data.Device;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.transaction.DeviceManagerTransaction;

/**
 *
 * @author Alexandre Marques
 */
public class DeviceServiceManager
    implements 
        ServiceManager<Device>
{
    private static DeviceServiceManager instance;
    private static DeviceManagerTransaction transaction;
    
    public static DeviceServiceManager getInstance()
    {
        if( instance == null )
        {
            instance = new DeviceServiceManager();
        }
        
        return instance;
    }
    
    public DeviceServiceManager()
    {
        transaction = new DeviceManagerTransaction();
    }
    
    @Override
    public void addItem( Device item ) throws Exception
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
    public void updateItem( Device item ) throws Exception
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
    public void deleteItem( Device item ) throws Exception
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
