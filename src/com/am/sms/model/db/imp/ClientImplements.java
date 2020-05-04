package com.am.sms.model.db.imp;

import com.am.sms.model.data.Client;
import com.am.sms.model.db.ConnectionFactory;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public interface ClientImplements
{
    public void addItem( ConnectionFactory cf, Client client ) throws Exception;
    public void updateItem( ConnectionFactory cf, Client client ) throws Exception;
    public void deleteItem( ConnectionFactory cf, Client client ) throws Exception;
    public List<Client> readClient() throws Exception;
}
