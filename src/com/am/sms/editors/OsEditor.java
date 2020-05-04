package com.am.sms.editors;

import com.am.sms.model.data.Client;
import java.awt.Color;
import com.am.sms.model.data.Collaborator;
import com.am.sms.model.data.Device;
import com.am.sms.model.data.ItemOs;
import com.am.sms.model.data.Os;
import com.am.sms.model.data.Service;
import com.am.sms.model.db.transaction.ClientManagerTransaction;
import com.am.sms.model.db.transaction.CollaboratorManagerTransaction;
import com.am.sms.model.db.transaction.DeviceManagerTransaction;
import com.am.sms.model.db.transaction.ItemOsManagerTransaction;
import com.am.sms.model.db.transaction.OsManagerTransaction;
import com.am.sms.model.db.transaction.ServiceManagerTransaction;
import com.am.sms.util.ApplicationUtilities;
import com.am.sms.util.Naming;
import java.text.DateFormat;
import java.text.NumberFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexandre Marques
 */
public class OsEditor 
    extends 
        javax.swing.JDialog
{
    private double value = 0;
    
    public OsEditor( java.awt.Frame parent, boolean modal )
    {
        super( parent, modal );
        
        initComponents();
        
        loadCombo();
        
        layoutInit();
    }
    
    private void layoutInit()
    {
        this.getContentPane().setBackground( new Color( 255, 255, 255 ) );
        
        pnGeneral.setBackground( new Color( 255, 255, 255 ) );
        pnDescCli.setBackground( new Color( 255, 255, 255 ) );
        pnFinished.setBackground( new Color( 255, 255, 255 ) );
        pnDescTec.setBackground( new Color( 255, 255, 255 ) );
        spGeneral.setBackground( new Color( 162, 171, 179 ) );
        
        btAdd.setEnabled( false );
        btRemove.setEnabled( false );
        
        java.util.Date date = new java.util.Date();
        
        DateFormat format = DateFormat.getDateInstance( DateFormat.MEDIUM );
        
        tfDate.setText( format.format( date ) );
    }
    
    private void refreshContent()
    {
        OsManagerTransaction transaction = new OsManagerTransaction();
        
        try 
        {
            for( Os o : transaction.readOs() )
            {
                if( o.getState().equals( Os.STATE_OPEN ) )
                {
                    cbOs.addItem( o.getId() );
                }
            }
        }
        
        catch ( Exception ex )
        {
            throw new RuntimeException( ex );
        }
    }
    
    private void removeItemsPos()
    {
        try
        {
            ItemOsManagerTransaction transaction = new ItemOsManagerTransaction();
        
            for( ItemOs i : transaction.readItems() )
            {
                if( ! i.getState().equals( ItemOs.STATE_FINISHED ) )
                {
                    com.am.sms.model.ModuleContext
                                    .getInstance()
                                    .getItemOsManager()
                                    .deleteItem( i );
                }
            }
        }
        
        catch( Exception e )
        {
            throw new RuntimeException( e );
        }
    }
    
    private void finishedItemsPos()
    {
        try
        {
            ItemOsManagerTransaction transaction = new ItemOsManagerTransaction();
        
            for( ItemOs i : transaction.readItems() )
            {
                i.setState( ItemOs.STATE_FINISHED );
                
                com.am.sms.model.ModuleContext
                                .getInstance()
                                .getItemOsManager()
                                .updateState( i );
            }
        }
        
        catch( Exception e )
        {
            throw new RuntimeException( e );
        }
    }
    
    private void addOs()
    {
        if( cbClient.getSelectedIndex() != 0 && cbCollaborator.getSelectedIndex() != 0 )
        {
            Os os = new Os();
            
            Client client = (Client) cbClient.getSelectedItem();
            Collaborator collaborator = (Collaborator) cbCollaborator.getSelectedItem();
            
            os.setCollaborator( collaborator );
            os.setClient( client );
            os.setState( Os.STATE_OPEN );
            os.setValue( value );
            
            try
            {
                com.am.sms.model.ModuleContext
                                .getInstance()
                                .getOsServiceManager()
                                .addItem( os );
                
                JOptionPane.showMessageDialog( this, Naming.EMISSION_OS, Naming.SUCESS, 1 );
                
                btAdd.setEnabled( true );
                btRemove.setEnabled( true );
                btOpen.setEnabled( false );
                clearInputs();
            }
            
            catch( Exception e )
            {
                throw new RuntimeException( e );
            }
            
            refreshContent();
        }
        
        else
        {
            JOptionPane.showMessageDialog( this, Naming.SELECTED_COMBO_OS );
        }
    }
    
    private void addItems()
    {
        if( cbOs.getSelectedIndex() != 0 && cbDevice.getSelectedIndex() != 0 && cbDevice.getSelectedIndex() != 0 && wdEditoCli.getText().length() != 0  && wdEditorTec.getText().length() != 0 )
        {
            ItemOs item = new ItemOs();
            
            Os o = new Os();
            
            Device d = (Device) cbDevice.getSelectedItem();
            Service s = (Service) cbService.getSelectedItem();
            
            o.setId( (Integer) cbOs.getSelectedItem() );
            
            value = value + s.getCost();
            
            item.setOs( o );
            item.setService( s );
            item.setDevice( d );
            item.setDescriptionClient( wdEditoCli.getText() );
            item.setDescriptionTecnical( wdEditorTec.getText() );
            item.setState( ItemOs.STATE_OPEN );
            
            tfValueMonitor.setText( String.valueOf( NumberFormat.getCurrencyInstance().format( value ) ) );
            
            try
            {
                com.am.sms.model.ModuleContext
                                .getInstance()
                                .getItemOsManager()
                                .addItem( item );
                
                mountTable();
            }
            
            catch( Exception e )
            {
                throw new RuntimeException( e );
            }
        }
        
        else
        {
            JOptionPane.showMessageDialog( this, Naming.SELECTED_COMBO_I );
        }
    }
    
    private void removeItems()
    {
        if( tbItem.getSelectedRow() != -1 )
        {
            ItemOs item = new ItemOs();
            item.setId( (Integer) tbItem.getValueAt( tbItem.getSelectedRow(), 0 ) );
            item.setService( (Service)tbItem.getValueAt( tbItem.getSelectedRow(), 3 ) );
            
            Service s = item.getService();
            
            value = value - s.getCost();
            
            tfValueMonitor.setText( String.valueOf( NumberFormat.getCurrencyInstance().format( value ) ) );
        
            try
            {
                com.am.sms.model.ModuleContext
                                .getInstance()
                                .getItemOsManager()
                                .deleteItem( item );

                mountTable();
            }

            catch( Exception e )
            {
                throw new RuntimeException( e );
            }
        }
        
        else
        {
            JOptionPane.showMessageDialog( this, Naming.CRUD_SELECTED );
        }
    }
    
    private void conclusionOs()
    {
        if( tbOs.getSelectedRow() != -1 )
        {
            Os os = new Os();
            
            os.setId( (Integer) tbOs.getValueAt( tbOs.getSelectedRow(), 0 ) );
            os.setState( Os.STATE_FINISHED );
            os.setValue( value );
            
            try
            {
                finishedItemsPos();
                
                com.am.sms.model.ModuleContext
                                .getInstance()
                                .getOsServiceManager()
                                .updateItem( os );

                mountTableOs();
                
                JOptionPane.showMessageDialog( this, Naming.FINISHED_OS );
                
                this.dispose();
            }

            catch( Exception e )
            {
                throw new RuntimeException( e );
            }
        }
        
        else
        {
            JOptionPane.showMessageDialog( this, Naming.CRUD_SELECTED );
        }
    }
    
    private void cancelOs()
    {
        if( tbOs.getSelectedRow() != -1 )
        {
            int confirm = JOptionPane.showConfirmDialog( this, Naming.CANCEL_OS, Naming.CONFIRM, 1 );
            
            if( confirm == JOptionPane.YES_OPTION )
            {
                Os os = new Os();
            
                os.setId( (Integer) tbOs.getValueAt( tbOs.getSelectedRow(), 0 ) );
                os.setState( Os.STATE_CANCEL );
            
                try
                {
                    removeItemsPos();
                    
                    com.am.sms.model.ModuleContext
                                    .getInstance()
                                    .getOsServiceManager()
                                    .deleteItem( os );

                    mountTableOs();
                    
                    JOptionPane.showMessageDialog( this, Naming.POP_UP_CANCEL, Naming.ATTENTION, 2 );
                
                    this.dispose();
                }
            
                catch( Exception e )
                {
                    throw new RuntimeException( e );
                }
            
                refreshContent();
            }
        }
        
        else
        {
            JOptionPane.showMessageDialog( this, Naming.CRUD_SELECTED );
        }
    }
    
    private void clearInputs()
    {
        cbClient.setSelectedIndex( 0 );
        cbCollaborator.setSelectedIndex( 0 );
    }
    
    private void mountTable()
    {
        DefaultTableModel model = (DefaultTableModel) tbItem.getModel();
        
        model.setNumRows( 0 );
        
        ItemOsManagerTransaction transaction = new ItemOsManagerTransaction();
        
        try
        {
            for( ItemOs i : transaction.readItems() )
            {
                if( i.getState().equals( ItemOs.STATE_OPEN ) )
                {
                    model.addRow( new Object[] 
                    {
                        i.getId(),
                        i.getOs().getId(),
                        i.getDevice(),
                        i.getService(),
                        NumberFormat.getCurrencyInstance().format( i.getService().getCost() ),
                        i.getDescriptionClient(),
                        i.getDescriptionTecnical()
                    } );
                }
            }
        }
        
        catch( Exception e )
        {
            throw new RuntimeException( e );
        }
    }
    
    private void mountTableOs()
    {
        DefaultTableModel model = (DefaultTableModel) tbOs.getModel();
        
        model.setNumRows( 0 );
        
        OsManagerTransaction transaction = new OsManagerTransaction();
        
        try
        {
            for( Os o : transaction.readOs() )
            {
                if( o.getState().equals( Os.STATE_OPEN ) )
                {
                    model.addRow( new Object[] 
                    {
                        o.getId(),
                        o.getDateRegister(),
                        o.getCollaborator(),
                        o.getClient(),
                        o.getState()
                    } );
                }
            }
        }
        
        catch( Exception e )
        {
            throw new RuntimeException( e );
        }
    }
    
    private void loadCombo()
    {
        DeviceManagerTransaction       deviceManagerTransaction         = new DeviceManagerTransaction();
        ServiceManagerTransaction      serviceManagerTransaction        = new ServiceManagerTransaction();
        CollaboratorManagerTransaction collaboratorManagerTransaction   = new CollaboratorManagerTransaction();
        ClientManagerTransaction       clientT                          = new ClientManagerTransaction();
        
        try
        {
            for( Collaborator c : collaboratorManagerTransaction.readCollaborators() )
            {
                if( ! c.getState().equals( "I" ) )
                {
                    cbCollaborator.addItem( c );
                }
            }
            
            for( Service s : serviceManagerTransaction.readService() )
            {
                if( ! s.getState().equals( "I" ) )
                {
                    cbService.addItem( s );
                }
            }
            
            for ( Device d : deviceManagerTransaction.readDevice() )
            {
                cbDevice.addItem( d );
            }
            
            for ( Client k : clientT.readClient() )
            {
                if( ! k.getState().equals( "I" ) )
                {
                    cbClient.addItem( k );
                }
            }
        }
        
        catch( Exception ex )
        {
            ApplicationUtilities.getException( this, ex );
        }
    }
    
    private void finished()
    {
        if( btOpen.isEnabled() )
        {
            JOptionPane.showMessageDialog( this, Naming.DATE_AGEND, Naming.ATTENTION, 2 );
            
            return;
        }
        
        tpGeneral.setSelectedIndex( 3 );
        
        mountTableOs();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpGeneral = new javax.swing.JTabbedPane();
        pnGeneral = new javax.swing.JPanel();
        lbClient = new javax.swing.JLabel();
        cbClient = new javax.swing.JComboBox<>();
        lbDateRegister = new javax.swing.JLabel();
        tfDate = new javax.swing.JTextField();
        lbCollaborator = new javax.swing.JLabel();
        lbDevice = new javax.swing.JLabel();
        cbDevice = new javax.swing.JComboBox<>();
        lbService = new javax.swing.JLabel();
        cbService = new javax.swing.JComboBox<>();
        btAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbItem = new javax.swing.JTable();
        spGeneral = new javax.swing.JSeparator();
        cbCollaborator = new javax.swing.JComboBox<>();
        btOpen = new javax.swing.JButton();
        lbOs = new javax.swing.JLabel();
        cbOs = new javax.swing.JComboBox<>();
        btFinished = new javax.swing.JButton();
        btRemove = new javax.swing.JButton();
        tfValueMonitor = new javax.swing.JTextField();
        lbValue = new javax.swing.JLabel();
        pnDescCli = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        wdEditoCli = new javax.swing.JTextArea();
        pnDescTec = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        wdEditorTec = new javax.swing.JTextArea();
        pnFinished = new javax.swing.JPanel();
        lbServiceFinished = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbOs = new javax.swing.JTable();
        btConclusion = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

        setTitle("Editor OS");

        pnGeneral.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbClient.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbClient.setText("Cliente");

        cbClient.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbClient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        lbDateRegister.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbDateRegister.setText("Data Registro");

        tfDate.setEditable(false);
        tfDate.setBackground(new java.awt.Color(255, 255, 255));
        tfDate.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbCollaborator.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbCollaborator.setText("Colaborador");

        lbDevice.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbDevice.setText("Equipamento");

        cbDevice.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbDevice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        lbService.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbService.setText("Serviço");

        cbService.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbService.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        btAdd.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_addcar.png"))); // NOI18N
        btAdd.setText("Adicionar Item");
        btAdd.setToolTipText("Adicionar Itens");
        btAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        tbItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ordem de Serviço", "Equipamento", "Serviço", "Custo", "Descrição Cliente", "Descrição Técnica"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbItem);

        cbCollaborator.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbCollaborator.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        btOpen.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_ose.png"))); // NOI18N
        btOpen.setText("Emitir Os");
        btOpen.setToolTipText("Adicionar Itens");
        btOpen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOpenActionPerformed(evt);
            }
        });

        lbOs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbOs.setText("OS");

        cbOs.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbOs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        btFinished.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btFinished.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_conclusion.png"))); // NOI18N
        btFinished.setText("Finalizar");
        btFinished.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btFinished.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinishedActionPerformed(evt);
            }
        });

        btRemove.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_removecar.png"))); // NOI18N
        btRemove.setText("Remover Item");
        btRemove.setToolTipText("Adicionar Itens");
        btRemove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveActionPerformed(evt);
            }
        });

        tfValueMonitor.setEditable(false);
        tfValueMonitor.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbValue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbValue.setText("Valor Total");

        javax.swing.GroupLayout pnGeneralLayout = new javax.swing.GroupLayout(pnGeneral);
        pnGeneral.setLayout(pnGeneralLayout);
        pnGeneralLayout.setHorizontalGroup(
            pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnGeneralLayout.createSequentialGroup()
                        .addComponent(btFinished, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbValue)
                        .addGap(18, 18, 18)
                        .addComponent(tfValueMonitor, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnGeneralLayout.createSequentialGroup()
                        .addGroup(pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbClient)
                            .addComponent(spGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnGeneralLayout.createSequentialGroup()
                                .addGroup(pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbCollaborator, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDateRegister, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(22, 22, 22)
                                .addGroup(pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnGeneralLayout.createSequentialGroup()
                                        .addGroup(pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbCollaborator, 0, 250, Short.MAX_VALUE)
                                            .addComponent(cbClient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(31, 31, 31)
                                        .addGroup(pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbDevice)
                                            .addComponent(lbOs)
                                            .addComponent(lbService))
                                        .addGap(22, 22, 22)
                                        .addGroup(pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbService, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cbOs, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cbDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(tfDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnGeneralLayout.createSequentialGroup()
                                .addComponent(btOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        pnGeneralLayout.setVerticalGroup(
            pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnGeneralLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDateRegister)
                    .addComponent(tfDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbOs)
                    .addComponent(cbOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCollaborator)
                    .addComponent(cbCollaborator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDevice)
                    .addComponent(cbDevice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbClient)
                    .addComponent(cbClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbService)
                    .addComponent(cbService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(spGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btFinished, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tfValueMonitor, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbValue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(130, Short.MAX_VALUE))
        );

        tpGeneral.addTab("Geral", pnGeneral);

        wdEditoCli.setColumns(20);
        wdEditoCli.setRows(5);
        jScrollPane2.setViewportView(wdEditoCli);

        javax.swing.GroupLayout pnDescCliLayout = new javax.swing.GroupLayout(pnDescCli);
        pnDescCli.setLayout(pnDescCliLayout);
        pnDescCliLayout.setHorizontalGroup(
            pnDescCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDescCliLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnDescCliLayout.setVerticalGroup(
            pnDescCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDescCliLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        tpGeneral.addTab("Descrição Cliente", pnDescCli);

        wdEditorTec.setColumns(20);
        wdEditorTec.setRows(5);
        jScrollPane3.setViewportView(wdEditorTec);

        javax.swing.GroupLayout pnDescTecLayout = new javax.swing.GroupLayout(pnDescTec);
        pnDescTec.setLayout(pnDescTecLayout);
        pnDescTecLayout.setHorizontalGroup(
            pnDescTecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDescTecLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnDescTecLayout.setVerticalGroup(
            pnDescTecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDescTecLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        tpGeneral.addTab("Descrição Técnica", pnDescTec);

        lbServiceFinished.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbServiceFinished.setText("Finalizar Ordem de Serviço");

        tbOs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Ordem de Serviço", "Data Emissão", "Técnico", "Cliente", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbOs);

        btConclusion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btConclusion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_finishOs.png"))); // NOI18N
        btConclusion.setText("Concluir ");
        btConclusion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btConclusion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConclusionActionPerformed(evt);
            }
        });

        btCancel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_cancel.png"))); // NOI18N
        btCancel.setText("Cancelar");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnFinishedLayout = new javax.swing.GroupLayout(pnFinished);
        pnFinished.setLayout(pnFinishedLayout);
        pnFinishedLayout.setHorizontalGroup(
            pnFinishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFinishedLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnFinishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addGroup(pnFinishedLayout.createSequentialGroup()
                        .addGroup(pnFinishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbServiceFinished)
                            .addGroup(pnFinishedLayout.createSequentialGroup()
                                .addComponent(btConclusion, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnFinishedLayout.setVerticalGroup(
            pnFinishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFinishedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbServiceFinished)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnFinishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btConclusion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        tpGeneral.addTab("Conclusão", pnFinished);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        addItems();
    }//GEN-LAST:event_btAddActionPerformed

    private void btOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOpenActionPerformed
        addOs();
    }//GEN-LAST:event_btOpenActionPerformed

    private void btRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveActionPerformed
        removeItems();
    }//GEN-LAST:event_btRemoveActionPerformed

    private void btFinishedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinishedActionPerformed
        finished();
    }//GEN-LAST:event_btFinishedActionPerformed

    private void btConclusionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConclusionActionPerformed
        conclusionOs();
    }//GEN-LAST:event_btConclusionActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        cancelOs();
    }//GEN-LAST:event_btCancelActionPerformed

    public static void main( String args[] )
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Windows".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(OsEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(OsEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(OsEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(OsEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                OsEditor dialog = new OsEditor(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter()
                {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e)
                    {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btConclusion;
    private javax.swing.JButton btFinished;
    private javax.swing.JButton btOpen;
    private javax.swing.JButton btRemove;
    private javax.swing.JComboBox<Object> cbClient;
    private javax.swing.JComboBox<Object> cbCollaborator;
    private javax.swing.JComboBox<Object> cbDevice;
    private javax.swing.JComboBox<Object> cbOs;
    private javax.swing.JComboBox<Object> cbService;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbClient;
    private javax.swing.JLabel lbCollaborator;
    private javax.swing.JLabel lbDateRegister;
    private javax.swing.JLabel lbDevice;
    private javax.swing.JLabel lbOs;
    private javax.swing.JLabel lbService;
    private javax.swing.JLabel lbServiceFinished;
    private javax.swing.JLabel lbValue;
    private javax.swing.JPanel pnDescCli;
    private javax.swing.JPanel pnDescTec;
    private javax.swing.JPanel pnFinished;
    private javax.swing.JPanel pnGeneral;
    private javax.swing.JSeparator spGeneral;
    private javax.swing.JTable tbItem;
    private javax.swing.JTable tbOs;
    private javax.swing.JTextField tfDate;
    private javax.swing.JTextField tfValueMonitor;
    private javax.swing.JTabbedPane tpGeneral;
    private javax.swing.JTextArea wdEditoCli;
    private javax.swing.JTextArea wdEditorTec;
    // End of variables declaration//GEN-END:variables
}
