package com.am.sms.model.db.transaction;

import com.am.sms.model.data.Finances;
import com.am.sms.model.data.Os;
import com.am.sms.model.data.Payment;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.model.db.Database;
import com.am.sms.model.db.imp.FinancesImplements;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexandre Marques
 */
public class FinancesManagerTransaction
    implements 
        FinancesImplements
{
    @Override
    public void addItem( ConnectionFactory cf, Finances finances ) throws Exception 
    {
        if( finances == null )
        {
            throw new Exception( "finances null" );
        }
        
        Database.Finances F = Database.Finances.table;
        
        String sql = "insert into " + F.name +
                     " ( "          + F.columns.DATE_PAY       + ", "
                                    + F.columns.DATE_SALE      + ", "
                                    + F.columns.VALUE_PROVIDED + ", "
                                    + F.columns.VALUE_RECEIVED + ", "
                                    + F.columns.PLOTS          + ", "
                                    + F.columns.DISCOUNT       + ", "
                                    + F.columns.REF_OS         + ", "
                                    + F.columns.REF_PAYMENT    + 
                     
                     " ) values ( " + cf.formatDate( finances.getDatePay() ) + ", "
                                    + cf.formatDate( finances.getDateSale() ) + ", "
                                    + finances.getValueProvided()   + ", "
                                    + finances.getValueReceived()   + ", "
                                    + finances.getPlots()           + ", "
                                    + finances.getDiscount()        + ", "
                                    + finances.getOs().getId()      + ", "
                                    + finances.getPayment().getId() +
                     " ) ";
        
        cf.executeQuery( sql );
    }

    @Override
    public void updateItem( ConnectionFactory cf, Finances finances ) throws Exception
    {
        if( finances == null )
        {
            throw new Exception( "finances null" );
        }
        
        Database.Finances F = Database.Finances.table;
        
        String sql = "update " + F.name + " set " +
                                 F.columns.DATE_PAY       + " = " + cf.formatDate( finances.getDatePay() )  + ", " +
                                 F.columns.DATE_SALE      + " = " + cf.formatDate( finances.getDateSale() ) + ", " +
                                 F.columns.VALUE_PROVIDED + " = " + finances.getValueProvided()             + ", " +
                                 F.columns.VALUE_RECEIVED + " = " + finances.getValueReceived()             + ", " +
                                 F.columns.PLOTS          + " = " + finances.getPlots()                     + ", " +
                                 F.columns.DISCOUNT       + " = " + finances.getDiscount()                  + ", " +
                                 F.columns.REF_OS         + " = " + finances.getOs().getId()                + ", " +
                                 F.columns.REF_PAYMENT    + " = " + finances.getPayment().getId()           +
                    
                     " where " + F.columns.ID             + " = " + finances.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public void deleteItem( ConnectionFactory cf, Finances finances ) throws Exception 
    {
        if( finances == null )
        {
            throw new Exception( "finances null" );
        }
        
        Database.Finances F = Database.Finances.table;
        
        String sql = "update " + F.name + " set " + F.columns.STATE + " = " + cf.format( finances.getState() ) + " where " + F.columns.ID + " = " + finances.getId();
        
        cf.executeQuery( sql );
    }

    @Override
    public List<Finances> readFinances() throws Exception 
    {
        Database.Finances F = Database.Finances.table;
        Database.Os O = Database.Os.table;
        Database.Payments P = Database.Payments.table;
        
        String sql = "select " + " f." + F.columns.ID               + " as id, "
                               + " f." + F.columns.DATE_PAY         + " as date_p "
                               + " f." + F.columns.DATE_SALE        + " as date_s "
                               + " f." + F.columns.VALUE_PROVIDED   + " as value_p "
                               + " f." + F.columns.VALUE_RECEIVED   + " as value_r "
                               + " f." + F.columns.PLOTS            + " as plots "
                               + " f." + F.columns.DISCOUNT         + " as discount "
                               + " o." + O.columns.ID               + " as id_os "
                               + " p." + P.columns.ID               + " as id_payment "
                               + " f." + F.columns.STATE            + " as state " +
                
                     " from " + F.name + " f " + " inner join "
                              + O.name + " o " + " on " + " f."
                              + F.columns.REF_OS + " = " + " o. " + O.columns.ID
                              + " inner join " + P.name + " p " + " on " + " p." + P.columns.ID + " = " + " f." + F.columns.REF_PAYMENT;
        
        PreparedStatement ps;
        ResultSet rs;
        
        List<Finances> financeses = new ArrayList<>();
        
        try
        {
            ps = ConnectionFactory.connection().prepareStatement( sql );
            rs = ps.executeQuery();
            
            while( rs.next() )
            {
                Finances finances = new Finances();
                
                finances.setId( rs.getInt( "id" ) );
                finances.setDatePay( rs.getDate( "date_p" ) );
                finances.setDateSale( rs.getDate( "date_s" ) );
                finances.setValueProvided( rs.getBigDecimal( "value_p" ) );
                finances.setValueReceived( rs.getBigDecimal( "value_r" ) );
                finances.setPlots( rs.getInt( "plots" ) );
                finances.setDiscount( rs.getDouble( "discount" ) );
                
                Os os = new Os();
                
                os.setId( rs.getInt( "id_os" ) );
                
                finances.setOs( os );
                finances.setState( rs.getString( "state" ) );
                
                Payment payment = new Payment();
                
                payment.setFormPayment( rs.getString( "payments" ) );
                
                finances.setPayment( payment );
                
                financeses.add( finances );
            }
        }
        
        catch( SQLException e )
        {
            throw new RuntimeException( e );
        }
        
        return financeses;
    }
}
