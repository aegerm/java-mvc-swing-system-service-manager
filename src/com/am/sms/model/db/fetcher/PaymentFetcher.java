package com.am.sms.model.db.fetcher;

import com.am.sms.model.data.Payment;
import java.sql.ResultSet;

/**
 *
 * @author Alexandre Marques
 */
public class PaymentFetcher
    implements 
        Fetcher<Payment>
{
    @Override
    public Payment fetcher( ResultSet rs ) throws Exception
    {
        int i = 1;
        
        Payment payment = new Payment();
        
        payment.setId( rs.getInt( i++ ) );
        payment.setFormPayment( rs.getString( i++ ) );
        payment.setState( rs.getString( i++ ) );
        
        return payment;
    }
}
