package com.am.sms.model.db.transaction;

import com.am.sms.model.data.TypeDevice;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.Database.TypesDevices;
import com.am.sms.model.db.imp.TypeDeviceImplements;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public class TypeDeviceManagerTransaction
    implements 
        TypeDeviceImplements
{
    @Override
    public void addItem( ConnectionFactory cf, TypeDevice device ) throws Exception
    {
        if( device == null )
        {
            throw new Exception( "device null" );
        }
        
        TypesDevices T = TypesDevices.table;
        
        String sql = "insert into " + T.name +
                     " ( "          + T.columns.DEVICE  + ", " 
                                    + T.columns.STATE   +
                
                     " ) values ( " + cf.format( device.getDevice() )  + ", " 
                                    + cf.format( device.getState() ) + " )";
        
        cf.executeQuery( sql );
                                   
    }

    @Override
    public void updateItem( ConnectionFactory cf, TypeDevice device ) throws Exception
    {
        if( device == null )
        {
            throw new Exception( "device null" );
        }
        
        TypesDevices T = TypesDevices.table;
        
        String sql = "update " + T.name + " set " 
                               + T.columns.DEVICE + " = " + cf.format( device.getDevice() ) + ", "
                               + T.columns.STATE  + " = " + cf.format( device.getState() ) +
                     " where " + T.columns.ID     + " = " + device.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public void deleteItem( ConnectionFactory cf, TypeDevice device ) throws Exception
    {
        if( device == null )
        {
            throw new Exception( "device null" );
        }
        
        TypesDevices T = TypesDevices.table;
        
        String sql = "update " + T.name + " set " + T.columns.STATE + " = " + cf.format( device.getState() ) + " where " + T.columns.ID + " = " + device.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public List<TypeDevice> readTypeDevice() throws Exception
    {
        ConnectionFactory cf = ConnectionFactory.getInstance();
        
        TypesDevices T = TypesDevices.table;
        
        String sql = T.select;
        
        return cf.fetcherFull( sql, T.fetcher );
    }
    
    public List<TypeDevice> search( String find )
    {
        TypesDevices T = TypesDevices.table;
        
        String sql = "select * from " + T.name + " where " + T.columns.DEVICE + " like ? ";
        
        PreparedStatement ps;
        ResultSet rs;
        
        List<TypeDevice> typeDevices = new ArrayList<>();
        
        try
        {
            ps = ConnectionFactory.connection().prepareStatement( sql );
            ps.setString( 1, "%" + find + "%" );
            rs = ps.executeQuery();
            
            while( rs.next() )
            {
                TypeDevice typeDevice = new TypeDevice();
                
                typeDevice.setId( rs.getInt( "id" ) );
                typeDevice.setDevice( rs.getString( "device" ) );
                typeDevice.setState( rs.getString( "state" ) );
                
                typeDevices.add( typeDevice );
            }
        }
        
        catch( SQLException e )
        {
            throw new RuntimeException( e );
        }
        
        return typeDevices;
    }
}
