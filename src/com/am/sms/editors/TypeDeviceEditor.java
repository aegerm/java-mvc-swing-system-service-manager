package com.am.sms.editors;

import com.am.sms.model.data.TypeDevice;
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
public class TypeDeviceEditor extends javax.swing.JDialog
{
    public TypeDeviceEditor( java.awt.Frame parent, boolean modal )
    {
        super( parent, modal );
        
        initComponents();
        
        tableRenderer();
        
        defineColor();
    }
    
    private void defineColor()
    {
        this.getContentPane().setBackground( new Color( 255, 255, 255 ) );
        spDevice.setBackground( new Color( 162, 171, 179 ) );
        spDevice.setForeground( new Color( 162, 171, 179 ) );
        tfDevice.requestFocus();
    }
    
    private void addType()
    {
        if( tfDevice.getText().length() != 0 )
        {
            TypeDevice typeDevice = new TypeDevice();
            typeDevice.setDevice( tfDevice.getText() );
            typeDevice.setState( TypeDevice.STATE_ACTIVE );
            
            try
            {
                com.am.sms.model.ModuleContext
                                .getInstance()
                                .getTypeDeviceManager()
                                .addItem( typeDevice );
                
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
    
    private void editType()
    {
        if( tbTypeDevice.getSelectedRow() != -1 )
        {
            TypeDevice typeDevice = new TypeDevice();
            typeDevice.setId( (Integer) tbTypeDevice.getValueAt( tbTypeDevice.getSelectedRow(), 0 ) );
            typeDevice.setDevice( tfDevice.getText() );
            typeDevice.setState( TypeDevice.STATE_ACTIVE );
            
            try
            {
                com.am.sms.model.ModuleContext
                                .getInstance()
                                .getTypeDeviceManager()
                                .updateItem( typeDevice );
                
                JOptionPane.showMessageDialog( this, Naming.CRUD_UPDATE );
                
                mountTable();
                
                btAdd.setEnabled( true );
                    
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
    
    private void deleteType()
    {
        if( tbTypeDevice.getSelectedRow() != -1 )
        {
            int confirm = JOptionPane.showConfirmDialog( this, Naming.CUSTOM_DELETE );
            
            if( confirm == JOptionPane.YES_OPTION )
            {
                TypeDevice typeDevice = new TypeDevice();
                typeDevice.setId( (Integer) tbTypeDevice.getValueAt( tbTypeDevice.getSelectedRow(), 0 ) );
                typeDevice.setState( TypeDevice.STATE_INATIVE );
                
                try
                {
                    com.am.sms.model.ModuleContext
                                    .getInstance()
                                    .getTypeDeviceManager()
                                    .deleteItem( typeDevice );
                    
                    JOptionPane.showMessageDialog( this, Naming.CRUD_DELETE );
                    
                    mountTable();
                
                    btAdd.setEnabled( true );
                    
                    clearInputs();
                }
                
                catch( Exception e )
                {
                    if( e instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException )
                    {
                        JOptionPane.showMessageDialog( this, "" );
                    }
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
        tfDevice.setText( "" );
        tfFind.setText( "" );
    }
    
    private void tableRenderer()
    {
        DefaultTableModel model = (DefaultTableModel) tbTypeDevice.getModel();
        
        tbTypeDevice.setRowSorter( new TableRowSorter(model) );
        tbTypeDevice.setDefaultEditor( Object.class, null );
        
        mountTable();
    }
    
    private void mountTable()
    {
        DefaultTableModel model = (DefaultTableModel) tbTypeDevice.getModel();
        
        model.setNumRows( 0 );
        
        TypeDeviceManagerTransaction transaction = new TypeDeviceManagerTransaction();
        
        try
        {
            for( TypeDevice t : transaction.readTypeDevice() )
            {
                if( t.getState().equals( TypeDevice.STATE_ACTIVE ) )
                {
                    model.addRow( new Object[] 
                    {
                        t.getId(),
                        t.getDevice(),
                        t.getState()
                    } );
                }
            }
        }
        
        catch( Exception e )
        {
            throw new RuntimeException( e );
        }
    }
    
    private void searchType( String find )
    {
        DefaultTableModel model = (DefaultTableModel) tbTypeDevice.getModel();
        
        model.setNumRows( 0 );
        
        TypeDeviceManagerTransaction transaction = new TypeDeviceManagerTransaction();
        
        try
        {
            for( TypeDevice t : transaction.search( find ) )
            {
                if( t.getState().equals( TypeDevice.STATE_ACTIVE ) )
                {
                    model.addRow( new Object[] 
                    {
                        t.getId(),
                        t.getDevice(),
                        t.getState()
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
        if( tbTypeDevice.getSelectedRow() != -1 )
        {
            tfId.setText( tbTypeDevice.getValueAt( tbTypeDevice.getSelectedRow(), 0 ).toString() );
            tfDevice.setText( tbTypeDevice.getValueAt( tbTypeDevice.getSelectedRow(), 1 ).toString() );
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lbId = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        lbDevice = new javax.swing.JLabel();
        tfDevice = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTypeDevice = new javax.swing.JTable();
        spDevice = new javax.swing.JSeparator();
        btAdd = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        btExit = new javax.swing.JButton();
        lbFind = new javax.swing.JLabel();
        tfFind = new javax.swing.JTextField();
        btFind = new javax.swing.JButton();
        lbPersistence = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editor Tipo Dispositivo");

        lbId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbId.setText("ID");

        tfId.setEditable(false);
        tfId.setBackground(new java.awt.Color(255, 255, 255));
        tfId.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbDevice.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbDevice.setText("Dispositivo (*)");

        tfDevice.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        tbTypeDevice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "ID", "Dispositivo", "Situação"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        tbTypeDevice.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tbTypeDeviceMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbTypeDevice);

        btAdd.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_add.png"))); // NOI18N
        btAdd.setText("Adicionar");
        btAdd.setToolTipText("Adicionar Tipo de Dispositivo");
        btAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdd.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btAddActionPerformed(evt);
            }
        });

        btEdit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_edit.png"))); // NOI18N
        btEdit.setText("Editar");
        btEdit.setToolTipText("Editar Tipo de Dispositivo");
        btEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEdit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btEditActionPerformed(evt);
            }
        });

        btDelete.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_delete.png"))); // NOI18N
        btDelete.setText("Excluir");
        btDelete.setToolTipText("Deletar Tipo de Dispositivo");
        btDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDelete.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btDeleteActionPerformed(evt);
            }
        });

        btExit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_exit.png"))); // NOI18N
        btExit.setText("Sair");
        btExit.setToolTipText("Fechar Editor");
        btExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btExitActionPerformed(evt);
            }
        });

        lbFind.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbFind.setText("Pesquisar");

        tfFind.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        btFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_search.png"))); // NOI18N
        btFind.setContentAreaFilled(false);
        btFind.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btFind.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btFindActionPerformed(evt);
            }
        });

