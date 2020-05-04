package com.am.sms.model.db.transaction;

import com.am.sms.model.data.Service;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.Database.Services;
import com.am.sms.model.db.imp.ServiceImplements;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public class ServiceManagerTransaction
    implements 
        ServiceImplements
{
    @Override
    public void addItem( ConnectionFactory cf, Service service ) throws Exception
    {
        if( service == null )
        {
            throw new Exception( "service null" );
        }
        
        Services S = Services.table;
        
        String sql = "insert into " + S.name +
                     " ( "
                                    + S.columns.COST    + ", "
                                    + S.columns.SERVICE + ", "
                                    + S.columns.STATE   +
                     
                     " ) values ( " 
                                    + service.getCost()                 + ", "
                                    + cf.format( service.getService() ) + ", "
                                    + cf.format( service.getState() )   +
                     " )";
        
        cf.executeQuery( sql );
    }

    @Override
    public void updateItem( ConnectionFactory cf, Service service ) throws Exception
    {
        if( service == null )
        {
            throw new Exception( "service null" );
        }
        
        Services S = Services.table;
        
        String sql = "update " + S.name + " set "  +
                                 S.columns.COST    + " = " + service.getCost()                 + ", " +
                                 S.columns.SERVICE + " = " + cf.format( service.getService() ) + ", " +
                                 S.columns.STATE   + " = " + cf.format( service.getState() )   +
                
                     " where " + S.columns.ID                + " = " + service.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public void deleteItem( ConnectionFactory cf, Service service ) throws Exception
    {
        if( service == null )
        {
            throw new Exception( "service null" );
        }
        
        Services S = Services.table;
        
        String sql = "update " + S.name + " set " + S.columns.STATE + " = " + cf.format( service.getState() ) + " where " + S.columns.ID + " = " + service.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public List<Service> readService() throws Exception
    {
        Services S = Services.table;
        
        String sql = "select " + " s." + S.columns.ID      + " as id, " 
                               + " s." + S.columns.COST    + " as cost, "
                               + " s." + S.columns.SERVICE + " as service, " 
                               + " s." + S.columns.STATE   + " as state " +
                     
                     " from "  + S.name + " s ";
        
        PreparedStatement ps;
        ResultSet rs;
        
        List<Service> services = new ArrayList<>();
        
        try
        {
            ps = ConnectionFactory.connection().prepareStatement( sql );
            rs = ps.executeQuery();
            
            while( rs.next() )
            {
                Service service = new Service();
                
                service.setId( rs.getInt( "id" ) );
                service.setCost( rs.getDouble( "cost" ) );
                service.setService( rs.getString( "service" ) );
                service.setState( rs.getString( "state" ) );
                
                services.add( service );
            }
        }
        
        catch( SQLException e )
        {
            throw new RuntimeException( e );
        }
        
        return services;
    }
    
    public List<Service> search( String find ) throws Exception
    {
        Services S = Services.table;
        
        String sql = "select * from " + S.name + " where " + S.columns.SERVICE + " like ? "; 
        
        PreparedStatement ps;
        ResultSet rs;
        
        List<Service> services = new ArrayList<>();
        
        try
        {
            ps = ConnectionFactory.connection().prepareStatement( sql );
            ps.setString( 1, "%" + find + "%" );
            rs = ps.executeQuery();
            
            while( rs.next() )
            {
                Service service = new Service();
                
                service.setId( rs.getInt( "id" ) );
                service.setCost( rs.getDouble( "cost" ) );
                service.setService( rs.getString( "service" ) );
                service.setState( rs.getString( "state" ) );
                
                services.add( service );
            }
        }
        
        catch( SQLException e )
        {
            throw new RuntimeException( e );
        }
        
        return services;
    }
}
