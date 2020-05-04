package com.am.sms.model.db.transaction;

import com.am.sms.model.data.Payment;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.Database;
import com.am.sms.model.db.Database.Payments;
import com.am.sms.model.db.imp.PaymentImplements;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public class PaymentManagerTransaction
    implements 
        PaymentImplements
{
    @Override
    public void addItem( ConnectionFactory cf, Payment payment ) throws Exception
    {
        if( payment == null )
        {
            throw new Exception( "payment null" );
        }
        
        Payments P = Database.Payments.table;
        
        String sql = "insert into " + P.name +
                     " ( "          + P.columns.FORM_PAYMENT + ", "
                                    + P.columns.STATE +
                     
                     " ) values ( " + cf.format( payment.getFormPayment() ) + ", "
                                    + cf.format( payment.getState() )       +
                
                     " )";
        
        cf.executeQuery( sql );
    }

    @Override
    public void updateItem( ConnectionFactory cf, Payment payment ) throws Exception
    {
        if( payment == null )
        {
            throw new Exception( "payment null" );
        }
        
        Payments P = Database.Payments.table;
        
        String sql = "update " + P.name + " set " + 
                                 P.columns.FORM_PAYMENT + " = " + cf.format( payment.getFormPayment() ) + ", " +
                                 P.columns.STATE        + " = " + cf.format( payment.getState() )       +
                
                     " where " + P.columns.ID           + " = " + payment.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public void deleteItem( ConnectionFactory cf, Payment payment ) throws Exception
    {
        if( payment == null )
        {
            throw new Exception( "payment null" );
        }
        
        Payments P = Database.Payments.table;
        
        String sql = "update " + P.name + " set " + P.columns.STATE + " = " + cf.format( payment.getState() ) + " where " + P.columns.ID + " = " + payment.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public List<Payment> readPayments() throws Exception
    {
        ConnectionFactory cf = ConnectionFactory.getInstance();
        
        Payments P = Database.Payments.table;
        
        String sql = P.select;
        
        return cf.fetcherFull( sql, P.fetcher );
    }
}
