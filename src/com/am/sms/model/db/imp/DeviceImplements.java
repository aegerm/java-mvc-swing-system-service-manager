package com.am.sms.model.db.imp;

import com.am.sms.model.data.Device;
import com.am.sms.model.db.ConnectionFactory;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public interface DeviceImplements
{
    public void addItem( ConnectionFactory cf, Device device ) throws Exception;
    public void updateItem( ConnectionFactory cf, Device device ) throws Exception;
    public void deleteItem( ConnectionFactory cf, Device device ) throws Exception;
    public List<Device> readDevice() throws Exception;
}
