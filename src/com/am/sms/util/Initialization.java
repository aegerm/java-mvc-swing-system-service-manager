package com.am.sms.util;

import com.am.sms.model.ConfigurationManager;
import java.io.File;

/**
 *
 * @author Alexandre Marques
 */
public class Initialization
{
    private static String path;

    static
    {
        String dir = ConfigurationManager.getInstance().getProperties( "sms.dir" );

        if ( dir != null && !dir.isEmpty() )
        {
            if ( !dir.endsWith( File.separator ) )
            {
                dir += File.separator;
            }

            if ( !dir.startsWith( File.separator ) )
            {
                dir = File.separator + dir;
            }

            path = dir;
        } 
        
        else
        {
            path = null;
        }
    }
}
