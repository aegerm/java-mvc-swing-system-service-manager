package com.am.sms.model.db.imp;

import com.am.sms.model.data.Os;
import com.am.sms.model.db.ConnectionFactory;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public interface OsImplements
{
    public void addItem( ConnectionFactory cf, Os os ) throws Exception;
    public void updateItem( ConnectionFactory cf, Os os ) throws Exception;
    public void deleteItem( ConnectionFactory cf, Os os ) throws Exception;
    public List<Os> readOs() throws Exception;
}
