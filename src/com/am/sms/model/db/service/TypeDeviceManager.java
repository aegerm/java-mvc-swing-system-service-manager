package com.am.sms.model.db.service;

import com.am.sms.model.data.TypeDevice;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.transaction.TypeDeviceManagerTransaction;

/**
 *
 * @author Alexandre Marques
 */
public class TypeDeviceManager
    implements 
        ServiceManager<TypeDevice>
{
    private static TypeDeviceManager instance;
    private static TypeDeviceManagerTransaction transaction;
    
    public static TypeDeviceManager getInstance()
    {
        if( instance == null )
        {
            instance = new TypeDeviceManager();
        }
        
        return instance;
    }
    
    public TypeDeviceManager()
    {
        transaction = new TypeDeviceManagerTransaction();
    }

    @Override
    public void addItem( TypeDevice item ) throws Exception
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
    public void updateItem( TypeDevice item ) throws Exception
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
    public void deleteItem( TypeDevice item ) throws Exception
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
