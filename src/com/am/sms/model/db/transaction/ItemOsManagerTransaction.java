package com.am.sms.model.db.transaction;

import com.am.sms.model.data.Device;
import com.am.sms.model.data.ItemOs;
import com.am.sms.model.data.Os;
import com.am.sms.model.data.Service;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.Database;
import com.am.sms.model.db.imp.ItemOsImplements;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public class ItemOsManagerTransaction
    implements 
        ItemOsImplements
{
    @Override
    public void addItem( ConnectionFactory cf, ItemOs itemOs ) throws Exception
    {
        if( itemOs == null )
        {
            throw new Exception( "itemOs null" );
        }
        
        Database.itemsOs I = Database.itemsOs.table;
        
        String sql = "insert into " + I.name +
                     "( "           + I.columns.REF_OS + ", "
                                    + I.columns.REF_DEVICES + ", "
                                    + I.columns.REF_SERVICES + ", "
                                    + I.columns.DESCRIPTION_CLIENT + ", "
                                    + I.columns.DESCRIPTION_TECNICAL + ", "
                                    + I.columns.STATE +
                
                     " ) values ( " + itemOs.getOs().getId()            + ", " 
                                    + itemOs.getDevice().getId()        + ", "
                                    + itemOs.getService().getId()        + ", "
                                    + cf.format( itemOs.getDescriptionClient() )     + ", "
                                    + cf.format( itemOs.getDescriptionTecnical() )   + ", "
                                    + cf.format( itemOs.getState() )   +
                     " )";
        
        cf.executeQuery( sql );
    }

    @Override
    public void updateItem( ConnectionFactory cf, ItemOs itemOs ) throws Exception 
    {
        if( itemOs == null )
        {
            throw new Exception( "itemOs null" );
        }
        
        Database.itemsOs I = Database.itemsOs.table;
        
        String sql = "update " + I.name + " set "   +
                                 I.columns.REF_OS               + " = " + itemOs.getOs().getId()            + ", " +
                                 I.columns.REF_DEVICES          + " = " + itemOs.getDevice().getId()        + ", " +
                                 I.columns.REF_SERVICES         + " = " + itemOs.getService().getId()        + ", " +
                                 I.columns.VALUE_FULL           + " = " + itemOs.getValueFull()             + ", " +
                                 I.columns.DESCRIPTION_CLIENT   + " = " + itemOs.getDescriptionClient()     + ", " +
                                 I.columns.DESCRIPTION_TECNICAL + " = " + itemOs.getDescriptionTecnical()   +
                     
                     " where " + I.columns.REF_OS               + " = " + itemOs.getOs().getId();
        
        cf.executeQuery( sql );
    }
    
    public void updateState( ConnectionFactory cf, ItemOs itemOs ) throws Exception 
    {
        if( itemOs == null )
        {
            throw new Exception( "itemOs null" );
        }
        
        Database.itemsOs I = Database.itemsOs.table;
        
        String sql = "update " + I.name + " set "   +
                                 I.columns.STATE               + " = " + cf.format( itemOs.getState() ) +
                     
                     " where " + I.columns.REF_OS               + " = " + itemOs.getOs().getId();
        
        cf.executeQuery( sql );
    }
    
    @Override
    public void deleteItem( ConnectionFactory cf, ItemOs itemOs ) throws Exception
    {
        if( itemOs == null )
        {
            throw new Exception( "client null" );
        }
        
        Database.itemsOs I = Database.itemsOs.table;
        
        String sql = "delete from " + I.name + " where " + I.columns.ID + " = " + itemOs.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public List<ItemOs> readItems() throws Exception
    {
        Database.Os O = Database.Os.table;
        Database.itemsOs I = Database.itemsOs.table;
        Database.Devices D = Database.Devices.table;
        Database.Services S = Database.Services.table;
        
        String sql = "select " + " i." + I.columns.ID + " as id, " +
                                 " o." + O.columns.ID + " as os, " +
                                 " d." + D.columns.MODEL + " as model, " +
                                 " s." + S.columns.SERVICE + " as service, " +
                                 " s." + S.columns.COST + " as cost, " +
                                 " i." + I.columns.STATE + " as state, " +
                                 " i." + I.columns.DESCRIPTION_CLIENT + " as client, " +
                                 " i." + I.columns.DESCRIPTION_TECNICAL + " as tecnical " +
                     " from " + I.name + " i " + " inner join " + O.name + " o " +
                     " on " + " i." + I.columns.REF_OS + " = " + " o." + O.columns.ID + " inner join " +
                     D.name + " d " + " on " + " i." + I.columns.REF_DEVICES + " = " + " d." + D.columns.ID + 
                     " inner join " + S.name + " s " + " on " + " i." + I.columns.REF_SERVICES + " = " + " s." + S.columns.ID;
        
        PreparedStatement ps;
        ResultSet rs;
        
        List<ItemOs> itemList = new ArrayList<>();
        
        try
        {
            ps = ConnectionFactory.connection().prepareStatement( sql );
            rs = ps.executeQuery();
            
            while( rs.next() )
            {
                Os o = new Os();
                
                o.setId( rs.getInt( "os" ) );
                
                Device d = new Device();
                
                d.setModel( rs.getString( "model" ) );
                
                Service s = new Service();
                
                s.setService( rs.getString( "service" ) );
                s.setCost( rs.getDouble( "cost" ) );
                
                ItemOs item = new ItemOs();
                
                item.setId( rs.getInt( "id" ) );
                
                item.setOs( o );
                item.setDevice( d );
                item.setService( s );
                item.setState( rs.getString( "state" ) );
                item.setDescriptionClient( rs.getString( "client" ) );
                item.setDescriptionTecnical( rs.getString( "tecnical" ) );
                
                itemList.add( item );
            }
        }
        
        catch( SQLException e )
        {
            throw new RuntimeException( e );
        }
        
        return itemList;
    }
}
