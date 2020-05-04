package com.am.sms.model.data;

/**
 *
 * @author Alexandre Marques
 */
public class Client
    extends 
        Kernel<Client>
{
    private String name;
    private String address;
    private String city;
    private String uf;
    private String email;
    private String phone;
    private String cellPhone;
    private String cpf;
    private String cnpj;
    private String state;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress( String address )
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getUf()
    {
        return uf;
    }

    public void setUf( String uf )
    {
        this.uf = uf;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone( String phone )
    {
        this.phone = phone;
    }

    public String getCellPhone()
    {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone)
    {
        this.cellPhone = cellPhone;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf( String cpf )
    {
        this.cpf = cpf;
    }

    public String getCnpj()
    {
        return cnpj;
    }

    public void setCnpj( String cnpj )
    {
        this.cnpj = cnpj;
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
        return getName();
    }
}
