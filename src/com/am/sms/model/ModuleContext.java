package com.am.sms.model;

import com.am.sms.model.db.service.ClientServiceManager;
import com.am.sms.model.db.service.DeviceServiceManager;
import com.am.sms.model.db.service.ServiceServiceManager;
import com.am.sms.model.db.service.TypeDeviceManager;
import com.am.sms.model.db.service.CollaboratorServiceManager;
import com.am.sms.model.db.service.ItemOsManager;
import com.am.sms.model.db.service.OsServiceManager;
import com.am.sms.model.db.service.PaymentServiceManager;

/**
 *
 * @author Alexandre Marques
 */
public class ModuleContext
{
    private static ModuleContext instance;
    
    public static ModuleContext getInstance()
    {
        if( instance == null )
        {
            instance = new ModuleContext();
        }
        
        return instance;
    }
    
    public CollaboratorServiceManager getCollaboratorManager()
    {
        return CollaboratorServiceManager.getInstance();
    }
    
    public ClientServiceManager getClientManager()
    {
        return ClientServiceManager.getInstance();
    }
    
    public ServiceServiceManager getServiceManager()
    {
        return ServiceServiceManager.getInstance();
    }
    
    public DeviceServiceManager getDeviceManager()
    {
        return DeviceServiceManager.getInstance();
    }
    
    public TypeDeviceManager getTypeDeviceManager()
    {
        return TypeDeviceManager.getInstance();
    }
    
    public PaymentServiceManager getPaymentServiceManager()
    {
        return PaymentServiceManager.getInstance();
    }
    
    public OsServiceManager getOsServiceManager()
    {
        return OsServiceManager.getInstance();
    }
    
    public ItemOsManager getItemOsManager()
    {
        return ItemOsManager.getInstance();
    }
}