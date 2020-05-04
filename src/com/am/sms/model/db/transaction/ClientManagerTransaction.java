package com.am.sms.model.db.transaction;

import com.am.sms.model.data.Client;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.Database.Clients;
import com.am.sms.model.db.imp.ClientImplements;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public class ClientManagerTransaction
    implements 
        ClientImplements
{
    @Override
    public void addItem( ConnectionFactory cf, Client client ) throws Exception
    {
        if( client == null )
        {
            throw new Exception( "client null" );
        }
        
        Clients C = Clients.table;
        
        String sql = "insert into " + C.name +
                     "( "           + C.columns.NAME        + ", "
                                    + C.columns.ADDRESS     + ", "
                                    + C.columns.CITY        + ", "
                                    + C.columns.UF          + ", "
                                    + C.columns.EMAIL       + ", "
                                    + C.columns.PHONE       + ", "
                                    + C.columns.CELL_PHONE  + ", "
                                    + C.columns.CPF         + ", "
                                    + C.columns.CNPJ        + ", "
                                    + C.columns.STATE       +
                
                     " ) values ( " + cf.format( client.getName() )             + ", "
                                    + cf.format( client.getAddress() )          + ", "
                                    + cf.format( client.getCity() )             + ", "
                                    + cf.format( client.getUf() )               + ", "
                                    + cf.format( client.getEmail() )            + ", "
                                    + cf.format( client.getPhone() )            + ", "
                                    + cf.format( client.getCellPhone() )        + ", "
                                    + cf.format( client.getCpf() )              + ", "
                                    + cf.format( client.getCnpj() )             + ", "
                                    + cf.format( client.getState() )            +
                     " )";
        
        cf.executeQuery( sql );
    }

    @Override
    public void updateItem( ConnectionFactory cf, Client client ) throws Exception
    {
        if( client == null )
        {
            throw new Exception( "client null" );
        }
        
        Clients C = Clients.table;
        
        String sql = "update " + C.name + " set " +
                                 C.columns.NAME         + " = " + cf.format( client.getName() )           + ", " +
                                 C.columns.ADDRESS      + " = " + cf.format( client.getAddress() )        + ", " +
                                 C.columns.CITY         + " = " + cf.format( client.getCity() )           + ", " +
                                 C.columns.UF           + " = " + cf.format( client.getUf() )             + ", " +
                                 C.columns.EMAIL        + " = " + cf.format( client.getEmail() )          + ", " +
                                 C.columns.PHONE        + " = " + cf.format( client.getPhone() )          + ", " +
                                 C.columns.CELL_PHONE   + " = " + cf.format( client.getCellPhone() )      + ", " +
                                 C.columns.CPF          + " = " + cf.format( client.getCpf() )            + ", " +
                                 C.columns.CNPJ         + " = " + cf.format( client.getCnpj() )           + ", " +
                                 C.columns.STATE        + " = " + cf.format( client.getState() )          +
                                 
                     " where " + C.columns.ID           + " = " + client.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public void deleteItem( ConnectionFactory cf, Client client ) throws Exception
    {
        if( client == null )
        {
            throw new Exception( "client null" );
        }
        
        Clients C = Clients.table;
        
        String sql = "update " + C.name + " set " + C.columns.STATE + " = " + cf.format( client.getState() ) + " where " + C.columns.ID + " = " + client.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public List<Client> readClient() throws Exception
    {
        ConnectionFactory cf = ConnectionFactory.getInstance();
        
        Clients C = Clients.table;
        
        String sql = C.select + " order by " + C.columns.NAME;
        
        return cf.fetcherFull( sql, C.fetcher );
    }
    
    public List<Client> search( String find )
    {
        Clients C = Clients.table;
        
        String sql = "select * from " + C.name + " where " + C.columns.NAME + " like ? ";
        
        PreparedStatement ps;
        ResultSet rs;
        
        List<Client> clients = new ArrayList<>();
        
        try
        {
            ps = ConnectionFactory.connection().prepareStatement( sql );
            ps.setString( 1, "%" + find + "%" );
            rs = ps.executeQuery();
            
            while( rs.next() )
            {
                Client client = new Client();
                
                client.setId( rs.getInt( "id" ) );
                client.setName( rs.getString( "name" ) );
                client.setAddress( rs.getString( "address" ) );
                client.setUf( rs.getString( "uf" ) );
                client.setEmail( rs.getString( "email" ) );
                client.setPhone( rs.getString( "phone" ) );
                client.setCellPhone( rs.getString( "cell_phone" ) );
                client.setCpf( rs.getString( "cpf" ) );
                client.setCnpj( rs.getString( "cnpj" ) );
                
                clients.add( client );
            }
        }
        
        catch( SQLException e )
        {
            throw new RuntimeException( e );
        }
        
        return clients;
    }
}
