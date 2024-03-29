package br.webverissimo.cadastro.cadastroTelas;

import br.webverissimo.cadastro.model.DAO.CargoDAO;
import br.webverissimo.cadastro.model.DAO.DepartamentoDAO;
import br.webverissimo.cadastro.model.DAO.ModelExtends;
import br.webverissimo.cadastro.model.DTO.DepartamentoDTO;
import br.webverissimo.cadastro.model.MODEL.SuperModel;
import br.webverissimo.cadastro.utils.Utilitario;
import br.webverissimo.cadastro.utils.Validacao;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Departamentos extends javax.swing.JFrame {
// public class Departamentos extends JDialog {

    /**
     * Creates new form Departamentos
     */
    
    public Departamentos() {
        initComponents();
         inicializa();
    }

    // caixa de dialogo modal...
    /*
    public Departamentos(Frame frame, boolean modal) {
        super(frame,modal);
        initComponents();
         inicializa();
    }
*/
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPBusca = new javax.swing.JPanel();
        jLCodigoBusca = new javax.swing.JLabel();
        jTCodigoBusca = new javax.swing.JTextField();
        jBusca = new javax.swing.JButton();
        jPMensagem = new javax.swing.JPanel();
        jLMensagem = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLCodigo = new javax.swing.JLabel();
        jLDescricao = new javax.swing.JLabel();
        jTCodigo = new javax.swing.JTextField();
        jTDescricao = new javax.swing.JTextField();
        jBInserir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDepartamentos = new javax.swing.JTable();
        jBAlterar = new javax.swing.JButton();
        jBExcluir = new javax.swing.JButton();
        jBNovo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciamento de Departamentos");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPBusca.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        jLCodigoBusca.setText("Código.:");

        jBusca.setText("Buscar");
        jBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBuscaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPMensagemLayout = new javax.swing.GroupLayout(jPMensagem);
        jPMensagem.setLayout(jPMensagemLayout);
        jPMensagemLayout.setHorizontalGroup(
            jPMensagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPMensagemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPMensagemLayout.setVerticalGroup(
            jPMensagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPMensagemLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLMensagem))
        );

        javax.swing.GroupLayout jPBuscaLayout = new javax.swing.GroupLayout(jPBusca);
        jPBusca.setLayout(jPBuscaLayout);
        jPBuscaLayout.setHorizontalGroup(
            jPBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPBuscaLayout.createSequentialGroup()
                        .addComponent(jLCodigoBusca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTCodigoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBusca)
                        .addGap(0, 217, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPBuscaLayout.setVerticalGroup(
            jPBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBuscaLayout.createSequentialGroup()
                .addGroup(jPBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCodigoBusca)
                    .addComponent(jTCodigoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBusca))
                .addGap(18, 18, 18)
                .addComponent(jPMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Conteúdo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        jLCodigo.setText("Codigo");

        jLDescricao.setText("Descrição");

        jBInserir.setText("INSERIR");
        jBInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBInserirActionPerformed(evt);
            }
        });

        jTableDepartamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableDepartamentos);

        jBAlterar.setText("ALTERAR");
        jBAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAlterarActionPerformed(evt);
            }
        });

        jBExcluir.setText("EXCLUIR");
        jBExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExcluirActionPerformed(evt);
            }
        });

        jBNovo.setText("NOVO");
        jBNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNovoActionPerformed(evt);
            }
        });

        jButton1.setText("Relatório");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLCodigo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jBInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jBAlterar)
                            .addGap(18, 18, 18)
                            .addComponent(jBExcluir)
                            .addGap(18, 18, 18)
                            .addComponent(jBNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLDescricao)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLCodigo, jLDescricao});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBAlterar, jBExcluir, jBInserir});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBInserir)
                    .addComponent(jBAlterar)
                    .addComponent(jBExcluir)
                    .addComponent(jBNovo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jPBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 224;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 0, 27);
        getContentPane().add(jPanel2, gridBagConstraints);

        jMenu1.setText("Opções");

        jMenuItemSair.setText("Sair");
        jMenuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSairActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSair);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-537)/2, (screenSize.height-501)/2, 537, 501);
    }// </editor-fold>//GEN-END:initComponents
// ----------------------------------------------------------------------------------------------    
    // Metodo para fechar a janela...
    private void jMenuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSairActionPerformed
        dispose();
    }//GEN-LAST:event_jMenuItemSairActionPerformed

