package com.am.sms.model.db.imp;

import com.am.sms.model.data.Finances;
import com.am.sms.model.db.ConnectionFactory;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public interface FinancesImplements
{
    public void addItem( ConnectionFactory cf, Finances finances ) throws Exception;
    public void updateItem( ConnectionFactory cf, Finances finances ) throws Exception;
    public void deleteItem( ConnectionFactory cf, Finances finances ) throws Exception;
    public List<Finances> readFinances() throws Exception;
}
