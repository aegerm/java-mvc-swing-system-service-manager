package com.am.sms.util;

import com.am.sms.model.data.Collaborator;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandre Marques
 */
public class ApplicationUtilities
{
    public static final String REPORT_SERVICES       = "/com/am/sms/reports/reportServices.jrxml";
    public static final String REPORT_CLIENTS        = "/com/am/sms/reports/reportClients.jrxml";
    public static final String REPORT_CLIENTS_ORDER  = "/com/am/sms/reports/reportClientsService.jrxml";
    public static final String REPORT_VALUE_USERS    = "/com/am/sms/reports/reportValue.jrxml";
    
    private static Collaborator activeCollaborator;
    private static ApplicationUtilities instance;
    
    public static ApplicationUtilities getInstance()
    {
        if( instance == null )
        {
            instance = new ApplicationUtilities();
        }
        
        return instance;
    }
    
    public static void getException( Component component, Exception e )
    {
        if( e instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException )
        {
            JOptionPane.showMessageDialog( component, Naming.EXCEPTION_DATA, Naming.ATTENTION, 2 );
        }
        
        else
        {
            throw new RuntimeException( e );
        }
    }

    public Collaborator getActiveCollaborator()
    {
        return activeCollaborator;
    }

    public void setActiveCollaborator( Collaborator activeCollaborator ) 
    {
        ApplicationUtilities.activeCollaborator = activeCollaborator;
    }
    
    public void backupSQL()
    {
        int cont = 0;
        
        File mkdir = new File( "C:" );
        
        File backup = new File( "C:/sql_backup0000.sql" );
        
        if( ! mkdir.isDirectory() )
        {
            new File( "C:" ).mkdir();
        }
        
        try
        {
            if( ! backup.isFile() )
            {
                Runtime.getRuntime().exec( "cmd /c mysqldump -u<root> -p<root> sms > C:/sql_backup0000.sql" );
            }
            
            else
            {
                while( backup.isFile() )
                {
                    cont ++;
                    backup = new File( "C:/sql_backup0000" + cont + ".sql" );
                }
                
                Runtime.getRuntime().exec( "cmd /c mysqldump -u<root> -p<root> sms > C:/sql_backup0000.sql" + backup );
            }
        }
        
        catch( IOException  e )
        {
            e.printStackTrace();
        }
    }
}
