package com.am.sms.model.db.fetcher;

import com.am.sms.model.data.Client;
import java.sql.ResultSet;

/**
 *
 * @author Alexandre Marques
 */
public class ClientFetcher
    implements 
        Fetcher<Client>
{
    @Override
    public Client fetcher( ResultSet rs ) throws Exception
    {
        int i = 1;
        
        Client client = new Client();
        
        client.setId( rs.getInt( i++ ) );
        client.setName( rs.getString( i++ ) );
        client.setAddress( rs.getString( i++ ) );
        client.setCity( rs.getString( i++ ) );
        client.setUf( rs.getString( i++ ) );
        client.setEmail( rs.getString( i++ ) );
        client.setPhone( rs.getString( i++ ) );
        client.setCellPhone( rs.getString( i++ ) );
        client.setCpf( rs.getString( i++ ) );
        client.setCnpj( rs.getString( i++ ) );
        client.setState( rs.getString( i++ ) );
        
        return client;
    }
}
