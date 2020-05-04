package com.am.sms.model.db.fetcher;

import com.am.sms.model.data.Collaborator;
import java.sql.ResultSet;

/**
 *
 * @author Marques
 */
public class CollaboratorFetcher
    implements 
        Fetcher<Collaborator>
{
    @Override
    public Collaborator fetcher( ResultSet rs ) throws Exception
    {
        int i = 1;
        
        Collaborator collaborator = new Collaborator();
        
        collaborator.setId( rs.getInt( i++ ) );
        collaborator.setName( rs.getString( i++ ) );
        collaborator.setAddress( rs.getString( i++ ) );
        collaborator.setCity( rs.getString( i++ ) );
        collaborator.setUf( rs.getString( i++ ) );
        collaborator.setEmail( rs.getString( i++ ) );
        collaborator.setCellPhone( rs.getString( i++ ) );
        collaborator.setFunction( rs.getString( i++ ) );
        collaborator.setCpf( rs.getString( i++ ) );
        collaborator.setLogin( rs.getString( i++ ) );
        collaborator.setPassword( rs.getString( i++ ) );
        collaborator.setState( rs.getString( i++ ) );
        
        return collaborator;
    }
}
