package com.am.sms.model.db.imp;

import com.am.sms.model.data.Payment;
import com.am.sms.model.db.ConnectionFactory;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public interface PaymentImplements
{
    public void addItem( ConnectionFactory cf, Payment payment ) throws Exception;
    public void updateItem( ConnectionFactory cf, Payment payment ) throws Exception;
    public void deleteItem( ConnectionFactory cf, Payment payment ) throws Exception;
    public List<Payment> readPayments() throws Exception;
}
