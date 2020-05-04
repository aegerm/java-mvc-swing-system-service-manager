package com.am.sms.editors;

import com.am.sms.model.data.Payment;
import com.am.sms.model.db.transaction.PaymentManagerTransaction;
import com.am.sms.util.Naming;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Alexandre Marques
 */
public class PaymentEditor 
    extends 
        javax.swing.JDialog
{

    public PaymentEditor( java.awt.Frame parent, boolean modal )
    {
        super( parent, modal );
        
        initComponents();
        
        tableRenderer();
        
        defineColor();
    }
    
    private void defineColor()
    {
        this.getContentPane().setBackground( new Color( 255, 255, 255 ) );
        spPayment.setBackground( new Color( 162, 171, 179 ) );
        spPayment.setForeground( new Color( 162, 171, 179 ) );
    }
    
    private void addPayment()
    {
        if( cbPayment.getSelectedIndex() != 0 )
        {
            Payment payment = new Payment();
            
            payment.setFormPayment( cbPayment.getSelectedItem().toString() );
            payment.setState( Payment.STATE_ACTIVE );
            
            try
            {
                com.am.sms.model.ModuleContext
                                .getInstance()
                                .getPaymentServiceManager()
                                .addItem( payment );
                
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
    
    private void editPayment()
    {
        if( tbPayment.getSelectedRow() != -1 )
        {
            Payment payment = new Payment();
            
            payment.setId( (Integer) tbPayment.getValueAt( tbPayment.getSelectedRow(), 0 ) );
            payment.setFormPayment( cbPayment.getSelectedItem().toString() );
            payment.setState( Payment.STATE_ACTIVE );
            
            try
            {
                com.am.sms.model.ModuleContext
                                .getInstance()
                                .getPaymentServiceManager()
                                .updateItem( payment );
                
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
    
    private void deletePayment()
    {
        if( tbPayment.getSelectedRow() != -1 )
        {
            int confirm = JOptionPane.showConfirmDialog( this, Naming.CUSTOM_DELETE );
            
            if( confirm == JOptionPane.YES_OPTION )
            {
                Payment payment = new Payment();
                
                payment.setId( (Integer) tbPayment.getValueAt( tbPayment.getSelectedRow(), 0 ) );
                payment.setState( Payment.STATE_INATIVE );
                
                try
                {
                    com.am.sms.model.ModuleContext
                                    .getInstance()
                                    .getPaymentServiceManager()
                                    .deleteItem( payment );
                    
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
        cbPayment.setSelectedIndex( 0 );
    }
    
    private void tableRenderer()
    {
        DefaultTableModel model = (DefaultTableModel) tbPayment.getModel();
        
        tbPayment.setRowSorter( new TableRowSorter(model) );
        tbPayment.setDefaultEditor( Object.class, null );
        
        mountTable();
    }
    
    private void mountTable()
    {
        DefaultTableModel model = (DefaultTableModel) tbPayment.getModel();
        
        model.setNumRows( 0 );
        
        PaymentManagerTransaction transaction = new PaymentManagerTransaction();
        
        try
        {
            for( Payment p : transaction.readPayments() )
            {
                if( p.getState().equals( Payment.STATE_ACTIVE ) )
                {
                    model.addRow( new Object[] 
                    {
                        p.getId(),
                        p.getFormPayment(),
                        p.getState()
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
        if( tbPayment.getSelectedRow() != -1 )
        {
            tfId.setText( tbPayment.getValueAt( tbPayment.getSelectedRow(), 0 ).toString() );
            cbPayment.getModel().setSelectedItem( tbPayment.getValueAt( tbPayment.getSelectedRow(), 1 ).toString() );
        }
    }

    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lbId = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        lbPayment = new javax.swing.JLabel();
        cbPayment = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPayment = new javax.swing.JTable();
        spPayment = new javax.swing.JSeparator();
        btAdd = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        btExit = new javax.swing.JButton();
        lbInfo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editor Pagamentos");

        lbId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbId.setText("ID");

        tfId.setEditable(false);
        tfId.setBackground(new java.awt.Color(255, 255, 255));
        tfId.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbPayment.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPayment.setText("Forma de Pagamento (*)");

        cbPayment.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbPayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "À Vista", "Boleto", "Cartão de Crédito", "Parcelado" }));

        tbPayment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "ID", "Forma de Pagamento", "Situação"
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
        tbPayment.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tbPaymentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPayment);

        btAdd.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_add.png"))); // NOI18N
        btAdd.setText("Adicionar");
        btAdd.setToolTipText("Adicionar Forma de Pagamento");
        btAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdd.setPreferredSize(new java.awt.Dimension(115, 35));
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
        btEdit.setToolTipText("Editar Forma de Pagamento");
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
        btDelete.setToolTipText("Deletar Forma de Pagamento");
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

        lbInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbInfo.setText("Os campos com (*) são obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addComponent(spPayment)
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
                            .addComponent(lbPayment)
                            .addComponent(cbPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbInfo)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                .addComponent(lbId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPayment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btAddActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btAddActionPerformed
    {//GEN-HEADEREND:event_btAddActionPerformed
        addPayment();
    }//GEN-LAST:event_btAddActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btEditActionPerformed
    {//GEN-HEADEREND:event_btEditActionPerformed
        editPayment();
    }//GEN-LAST:event_btEditActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btDeleteActionPerformed
    {//GEN-HEADEREND:event_btDeleteActionPerformed
        deletePayment();
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btExitActionPerformed
    {//GEN-HEADEREND:event_btExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btExitActionPerformed

    private void tbPaymentMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tbPaymentMouseClicked
    {//GEN-HEADEREND:event_tbPaymentMouseClicked
        selectedItem();
    }//GEN-LAST:event_tbPaymentMouseClicked

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
            java.util.logging.Logger.getLogger(PaymentEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(PaymentEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(PaymentEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(PaymentEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                PaymentEditor dialog = new PaymentEditor(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<Object> cbPayment;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbInfo;
    private javax.swing.JLabel lbPayment;
    private javax.swing.JSeparator spPayment;
    private javax.swing.JTable tbPayment;
    private javax.swing.JTextField tfId;
    // End of variables declaration//GEN-END:variables
}
