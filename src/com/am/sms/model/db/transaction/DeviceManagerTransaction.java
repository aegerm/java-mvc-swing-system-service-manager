package com.am.sms.model.db.transaction;

import com.am.sms.model.data.Device;
import com.am.sms.model.data.TypeDevice;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.Database.Devices;
import com.am.sms.model.db.Database.TypesDevices;
import com.am.sms.model.db.imp.DeviceImplements;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public class DeviceManagerTransaction
    implements 
        DeviceImplements
{
    @Override
    public void addItem( ConnectionFactory cf, Device device ) throws Exception
    {
        if( device == null )
        {
            throw new Exception( "device null" );
        }
        
        Devices D = Devices.table;
        
        String sql = "insert into " + D.name + 
                     "( "           + D.columns.MODEL            + ", "
                                    + D.columns.MAKER            + ", "
                                    + D.columns.SERIAL_NUMBER    + ", "
                                    + D.columns.REF_TYPE_DEVICES + ", "
                                    + D.columns.STATE            +
                                    
                     
                     " ) values ( " + cf.format( device.getModel() )                 + ", "
                                    + cf.format( device.getMaker() )                 + ", "
                                    + cf.format( device.getSerialNumber() )          + ", "
                                    + device.getTypeDevice().getId()                 + ", "
                                    + cf.format( device.getState() )                 +
                                    
                     " )";
        
        cf.executeQuery( sql );
    }

    @Override
    public void updateItem( ConnectionFactory cf, Device device ) throws Exception
    {
        if( device == null )
        {
            throw new Exception( "device null" );
        }
        
        Devices D = Devices.table;
        
        String sql = "update " + D.name + " set " +
                                 D.columns.MODEL               + " = " + cf.format( device.getModel() )                 + ", " +
                                 D.columns.MAKER               + " = " + cf.format( device.getMaker() )                 + ", " +
                                 D.columns.SERIAL_NUMBER       + " = " + cf.format( device.getSerialNumber() )          + ", " +
                                 D.columns.REF_TYPE_DEVICES    + " = " + device.getTypeDevice().getId()                 + ", " +
                                 D.columns.STATE               + " = " + cf.format( device.getState() )                 +
                    
                     " where " + D.columns.ID                  + " = " + device.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public void deleteItem( ConnectionFactory cf, Device device ) throws Exception
    {
        if( device == null )
        {
            throw new Exception( "device null" );
        }
        
        Devices D = Devices.table;
        
        String sql = "update " + D.name + " set " + D.columns.STATE + " = " + cf.format( device.getState() ) + " where " + D.columns.ID + " = " + device.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public List<Device> readDevice() throws Exception
    {
        Devices D = Devices.table;
        TypesDevices T = TypesDevices.table;
        
        String sql = "select " + " d." + D.columns.ID               + " as id, " 
                               + " d." + D.columns.MODEL            + " as model, "
                               + " d." + D.columns.MAKER            + " as maker, "
                               + " d." + D.columns.SERIAL_NUMBER    + " as serial, "
                               + " t." + T.columns.DEVICE           + " as device,  "
                               + " d." + D.columns.STATE            + " as state "
                               +
                     
                     " from "       + D.name                     + " d " + " inner join "
                                    + T.name                     + " t " + " on " + " d."
                                    + D.columns.REF_TYPE_DEVICES + " = " + " t." + T.columns.ID;
        
        PreparedStatement ps;
        ResultSet rs;
        
        List<Device> devices = new ArrayList<>();
        
        try
        {
            ps = ConnectionFactory.connection().prepareStatement( sql );
            rs = ps.executeQuery();
            
            while( rs.next() )
            {
                Device device = new Device();
                device.setId( rs.getInt( "id" ) );
                device.setModel( rs.getString( "model" ) );
                device.setMaker( rs.getString( "maker" ) );
                device.setSerialNumber( rs.getString( "serial" ) );
                device.setState( rs.getString( "state" ) );
                
                TypeDevice typeDevice = new TypeDevice();
                
                typeDevice.setDevice( rs.getString( "device" ) );
                
                device.setTypeDevice( typeDevice );
                
                devices.add( device );
            }
        }
        
        catch( SQLException e )
        {
            throw new RuntimeException( e );
        }
        
        return devices;
    }
}
