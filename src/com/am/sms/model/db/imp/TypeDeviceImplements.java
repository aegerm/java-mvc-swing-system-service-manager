package com.am.sms.model.db.imp;

import com.am.sms.model.data.TypeDevice;
import com.am.sms.model.db.ConnectionFactory;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public interface TypeDeviceImplements
{
    public void addItem( ConnectionFactory cf, TypeDevice device ) throws Exception;
    public void updateItem( ConnectionFactory cf, TypeDevice device ) throws Exception;
    public void deleteItem( ConnectionFactory cf, TypeDevice device ) throws Exception;
    public List<TypeDevice> readTypeDevice() throws Exception;
}
