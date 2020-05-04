package com.am.sms.editors;

import com.am.sms.model.data.Service;
import com.am.sms.model.db.transaction.ServiceManagerTransaction;
import com.am.sms.util.Naming;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Alexandre Marques
 */
public class ServiceEditor 
    extends 
        javax.swing.JDialog
{
    public ServiceEditor( java.awt.Frame parent, boolean modal )
    {
        super( parent, modal );
        
        initComponents();
        
        tableRenderer();
        
        defineColor();
    }
    
    private void defineColor()
    {
        this.getContentPane().setBackground( new Color( 255, 255, 255 ) );
        spService.setBackground( new Color( 162, 171, 179 ) );
        spService.setForeground( new Color( 162, 171, 179 ) );
    }
    
    private void addService()
    {
        if( tfCost.getText().length() != 0 )
        {
            Service service = new Service();
            
            service.setCost( Double.parseDouble( tfCost.getText() ) );
            service.setService( tfService.getText() );
            service.setState( Service.STATE_ACTIVE );
            
            try
            {
                com.am.sms.model.ModuleContext
                                .getInstance()
                                .getServiceManager()
                                .addItem( service );
                
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
    
    private void editService()
    {
        if( tbService.getSelectedRow() != -1 )
        {
            Service service = new Service();
            
            service.setId( (Integer) tbService.getValueAt( tbService.getSelectedRow(), 0 ) );
            service.setCost( Double.parseDouble( tfCost.getText() ) );
            service.setService( tfService.getText() );
            service.setState( Service.STATE_ACTIVE );
            
            try
            {
                com.am.sms.model.ModuleContext
                                .getInstance()
                                .getServiceManager()
                                .updateItem( service );
                
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
    
    private void deleteService()
    {
        if( tbService.getSelectedRow() != -1 )
        {
            int confirm = JOptionPane.showConfirmDialog( this, Naming.CUSTOM_DELETE );
            
            if( confirm == JOptionPane.YES_OPTION )
            {
                Service service = new Service();
                
                service.setId( (Integer) tbService.getValueAt( tbService.getSelectedRow(), 0 ) );
                service.setState( Service.STATE_INATIVE );
                
                try
                {
                    com.am.sms.model.ModuleContext
                                    .getInstance()
                                    .getServiceManager()
                                    .deleteItem( service );
                    
                    JOptionPane.showMessageDialog( this, Naming.CRUD_DELETE );
                    
                    mountTable();
                
                    btAdd.setEnabled( true );
                    
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
        tfCost.setText( "" );
        tfService.setText( "" );
        tfFind.setText( "" );
    }
    
    private void tableRenderer()
    {
        DefaultTableModel model = (DefaultTableModel) tbService.getModel();
        
        tbService.setRowSorter( new TableRowSorter(model) );
        tbService.setDefaultEditor( Object.class, null );
        
        mountTable();
    }
    
    private void mountTable()
    {
        DefaultTableModel model = (DefaultTableModel) tbService.getModel();
        
        model.setNumRows( 0 );
        
        ServiceManagerTransaction transaction = new ServiceManagerTransaction();
        
        try
        {
            for( Service s : transaction.readService() )
            {
                if( s.getState().equals( Service.STATE_ACTIVE ) )
                {
                    model.addRow( new Object[] 
                    {
                        s.getId(),
                        s.getCost(),
                        s.getService(),
                        s.getState()
                    } );
                }
            }
        }
        
        catch( Exception e )
        {
            throw new RuntimeException( e );
        }
    }
    
    private void search( String find )
    {
        DefaultTableModel model = (DefaultTableModel) tbService.getModel();
        
        model.setNumRows( 0 );
        
        ServiceManagerTransaction transaction = new ServiceManagerTransaction();
        
        try
        {
            for( Service s : transaction.search( find ) )
            {
                if( s.getState().equals( Service.STATE_ACTIVE ) );
                {
                    model.addRow( new Object[] 
                    {
                        s.getId(),
                        s.getCost(),
                        s.getService(),
                        s.getState()
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
        if( tbService.getSelectedRow() != -1 )
        {
            tfId.setText( tbService.getValueAt( tbService.getSelectedRow(), 0 ).toString() );
            tfCost.setText( tbService.getValueAt( tbService.getSelectedRow(), 1 ).toString() );
            tfService.setText( tbService.getValueAt( tbService.getSelectedRow(), 2 ).toString() );
        }
    }
    
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbId = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        lbCost = new javax.swing.JLabel();
        tfCost = new javax.swing.JTextField();
        lbTypeService = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbService = new javax.swing.JTable();
        spService = new javax.swing.JSeparator();
        btAdd = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        btExit = new javax.swing.JButton();
        tfService = new javax.swing.JTextField();
        lbSearch = new javax.swing.JLabel();
        tfFind = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lbID1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editor Serviços");

        lbId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbId.setText("ID");

        tfId.setEditable(false);
        tfId.setBackground(new java.awt.Color(255, 255, 255));
        tfId.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbCost.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbCost.setText("Custo (*)");

        tfCost.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbTypeService.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTypeService.setText("Serviço (*)");

        tbService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Custo", "Serviço", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbService.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbServiceMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbService);

        btAdd.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_add.png"))); // NOI18N
        btAdd.setText("Adicionar");
        btAdd.setToolTipText("Adicionar Serviço");
        btAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdd.setPreferredSize(new java.awt.Dimension(115, 35));
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        btEdit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_edit.png"))); // NOI18N
        btEdit.setText("Editar");
        btEdit.setToolTipText("Editar Serviço");
        btEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEdit.setPreferredSize(new java.awt.Dimension(115, 35));
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });

        btDelete.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_delete.png"))); // NOI18N
        btDelete.setText("Excluir");
        btDelete.setToolTipText("Deletar Serviço");
        btDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDelete.setPreferredSize(new java.awt.Dimension(115, 35));
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

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

        tfService.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbSearch.setText("Pesquisar");

        tfFind.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_search.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lbID1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbID1.setText("Os campos com (*) são obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addComponent(spService)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbId)
                            .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCost)
                            .addComponent(tfCost, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTypeService)
                            .addComponent(tfService, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSearch))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbID1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tfFind)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbID1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(lbId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbCost)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTypeService)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spService, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btAddActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btAddActionPerformed
    {//GEN-HEADEREND:event_btAddActionPerformed
        addService();
    }//GEN-LAST:event_btAddActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btEditActionPerformed
    {//GEN-HEADEREND:event_btEditActionPerformed
        editService();
    }//GEN-LAST:event_btEditActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btDeleteActionPerformed
    {//GEN-HEADEREND:event_btDeleteActionPerformed
        deleteService();
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btExitActionPerformed
    {//GEN-HEADEREND:event_btExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btExitActionPerformed

    private void tbServiceMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tbServiceMouseClicked
    {//GEN-HEADEREND:event_tbServiceMouseClicked
        selectedItem();
    }//GEN-LAST:event_tbServiceMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        search( tfFind.getText() );
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ServiceEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ServiceEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ServiceEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ServiceEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                ServiceEditor dialog = new ServiceEditor(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCost;
    private javax.swing.JLabel lbID1;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbSearch;
    private javax.swing.JLabel lbTypeService;
    private javax.swing.JSeparator spService;
    private javax.swing.JTable tbService;
    private javax.swing.JTextField tfCost;
    private javax.swing.JTextField tfFind;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfService;
    // End of variables declaration//GEN-END:variables
}
