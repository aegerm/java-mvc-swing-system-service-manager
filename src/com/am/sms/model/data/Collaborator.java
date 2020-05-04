package com.am.sms.model.data;

/**
 *
 * @author Alexandre Marques
 */
public class Collaborator
    extends 
        Kernel<Collaborator>
{
    private String name;
    private String address;
    private String city;
    private String uf;
    private String email;
    private String cellPhone;
    private String function;
    private String cpf;
    private String login;
    private String password;
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

    public String getCellPhone()
    {
        return cellPhone;
    }

    public void setCellPhone( String cellPhone )
    {
        this.cellPhone = cellPhone;
    }
    
    public String getFunction()
    {
        return function;
    }

    public void setFunction( String function )
    {
        this.function = function;
    }

    public String getCpf()
    {
        return cpf;
    }
    
    public void setCpf( String cpf )
    {
        this.cpf = cpf;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin( String login )
    {
        this.login = login;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
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
