package com.am.sms.editors;

import com.am.sms.util.FormatUtil;
import java.awt.Color;

/**
 *
 * @author Alexandre Marques
 */
public class FinancesEditor 
    extends 
        javax.swing.JDialog
{
    public FinancesEditor( java.awt.Frame parent, boolean modal )
    {
        super( parent, modal );
        initComponents();
        defineColor();
        format();
    }
    
    private void format()
    {
        FormatUtil.formatDate( dtDatePay );
        FormatUtil.formatDate( dtDateSale );
    }
    
    private void defineColor()
    {
        this.getContentPane().setBackground( new Color( 255, 255, 255 ) );
        spFinances.setBackground( new Color( 162, 171, 179 ) );
        spFinances.setForeground( new Color( 162, 171, 179 ) );
        dtDatePay.requestFocus();
    }
    
    private void addFinances()
    {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbId = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        lbDatePay = new javax.swing.JLabel();
        lbSale = new javax.swing.JLabel();
        lbPrevided = new javax.swing.JLabel();
        tfValuePreview = new javax.swing.JTextField();
        lbReceived = new javax.swing.JLabel();
        tfValueR = new javax.swing.JTextField();
        lbPlots = new javax.swing.JLabel();
        tfPlots = new javax.swing.JTextField();
        lbDiscont = new javax.swing.JLabel();
        tfDiscount = new javax.swing.JTextField();
        lbSearch = new javax.swing.JLabel();
        tfSearch = new javax.swing.JTextField();
        btFind = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFinances = new javax.swing.JTable();
        spFinances = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        dtDatePay = new javax.swing.JFormattedTextField();
        dtDateSale = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editor Financeiro");

        lbId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbId.setText("ID");

        tfId.setEditable(false);
        tfId.setBackground(new java.awt.Color(255, 255, 255));
        tfId.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbDatePay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbDatePay.setText("Data Pagamento");

        lbSale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbSale.setText("Data Venda");

        lbPrevided.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPrevided.setText("Valor Previsto");

        tfValuePreview.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbReceived.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbReceived.setText("Valor Recebido");

        tfValueR.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbPlots.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPlots.setText("Parcelas");

        tfPlots.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbDiscont.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbDiscont.setText("Desconto");

        tfDiscount.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbSearch.setText("Pesquisar");

        tfSearch.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        btFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_search.png"))); // NOI18N
        btFind.setContentAreaFilled(false);
        btFind.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tbFinances.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data Pagamento", "Data Venda", "Valor Previsto", "Valor Recebido", "Parcelas", "Desconto", "Os", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbFinances);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_add.png"))); // NOI18N
        jButton1.setText("Adicionar");

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_edit.png"))); // NOI18N
        jButton3.setText("Editar");

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_delete.png"))); // NOI18N
        jButton4.setText("Excluir");

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_exit.png"))); // NOI18N
        jButton5.setText("Sair");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btFind, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spFinances)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbId)
                            .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSearch)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDatePay)
                                    .addComponent(lbSale)
                                    .addComponent(dtDatePay, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dtDateSale, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbPrevided)
                                    .addComponent(tfValuePreview)
                                    .addComponent(lbReceived)
                                    .addComponent(tfValueR, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbPlots)
                                    .addComponent(lbDiscont)
                                    .addComponent(tfDiscount)
                                    .addComponent(tfPlots, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 266, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addComponent(lbId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbDatePay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtDatePay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbSale))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(dtDateSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbPrevided)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfValuePreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbPlots)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfPlots, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbReceived)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfValueR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbDiscont)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(lbSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFind, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spFinances, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FinancesEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(FinancesEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(FinancesEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(FinancesEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                FinancesEditor dialog = new FinancesEditor(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btFind;
    private javax.swing.JFormattedTextField dtDatePay;
    private javax.swing.JFormattedTextField dtDateSale;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDatePay;
    private javax.swing.JLabel lbDiscont;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbPlots;
    private javax.swing.JLabel lbPrevided;
    private javax.swing.JLabel lbReceived;
    private javax.swing.JLabel lbSale;
    private javax.swing.JLabel lbSearch;
    private javax.swing.JSeparator spFinances;
    private javax.swing.JTable tbFinances;
    private javax.swing.JTextField tfDiscount;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfPlots;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JTextField tfValuePreview;
    private javax.swing.JTextField tfValueR;
    // End of variables declaration//GEN-END:variables
}
