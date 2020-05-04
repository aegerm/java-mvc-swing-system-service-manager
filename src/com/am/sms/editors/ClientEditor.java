package com.am.sms.editors;

import com.am.sms.model.data.Client;
import com.am.sms.model.db.transaction.ClientManagerTransaction;
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
public class ClientEditor 
    extends 
        javax.swing.JDialog
{
    public ClientEditor( java.awt.Frame parent, boolean modal )
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
        FormatUtil.formatCNPJ( tfCnpj );
        FormatUtil.formatPhone( tfCellPhone );
        FormatUtil.formatFix( tfPhone );
    }
    
    private void defineColor()
    {
        this.getContentPane().setBackground( new Color( 255, 255, 255 ) );
        spClient.setBackground( new Color( 162, 171, 179 ) );
        spClient.setForeground( new Color( 162, 171, 179 ) );
        tfName.requestFocus();
    }
    
    private void addClient()
    {
        if( tfName.getText().length() != 0 && tfAddress.getText().length() != 0 && tfCity.getText().length() != 0 &&
            cbUf.getSelectedIndex() != 0 && tfMail.getText().length() != 0 && FormatUtil.removeFormat( tfCellPhone.getText() ).length() != 0 &&
            ValidateUtil.validateCPF( FormatUtil.removeFormat( tfCpf.getText() ) ) )
        {
            Client client = new Client();
            
            client.setName( tfName.getText() );
            client.setAddress( tfAddress.getText() );
            client.setCity( tfCity.getText() );
            client.setUf( cbUf.getSelectedItem().toString() );
            client.setEmail( tfMail.getText() );
            client.setPhone( FormatUtil.removeFormat( tfPhone.getText() ) );
            client.setCellPhone( FormatUtil.removeFormat( tfCellPhone.getText() ) );
            client.setCpf( FormatUtil.removeFormat( tfCpf.getText() ) );
            client.setCnpj( FormatUtil.removeFormat( tfCnpj.getText() ) );
            client.setState( Client.STATE_ACTIVE );
            
            try
            {
                com.am.sms.model.ModuleContext
                                .getInstance()
                                .getClientManager()
                                .addItem( client );
                
                JOptionPane.showMessageDialog( this, Naming.CRUD_ADD );

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
            JOptionPane.showMessageDialog( this, Naming.CRUD_VALIDATE );
        }
    }
    
    private void editClient()
    {
        if( tbClient.getSelectedRow() != -1 )
        {
            Client client = new Client();
            
            client.setId( (Integer) tbClient.getValueAt( tbClient.getSelectedRow(), 0 ) );
            client.setName( tfName.getText() );
            client.setAddress( tfAddress.getText() );
            client.setCity( tfCity.getText() );
            client.setUf( cbUf.getSelectedItem().toString() );
            client.setEmail( tfMail.getText() );
            client.setPhone( FormatUtil.removeFormat( tfPhone.getText() ) );
            client.setCellPhone( FormatUtil.removeFormat( tfCellPhone.getText() ) );
            client.setCpf( FormatUtil.removeFormat( tfCpf.getText() ) );
            client.setCnpj( FormatUtil.removeFormat( tfCnpj.getText() ) );
            client.setState( Client.STATE_ACTIVE );
            
            try
            {
                com.am.sms.model.ModuleContext
                                .getInstance()
                                .getClientManager()
                                .updateItem( client );
                
                JOptionPane.showMessageDialog( this, Naming.CRUD_UPDATE );
                
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
            JOptionPane.showMessageDialog( this,  Naming.CRUD_SELECTED );
        }
    }
    
    private void deleteClient()
    {
        if( tbClient.getSelectedRow() != -1 )
        {
            int confirm = JOptionPane.showConfirmDialog( this, Naming.CUSTOM_DELETE );
            
            if( confirm == JOptionPane.YES_OPTION )
            {
                Client client = new Client();
                client.setId( (Integer) tbClient.getValueAt( tbClient.getSelectedRow(), 0 ) );
                client.setState( Client.STATE_INATIVE );
                
                try
                {
                    com.am.sms.model.ModuleContext
                                    .getInstance()
                                    .getClientManager()
                                    .deleteItem( client );
                    
                    JOptionPane.showMessageDialog( this, Naming.CRUD_DELETE );
                    
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
            JOptionPane.showMessageDialog( this, Naming.CRUD_SELECTED );
        }
    }
    
    private void clearInputs()
    {
        tfAddress.setText( "" );
        tfCity.setText( "" );
        tfCpf.setText( "" );
        tfCnpj.setText( "" );
        tfMail.setText( "" );
        tfCellPhone.setText( "" );
        tfId.setText( "" );
        tfName.setText( "" );
        tfPhone.setText( "" );
        cbUf.setSelectedIndex( 0 );
        tfName.requestFocus();
        tfFind.setText( "" );
    }
    
    private void tableRenderer()
    {
        DefaultTableModel model = (DefaultTableModel) tbClient.getModel();
        
        tbClient.setRowSorter( new TableRowSorter(model) );
        tbClient.setDefaultEditor( Object.class, null );
        
        mountTable();
    }
    
    private void mountTable()
    {
        DefaultTableModel model = (DefaultTableModel) tbClient.getModel();
        
        model.setNumRows( 0 );
        
        ClientManagerTransaction transaction = new ClientManagerTransaction();
        
        try
        {
            for( Client c : transaction.readClient() )
            {
                if( c.getState().equals( Client.STATE_ACTIVE ) )
                {
                    model.addRow( new Object[] 
                    {
                        c.getId(),
                        c.getName(),
                        c.getAddress(),
                        c.getCity(),
                        c.getUf(),
                        c.getEmail(),
                        c.getPhone(),
                        c.getCellPhone(),
                        c.getCpf(),
                        c.getCnpj(),
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
    
    private void findClients( String find )
    {
        DefaultTableModel model = (DefaultTableModel) tbClient.getModel();
        
        model.setNumRows( 0 );
        
        ClientManagerTransaction transaction = new ClientManagerTransaction();
        
        try
        {
            for( Client c : transaction.search( find ) )
            {
                if( c.getState().equals( Client.STATE_ACTIVE ) )
                {
                    model.addRow( new Object[] 
                    {
                        c.getId(),
                        c.getName(),
                        c.getAddress(),
                        c.getCity(),
                        c.getUf(),
                        c.getEmail(),
                        c.getPhone(),
                        c.getCellPhone(),
                        c.getCpf(),
                        c.getCnpj(),
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
        if( tbClient.getSelectedRow() != -1 )
        {
            tfId.setText( tbClient.getValueAt( tbClient.getSelectedRow(), 0 ).toString() );
            tfName.setText( tbClient.getValueAt( tbClient.getSelectedRow(), 1 ).toString() );
            tfAddress.setText( tbClient.getValueAt( tbClient.getSelectedRow(), 2 ).toString() );
            tfCity.setText( tbClient.getValueAt( tbClient.getSelectedRow(), 3 ).toString() );
            cbUf.setSelectedItem( tbClient.getValueAt( tbClient.getSelectedRow(), 4 ).toString() );
            tfMail.setText( tbClient.getValueAt( tbClient.getSelectedRow(), 5 ).toString() );
            tfPhone.setText( tbClient.getValueAt( tbClient.getSelectedRow(), 6 ).toString() );
            tfCellPhone.setText( tbClient.getValueAt( tbClient.getSelectedRow(), 7 ).toString() );
            tfCpf.setText( tbClient.getValueAt( tbClient.getSelectedRow(), 8 ).toString() );
            tfCnpj.setText( tbClient.getValueAt( tbClient.getSelectedRow(), 9 ).toString() );
            
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
        lbName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfAddress = new javax.swing.JTextField();
        lbAddress = new javax.swing.JLabel();
        lbCity = new javax.swing.JLabel();
        tfCity = new javax.swing.JTextField();
        tfMail = new javax.swing.JTextField();
        lbmail = new javax.swing.JLabel();
        lbPhone = new javax.swing.JLabel();
        lbCellPhone = new javax.swing.JLabel();
        lbCpf = new javax.swing.JLabel();
        lbCnpj = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbClient = new javax.swing.JTable();
        lbUf = new javax.swing.JLabel();
        cbUf = new javax.swing.JComboBox<>();
        spClient = new javax.swing.JSeparator();
        btAdd = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        btExit = new javax.swing.JButton();
        tfCpf = new javax.swing.JFormattedTextField();
        tfCnpj = new javax.swing.JFormattedTextField();
        tfPhone = new javax.swing.JFormattedTextField();
        tfCellPhone = new javax.swing.JFormattedTextField();
        lbInfo = new javax.swing.JLabel();
        tfFind = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editor Clientes");

        lbId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbId.setText("Id");

        tfId.setEditable(false);
        tfId.setBackground(new java.awt.Color(255, 255, 255));
        tfId.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbName.setText("Nome (*)");

        tfName.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        tfAddress.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbAddress.setText("Endereço (*)");

        lbCity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbCity.setText("Cidade (*)");

        tfCity.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        tfMail.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbmail.setText("E-mail (*)");

        lbPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPhone.setText("Telefone");

        lbCellPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbCellPhone.setText("Celular (*)");

        lbCpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbCpf.setText("Cpf (*)");

        lbCnpj.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbCnpj.setText("Cnpj");

        tbClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Endereço", "Cidade", "Estado", "E-mail", "Telefone", "Celular", "CPF", "CNPJ", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbClient.setGridColor(new java.awt.Color(255, 255, 255));
        tbClient.setOpaque(false);
        tbClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbClientMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbClient);

        lbUf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbUf.setText("Estado (*)");

        cbUf.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        btAdd.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_add.png"))); // NOI18N
        btAdd.setText("Adicionar");
        btAdd.setToolTipText("Cadastrar Cliente");
        btAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        btEdit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_edit.png"))); // NOI18N
        btEdit.setText("Editar");
        btEdit.setToolTipText("Editar Cliente");
        btEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });

        btDelete.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_delete.png"))); // NOI18N
        btDelete.setText("Excluir");
        btDelete.setToolTipText("Deletar Cliente");
        btDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        btExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExitActionPerformed(evt);
            }
        });

        tfCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        tfCpf.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        tfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        tfCnpj.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        tfPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        tfPhone.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        tfCellPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        tfCellPhone.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbInfo.setText("Os campos com (*) são obrigatórios");

        tfFind.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Pesquisar");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/am/sms/res/icons/ic_search.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(spClient)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbInfo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfFind)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCity)
                                    .addComponent(lbmail))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfCity, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbCnpj)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lbUf)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(1, 1, 1)
                                                        .addComponent(lbPhone))
                                                    .addComponent(lbCpf))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(tfCpf)
                                                    .addComponent(tfCnpj)
                                                    .addComponent(tfPhone)
                                                    .addComponent(cbUf, 0, 250, Short.MAX_VALUE)))))
                                    .addComponent(tfMail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lbAddress)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(lbName)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lbCellPhone)
                                    .addGap(18, 18, 18)
                                    .addComponent(tfCellPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 107, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbId)
                            .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbName)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbAddress)
                            .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCity)
                            .addComponent(tfCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbUf)
                            .addComponent(cbUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbPhone)
                            .addComponent(tfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCnpj)
                            .addComponent(tfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbmail)
                    .addComponent(lbCpf)
                    .addComponent(tfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCellPhone)
                    .addComponent(tfCellPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spClient, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btAddActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btAddActionPerformed
    {//GEN-HEADEREND:event_btAddActionPerformed
        addClient();
    }//GEN-LAST:event_btAddActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btEditActionPerformed
    {//GEN-HEADEREND:event_btEditActionPerformed
        editClient();
    }//GEN-LAST:event_btEditActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btDeleteActionPerformed
    {//GEN-HEADEREND:event_btDeleteActionPerformed
        deleteClient();
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btExitActionPerformed
    {//GEN-HEADEREND:event_btExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btExitActionPerformed

    private void tbClientMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tbClientMouseClicked
    {//GEN-HEADEREND:event_tbClientMouseClicked
        selectedItem();
    }//GEN-LAST:event_tbClientMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        findClients( tfFind.getText() );
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
            java.util.logging.Logger.getLogger(ClientEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ClientEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ClientEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ClientEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                ClientEditor dialog = new ClientEditor(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<Object> cbUf;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAddress;
    private javax.swing.JLabel lbCellPhone;
    private javax.swing.JLabel lbCity;
    private javax.swing.JLabel lbCnpj;
    private javax.swing.JLabel lbCpf;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbInfo;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPhone;
    private javax.swing.JLabel lbUf;
    private javax.swing.JLabel lbmail;
    private javax.swing.JSeparator spClient;
    private javax.swing.JTable tbClient;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JFormattedTextField tfCellPhone;
    private javax.swing.JTextField tfCity;
    private javax.swing.JFormattedTextField tfCnpj;
    private javax.swing.JFormattedTextField tfCpf;
    private javax.swing.JTextField tfFind;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfMail;
    private javax.swing.JTextField tfName;
    private javax.swing.JFormattedTextField tfPhone;
    // End of variables declaration//GEN-END:variables
}