// ----------------------------------------------------------------------------------------------    
    // Interface para inserir novo registro
    private void jBInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBInserirActionPerformed
        jLMensagem.setText("");
        DepartamentoDAO departamento = new DepartamentoDAO();
        if (jTDescricao.getText().length() < 3) {
            jLMensagem.setText("O campo DESCRIÇÂO deve ter mais que 3 caracteres.");
        } else {
            try {
                departamento.gravar(preencherDepartamentoDTO());
                jLMensagem.setText("Registro GRAVADO com sucesso!");
                inicializa();
            } catch (SQLException ex) {
                Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //inicializa();
    }//GEN-LAST:event_jBInserirActionPerformed

// ----------------------------------------------------------------------------------------------    
    // interface para alteracao de registro.
    private void jBAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAlterarActionPerformed
         jLMensagem.setText("");
    DepartamentoDAO departamento = new DepartamentoDAO();
    if (jTDescricao.getText().length() < 3) {
        jLMensagem.setText("O campo DESCRIÇÂO deve ter mais que 3 caracteres.");
        JOptionPane.showMessageDialog(this, jLMensagem);
    } else {
        try {
            departamento.gravar(preencherDepartamentoDTO());
            jLMensagem.setText("Registro GRAVADO com sucesso!");
            inicializa();
        } catch (SQLException ex) {
            Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // inicializa();
    }//GEN-LAST:event_jBAlterarActionPerformed

// ----------------------------------------------------------------------------------------------    
// interface para exclusao de registro.

    private void jBExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExcluirActionPerformed
       jLMensagem.setText("");
    DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    if (Utilitario.isEmpty(jTCodigoBusca.getText())) {
        jLMensagem.setText("É Preciso selecionar um registro!");
    } else {
        try {
            departamentoDAO.excluir(preencherDepartamentoDTO());
            jLMensagem.setText("Registro excluido com sucesso!");
            inicializa();
        } catch (SQLException ex) {
            jLMensagem.setText("Não foi possivel excluir o registro!" + ex.getSQLState());
            Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // inicializa();
    }//GEN-LAST:event_jBExcluirActionPerformed

    private void jBNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNovoActionPerformed
        inicializa();
    }//GEN-LAST:event_jBNovoActionPerformed

// ----------------------------------------------------------------------------------------------    
    // interface para busca de registro...
    private void jBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBuscaActionPerformed
        try {
            if (!Validacao.isNaN(jTCodigoBusca.getText())) {
                jLMensagem.setText("O campo CÓDIGO deve ser um número.");
                return;
            }
            DepartamentoDTO departamento = new DepartamentoDTO();
            DepartamentoDAO departamentoDAO = new DepartamentoDAO();
            departamento = departamentoDAO.selecionar(Integer.parseInt(jTCodigoBusca.getText()));

            if (departamento == null) {
                jLMensagem.setText("Registro não encontrado !!!!");
                inicializa();
            } else {
                jTCodigo.setText(String.valueOf(departamento.getId()));
                jTDescricao.setText(departamento.getDescricao());
                jTDescricao.requestFocus();
                jLMensagem.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBuscaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            relatorio();
        } catch (SQLException ex) {
            Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Departamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Departamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Departamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Departamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Departamentos().setVisible(true); // chamada normal
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAlterar;
    private javax.swing.JButton jBExcluir;
    private javax.swing.JButton jBInserir;
    private javax.swing.JButton jBNovo;
    private javax.swing.JButton jBusca;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLCodigo;
    private javax.swing.JLabel jLCodigoBusca;
    private javax.swing.JLabel jLDescricao;
    private javax.swing.JLabel jLMensagem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemSair;
    private javax.swing.JPanel jPBusca;
    private javax.swing.JPanel jPMensagem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTCodigo;
    private javax.swing.JTextField jTCodigoBusca;
    private javax.swing.JTextField jTDescricao;
    private javax.swing.JTable jTableDepartamentos;
    // End of variables declaration//GEN-END:variables

// --------------------------------------------------------------------------------------
// Lê os campos do formulario e passa para o DTO.
    private DepartamentoDTO preencherDepartamentoDTO() {
        DepartamentoDTO departamento = new DepartamentoDTO();
        String codigo = jTCodigo.getText();
        String descricao = jTDescricao.getText();
        if (jTCodigo.getText().trim().length() == 0) {
            codigo = "0";
        }
        departamento.setId(Integer.parseInt(codigo));
        departamento.setDescricao(descricao);
        return departamento;
    }

// --------------------------------------------------------------------------------------
// Limpa o formulario e mostra registro gravados na tabela...
    private void inicializa() {
        try {
            jTCodigoBusca.setText("");
            jTCodigo.setText("0");
            jTCodigo.setEnabled(false);
            jTDescricao.setText("");
            jTDescricao.requestFocus();
            mostrarDados();
        } catch (Exception ex) {
            Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
    }

// --------------------------------------------------------------------------------------
    // mostra os dados na tabela do formulario...
    private void mostrarDados() {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        DepartamentoDTO departamentoDTO = null;
        DefaultTableModel model = new DefaultTableModel();
        jTableDepartamentos.addRowSelectionInterval(0, 1);
        jTableDepartamentos.setModel(model);
        model.addColumn("Codigo");
        model.addColumn("Descrição");
        try {
            Iterator lista = departamentoDAO.list("").iterator();  // metodo departamentoDAO.list pode receber parametro where - condicao
            int coluna = 0;
            while (lista.hasNext()) {
                departamentoDTO = (DepartamentoDTO) lista.next();
                model.insertRow(coluna, new Object[]{departamentoDTO.getId(), departamentoDTO.getDescricao()});
                coluna++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
    }

// ======================================    
/**
 * RELATORIO DEPARTAMENTO...
 * @throws SQLException
 * @throws FileNotFoundException 
 */    
private void relatorio() throws SQLException, FileNotFoundException{
     DepartamentoDAO cargos = new DepartamentoDAO();        
     String titulo = "Relatório de Departamentos:";
     String arquivo = "C:/relatorio/departamentos.pdf";
     String campos[]= {"id","descricao"};
     String rotulos[] = {"ID","DESCRIÇÃO"};
     
     SuperModel model = new SuperModel();
     ResultSet lista = model.list("departamentos",campos,"");
     
     ModelExtends pdf = new ModelExtends();
     pdf.exportarPDF(titulo,arquivo,model.DevolveLista(campos,lista),rotulos, campos);
}    
    
}