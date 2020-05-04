package com.am.sms.controller;

import com.am.sms.model.data.Collaborator;
import com.am.sms.model.data.EncryptionMD5;
import com.am.sms.util.ApplicationUtilities;

/**
 *
 * @author Alexandre Marques
 */
public class LoginController
{
    public static LoginController instance;
    
    public static LoginController getInstance()
    {
        if( instance == null )
        {
            instance = new LoginController();
        }
        
        return instance;
    }
    
    public Collaborator access( String user, String password )
    {
        Collaborator login;
        
        try
        {
            login = com.am.sms.model.ModuleContext
                                    .getInstance()
                                    .getCollaboratorManager()
                                    .getCollaboratorLogin( user, EncryptionMD5.crypt( password ) );
            
            if( login != null )
            {
                ApplicationUtilities.getInstance().setActiveCollaborator( login );
            }
        }
        
        catch( Exception ex )
        {
            throw new RuntimeException( ex );
        }
        
        return login;
    }
}