        lbPersistence.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPersistence.setText("Os campos com (*) são obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addComponent(spDevice)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfFind)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btFind, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btExit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbId)
                            .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDevice)
                            .addComponent(tfDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbFind))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbPersistence)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPersistence)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(lbId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbDevice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDevice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbFind)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFind, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void btAddActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btAddActionPerformed
    {//GEN-HEADEREND:event_btAddActionPerformed
        addType();
    }//GEN-LAST:event_btAddActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btEditActionPerformed
    {//GEN-HEADEREND:event_btEditActionPerformed
        editType();
    }//GEN-LAST:event_btEditActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btDeleteActionPerformed
    {//GEN-HEADEREND:event_btDeleteActionPerformed
        deleteType();
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btExitActionPerformed
    {//GEN-HEADEREND:event_btExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btExitActionPerformed

    private void tbTypeDeviceMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tbTypeDeviceMouseClicked
    {//GEN-HEADEREND:event_tbTypeDeviceMouseClicked
        selectedItem();
    }//GEN-LAST:event_tbTypeDeviceMouseClicked

    private void btFindActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btFindActionPerformed
    {//GEN-HEADEREND:event_btFindActionPerformed
        searchType( tfFind.getText() );
    }//GEN-LAST:event_btFindActionPerformed

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
            java.util.logging.Logger.getLogger(TypeDeviceEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(TypeDeviceEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(TypeDeviceEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(TypeDeviceEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                TypeDeviceEditor dialog = new TypeDeviceEditor(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btFind;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDevice;
    private javax.swing.JLabel lbFind;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbPersistence;
    private javax.swing.JSeparator spDevice;
    private javax.swing.JTable tbTypeDevice;
    private javax.swing.JTextField tfDevice;
    private javax.swing.JTextField tfFind;
    private javax.swing.JTextField tfId;
    // End of variables declaration//GEN-END:variables
}
