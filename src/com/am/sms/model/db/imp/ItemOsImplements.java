package com.am.sms.model.db.imp;

import com.am.sms.model.data.ItemOs;
import com.am.sms.model.db.ConnectionFactory;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public interface ItemOsImplements
{
    public void addItem( ConnectionFactory cf, ItemOs itemOs ) throws Exception;
    public void updateItem( ConnectionFactory cf, ItemOs itemOs ) throws Exception;
    public void deleteItem( ConnectionFactory cf, ItemOs itemOs ) throws Exception;
    public List<ItemOs> readItems() throws Exception;
}
