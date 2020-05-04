package com.am.sms.model.db.service;

import com.am.sms.model.data.Kernel;

/**
 *
 * @author Alexandre Marques
 * @param <T>
 */
public interface ServiceManager<T extends Kernel>
{
    public void addItem( T item ) throws Exception;
    public void updateItem( T item ) throws Exception;
    public void deleteItem( T item ) throws Exception;
}
