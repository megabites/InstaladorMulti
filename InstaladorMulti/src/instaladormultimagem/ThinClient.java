/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package instaladormultimagem;

import javax.swing.JOptionPane;

/**
 *
 * @author p532717
 */
public class ThinClient extends Tela{
     
     EquipamentoDTO equipamento = new EquipamentoDTO();
    /**
     * Creates new form FrmInstalacao
     */
    public ThinClient() {
        //initComponents();
        //Inicializa o formulario tipo equipamento 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrpTipoEqpto = new javax.swing.ButtonGroup();
        lbTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnlTipoEqpto = new javax.swing.JPanel();
        lbTipoEqpto = new javax.swing.JLabel();
        rbToten = new javax.swing.JRadioButton();
        rbThinClient = new javax.swing.JRadioButton();
        btTipoEqpto = new javax.swing.JButton();
        txtNomeThinClient = new javax.swing.JFormattedTextField();
        try{
            javax.swing.text.MaskFormatter nomethin = new javax.swing.text.MaskFormatter("UU####T'H2##");
            txtNomeThinClient = new javax.swing.JFormattedTextField(nomethin);  
        }  
        catch (Exception e){  
        }
        txtNomeToten = new javax.swing.JFormattedTextField();
        try{
            javax.swing.text.MaskFormatter nometoten = new javax.swing.text.MaskFormatter("UU####T'O1##");
            txtNomeToten = new javax.swing.JFormattedTextField(nometoten);  
        }  
        catch (Exception e){  
        }
        btNomeEqpto = new javax.swing.JButton();
        btVoltar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbTitulo.setForeground(new java.awt.Color(51, 51, 51));
        lbTitulo.setText("Sistema de Instalação de Imagens");
        getContentPane().add(lbTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 430, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/cabecalho.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setMaximumSize(new java.awt.Dimension(849, 96));
        jLabel2.setMinimumSize(new java.awt.Dimension(849, 96));
        jLabel2.setPreferredSize(new java.awt.Dimension(849, 95));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 90));

        pnlTipoEqpto.setOpaque(false);
        pnlTipoEqpto.setLayout(null);

        lbTipoEqpto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbTipoEqpto.setForeground(new java.awt.Color(255, 255, 255));
        lbTipoEqpto.setText("Selecione o tipo de equipamento");
        pnlTipoEqpto.add(lbTipoEqpto);
        lbTipoEqpto.setBounds(10, 40, 740, 30);

