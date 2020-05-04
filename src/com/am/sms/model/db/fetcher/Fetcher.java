package com.am.sms.model.db.fetcher;

import java.sql.ResultSet;

/**
 *
 * @author Alexandre Marques
 * @param <T>
 */
public interface Fetcher<T>
{
    public T fetcher( ResultSet rs ) throws Exception;
}
