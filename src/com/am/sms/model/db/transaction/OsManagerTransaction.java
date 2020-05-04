package com.am.sms.model.db.transaction;

import com.am.sms.model.data.Client;
import com.am.sms.model.data.Collaborator;
import com.am.sms.model.data.Os;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.Database;
import com.am.sms.model.db.Database.Clients;
import com.am.sms.model.db.Database.Collaborators;
import com.am.sms.model.db.imp.OsImplements;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public class OsManagerTransaction
    implements 
        OsImplements
{
    @Override
    public void addItem( ConnectionFactory cf, Os os ) throws Exception
    {
        if( os == null )
        {
            throw new Exception( "os null" );
        }
        
        Database.Os O = Database.Os.table;
        
        String sql = "insert into " + O.name +
                     " ( "          + O.columns.DATE_REGISTER       + ", "
                                    + O.columns.REF_COLLABORATORS   + ", "
                                    + O.columns.REF_CLIENTS         + ", "
                                    + O.columns.STATE               + ", "
                                    + O.columns.VALUE_FULL          +
                     
                     " ) values ( " + cf.formatDateTimestamp( os.getDateRegister() ) + ", "
                                    + os.getCollaborator().getId()                   + ", "
                                    + os.getClient().getId()                         + ", "
                                    + cf.format( os.getState() )                     + ", "
                                    + os.getValue()                                  +
                     " )";
        
        cf.executeQuery( sql );
    }

    @Override
    public void updateItem( ConnectionFactory cf, Os os ) throws Exception
    {
        if( os == null )
        {
            throw new Exception( "os null" );
        }
        
        Database.Os O = Database.Os.table;
        
        String sql = "update " + O.name + " set " +
                                 O.columns.STATE        + " = " + cf.format( os.getState() )    + ", " +
                                 O.columns.VALUE_FULL + " = " + os.getValue() +
                     " where " + O.columns.ID                   + " = " + os.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public void deleteItem( ConnectionFactory cf, Os os ) throws Exception
    {
        if( os == null )
        {
            throw new Exception( "os null" );
        }
        
        Database.Os O = Database.Os.table;
        
        String sql = "update " + O.name + " set " +
                                 O.columns.STATE        + " = " + cf.format( os.getState() )    + 
                     " where " + O.columns.ID                   + " = " + os.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public List<Os> readOs() throws Exception
    {
        Database.Os O = Database.Os.table;
        
        Collaborators C = Collaborators.table;
        Clients CLI = Clients.table;
        
        String sql = "select " + " o." + O.columns.ID               + " as id, " 
                               + " o." + O.columns.DATE_REGISTER    + " as date, "
                               + " c." + C.columns.NAME             + " as collaborators, "
                               + " cli." + CLI.columns.NAME           + " as clients, "
                               + " o." + O.columns.STATE + " as state " +
                     
                     " from "       + O.name                      + " o " + " inner join "
                                    + C.name                      + " c " + " on " + " o."
                                    + O.columns.REF_COLLABORATORS + " = " + " c." + C.columns.ID
                                    + " inner join " + CLI.name   
                                    + " cli " + " on " + " cli." + CLI.columns.ID + " = " + " o." + O.columns.REF_CLIENTS;
                                    
        PreparedStatement ps;
        ResultSet rs;
        
        List<Os> os = new ArrayList<>();
        
        try
        {
            ps = ConnectionFactory.connection().prepareStatement( sql );
            rs = ps.executeQuery();
            
            while( rs.next() )
            {
                Os o = new Os();
                
                o.setId( rs.getInt( "id" ) );
                o.setDateRegister( rs.getTimestamp( "date" ) );
                o.setState( rs.getString( "state" ) );
                
                Collaborator collaborator = new Collaborator();
                
                collaborator.setName( rs.getString( "collaborators" ) );
                
                Client client = new Client();
                
                client.setName( rs.getString( "clients" ) );
                
                o.setCollaborator( collaborator );
                o.setClient( client );
                
                os.add( o );
            }
        }
        
        catch( SQLException e )
        {
            throw new RuntimeException( e );
        }
        
        return os;
    }
}
