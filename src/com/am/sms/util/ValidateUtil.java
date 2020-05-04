package com.am.sms.util;

/**
 *
 * @author Alexandre Marques
 */
public class ValidateUtil
{
    private static final int[] CPF  = {11,10,9,8,7,6,5,4,3,2};
    private static final int[] CNPJ = {6,5,4,3,2,9,8,7,6,5,4,3,2};
    
    private static int calculateDigit( String init, int[] vet )
    {
        int sum = 0;
        
        for( int i = init.length() - 1, digit; i >= 0; i-- )
        {
            digit = Integer.parseInt( init.substring( i, i + 1 ) );
            int v = vet.length - init.length() + i;
            sum += digit * vet[v];
        }
        
        sum = 11 - sum % 11;
        
        return sum > 9 ? 0 : sum;
    }
    
    public static boolean validateCPF( String cpf )
    {
        if( ( cpf == null ) || ( cpf.length() != 11 ) )
        {
            return false;
        }
        
        Integer digitOne = calculateDigit( cpf.substring( 0, 9 ), CPF );
        Integer digitTwo = calculateDigit( cpf.substring( 0, 9 ) + digitOne, CPF );
        
        return cpf.equals( cpf.substring( 0, 9 ) + digitOne.toString() + digitTwo.toString() );
    }
    
    public static boolean validateCNPJ( String cnpj )
    {
        if( ( cnpj == null ) || ( cnpj.length() != 14 ) )
        {
            return false;
        }
        
        Integer digitOne = calculateDigit( cnpj.substring( 0, 12 ), CNPJ );
        Integer digitTwo = calculateDigit( cnpj.substring( 0, 12 ) + digitOne, CNPJ );
        
        return cnpj.equals( cnpj.substring( 0, 12 ) + digitOne.toString() + digitTwo.toString() );
    }
}
