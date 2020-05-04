package com.am.sms.model.db.service;

import com.am.sms.model.data.ItemOs;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.transaction.ItemOsManagerTransaction;

/**
 *
 * @author Alexandre Marques
 */
public class ItemOsManager
    implements 
        ServiceManager<ItemOs>
{
    private static ItemOsManager instance;
    private static ItemOsManagerTransaction transaction;
    
    public static ItemOsManager getInstance()
    {
        if( instance == null )
        {
            instance = new ItemOsManager();
        }
        
        return instance;
    }

    public ItemOsManager() 
    {
        transaction = new ItemOsManagerTransaction();
    }
    
    @Override
    public void addItem( ItemOs item ) throws Exception 
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
    public void updateItem( ItemOs item ) throws Exception 
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
    
    public void updateState( ItemOs item ) throws Exception 
    {
        ConnectionFactory factory =  ConnectionFactory.getInstance();
        
        try
        {
            transaction.updateState( factory, item );
        }
        
        finally
        {
            factory.closeConnection();
        }
    }

    @Override
    public void deleteItem( ItemOs item ) throws Exception
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
