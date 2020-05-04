package com.am.sms.model.data;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Alexandre Marques
 */
public class Finances
    extends 
        Kernel<Finances>
{
    private Date       datePay;
    private Date       dateSale;
    private BigDecimal valueProvided;
    private BigDecimal valueReceived;
    private int        plots;
    private double     discount;
    private Os         os;
    private Payment    payment;
    private String     state;

    public Date getDatePay()
    {
        return datePay;
    }

    public void setDatePay( Date datePay )
    {
        this.datePay = datePay;
    }
    
    public Date getDateSale()
    {
        return dateSale;
    }

    public void setDateSale( Date dateSale )
    {
        this.dateSale = dateSale;
    }

    public BigDecimal getValueProvided()
    {
        return valueProvided;
    }

    public void setValueProvided( BigDecimal valueProvided )
    {
        this.valueProvided = valueProvided;
    }

    public BigDecimal getValueReceived()
    {
        return valueReceived;
    }

    public void setValueReceived( BigDecimal valueReceived )
    {
        this.valueReceived = valueReceived;
    }

    public Os getOs()
    {
        return os;
    }

    public void setOs( Os os )
    {
        this.os = os;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
    public int getPlots() {
        return plots;
    }

    public void setPlots( int plots ) 
    {
        this.plots = plots;
    }

    public double getDiscount()
    {
        return discount;
    }

    public void setDiscount( double discount )
    {
        this.discount = discount;
    }

    public String getState() 
    {
        return state;
    }

    public void setState( String state )
    {
        this.state = state;
    }
}
