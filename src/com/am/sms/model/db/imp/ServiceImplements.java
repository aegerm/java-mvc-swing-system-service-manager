package com.am.sms.model.db.imp;

import com.am.sms.model.data.Service;
import com.am.sms.model.db.ConnectionFactory;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public interface ServiceImplements
{
    public void addItem( ConnectionFactory cf, Service service ) throws Exception;
    public void updateItem( ConnectionFactory cf, Service service ) throws Exception;
    public void deleteItem( ConnectionFactory cf, Service service ) throws Exception;
    public List<Service> readService() throws Exception;
}
