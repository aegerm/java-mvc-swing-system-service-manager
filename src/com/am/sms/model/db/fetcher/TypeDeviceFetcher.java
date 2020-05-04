package com.am.sms.model.db.fetcher;

import com.am.sms.model.data.TypeDevice;
import java.sql.ResultSet;

/**
 *
 * @author Alexandre Marques
 */
public class TypeDeviceFetcher
    implements 
        Fetcher<TypeDevice>
{
    @Override
    public TypeDevice fetcher( ResultSet rs ) throws Exception
    {
        int i = 1;
        
        TypeDevice typeDevice = new TypeDevice();
        
        typeDevice.setId( rs.getInt( i++ ) );
        typeDevice.setDevice( rs.getString( i++ ) );
        typeDevice.setState( rs.getString( i++ ) );
        
        return typeDevice;
    }
}
