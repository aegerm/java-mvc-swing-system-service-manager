package com.am.sms.model.db.fetcher;

import com.am.sms.model.data.Service;
import java.sql.ResultSet;

/**
 *
 * @author Alexandre Marques
 */
public class ServicesFetcher
    implements 
        Fetcher<Service>
{
    @Override
    public Service fetcher( ResultSet rs ) throws Exception
    {
        int i = 1;
        
        Service service = new Service();
        
        service.setId( rs.getInt( i++ ) );
        service.setCost( rs.getDouble( i++ ) );
        service.setService( rs.getString( i++ ) );
        service.setState( rs.getString( i++ ) );
        
        return service;
    }
}
