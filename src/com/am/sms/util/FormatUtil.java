package com.am.sms.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Alexandre Marques
 */
public class FormatUtil
{
    private static final String PHONE_FORMAT = "(##) #####-####";
    private static final String P_FIX_FORMAT = "(##) ####-####";
    private static final String CNPJ_FORMAT  = "##.###.###/####-##";
    private static final String CPF_FORMAT   = "###.###.###-##";
    private static final String DATE_FORMAT  = "##/##/####";
    
    static DecimalFormat df = new DecimalFormat( "#,###0.00", new DecimalFormatSymbols( new Locale( "pt", "BR" ) ) );
    
    public static JFormattedTextField getFormat( String format )
    {
        JFormattedTextField fieldFormat = null;
        MaskFormatter formatter = new MaskFormatter();
        
        formatter.setPlaceholderCharacter( ' ' );
        formatter.setValueContainsLiteralCharacters( false );
        
        try
        {
            formatter.setMask( format );
            
            fieldFormat = new JFormattedTextField( format );
        }
        
        catch( ParseException e )
        {
            throw new RuntimeException( e );
        }
        
        return fieldFormat;
    }
    
    public static void formatDecimal( JTextField field )
    {
        field.setText( df.format( Double.parseDouble( field.getText() ) ) );
    }
    
    public static JFormattedTextField getPhone()
    {
        return getFormat( PHONE_FORMAT );
    }
    
    public static JFormattedTextField getCNPJ()
    {
        return getFormat( CNPJ_FORMAT );
    }
    
    public static JFormattedTextField getCPF()
    {
        return getFormat( CPF_FORMAT );
    }
    
    public static JFormattedTextField getDate()
    {
        return getFormat( DATE_FORMAT );
    }
    
    public static void formatDate( JFormattedTextField field )
    {
        try
        {
            MaskFormatter maskFormatter = new MaskFormatter();
            maskFormatter.setPlaceholderCharacter( '_' );
            maskFormatter.setMask( DATE_FORMAT );
            field.setFormatterFactory( null );
            field.setFormatterFactory( new DefaultFormatterFactory( maskFormatter ) );
            field.setValue( null );
        }
        
        catch( ParseException e )
        {
            throw new RuntimeException( e );
        }
    }
    
    public static void formatCPF( JFormattedTextField field )
    {
        try
        {
            MaskFormatter maskFormatter = new MaskFormatter();
            maskFormatter.setPlaceholderCharacter( '_' );
            maskFormatter.setMask( CPF_FORMAT );
            field.setFormatterFactory( null );
            field.setFormatterFactory( new DefaultFormatterFactory( maskFormatter ) );
            field.setValue( null );
        }
        
        catch( ParseException e )
        {
            throw new RuntimeException( e );
        }
    }
    
    public static void formatCNPJ( JFormattedTextField field )
    {
        try
        {
            MaskFormatter maskFormatter = new MaskFormatter();
            maskFormatter.setPlaceholderCharacter( '_' );
            maskFormatter.setMask( CNPJ_FORMAT );
            field.setFormatterFactory( null );
            field.setFormatterFactory( new DefaultFormatterFactory( maskFormatter ) );
            field.setValue( null );
        }
        
        catch( ParseException e )
        {
            throw new RuntimeException( e );
        }
    }
    
    public static void formatPhone( JFormattedTextField field )
    {
        try
        {
            MaskFormatter maskFormatter = new MaskFormatter();
            maskFormatter.setPlaceholderCharacter( '_' );
            maskFormatter.setMask( PHONE_FORMAT );
            field.setFormatterFactory( null );
            field.setFormatterFactory( new DefaultFormatterFactory( maskFormatter ) );
            field.setValue( null );
        }
        
        catch( ParseException e )
        {
            throw new RuntimeException( e );
        }
    }
    
    public static void formatFix( JFormattedTextField field )
    {
        try
        {
            MaskFormatter maskFormatter = new MaskFormatter();
            maskFormatter.setPlaceholderCharacter( '_' );
            maskFormatter.setMask( P_FIX_FORMAT );
            field.setFormatterFactory( null );
            field.setFormatterFactory( new DefaultFormatterFactory( maskFormatter ) );
            field.setValue( null );
        }
        
        catch( ParseException e )
        {
            throw new RuntimeException( e );
        }
    }
    
    public static String removeFormat( String value )
    {
        String remove = "";
        
        for( int i = 0; i < value.length(); i++ )
        {
            if( value.charAt( i ) != '.' && value.charAt( i ) != '/' && value.charAt( i ) != '-' && value.charAt( i ) != '_' && value.charAt( i ) != '(' && value.charAt( i ) != ')' && value.charAt( i ) != ' ' )
            {
                remove += value.charAt( i );
            }
        }
        
        return remove;
    }
}
