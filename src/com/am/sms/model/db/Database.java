package com.am.sms.model.db;

import com.am.sms.model.db.fetcher.ClientFetcher;
import com.am.sms.model.db.fetcher.CollaboratorFetcher;
import com.am.sms.model.db.fetcher.TypeDeviceFetcher;
import com.am.sms.model.db.fetcher.PaymentFetcher;
import com.am.sms.model.db.fetcher.ServicesFetcher;

/**
 *
 * @author Alexandre Marques
 */
public class Database
{
    public static class Collaborators
    {
        public static Collaborators alias( String alias )
        {
            return new Collaborators( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String NAME;
            public String ADDRESS;
            public String CITY;
            public String UF;
            public String EMAIL;
            public String CELL_PHONE;
            public String FUNCTION;
            public String CPF;
            public String LOGIN;
            public String PASSWORD;
            public String STATE;
            
            public Columns( String alias )
            {
                ID          = alias + "id";
                NAME        = alias + "name";
                ADDRESS     = alias + "address";
                CITY        = alias + "city";
                UF          = alias + "uf";
                EMAIL       = alias + "email";
                CELL_PHONE  = alias + "cell_phone";
                FUNCTION    = alias + "function";
                CPF         = alias + "cpf";
                LOGIN       = alias + "login";
                PASSWORD    = alias + "password";
                STATE       = alias + "state";
            }

            @Override
            public String toString()
            {
                return ID           + ", " +
                       NAME         + ", " +
                       ADDRESS      + ", " +
                       CITY         + ", " + 
                       UF           + ", " +
                       EMAIL        + ", " +
                       CELL_PHONE   + ", " +
                       FUNCTION     + ", " +
                       CPF          + ", " +
                       LOGIN        + ", " +
                       PASSWORD     + ", " +
                       STATE;
            }
        }
        
        private final String TABLE_NAME = "collaborators";
        
        public String name = TABLE_NAME;
        
        public final String select;
        
        public static final Collaborators table = new Collaborators( null );
        
        public final CollaboratorFetcher fetcher = new CollaboratorFetcher();
        
        public final Collaborators.Columns columns;
        
        private Collaborators( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Collaborators.Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
    
    public static class Clients
    {
        public static Clients alias( String alias )
        {
            return new Clients( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String NAME;
            public String ADDRESS;
            public String CITY;
            public String UF;
            public String EMAIL;
            public String PHONE;
            public String CELL_PHONE;
            public String CPF;
            public String CNPJ;
            public String STATE;
            
            public Columns( String alias )
            {
                ID          = alias + "id";
                NAME        = alias + "name";
                ADDRESS     = alias + "address";
                CITY        = alias + "city";
                UF          = alias + "uf";
                EMAIL       = alias + "email";
                PHONE       = alias + "phone";
                CELL_PHONE  = alias + "cell_phone";
                CPF         = alias + "cpf";
                CNPJ        = alias + "cnpj";
                STATE       = alias + "state";
            }

            @Override
            public String toString()
            {
                return ID           + ", " +
                       NAME         + ", " +
                       ADDRESS      + ", " +
                       CITY         + ", " + 
                       UF           + ", " +
                       EMAIL        + ", " +
                       PHONE        + ", " +
                       CELL_PHONE   + ", " +
                       CPF          + ", " +
                       CNPJ         + ", " +
                       STATE;
            }
        }
        
        private final String TABLE_NAME = "clients";
        
        public String name = TABLE_NAME;
        
        public final String select;
        
        public final ClientFetcher fetcher = new ClientFetcher();
        
        public static final Clients table = new Clients( null );
        
        public final Clients.Columns columns;
        
        private Clients( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Clients.Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
    
    public static class Devices
    {
        public static Devices alias( String alias )
        {
            return new Devices( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String MODEL;
            public String MAKER;
            public String SERIAL_NUMBER;
            public String REF_TYPE_DEVICES;
            public String STATE;
            
            public Columns( String alias )
            {
                ID                      = alias + "id";
                MODEL                   = alias + "model";
                MAKER                   = alias + "maker";
                SERIAL_NUMBER           = alias + "serial_number";
                REF_TYPE_DEVICES        = alias + "types_devices_id";
                STATE                   = alias + "state";
            }

            @Override
            public String toString()
            {
                return ID                   + ", " +
                       MODEL                + ", " +
                       MAKER                + ", " + 
                       SERIAL_NUMBER        + ", " +
                       REF_TYPE_DEVICES     + ", " +
                       STATE;
            }
        }
        
        private final String TABLE_NAME = "devices";
        
        public String name = TABLE_NAME;
        
        public final String select;
        
        public static final Devices table = new Devices( null );
        
        public final Devices.Columns columns;
        
        private Devices( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Devices.Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
    
    public static class Os
    {
        public static Os alias( String alias )
        {
            return new Os( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String DATE_REGISTER;
            public String REF_COLLABORATORS;
            public String REF_CLIENTS;
            public String STATE;
            public String VALUE_FULL;
            
            public Columns( String alias )
            {
                ID                  = alias + "id";
                DATE_REGISTER       = alias + "date_register";
                REF_COLLABORATORS   = alias + "collaborators_id";
                REF_CLIENTS         = alias + "clients_id";
                STATE               = alias + "state";
                VALUE_FULL          = alias + "value_full";
            }

            @Override
            public String toString()
            {
                return ID                   + ", " +
                       DATE_REGISTER        + ", " +
                       REF_COLLABORATORS    + ", " +
                       REF_CLIENTS          + ", " +
                       STATE                + ", " +
                       VALUE_FULL;
            }
        }
        
        private final String TABLE_NAME = "os";
        
        public String name = TABLE_NAME;
        
        public final String select;
        
        public static final Os table = new Os( null );
        
        public final Columns columns;
        
        private Os( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
    
    public static class TypesDevices
    {
        public static TypesDevices alias( String alias )
        {
            return new TypesDevices( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String DEVICE;
            public String STATE;
            
            public Columns( String alias )
            {
                ID          = alias + "id";
                DEVICE      = alias + "device";
                STATE       = alias + "state";
            }

            @Override
            public String toString()
            {
                return ID       + ", " +
                       DEVICE   + ", " +
                       STATE;
            }
        }
        
        private final String TABLE_NAME = "types_devices";
        
        public String name = TABLE_NAME;
        
        public final String select;
        
        public final TypeDeviceFetcher fetcher = new TypeDeviceFetcher();
        
        public static final TypesDevices table = new TypesDevices( null );
        
        public final Columns columns;
        
        private TypesDevices( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
    
    public static class Services
    {
        public static Services alias( String alias )
        {
            return new Services( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String COST;
            public String SERVICE;
            public String STATE;
            
            public Columns( String alias )
            {
                ID                  = alias + "id";
                COST                = alias + "cost";
                SERVICE             = alias + "service";
                STATE               = alias + "state";
            }

            @Override
            public String toString()
            {
                return ID                   + ", " +
                       COST                 + ", " +
                       SERVICE              + ", " +
                       STATE;
            }
        }
        
        private final String TABLE_NAME = "services";
        
        public String name = TABLE_NAME;
        
        public final String select;
        
        public final ServicesFetcher fetcher = new ServicesFetcher();
        
        public static final Services table = new Services( null );
        
        public final Columns columns;
        
        private Services( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
    
    public static class Payments
    {
        public static Payments alias( String alias )
        {
            return new Payments( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String FORM_PAYMENT;
            public String STATE;
            
            public Columns( String alias )
            {
                ID              = alias + "id";
                FORM_PAYMENT    = alias + "form_payment";
                STATE           = alias + "state";
            }

            @Override
            public String toString()
            {
                return ID            + ", " +
                       FORM_PAYMENT  + ", " +
                       STATE;
            }
        }
        
        private final String TABLE_NAME = "payments";
        
        public String name = TABLE_NAME;
        
        public final String select;
        
        public final PaymentFetcher fetcher = new PaymentFetcher();
        
        public static final Payments table = new Payments( null );
        
        public final Columns columns;
        
        private Payments( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
    
    public static class itemsOs
    {
        public static itemsOs alias( String alias )
        {
            return new itemsOs( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String REF_OS;
            public String REF_DEVICES;
            public String REF_SERVICES;
            public String VALUE_FULL;
            public String DESCRIPTION_CLIENT;
            public String DESCRIPTION_TECNICAL;
            public String STATE;
            
            public Columns( String alias )
            {
                ID                     = alias + "id";
                REF_OS                 = alias + "os_id";
                REF_DEVICES            = alias + "devices_id";
                REF_SERVICES           = alias + "services_id";
                VALUE_FULL             = alias + "value_full";
                DESCRIPTION_CLIENT     = alias + "description_client";
                DESCRIPTION_TECNICAL   = alias + "description_finished";
                STATE                  = alias + "state";
            }

            @Override
            public String toString()
            {
                return ID                       + ", " +
                       REF_OS                   + ", " +
                       REF_DEVICES              + ", " +
                       REF_SERVICES             + ", " +
                       VALUE_FULL               + ", " +
                       DESCRIPTION_CLIENT       + ", " +
                       DESCRIPTION_TECNICAL     + ", " +
                       STATE;
            }
        }
        
        private final String TABLE_NAME = "items_os";
        
        public String name = TABLE_NAME;
        
        public final String select;
        
        public static final itemsOs table = new itemsOs( null );
        
        public final Columns columns;
        
        private itemsOs( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
    
    public static class Finances
    {
        public static Finances alias( String alias )
        {
            return new Finances( alias );
        }
        
        public class Columns
        {
            public String ID;
            public String DATE_PAY;
            public String DATE_SALE;
            public String VALUE_PROVIDED;
            public String VALUE_RECEIVED;
            public String PLOTS;
            public String DISCOUNT;
            public String STATE;
            public String REF_OS;
            public String REF_PAYMENT;
            
            public Columns( String alias )
            {
                ID              = alias + "id";
                DATE_PAY        = alias + "date_pay";
                DATE_SALE       = alias + "date_sale";
                VALUE_PROVIDED  = alias + "value_provided";
                VALUE_RECEIVED  = alias + "value_received";
                PLOTS           = alias + "plots";
                DISCOUNT        = alias + "discount";
                STATE           = alias + "alias";
                REF_OS          = alias + "ref_os";
                REF_PAYMENT     = alias + "ref_payments";
            }

            @Override
            public String toString()
            {
                return ID             + ", " +
                       DATE_PAY       + ", " +
                       DATE_SALE      + ", " +
                       VALUE_PROVIDED + ", " +
                       VALUE_RECEIVED + ", " +
                       PLOTS          + ", " +
                       DISCOUNT       + ", " +
                       STATE          + ", " +
                       REF_OS         + ", " +
                       REF_PAYMENT;
                       
            }
        }
        
        private final String TABLE_NAME = "finances";
        
        public String name = TABLE_NAME;
        
        public final String select;
        
        public static final Finances table = new Finances( null );
        
        public final Columns columns;
        
        private Finances( String alias )
        {
            this.name = alias != null ? TABLE_NAME + " " + alias : TABLE_NAME;
            
            columns = new Columns( alias != null ? alias + "." : "" );
            
            select = "select " + columns + " from " + this.name;
        }
    }
}
