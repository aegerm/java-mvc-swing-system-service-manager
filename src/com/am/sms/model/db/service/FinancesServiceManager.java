package com.am.sms.model.db.service;

import com.am.sms.model.data.Finances;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.transaction.FinancesManagerTransaction;

/**
 *
 * @author Alexandre Marques
 */
public class FinancesServiceManager
    implements 
        ServiceManager<Finances>
{
    private static FinancesServiceManager instance;
    private static FinancesManagerTransaction transaction;
    
    public static FinancesServiceManager getInstance()
    {
        if( instance == null )
        {
            instance = new FinancesServiceManager();
        }
        
        return instance;
    }

    public FinancesServiceManager()
    {
        transaction = new FinancesManagerTransaction();
    }
    
    @Override
    public void addItem( Finances item ) throws Exception
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
    public void updateItem( Finances item ) throws Exception
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
    public void deleteItem( Finances item ) throws Exception
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
}
