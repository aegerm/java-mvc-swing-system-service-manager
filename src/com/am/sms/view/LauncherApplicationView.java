package com.am.sms.view;

import com.am.sms.editors.ClientEditor;
import com.am.sms.editors.CollaboratorEditor;
import com.am.sms.editors.DeviceEditor;
import com.am.sms.editors.FinancesEditor;
import com.am.sms.editors.OsEditor;
import com.am.sms.editors.PaymentEditor;
import com.am.sms.editors.ServiceEditor;
import com.am.sms.editors.TypeDeviceEditor;
import com.am.sms.filters.FilterByName;
import com.am.sms.filters.FilterValue;
import com.am.sms.model.db.ConnectionFactory;
import com.am.sms.util.ApplicationUtilities;
import com.am.sms.util.Naming;
import java.awt.Color;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Alexandre Marques
 */
public class LauncherApplicationView 
    extends 
        javax.swing.JFrame
{
    public LauncherApplicationView()
    {
        initComponents();
        
        layoutInit();
    }
    
    private void layoutInit()
    {
        this.getContentPane().setBackground( new Color( 240,240,240 ) );
        
        setExtendedState( MAXIMIZED_BOTH );
        
        java.util.Date date = new java.util.Date();
        
        DateFormat format = DateFormat.getDateInstance( DateFormat.MEDIUM );
        
        String info = format.format( date ) + " - " + ApplicationUtilities.getInstance().getActiveCollaborator().getLogin();
        
        lbInfoUser.setText( info );
    }
    
    private void logout()
    {
        this.dispose();
        
        LoginApplicationView login = new LoginApplicationView( this, true );
        
        login.setVisible( true );
    }
    
    private void createReport( String key )
    {
        try
        {
            JasperReport report = JasperCompileManager.compileReport( getClass().getResourceAsStream( key ) );
            
            Map param = new HashMap();
            
            JasperPrint print = JasperFillManager.fillReport( report, param, ConnectionFactory.connection() );
            
            JasperViewer.viewReport( print, false );
        }
        
        catch( JRException e )
        {
            throw new RuntimeException( e );
        }
    }

    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbInfoUser = new javax.swing.JLabel();
        jmMenu = new javax.swing.JMenuBar();
        mnRegister = new javax.swing.JMenu();
        miUser = new javax.swing.JMenuItem();
        miClient = new javax.swing.JMenuItem();
        miPayments = new javax.swing.JMenuItem();
        miFinances = new javax.swing.JMenuItem();
        miTypeDevice = new javax.swing.JMenuItem();
        miDevice = new javax.swing.JMenuItem();
        miService = new javax.swing.JMenuItem();
        miOs = new javax.swing.JMenuItem();
        mnReport = new javax.swing.JMenu();
        miReportService = new javax.swing.JMenuItem();
        miReportClient = new javax.swing.JMenuItem();
        miReportUsers = new javax.swing.JMenuItem();
        miReportUser = new javax.swing.JMenuItem();
        mnConfiguration = new javax.swing.JMenu();
        miBackup = new javax.swing.JMenuItem();
        mnAbout = new javax.swing.JMenu();
        miLogout = new javax.swing.JMenuItem();
        miExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("System Manager Service");

        lbInfoUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbInfoUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jmMenu.setBorder(null);

        mnRegister.setBorder(null);
        mnRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_register.png"))); // NOI18N
        mnRegister.setText("Registros");
        mnRegister.setToolTipText("Modulo de cadastros");
        mnRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        miUser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        miUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_login.png"))); // NOI18N
        miUser.setText("Colaboradores");
        miUser.setToolTipText("Cadastro de Usuários");
        miUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miUserActionPerformed(evt);
            }
        });
        mnRegister.add(miUser);

        miClient.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        miClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_clients.png"))); // NOI18N
        miClient.setText("Clientes");
        miClient.setToolTipText("Cadastro de Clientes");
        miClient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miClientActionPerformed(evt);
            }
        });
        mnRegister.add(miClient);

        miPayments.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        miPayments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_money.png"))); // NOI18N
        miPayments.setText("Pagamentos");
        miPayments.setToolTipText("Cadastrar Formas de Pagamentos");
        miPayments.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miPayments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPaymentsActionPerformed(evt);
            }
        });
        mnRegister.add(miPayments);

        miFinances.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        miFinances.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_fin.png"))); // NOI18N
        miFinances.setText("Finanças");
        miFinances.setToolTipText("Cadastro e Gerenciamento Financeiro");
        miFinances.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miFinances.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miFinancesActionPerformed(evt);
            }
        });
        mnRegister.add(miFinances);

        miTypeDevice.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        miTypeDevice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_types_devices.png"))); // NOI18N
        miTypeDevice.setText("Tipos de Dispositivos");
        miTypeDevice.setToolTipText("Cadastro Tipo de Dispositivos");
        miTypeDevice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miTypeDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miTypeDeviceActionPerformed(evt);
            }
        });
        mnRegister.add(miTypeDevice);

        miDevice.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        miDevice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_devices.png"))); // NOI18N
        miDevice.setText("Dispositivos");
        miDevice.setToolTipText("Cadastro de Equipamentos");
        miDevice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDeviceActionPerformed(evt);
            }
        });
        mnRegister.add(miDevice);

        miService.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        miService.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_memory.png"))); // NOI18N
        miService.setText("Serviços");
        miService.setToolTipText("Cadastro de Serviços");
        miService.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miServiceActionPerformed(evt);
            }
        });
        mnRegister.add(miService);

        miOs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        miOs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_os.png"))); // NOI18N
        miOs.setText("Os");
        miOs.setToolTipText("Cadastrar Os");
        miOs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miOsActionPerformed(evt);
            }
        });
        mnRegister.add(miOs);

        jmMenu.add(mnRegister);

        mnReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_reports.png"))); // NOI18N
        mnReport.setText("Relatórios");
        mnReport.setToolTipText("Modulo para gerar relatórios");
        mnReport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        miReportService.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        miReportService.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_report_pdf.png"))); // NOI18N
        miReportService.setText("Serviços");
        miReportService.setToolTipText("Relatório de Serviços");
        miReportService.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miReportService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miReportServiceActionPerformed(evt);
            }
        });
        mnReport.add(miReportService);

        miReportClient.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        miReportClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_report_pdf.png"))); // NOI18N
        miReportClient.setText("Clientes");
        miReportClient.setToolTipText("Relatório de Clientes");
        miReportClient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miReportClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miReportClientActionPerformed(evt);
            }
        });
        mnReport.add(miReportClient);

        miReportUsers.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        miReportUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_report_pdf.png"))); // NOI18N
        miReportUsers.setText("Ordem de Serviço");
        miReportUsers.setToolTipText("Relatório de Usuários");
        miReportUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miReportUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miReportUsersActionPerformed(evt);
            }
        });
        mnReport.add(miReportUsers);

        miReportUser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        miReportUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_report_pdf.png"))); // NOI18N
        miReportUser.setText("Valores Executados Os");
        miReportUser.setToolTipText("Relatório de Usuários");
        miReportUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miReportUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miReportUserActionPerformed(evt);
            }
        });
        mnReport.add(miReportUser);

        jmMenu.add(mnReport);

        mnConfiguration.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_settings.png"))); // NOI18N
        mnConfiguration.setText("Configuração");
        mnConfiguration.setToolTipText("Modulo de configuração do sistema");
        mnConfiguration.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        miBackup.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        miBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_backup.png"))); // NOI18N
        miBackup.setText("Backup");
        miBackup.setToolTipText("Ferramento de backup do sistema");
        miBackup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnConfiguration.add(miBackup);

        jmMenu.add(mnConfiguration);

        mnAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_info.png"))); // NOI18N
        mnAbout.setText("Sobre");
        mnAbout.setToolTipText("Modulo de informações e controle");
        mnAbout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        miLogout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        miLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_logout.png"))); // NOI18N
        miLogout.setText("Logout");
        miLogout.setToolTipText("Efetua logout do sistema");
        miLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLogoutActionPerformed(evt);
            }
        });
        mnAbout.add(miLogout);

        miExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        miExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_finished.png"))); // NOI18N
        miExit.setText("Sair");
        miExit.setToolTipText("Encerra a aplicação");
        miExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        mnAbout.add(miExit);

        jmMenu.add(mnAbout);

        setJMenuBar(jmMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbInfoUser, javax.swing.GroupLayout.DEFAULT_SIZE, 1004, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(719, Short.MAX_VALUE)
                .addComponent(lbInfoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void miExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miExitActionPerformed
    {//GEN-HEADEREND:event_miExitActionPerformed
        int exit = JOptionPane.showConfirmDialog( this, Naming.SHUTDOWN, Naming.ATTENTION, JOptionPane.YES_NO_OPTION );
        
        if( exit == JOptionPane.YES_OPTION )
        {
            System.exit( 0 );
        }
    }//GEN-LAST:event_miExitActionPerformed

    private void miUserActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miUserActionPerformed
    {//GEN-HEADEREND:event_miUserActionPerformed
       CollaboratorEditor collaboratorEditor = new CollaboratorEditor( this, true );
       collaboratorEditor.setVisible( true );
    }//GEN-LAST:event_miUserActionPerformed

    private void miClientActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miClientActionPerformed
    {//GEN-HEADEREND:event_miClientActionPerformed
        ClientEditor clientEditor = new ClientEditor( this, true );
        clientEditor.setVisible( true );
    }//GEN-LAST:event_miClientActionPerformed

    private void miDeviceActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miDeviceActionPerformed
    {//GEN-HEADEREND:event_miDeviceActionPerformed
        DeviceEditor deviceEditor = new DeviceEditor( this, true );
        deviceEditor.setVisible( true );
    }//GEN-LAST:event_miDeviceActionPerformed

    private void miServiceActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miServiceActionPerformed
    {//GEN-HEADEREND:event_miServiceActionPerformed
        ServiceEditor serviceEditor = new ServiceEditor( this, true );
        serviceEditor.setVisible( true );
    }//GEN-LAST:event_miServiceActionPerformed

    private void miReportServiceActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miReportServiceActionPerformed
    {//GEN-HEADEREND:event_miReportServiceActionPerformed
        createReport( ApplicationUtilities.REPORT_SERVICES );
    }//GEN-LAST:event_miReportServiceActionPerformed

    private void miReportClientActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miReportClientActionPerformed
    {//GEN-HEADEREND:event_miReportClientActionPerformed
        createReport( ApplicationUtilities.REPORT_CLIENTS );
    }//GEN-LAST:event_miReportClientActionPerformed

    private void miReportUsersActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miReportUsersActionPerformed
    {//GEN-HEADEREND:event_miReportUsersActionPerformed
        FilterByName filter = new FilterByName( this, false );
        filter.setLocationRelativeTo( this );
        filter.setVisible( true );
    }//GEN-LAST:event_miReportUsersActionPerformed

    private void miLogoutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miLogoutActionPerformed
    {//GEN-HEADEREND:event_miLogoutActionPerformed
        logout();
    }//GEN-LAST:event_miLogoutActionPerformed

    private void miPaymentsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miPaymentsActionPerformed
    {//GEN-HEADEREND:event_miPaymentsActionPerformed
        PaymentEditor paymentEditor = new PaymentEditor( this, true );
        paymentEditor.setVisible( true );
    }//GEN-LAST:event_miPaymentsActionPerformed

    private void miTypeDeviceActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miTypeDeviceActionPerformed
    {//GEN-HEADEREND:event_miTypeDeviceActionPerformed
        TypeDeviceEditor typeDeviceEditor = new TypeDeviceEditor( this, true );
        typeDeviceEditor.setVisible( true );
    }//GEN-LAST:event_miTypeDeviceActionPerformed

    private void miOsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miOsActionPerformed
    {//GEN-HEADEREND:event_miOsActionPerformed
        OsEditor osEditor = new OsEditor( this, true );
        osEditor.setVisible( true );
    }//GEN-LAST:event_miOsActionPerformed

    private void miFinancesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miFinancesActionPerformed
        FinancesEditor financesEditor = new FinancesEditor( this, true );
        financesEditor.setVisible( true );
    }//GEN-LAST:event_miFinancesActionPerformed

    private void miReportUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miReportUserActionPerformed
        FilterValue value = new FilterValue( this, false );
        value.setLocationRelativeTo( this );
        value.setVisible( true );
    }//GEN-LAST:event_miReportUserActionPerformed

    public static void main( String args[] )
    {
        try
        {
            for ( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() )
            {
                if ( "Windows".equals( info.getName() ) )
                {
                    javax.swing.UIManager.setLookAndFeel( info.getClassName() );
                    break;
                }
            }
        }
        
        catch ( ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex )
        {
            java.util.logging.Logger.getLogger( LauncherApplicationView.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }

        java.awt.EventQueue.invokeLater( new Runnable()
        {
            @Override
            public void run()
            {
                new LauncherApplicationView().setVisible( true );
            }
        } );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jmMenu;
    private javax.swing.JLabel lbInfoUser;
    private javax.swing.JMenuItem miBackup;
    private javax.swing.JMenuItem miClient;
    private javax.swing.JMenuItem miDevice;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miFinances;
    private javax.swing.JMenuItem miLogout;
    private javax.swing.JMenuItem miOs;
    private javax.swing.JMenuItem miPayments;
    private javax.swing.JMenuItem miReportClient;
    private javax.swing.JMenuItem miReportService;
    private javax.swing.JMenuItem miReportUser;
    private javax.swing.JMenuItem miReportUsers;
    private javax.swing.JMenuItem miService;
    private javax.swing.JMenuItem miTypeDevice;
    private javax.swing.JMenuItem miUser;
    private javax.swing.JMenu mnAbout;
    private javax.swing.JMenu mnConfiguration;
    private javax.swing.JMenu mnRegister;
    private javax.swing.JMenu mnReport;
    // End of variables declaration//GEN-END:variables
}
