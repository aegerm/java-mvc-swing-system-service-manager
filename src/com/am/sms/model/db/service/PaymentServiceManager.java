package com.am.sms.model.db.service;

import com.am.sms.model.data.Payment;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.transaction.PaymentManagerTransaction;

/**
 *
 * @author Alexandre Marques
 */
public class PaymentServiceManager
    implements 
        ServiceManager<Payment>
{
    private static PaymentServiceManager instance;
    private static PaymentManagerTransaction transaction;
    
    public static PaymentServiceManager getInstance()
    {
        if( instance == null )
        {
            instance = new PaymentServiceManager();
        }
        
        return instance;
    }
    
    public PaymentServiceManager()
    {
        transaction = new PaymentManagerTransaction();
    }

    @Override
    public void addItem( Payment item ) throws Exception
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
    public void updateItem( Payment item ) throws Exception
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
    public void deleteItem( Payment item ) throws Exception
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
