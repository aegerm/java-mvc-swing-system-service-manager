package com.am.sms.editors;

import com.am.sms.model.data.Device;
import com.am.sms.model.data.TypeDevice;
import com.am.sms.model.db.transaction.DeviceManagerTransaction;
import com.am.sms.model.db.transaction.TypeDeviceManagerTransaction;
import com.am.sms.util.Naming;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Alexandre Marques
 */
public class DeviceEditor 
    extends 
        javax.swing.JDialog
{
    public DeviceEditor( java.awt.Frame parent, boolean modal )
    {
        super( parent, modal );
        
        initComponents();
        
        tableRenderer();
        
        rendererComboItems();
        
        defineColor();
    }
    
    private void defineColor()
    {
        this.getContentPane().setBackground( new Color( 255, 255, 255 ) );
        spDevice.setBackground( new Color( 162, 171, 179 ) );
        spDevice.setForeground( new Color( 162, 171, 179 ) );
        tfModel.requestFocus();
    }
    
    private void addDevice()
    {
        if( tfMaker.getText().length() != 0 && tfModel.getText().length() != 0 && tfSerial.getText().length() != 0 &&
            cbDevice.getSelectedIndex() != 0  )
        {
            Device device = new Device();
            
            TypeDevice typeDevice = (TypeDevice) cbDevice.getSelectedItem();
            
            
            device.setModel( tfModel.getText() );
            device.setMaker( tfMaker.getText() );
            device.setSerialNumber( tfSerial.getText() );
            device.setTypeDevice( typeDevice );
            device.setState( Device.STATE_ACTIVE );
            
            try
            {
                com.am.sms.model.ModuleContext
                                .getInstance()
                                .getDeviceManager()
                                .addItem( device );
                
                JOptionPane.showMessageDialog( this, Naming.CRUD_ADD );
                
                mountTable();
                
                clearInputs();
            }
            
            catch( Exception e )
            {
                throw new RuntimeException( e );
            }
        }
        
        else
        {
            JOptionPane.showMessageDialog( this, Naming.CRUD_VALIDATE );
        }
    }
    
    private void editDevice()
    {
        if( tbDevice.getSelectedRow() != -1 )
        {
            Device device = new Device();
            
            TypeDevice typeDevice = (TypeDevice) cbDevice.getSelectedItem();
            
            
            device.setId( (Integer) tbDevice.getValueAt( tbDevice.getSelectedRow(), 0 ) );
            device.setModel( tfModel.getText() );
            device.setMaker( tfMaker.getText() );
            device.setSerialNumber( tfSerial.getText() );
            device.setTypeDevice( typeDevice );
            device.setState( Device.STATE_ACTIVE );
            
            try
            {
                com.am.sms.model.ModuleContext
                                .getInstance()
                                .getDeviceManager()
                                .updateItem( device );
                
                JOptionPane.showMessageDialog( this, Naming.CRUD_UPDATE );
                
                mountTable();
                
                btAdd.setEnabled( true );
            
                btEdit.setText( "Editar" );
                btEdit.setIcon( new javax.swing.ImageIcon( getClass().getResource("/com/am/sms/res/icons/ic_edit.png") ) );
                
                clearInputs();
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
    
    private void deleteDevice()
    {
        if( tbDevice.getSelectedRow() != -1 )
        {
            int confirm = JOptionPane.showConfirmDialog( this, Naming.CUSTOM_DELETE );
            
            if( confirm == JOptionPane.YES_OPTION )
            {
                Device device = new Device();
                
                device.setId( (Integer) tbDevice.getValueAt( tbDevice.getSelectedRow(), 0 ) );
                device.setState( Device.STATE_INATIVE );
                
                try
                {
                    com.am.sms.model.ModuleContext
                                    .getInstance()
                                    .getDeviceManager()
                                    .deleteItem( device );
                    
                    JOptionPane.showMessageDialog( this, Naming.CRUD_DELETE );
                    
                    mountTable();
                
                    btAdd.setEnabled( true );
            
                    btEdit.setText( "Editar" );
                    btEdit.setIcon( new javax.swing.ImageIcon( getClass().getResource("/com/am/sms/res/icons/ic_edit.png") ) );
                    
                    clearInputs();
                }
                
                catch( Exception e )
                {
                    throw new RuntimeException( e );
                }
            }
        }
        
        else
        {
            JOptionPane.showMessageDialog( this, Naming.CRUD_SELECTED );
        }
    }
    
    private void clearInputs()
    {
        tfId.setText( "" );
        tfMaker.setText( "" );
        tfModel.setText( "" );
        tfSerial.setText( "" );
        cbDevice.setSelectedIndex( 0 );
    }
    
    private void tableRenderer()
    {
        DefaultTableModel model = (DefaultTableModel) tbDevice.getModel();
        
        tbDevice.setRowSorter( new TableRowSorter(model) );
        tbDevice.setDefaultEditor( Object.class, null );
        
        mountTable();
    }
    
    private void mountTable()
    {
        DefaultTableModel model = (DefaultTableModel) tbDevice.getModel();
        
        model.setNumRows( 0 );
        
        DeviceManagerTransaction transaction = new DeviceManagerTransaction();
        
        try
        {
            for( Device d : transaction.readDevice() )
            {
                if( ! d.getState().equals( Device.STATE_INATIVE ) )
                {
                    model.addRow( new Object[] 
                    {
                        d.getId(),
                        d.getModel(),
                        d.getMaker(),
                        d.getSerialNumber(),
                        d.getTypeDevice()
                    } );
                }
            }
        }
        
        catch( Exception e )
        {
            throw new RuntimeException( e );
        }
    }
    
    private void selectedItem()
    {
        if( tbDevice.getSelectedRow() != -1 )
        {
            tfId.setText( tbDevice.getValueAt( tbDevice.getSelectedRow(), 0 ).toString() );
            tfModel.setText( tbDevice.getValueAt( tbDevice.getSelectedRow(), 1 ).toString() );
            tfMaker.setText( tbDevice.getValueAt( tbDevice.getSelectedRow(), 2 ).toString() );
            tfSerial.setText( tbDevice.getValueAt( tbDevice.getSelectedRow(), 3 ).toString() );
            
            String d = String.valueOf( tbDevice.getValueAt( tbDevice.getSelectedRow(), 4 ).toString() );
            
            for ( int i = 0; i < cbDevice.getItemCount(); i++ )
            {
                if( cbDevice.getItemAt( i ).toString().equals( d ) )
                {
                    cbDevice.setSelectedIndex( i );
                }
            }
            
            btAdd.setEnabled( false );
            
            btEdit.setText( "Salvar" );
            btEdit.setIcon( new javax.swing.ImageIcon( getClass().getResource("/com/am/sms/res/icons/ic_save.png") ) );
        }
    }
    
    private void rendererComboItems()
    {
        TypeDeviceManagerTransaction typeManagerTransaction = new TypeDeviceManagerTransaction();
        
        try
        {
            for ( TypeDevice t : typeManagerTransaction.readTypeDevice() )
            {
                cbDevice.addItem( t );
            }
        }
        
        catch( Exception ex )
        {
            throw new RuntimeException( ex );
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btExit = new javax.swing.JButton();
        spDevice = new javax.swing.JSeparator();
        btDelete = new javax.swing.JButton();
        lbTypeDevice = new javax.swing.JLabel();
        btAdd = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDevice = new javax.swing.JTable();
        tfId = new javax.swing.JTextField();
        lbSerial = new javax.swing.JLabel();
        tfSerial = new javax.swing.JTextField();
        cbDevice = new javax.swing.JComboBox<>();
        tfModel = new javax.swing.JTextField();
        tfMaker = new javax.swing.JTextField();
        btEdit = new javax.swing.JButton();
        lbID = new javax.swing.JLabel();
        lbModel = new javax.swing.JLabel();
        lbMaker = new javax.swing.JLabel();
        lbFind = new javax.swing.JLabel();
        tfFind = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lbID1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editor Dispositivos");

        btExit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_exit.png"))); // NOI18N
        btExit.setText("Sair");
        btExit.setToolTipText("Fechar Editor");
        btExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExit.setPreferredSize(new java.awt.Dimension(115, 35));
        btExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExitActionPerformed(evt);
            }
        });

        btDelete.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_delete.png"))); // NOI18N
        btDelete.setText("Excluir");
        btDelete.setToolTipText("Excluir Equipamento");
        btDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDelete.setPreferredSize(new java.awt.Dimension(115, 35));
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        lbTypeDevice.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTypeDevice.setText("Tipo Equipamento(*)");

        btAdd.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_add.png"))); // NOI18N
        btAdd.setText("Adicionar");
        btAdd.setToolTipText("Adicionar Equipamento");
        btAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdd.setPreferredSize(new java.awt.Dimension(115, 35));
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        tbDevice.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbDevice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Modelo", "Marca", "S/N", "Equipamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDevice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDeviceMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbDevice);

        tfId.setEditable(false);
        tfId.setBackground(new java.awt.Color(255, 255, 255));
        tfId.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbSerial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbSerial.setText("S/N(*)");

        tfSerial.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        cbDevice.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbDevice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        tfModel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        tfMaker.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        btEdit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_edit.png"))); // NOI18N
        btEdit.setText("Editar");
        btEdit.setToolTipText("Editar Equipamento");
        btEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEdit.setPreferredSize(new java.awt.Dimension(115, 35));
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });

        lbID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbID.setText("Id");

        lbModel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbModel.setText("Modelo(*)");

        lbMaker.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbMaker.setText("Marca(*)");

        lbFind.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbFind.setText("Pesquisar");

        tfFind.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_search.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lbID1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbID1.setText("Os campos com (*) são obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfFind, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spDevice)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbTypeDevice)
                                    .addComponent(lbSerial)
                                    .addComponent(lbMaker)
                                    .addComponent(lbModel)
                                    .addComponent(lbID))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfMaker, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfModel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lbFind)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btExit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbID1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbID1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbID)
                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbModel)
                    .addComponent(tfModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMaker)
                    .addComponent(tfMaker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSerial)
                    .addComponent(tfSerial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTypeDevice)
                    .addComponent(cbDevice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbFind)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbDeviceMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tbDeviceMouseClicked
    {//GEN-HEADEREND:event_tbDeviceMouseClicked
        selectedItem();
    }//GEN-LAST:event_tbDeviceMouseClicked

    private void btAddActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btAddActionPerformed
    {//GEN-HEADEREND:event_btAddActionPerformed
        addDevice();
    }//GEN-LAST:event_btAddActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btEditActionPerformed
    {//GEN-HEADEREND:event_btEditActionPerformed
        editDevice();
    }//GEN-LAST:event_btEditActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btDeleteActionPerformed
    {//GEN-HEADEREND:event_btDeleteActionPerformed
        deleteDevice();
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btExitActionPerformed
    {//GEN-HEADEREND:event_btExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btExitActionPerformed

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
            java.util.logging.Logger.getLogger(DeviceEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(DeviceEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(DeviceEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(DeviceEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                DeviceEditor dialog = new DeviceEditor(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btExit;
    private javax.swing.JComboBox<Object> cbDevice;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbFind;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbID1;
    private javax.swing.JLabel lbMaker;
    private javax.swing.JLabel lbModel;
    private javax.swing.JLabel lbSerial;
    private javax.swing.JLabel lbTypeDevice;
    private javax.swing.JSeparator spDevice;
    private javax.swing.JTable tbDevice;
    private javax.swing.JTextField tfFind;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfMaker;
    private javax.swing.JTextField tfModel;
    private javax.swing.JTextField tfSerial;
    // End of variables declaration//GEN-END:variables
}
