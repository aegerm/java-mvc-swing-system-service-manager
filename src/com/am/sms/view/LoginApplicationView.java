package com.am.sms.view;

import com.am.sms.controller.LoginController;
import com.am.sms.model.data.Collaborator;
import com.am.sms.util.Naming;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandre Marques
 */
public class LoginApplicationView 
    extends 
        javax.swing.JDialog
{
    private LoginController controller = LoginController.getInstance();
    
    public LoginApplicationView( java.awt.Frame parent, boolean modal )
    {
        super( parent, modal );
        
        initComponents();
        
        layoutinit();
    }
    
    private void layoutinit()
    {
        this.getContentPane().setBackground( new Color( 162, 171, 179 ) );
        
        tfUser.requestFocus();
    }
    
    private void validateLogin()
    {
        Collaborator login = controller.access( tfUser.getText(), tfPassword.getText() );
        
        if( login == null )
        {
            JOptionPane.showMessageDialog( this, Naming.VALIDATE_USER, Naming.ATTENTION, JOptionPane.INFORMATION_MESSAGE );
            
            clearInputs();
        }
        
        else
        {
            LauncherApplicationView view = new LauncherApplicationView();
            
            view.setVisible( true );
            
            this.dispose();
        }
    }
    
    private void clearInputs()
    {
        tfUser.setText( "" );
        tfPassword.setText( "" );
        tfUser.requestFocus();
    }

    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbUserLogin = new javax.swing.JLabel();
        tfUser = new javax.swing.JTextField();
        lbPasswordLogin = new javax.swing.JLabel();
        tfPassword = new javax.swing.JPasswordField();
        btLogin = new javax.swing.JButton();
        btExitLogin = new javax.swing.JButton();
        lbIconLogin = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setPreferredSize(new java.awt.Dimension(600, 300));

        lbUserLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbUserLogin.setText("Usuário");

        tfUser.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tfUser.setToolTipText("Nome do Usuário");
        tfUser.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        lbPasswordLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPasswordLogin.setText("Senha");

        tfPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfPassword.setToolTipText("Senha do Usuário");

        btLogin.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btLogin.setText("Login");
        btLogin.setToolTipText("");
        btLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });

        btExitLogin.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btExitLogin.setText("Sair");
        btExitLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExitLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExitLoginActionPerformed(evt);
            }
        });

        lbIconLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIconLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_login_1.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("System Manager Service - Version 1.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbUserLogin)
                            .addComponent(tfUser)
                            .addComponent(lbPasswordLogin)
                            .addComponent(tfPassword)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addComponent(btExitLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                        .addComponent(lbIconLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbIconLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbUserLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfUser, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbPasswordLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExitLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btLoginActionPerformed
    {//GEN-HEADEREND:event_btLoginActionPerformed
        validateLogin();
    }//GEN-LAST:event_btLoginActionPerformed

    private void btExitLoginActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btExitLoginActionPerformed
    {//GEN-HEADEREND:event_btExitLoginActionPerformed
        System.exit( 0 );
    }//GEN-LAST:event_btExitLoginActionPerformed

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
        
        catch ( ClassNotFoundException ex )
        {
            java.util.logging.Logger.getLogger( LoginApplicationView.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } 
        
        catch ( InstantiationException ex )
        {
            java.util.logging.Logger.getLogger( LoginApplicationView.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } 
        
        catch ( IllegalAccessException ex )
        {
            java.util.logging.Logger.getLogger( LoginApplicationView.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } 
        
        catch ( javax.swing.UnsupportedLookAndFeelException ex )
        {
            java.util.logging.Logger.getLogger( LoginApplicationView.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater( new Runnable()
        {
            @Override
            public void run()
            {
                LoginApplicationView dialog = new LoginApplicationView( new javax.swing.JFrame(), true );
                dialog.addWindowListener( new java.awt.event.WindowAdapter()
                {
                    @Override
                    public void windowClosing( java.awt.event.WindowEvent e )
                    {
                        System.exit(0);
                    }
                } );
                
                dialog.setVisible( true );
            }
        } );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExitLogin;
    private javax.swing.JButton btLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbIconLogin;
    private javax.swing.JLabel lbPasswordLogin;
    private javax.swing.JLabel lbUserLogin;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JTextField tfUser;
    // End of variables declaration//GEN-END:variables
}
