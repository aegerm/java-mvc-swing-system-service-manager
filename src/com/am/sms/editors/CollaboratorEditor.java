package com.am.sms.editors;

import com.am.sms.model.data.Collaborator;
import com.am.sms.model.data.EncryptionMD5;
import com.am.sms.model.db.transaction.CollaboratorManagerTransaction;
import com.am.sms.util.ApplicationUtilities;
import com.am.sms.util.FormatUtil;
import com.am.sms.util.Naming;
import com.am.sms.util.ValidateUtil;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Alexandre Marques
 */
public class CollaboratorEditor 
    extends 
        javax.swing.JDialog
{
    public CollaboratorEditor( java.awt.Frame parent, boolean modal )
    {
        super( parent, modal );
        
        initComponents();
        
        defineColor();
        
        tableRenderer();
        
        formatInit();
    }
    
    private void formatInit()
    {
        FormatUtil.formatCPF( tfCpf );
        FormatUtil.formatPhone( tfCellPhone );
    }
    
    private void defineColor()
    {
        this.getContentPane().setBackground( new Color( 255, 255, 255 ) );
        spCollaborator.setBackground( new Color( 162, 171, 179 ) );
        spCollaborator.setForeground( new Color( 162, 171, 179 ) );
    }
    
    private void addCollaborator()
    {
        if( tfName.getText().length() != 0 && tfAddress.getText().length() != 0 && tfCity.getText().length() != 0 &&
            tfMail.getText().length() != 0 && FormatUtil.removeFormat( tfCellPhone.getText() ).length() != 0 && tfFunction.getText().length() != 0 &&
            cbUf.getSelectedIndex() != 0 && FormatUtil.removeFormat( tfCpf.getText() ).length() != 0 && tfLogin.getText().length() != 0 && pwPassword.getText().length() != 0 )
        {
            if( ValidateUtil.validateCPF( FormatUtil.removeFormat( tfCpf.getText() ) ) )
            {
                Collaborator collaborator = new Collaborator();
            
                collaborator.setName( tfName.getText() );
                collaborator.setAddress( tfAddress.getText() );
                collaborator.setCity( tfCity.getText() );
                collaborator.setEmail( tfMail.getText() );
                collaborator.setCellPhone( FormatUtil.removeFormat( tfCellPhone.getText() ) );
                collaborator.setFunction( tfFunction.getText() );
                collaborator.setLogin( tfLogin.getText() );
                collaborator.setPassword( EncryptionMD5.crypt( pwPassword.getText() ) );
                collaborator.setCpf( FormatUtil.removeFormat( tfCpf.getText() ) );
                collaborator.setUf( cbUf.getSelectedItem().toString() );
                collaborator.setState( Collaborator.STATE_ACTIVE );

                try
                {
                    com.am.sms.model.ModuleContext
                                    .getInstance()
                                    .getCollaboratorManager()
                                    .addItem( collaborator );

                    JOptionPane.showMessageDialog( this, Naming.CRUD_ADD, Naming.ATTENTION, 1 );

                    mountTable();

                    clearInputs();
                }

                catch( Exception e )
                {
                    ApplicationUtilities.getException( this, e );
                }
            }
            
            else
            {
                JOptionPane.showMessageDialog( this, Naming.CPF_INVALID, Naming.ATTENTION, 0 );
                tfCpf.setText( "" );
                tfCpf.requestFocus();
            }
            
        }
        
        else
        {
            JOptionPane.showMessageDialog( this, Naming.CRUD_VALIDATE, Naming.ATTENTION, 1 );
        }
    }
    
    private void editCollaborator()
    {
        if( tbCollaborator.getSelectedRow() != -1 )
        {
            if( ValidateUtil.validateCPF( FormatUtil.removeFormat( tfCpf.getText() ) ) )
            {
                if( pwPassword.getText().length() != 0 )
                {
                    Collaborator collaborator = new Collaborator();
            
                    collaborator.setId( (Integer) tbCollaborator.getValueAt( tbCollaborator.getSelectedRow(), 0 ) );
                    collaborator.setName( tfName.getText() );
                    collaborator.setAddress( tfAddress.getText() );
                    collaborator.setCity( tfCity.getText() );
                    collaborator.setEmail( tfMail.getText() );
                    collaborator.setCellPhone( FormatUtil.removeFormat( tfCellPhone.getText() ) );
                    collaborator.setFunction( tfFunction.getText() );
                    collaborator.setLogin( tfLogin.getText() );
                    collaborator.setPassword( EncryptionMD5.crypt( pwPassword.getText() ) );
                    collaborator.setCpf( FormatUtil.removeFormat( tfCpf.getText() ) );
                    collaborator.setUf( cbUf.getSelectedItem().toString() );
                    collaborator.setState( Collaborator.STATE_ACTIVE );

                    try
                    {
                        com.am.sms.model.ModuleContext
                                        .getInstance()
                                        .getCollaboratorManager()
                                        .updateItem( collaborator );

                        JOptionPane.showMessageDialog( this, Naming.CRUD_UPDATE, Naming.ATTENTION, 1 );

                        mountTable();

                        btAdd.setEnabled( true );

                        btEdit.setText( "Editar" );
                        btEdit.setIcon( new javax.swing.ImageIcon( getClass().getResource("/com/am/sms/res/icons/ic_edit.png") ) );

                        clearInputs();
                    }

                    catch( Exception e )
                    {
                        ApplicationUtilities.getException( this, e );
                    }
                }
                
                else
                {
                    JOptionPane.showMessageDialog( this, Naming.PASSWORD_CONF, Naming.ATTENTION, 1 );
                    pwPassword.requestFocus();
                }
            }
            
            else
            {
                JOptionPane.showMessageDialog( this, Naming.CPF_INVALID, Naming.ATTENTION, 0 );
                tfCpf.setText( "" );
                tfCpf.requestFocus();
            }
        }
        
        else
        {
            JOptionPane.showMessageDialog( this, Naming.CRUD_SELECTED, Naming.ATTENTION, 1 );
        }
    }
    
    private void deleteCollaborator()
    {
        if( tbCollaborator.getSelectedRow() != -1 )
        {
            int confirm = JOptionPane.showConfirmDialog( this, Naming.CUSTOM_DELETE, Naming.CONFIRM, 2 );
            
            if( confirm == JOptionPane.YES_OPTION )
            {
                Collaborator collaborator = new Collaborator();
                
                collaborator.setId( (Integer) tbCollaborator.getValueAt( tbCollaborator.getSelectedRow(), 0 ) );
                collaborator.setState( Collaborator.STATE_INATIVE );
                
                try
                {
                    com.am.sms.model.ModuleContext
                                    .getInstance()
                                    .getCollaboratorManager()
                                    .deleteItem( collaborator );
                    
                    JOptionPane.showMessageDialog( this, Naming.CRUD_DELETE, Naming.ATTENTION, 1 );
                    
                    mountTable();
                    
                    btAdd.setEnabled( true );
                    
                    btEdit.setText( "Editar" );
                    btEdit.setIcon( new javax.swing.ImageIcon( getClass().getResource("/com/am/sms/res/icons/ic_edit.png") ) );
                    
                    clearInputs();
                }
                
                catch( Exception e )
                {
                    ApplicationUtilities.getException( this, e );
                }
            }
        }
        
        else
        {
            JOptionPane.showMessageDialog( this, Naming.CRUD_SELECTED, Naming.ATTENTION, 1 );
        }
    }
    
    private void clearInputs()
    {
        tfAddress.setText( "" );
        tfCity.setText( "" );
        tfCpf.setText( "" );
        tfMail.setText( "" );
        tfFunction.setText( "" );
        tfId.setText( "" );
        tfName.setText( "" );
        tfCellPhone.setText( "" );
        tfFind.setText( "" );
        tfLogin.setText( "" );
        pwPassword.setText( "" );
        cbUf.setSelectedIndex( 0 );
        tfName.requestFocus();
    }
    
    private void tableRenderer()
    {
        DefaultTableModel model = (DefaultTableModel) tbCollaborator.getModel();
        
        tbCollaborator.setRowSorter( new TableRowSorter(model) );
        tbCollaborator.setDefaultEditor( Object.class, null );
        
        mountTable();
    }
    
    private void mountTable()
    {
        DefaultTableModel model = (DefaultTableModel) tbCollaborator.getModel();
        
        model.setNumRows( 0 );
        
        CollaboratorManagerTransaction transaction = new CollaboratorManagerTransaction();
        
        try
        {
            for( Collaborator c : transaction.readCollaborators() )
            {
                if( ! c.getState().equals( Collaborator.STATE_INATIVE ) )
                {
                    model.addRow( new Object[] 
                    {
                        c.getId(),
                        c.getName(),
                        c.getAddress(),
                        c.getCity(),
                        c.getUf(),
                        c.getEmail(),
                        c.getCellPhone(),
                        c.getFunction(),
                        c.getCpf(),
                        c.getLogin(),
                        c.getState()
                    } );
                }
            }
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.getException( this, e );
        }
    }
    
    private void searchCollaborators( String find )
    {
        DefaultTableModel model = (DefaultTableModel) tbCollaborator.getModel();
        
        model.setNumRows( 0 );
        
        CollaboratorManagerTransaction transaction = new CollaboratorManagerTransaction();
        
        try
        {
            for( Collaborator c : transaction.search( find ) )
            {
                if( ! c.getState().equals( Collaborator.STATE_INATIVE ) )
                {
                    model.addRow( new Object[] 
                    {
                        c.getId(),
                        c.getName(),
                        c.getAddress(),
                        c.getCity(),
                        c.getUf(),
                        c.getEmail(),
                        c.getCellPhone(),
                        c.getFunction(),
                        c.getCpf(),
                        c.getLogin(),
                        c.getState()
                    } );
                }
            }
        }
        
        catch( Exception e )
        {
            ApplicationUtilities.getException( this, e );
        }
    }
    
    private void selectedItem()
    {
        if( tbCollaborator.getSelectedRow() != -1 )
        {
            tfId.setText( tbCollaborator.getValueAt( tbCollaborator.getSelectedRow(), 0 ).toString() );
            tfName.setText( tbCollaborator.getValueAt( tbCollaborator.getSelectedRow(), 1 ).toString() );
            tfAddress.setText( tbCollaborator.getValueAt( tbCollaborator.getSelectedRow(), 2 ).toString() );
            tfCity.setText( tbCollaborator.getValueAt( tbCollaborator.getSelectedRow(), 3 ).toString() );
            cbUf.setSelectedItem( tbCollaborator.getValueAt( tbCollaborator.getSelectedRow(), 4 ).toString() );
            tfMail.setText( tbCollaborator.getValueAt( tbCollaborator.getSelectedRow(), 5 ).toString() );
            tfCellPhone.setText( tbCollaborator.getValueAt( tbCollaborator.getSelectedRow(), 6 ).toString() );
            tfFunction.setText( tbCollaborator.getValueAt( tbCollaborator.getSelectedRow(), 7 ).toString() );
            tfCpf.setText( tbCollaborator.getValueAt( tbCollaborator.getSelectedRow(), 8 ).toString() );
            tfLogin.setText( tbCollaborator.getValueAt( tbCollaborator.getSelectedRow(), 9 ).toString() );
            
            btAdd.setEnabled( false );
            
            btEdit.setText( "Salvar" );
            btEdit.setIcon( new javax.swing.ImageIcon( getClass().getResource("/com/am/sms/res/icons/ic_save.png") ) );
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbId = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        btExit = new javax.swing.JButton();
        lbName = new javax.swing.JLabel();
        lbCity = new javax.swing.JLabel();
        btEdit = new javax.swing.JButton();
        lbCellPhone = new javax.swing.JLabel();
        lbLogin = new javax.swing.JLabel();
        lbUf = new javax.swing.JLabel();
        spCollaborator = new javax.swing.JSeparator();
        tfMail = new javax.swing.JTextField();
        btDelete = new javax.swing.JButton();
        btAdd = new javax.swing.JButton();
        tfFunction = new javax.swing.JTextField();
        lbFunction = new javax.swing.JLabel();
        lbAddress = new javax.swing.JLabel();
        lbMail = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCollaborator = new javax.swing.JTable();
        cbUf = new javax.swing.JComboBox<>();
        tfCity = new javax.swing.JTextField();
        lbCpf = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfAddress = new javax.swing.JTextField();
        tfCpf = new javax.swing.JFormattedTextField();
        tfCellPhone = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        tfFind = new javax.swing.JTextField();
        lbFind = new javax.swing.JLabel();
        btFind = new javax.swing.JButton();
        tfLogin = new javax.swing.JTextField();
        lbPassword = new javax.swing.JLabel();
        pwPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editor Colaboradores");

        lbId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbId.setText("ID");

        tfId.setEditable(false);
        tfId.setBackground(new java.awt.Color(255, 255, 255));
        tfId.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        btExit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_exit.png"))); // NOI18N
        btExit.setText("Sair");
        btExit.setToolTipText("Fechar Editor");
        btExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExitActionPerformed(evt);
            }
        });

        lbName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbName.setText("Nome (*)");

        lbCity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbCity.setText("Cidade (*)");

        btEdit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_edit.png"))); // NOI18N
        btEdit.setText("Editar");
        btEdit.setToolTipText("Editar Colaborador");
        btEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });

        lbCellPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbCellPhone.setText("Celular (*)");

        lbLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbLogin.setText("Login (*)");

        lbUf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbUf.setText("Estado (*)");

        tfMail.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        btDelete.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_delete.png"))); // NOI18N
        btDelete.setText("Excluir");
        btDelete.setToolTipText("Deletar Colaborador");
        btDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        btAdd.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_add.png"))); // NOI18N
        btAdd.setText("Adicionar");
        btAdd.setToolTipText("Cadastrar Colaborador");
        btAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        tfFunction.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbFunction.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbFunction.setText("Função (*)");

        lbAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbAddress.setText("Endereço (*)");

        lbMail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbMail.setText("E-mail (*)");

        tbCollaborator.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Endereço", "Cidade", "Estado", "E-mail", "Celular", "Função", "CPF", "Login", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCollaborator.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCollaboratorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbCollaborator);

        cbUf.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        tfCity.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbCpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbCpf.setText("CPF (*)");

        tfName.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        tfAddress.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        tfCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        tfCpf.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        tfCellPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        tfCellPhone.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Os campos com (*) são obrigatórios");

        tfFind.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbFind.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbFind.setText("Pesquisar");

        btFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_search.png"))); // NOI18N
        btFind.setContentAreaFilled(false);
        btFind.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFindActionPerformed(evt);
            }
        });

        tfLogin.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPassword.setText("Senha(*)");

        pwPassword.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFind)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btExit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbAddress)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbCellPhone)
                                            .addComponent(lbUf)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(tfMail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbCity)
                                                .addGap(18, 18, 18)
                                                .addComponent(tfCity, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(tfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbCpf)
                                            .addComponent(lbFunction)
                                            .addComponent(lbPassword)))
                                    .addComponent(lbName)
                                    .addComponent(lbId)
                                    .addComponent(lbLogin)
                                    .addComponent(lbMail))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbUf, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfCellPhone)
                                    .addComponent(tfCpf)
                                    .addComponent(tfFunction, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pwPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 112, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tfFind)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btFind, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spCollaborator))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbName)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUf)
                    .addComponent(cbUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAddress)
                    .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCellPhone)
                    .addComponent(tfCellPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCity)
                    .addComponent(tfCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCpf)
                    .addComponent(tfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbMail)
                        .addComponent(tfMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbFunction)
                        .addComponent(tfFunction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLogin)
                    .addComponent(tfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPassword)
                    .addComponent(pwPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbFind)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFind, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spCollaborator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        addCollaborator();
    }//GEN-LAST:event_btAddActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btEditActionPerformed
    {//GEN-HEADEREND:event_btEditActionPerformed
        editCollaborator();
    }//GEN-LAST:event_btEditActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btDeleteActionPerformed
    {//GEN-HEADEREND:event_btDeleteActionPerformed
        deleteCollaborator();
    }//GEN-LAST:event_btDeleteActionPerformed

    private void tbCollaboratorMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tbCollaboratorMouseClicked
    {//GEN-HEADEREND:event_tbCollaboratorMouseClicked
        selectedItem();
    }//GEN-LAST:event_tbCollaboratorMouseClicked

    private void btExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btExitActionPerformed
    {//GEN-HEADEREND:event_btExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btExitActionPerformed

    private void btFindActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btFindActionPerformed
    {//GEN-HEADEREND:event_btFindActionPerformed
        searchCollaborators( tfFind.getText() );
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
            java.util.logging.Logger.getLogger(CollaboratorEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(CollaboratorEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(CollaboratorEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(CollaboratorEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                CollaboratorEditor dialog = new CollaboratorEditor(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<Object> cbUf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAddress;
    private javax.swing.JLabel lbCellPhone;
    private javax.swing.JLabel lbCity;
    private javax.swing.JLabel lbCpf;
    private javax.swing.JLabel lbFind;
    private javax.swing.JLabel lbFunction;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbMail;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbUf;
    private javax.swing.JPasswordField pwPassword;
    private javax.swing.JSeparator spCollaborator;
    private javax.swing.JTable tbCollaborator;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JFormattedTextField tfCellPhone;
    private javax.swing.JTextField tfCity;
    private javax.swing.JFormattedTextField tfCpf;
    private javax.swing.JTextField tfFind;
    private javax.swing.JTextField tfFunction;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfLogin;
    private javax.swing.JTextField tfMail;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables
}
