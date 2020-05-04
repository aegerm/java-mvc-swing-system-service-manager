package com.am.sms.model.db.transaction;

import com.am.sms.model.data.Collaborator;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.Database.Collaborators;
import java.util.List;
import com.am.sms.model.db.imp.CollaboratorImplements;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Alexandre Marques
 */
public class CollaboratorManagerTransaction
    implements 
        CollaboratorImplements
{
    @Override
    public void addItem( ConnectionFactory cf, Collaborator collaborator ) throws Exception
    {
        if( collaborator == null )
        {
            throw new Exception( "collaborator null" );
        }
        
        Collaborators C = Collaborators.table;
        
        String sql = "insert into " + C.name +
                     "( "           + C.columns.NAME        + ", "
                                    + C.columns.ADDRESS     + ", "
                                    + C.columns.CITY        + ", "
                                    + C.columns.UF          + ", "
                                    + C.columns.EMAIL       + ", "
                                    + C.columns.CELL_PHONE  + ", "
                                    + C.columns.FUNCTION    + ", "
                                    + C.columns.CPF         + ", "
                                    + C.columns.LOGIN       + ", "
                                    + C.columns.PASSWORD    + ", "
                                    + C.columns.STATE       +
                
                     " ) values ( " + cf.format( collaborator.getName() )           + ", "
                                    + cf.format( collaborator.getAddress() )        + ", "
                                    + cf.format( collaborator.getCity() )           + ", "
                                    + cf.format( collaborator.getUf() )             + ", "
                                    + cf.format( collaborator.getEmail() )          + ", "
                                    + cf.format( collaborator.getCellPhone() )      + ", "
                                    + cf.format( collaborator.getFunction() )       + ", "
                                    + cf.format( collaborator.getCpf() )            + ", "
                                    + cf.format( collaborator.getLogin() )          + ", "
                                    + cf.format( collaborator.getPassword() )       + ", "
                                    + cf.format( collaborator.getState() )          +
                    " )";
                    
        cf.executeQuery( sql );
    }

    @Override
    public void updateItem( ConnectionFactory cf, Collaborator collaborator ) throws Exception
    {
        if( collaborator == null )
        {
            throw new Exception( "collaborator null" );
        }
        
        Collaborators C = Collaborators.table;
        
        String sql = "update " + C.name + " set "       +
                                 C.columns.NAME         + " = " + cf.format( collaborator.getName() )           + ", " +
                                 C.columns.ADDRESS      + " = " + cf.format( collaborator.getAddress() )        + ", " +
                                 C.columns.CITY         + " = " + cf.format( collaborator.getCity() )           + ", " +
                                 C.columns.UF           + " = " + cf.format( collaborator.getUf() )             + ", " +
                                 C.columns.EMAIL        + " = " + cf.format( collaborator.getEmail() )          + ", " +
                                 C.columns.CELL_PHONE   + " = " + cf.format( collaborator.getCellPhone() )      + ", " +
                                 C.columns.FUNCTION     + " = " + cf.format( collaborator.getFunction() )       + ", " +
                                 C.columns.CPF          + " = " + cf.format( collaborator.getCpf() )            + ", " +
                                 C.columns.LOGIN        + " = " + cf.format( collaborator.getLogin() )          + ", " +
                                 C.columns.PASSWORD     + " = " + cf.format( collaborator.getPassword() )       + ", " +
                                 C.columns.STATE        + " = " + cf.format( collaborator.getState() )          +
                                 
                     " where " + C.columns.ID           + " = " + collaborator.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public void deleteItem( ConnectionFactory cf, Collaborator collaborator ) throws Exception
    {
        if( collaborator == null )
        {
            throw new Exception( "collaborator null" );
        }
        
        Collaborators C = Collaborators.table;
        
        String sql = "update " + C.name + " set " + C.columns.STATE + " = " + cf.format( collaborator.getState() ) + " where " + C.columns.ID + " = " + collaborator.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public List<Collaborator> readCollaborators() throws Exception
    {
        ConnectionFactory cf = ConnectionFactory.getInstance();
        
        Collaborators C = Collaborators.table;
        
        String sql = C.select + " order by " + C.columns.NAME;
        
        return cf.fetcherFull( sql, C.fetcher );
    }
    
    public List<Collaborator> search( String find )
    {
        Collaborators C = Collaborators.table;
        
        String sql = "select * from " + C.name + " where " + C.columns.NAME + " like ? ";
        
        PreparedStatement ps;
        ResultSet rs;
        
        List<Collaborator> collaborators = new ArrayList<>();
        
        try
        {
            ps = ConnectionFactory.connection().prepareCall( sql );
            ps.setString( 1, "%" + find + "%" );
            rs = ps.executeQuery();
            
            while( rs.next() )
            {
                Collaborator collaborator = new Collaborator();
                
                collaborator.setId( rs.getInt( "id" ) );
                collaborator.setName( rs.getString( "name" ) );
                collaborator.setAddress( rs.getString( "address" ) );
                collaborator.setCity( rs.getString( "city" ) );
                collaborator.setUf( rs.getString( "uf" ) );
                collaborator.setEmail( rs.getString( "email" ) );
                collaborator.setCellPhone( rs.getString( "cell_phone" ) );
                collaborator.setFunction( rs.getString( "function" ) );
                collaborator.setCpf( rs.getString( "cpf" ) );
                collaborator.setLogin( rs.getString( "login" ) );
                collaborator.setState( rs.getString( "state" ) );
                
                collaborators.add( collaborator );
            }
        }
        
        catch( SQLException e )
        {
            throw new RuntimeException( e );
        }
        
        return collaborators;
    }

    @Override
    public Collaborator getCollaboratorLogin( ConnectionFactory cf, String login, String password ) throws Exception 
    {
        Collaborators C = Collaborators.table;
        
        String sql = C.select  +
                     " where " +
                     C.columns.LOGIN + " = " + cf.format( login ) +
                     " and "   +
                     C.columns.PASSWORD + " = " + cf.format( password );
        
        return cf.fetcher( sql, C.fetcher );
    }
}