        rbToten.setBackground(new java.awt.Color(0, 0, 153));
        GrpTipoEqpto.add(rbToten);
        rbToten.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        rbToten.setForeground(new java.awt.Color(255, 255, 255));
        rbToten.setText("Toten");
        rbToten.setBorder(null);
        rbToten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTotenActionPerformed(evt);
            }
        });
        pnlTipoEqpto.add(rbToten);
        rbToten.setBounds(310, 180, 160, 27);

        rbThinClient.setBackground(new java.awt.Color(0, 0, 153));
        GrpTipoEqpto.add(rbThinClient);
        rbThinClient.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        rbThinClient.setForeground(new java.awt.Color(255, 255, 255));
        rbThinClient.setSelected(true);
        rbThinClient.setText("ThinClient");
        rbThinClient.setBorder(null);
        rbThinClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbThinClientActionPerformed(evt);
            }
        });
        pnlTipoEqpto.add(rbThinClient);
        rbThinClient.setBounds(310, 140, 170, 27);

        btTipoEqpto.setText("Continuar");
        btTipoEqpto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTipoEqptoActionPerformed(evt);
            }
        });
        pnlTipoEqpto.add(btTipoEqpto);
        btTipoEqpto.setBounds(320, 280, 107, 39);

        txtNomeThinClient.setBorder(null);
        txtNomeThinClient.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNomeThinClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeThinClientActionPerformed(evt);
            }
        });
        pnlTipoEqpto.add(txtNomeThinClient);
        txtNomeThinClient.setBounds(270, 150, 220, 40);

        txtNomeToten.setBorder(null);
        txtNomeToten.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNomeToten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeTotenActionPerformed(evt);
            }
        });
        pnlTipoEqpto.add(txtNomeToten);
        txtNomeToten.setBounds(270, 160, 220, 40);

        btNomeEqpto.setText("Continuar");
        btNomeEqpto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNomeEqptoActionPerformed(evt);
            }
        });
        pnlTipoEqpto.add(btNomeEqpto);
        btNomeEqpto.setBounds(380, 280, 107, 39);

        btVoltar.setText("Voltar");
        btVoltar.setMaximumSize(new java.awt.Dimension(79, 23));
        btVoltar.setMinimumSize(new java.awt.Dimension(79, 23));
        btVoltar.setPreferredSize(new java.awt.Dimension(79, 23));
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });
        pnlTipoEqpto.add(btVoltar);
        btVoltar.setBounds(270, 280, 107, 39);

        getContentPane().add(pnlTipoEqpto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 760, 350));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/fundo.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rbTotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbTotenActionPerformed

    private void rbThinClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbThinClientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbThinClientActionPerformed

    private void btTipoEqptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTipoEqptoActionPerformed
        // TODO add your handling code here:
        FrmNome();
        //JOptionPane.showMessageDialog(null,equipamento.getTipo());
    }//GEN-LAST:event_btTipoEqptoActionPerformed

    
    
    private void btNomeEqptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNomeEqptoActionPerformed
        // TODO add your handling code here:
        if (equipamento.getTipo().contains("ThinClient")){
            if (txtNomeThinClient.getText().trim().length()!= 11) {
                JOptionPane.showMessageDialog(null, "O nome do equipamento não foi preenchido no padrão !");
                txtNomeThinClient.requestFocus();
            }else{
                equipamento.setNome(txtNomeThinClient.getText());
                //JOptionPane.showMessageDialog(null,equipamento.getTipo()+ "-" + equipamento.getNome());
            }
        }else if (equipamento.getTipo().contains("Toten")){
            if (txtNomeToten.getText().trim().length() != 11) {
                JOptionPane.showMessageDialog(null, "O nome do equipamento não foi preenchido no padrão !");
                txtNomeToten.requestFocus();
            }else{
                equipamento.setNome(txtNomeToten.getText());
                //JOptionPane.showMessageDialog(null,equipamento.getTipo()+ "-" + equipamento.getNome());
            }
        }
        
        this.dispose();
        Tela tela = new FrmInstalacao(equipamento);
        tela.setUndecorated(true);
        tela.setLocationRelativeTo(null);
        tela.centralizarComponente();
        tela.criaTela();
        tela.lbLogo.setVisible(true);
        tela.setVisible(true);
       
        
        
        
        //JOptionPane.showMessageDialog(null,equipamento.getTipo()+ "-" + equipamento.getNome());
    }//GEN-LAST:event_btNomeEqptoActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        // TODO add your handling code here:
        FrmTipo();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void txtNomeThinClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeThinClientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeThinClientActionPerformed

    private void txtNomeTotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeTotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeTotenActionPerformed

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
            java.util.logging.Logger.getLogger(ThinClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThinClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThinClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThinClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThinClient().setVisible(true);
            }
        });
    }
    
    private void FrmTipo(){
        //Mostrando Itens do formulário do tipo equipamento
        btVoltar.setVisible(false);
        btTipoEqpto.setVisible(true);
        rbThinClient.setVisible(true);
        rbToten.setVisible(true);
        //Ocultando itens do formulário nome equipamento
        txtNomeThinClient.setVisible(false);
        txtNomeToten.setVisible(false);
        btNomeEqpto.setVisible(false);
    }
    
    private void FrmNome(){
        btTipoEqpto.setVisible(false);
        btNomeEqpto.setVisible(true);
        btVoltar.setVisible(true);
       if (rbThinClient.isSelected()){
          equipamento.setTipo("ThinClient");
          rbThinClient.setVisible(false);
          rbToten.setVisible(false);
          txtNomeThinClient.setVisible(true);
          txtNomeThinClient.requestFocus();
          lbTipoEqpto.setText("Entre com o Nome do Equipamento ex.: (UF1234TH2XX)");
          
       }else if (rbToten.isSelected()){
          equipamento.setTipo("Toten");
          rbThinClient.setVisible(false);
          rbToten.setVisible(false);
          txtNomeToten.setVisible(true);
          txtNomeToten.requestFocus();
          lbTipoEqpto.setText("Entre com o Nome do Equipamento ex.: (UF1234TO1XX)");
       }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrpTipoEqpto;
    private javax.swing.JButton btNomeEqpto;
    private javax.swing.JButton btTipoEqpto;
    private javax.swing.JButton btVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbTipoEqpto;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pnlTipoEqpto;
    private javax.swing.JRadioButton rbThinClient;
    private javax.swing.JRadioButton rbToten;
    private javax.swing.JFormattedTextField txtNomeThinClient;
    private javax.swing.JFormattedTextField txtNomeToten;
    // End of variables declaration//GEN-END:variables

    @Override
    public void criaTela() {
        
        initComponents();
        lbLogo.setVisible(true);
        //setUndecorated(true);
        //setLocationRelativeTo(null);
        //centralizarComponente();
        FrmTipo();
    }
}