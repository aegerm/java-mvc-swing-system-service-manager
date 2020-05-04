package com.am.sms.model.data;

/**
 *
 * @author Alexandre Marques
 */
public class Payment
    extends 
        Kernel<Payment>
{
    private String formPayment;
    private String state;

    public String getFormPayment()
    {
        return formPayment;
    }

    public void setFormPayment( String formPayment )
    {
        this.formPayment = formPayment;
    }

    public String getState()
    {
        return state;
    }

    public void setState( String state )
    {
        this.state = state;
    }
    
    @Override
    public String toString()
    {
        return getFormPayment();
    }
}
