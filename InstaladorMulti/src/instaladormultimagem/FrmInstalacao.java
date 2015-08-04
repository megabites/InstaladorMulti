/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package instaladormultimagem;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import static java.lang.Thread.sleep;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author p532717
 */
public class FrmInstalacao extends Tela{
     
    EquipamentoDTO equipamento;
    private String _etapa = null ;
     
    /**
     * Creates new form FrmInstalacao
     * @param eqpto
     */
    public FrmInstalacao(EquipamentoDTO eqpto) {
        //initComponents();
        this.equipamento = eqpto;
    }
    
    private void DadosDisco() throws Exception{
        
        
        
        ExecutaComandos("/usr/bin/lshw -C disk -xml >disco.xml", "Obtendo informações do disco");
        _etapa = "DadosDisco";
        sleep(2000);
        //Array para guardar dados do disco 
        String[] disco = new String[2];
        
        //informando o arquivo XML
        File arquivo = new File("disco.xml");
        
        //Criando a classe SAXBuilder que vai processar o XML
        SAXBuilder sb = new SAXBuilder();
        
        //Este documento agora possui a estrutura do XML 
        Document d = sb.build(arquivo);
        
        //Recuperando o elemento root
        Element mural = d.getRootElement();
        
        //Recuperando os elementos Filhos
        List elementos = mural.getChildren();
        Iterator i = elementos.iterator();
       
        //lendo os dados 
        while (i.hasNext()){
            Element element = (Element) i.next();
            if (! element.getAttribute("id").toString().contains("cdrom")){
                if (element.getChildText("description").contains("ATA Disk")){
                    equipamento.setDiscoLogico(element.getChildText("logicalname"));
                    Long tamanhodisco = Long.parseLong(element.getChildText("size"));
                    tamanhodisco = tamanhodisco / 1024;
                    tamanhodisco = tamanhodisco / 1024;
                    equipamento.setTamanhoDisco(tamanhodisco.toString());
                }
            }
        }
        
        
    }
    
    private void LimpaDisco() throws Exception {
        ExecutaComandos("/bin/dd if=/dev/zero of="+equipamento.getDiscoLogico()+" bs=512 count=1 &>/dev/null","Limpando dados do disco");
        _etapa = "LimpaDisco";
    }
    
    private void CriaTabelaParticoes() throws Exception {
        ExecutaComandos("/sbin/parted --script "+equipamento.getDiscoLogico()+"  mklabel msdos &>/dev/null","Criando a tabela de partições");
        _etapa = "CriaTabelaParticoes";
    }
    
    private void ParticionaDisco() throws Exception {
        ExecutaComandos("/sbin/parted --script "+equipamento.getDiscoLogico()+" mkpart primary 0 "+equipamento.getTamanhoDisco(),"Criando partições");
         _etapa = "ParticionaDisco";
    }
    
    private void ParticaoBotavel() throws Exception {
        ExecutaComandos("/sbin/parted --script "+equipamento.getDiscoLogico()+" set 1 boot on","Tornando Partição botável");
         _etapa = "ParticaoBotavel";
    }
    
    private void FormataDisco() throws Exception {
        ExecutaComandos("/sbin/mkfs.ext3 "+equipamento.getDiscoLogico()+"1", "Particionando e formatando o disco");
        _etapa = "FormataDisco";
        
    }
    
    private void CriaDiretorio() throws Exception {
        ExecutaComandos("/bin/mkdir /mnt/sistema","Criando Diretório");
        _etapa = "CriaDir";
    }
    
    private void MontaDisco() throws Exception {
        ExecutaComandos("/bin/mount "+equipamento.getDiscoLogico()+"1 /mnt/sistema", "Montando Disco");
        _etapa = "MontaDisco";
    }
    
    private void ExtraiSistemaBase() throws Exception {
        ExecutaComandos("/bin/tar -zxvpf imagem.tar.gz -C /mnt/sistema", "Extraindo sistema base");
        _etapa = "ExtraiSistema";
    }
    
    private void AplicaConfiguracoes() throws Exception {
        File hostname = new File("/mnt/sistema/etc/hostname");
        if (hostname.exists()){
            hostname.delete();
        }
        
        //Criação do arquivo 
        hostname.createNewFile();
        FileWriter hostnamefw = new FileWriter(hostname,true);
        BufferedWriter hostnamebw = new BufferedWriter(hostnamefw);
        
        //escrevendo as linhas no arquivo
        hostnamebw.write(equipamento.getNome());
        hostnamebw.newLine();
        hostnamebw.close();
        hostnamefw.close();
        
        File ocs = new File("/mnt/sistema/etc/ocsinventory/ocsinventory-agent.cfg");
        if (ocs.exists()){
            ocs.delete();
        }
        
        //criando o arquivo do OCS (inventário) 
        ocs.createNewFile();
        FileWriter ocsfw = new FileWriter(ocs,true);
        BufferedWriter ocsbw = new BufferedWriter(ocsfw);
        
        ocsbw.write("SERVER=inventariolivre.caixa ");
        ocsbw.newLine();
        
        if (equipamento.getNome().contains("TO")){
            //executa configuração totem
            ocsbw.write("TAG=TOTEN ");
            ocsbw.newLine();
            
        }
        if (equipamento.getNome().contains("TH")){
            //executa configurações thinclient
            ocsbw.write("TAG=THINCLIENT ");
            ocsbw.newLine();
        }
        
        ocsbw.close();
        ocsfw.close();
        
        ExecutaComandos("/bin/echo Definindo nome do Equipamento", "Aplicando Nome da Máquina");
        _etapa = "aplicaConf";
    }
    
    private void InstalaGrub() throws Exception {
        ExecutaComandos("/usr/sbin/grub-install --root-directory=/mnt/sistema/ --recheck --no-floppy \"(hd0)\"", "Instalando Gerenciador de boot");
        _etapa = "grub";
    }
    
    private void ExecutaComandos(String Comando,String msg){

        final String mensagem = msg;
        
        PropertyChangeListener listenerFormata = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println(_etapa + "; " + evt.getNewValue());
                
                if ("state".equals(evt.getPropertyName())) {

                    SwingWorker.StateValue state = (SwingWorker.StateValue) evt.getNewValue();
                    
                    switch (state) {
                        case DONE:
                            try { 
                                int exitLevel = ((SwingWorker<Integer, ?>)evt.getSource()).get();
                                BarraProgresso.setIndeterminate(false);
                                BarraProgresso.setValue(100);
                                switch(_etapa){
                                    case "DadosDisco":
                                        try {
                                            LimpaDisco();
                                            sleep(3000);
                                        } catch (Exception ex) {
                                            Logger.getLogger(FrmInstalacao.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    break;
                                    case "LimpaDisco":
                                        try {
                                            CriaTabelaParticoes();
                                            sleep(3000);
                                        } catch (Exception ex) {
                                            Logger.getLogger(FrmInstalacao.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    break;
                                    case "CriaTabelaParticoes":
                                        try {
                                            ParticionaDisco();
                                            sleep(3000);
                                        } catch (Exception ex) {
                                            Logger.getLogger(FrmInstalacao.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    break;
                                    case "ParticionaDisco":
                                        try {
                                            ParticaoBotavel();
                                            sleep(3000);
                                        } catch (Exception ex) {
                                            Logger.getLogger(FrmInstalacao.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    break;
                                    case "ParticaoBotavel":
                                        try {
                                            FormataDisco();
                                            sleep(3000);
                                        } catch (Exception ex) {
                                            Logger.getLogger(FrmInstalacao.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    break;
                                    case "FormataDisco":
                                        try {
                                            CriaDiretorio();
                                            sleep(3000);
                                        } catch (Exception ex) {
                                            Logger.getLogger(FrmInstalacao.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    break;
                                    case "CriaDir":
                                        try {
                                            MontaDisco();
                                            sleep(3000);
                                        } catch (Exception ex) {
                                            Logger.getLogger(FrmInstalacao.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    break;
                                    case "MontaDisco":
                                        try {
                                            ExtraiSistemaBase();
                                            sleep(3000);
                                        } catch (Exception ex) {
                                            Logger.getLogger(FrmInstalacao.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    break;
                                    case "ExtraiSistema":
                                        try {
                                            AplicaConfiguracoes();
                                            sleep(3000);
                                        } catch (Exception ex) {
                                            Logger.getLogger(FrmInstalacao.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    break;
                                    case "aplicaConf":
                                        try {
                                            InstalaGrub();
                                            sleep(3000);
                                        } catch (Exception ex) {
                                            Logger.getLogger(FrmInstalacao.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    break;
                                    case "grub":
                                        btFinalizar.setVisible(true);
                                        lbTipoEqpto.setText("Instalação Finalizada");
                                        BarraProgresso.setVisible(false);
                                        jScrollPane1.setVisible(false);
                                        lbFinal.setVisible(true);
                                    break;    
                                    default:
                                        
                                    
                                }
                            } catch (InterruptedException | ExecutionException ex) {
                                JOptionPane.showMessageDialog(BarraProgresso, ex.getMessage());
                            } finally {
                               //JOptionPane.showMessageDialog(null, "Format command completed with exit level of");
                            }
                            break;
                        case STARTED:
                            lbTipoEqpto.setText(mensagem);
                            BarraProgresso.setIndeterminate(true);
                            break;
                        
                    }

                }
            }
        };
        
        
        InstaladorWorker install = new InstaladorWorker(Comando);
        install.addPropertyChangeListener(listenerFormata);
        install.execute();
        
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
        pnlTipoEqpto = new javax.swing.JPanel();
        lbTipoEqpto = new javax.swing.JLabel();
        btFinalizar = new javax.swing.JButton();
        BarraProgresso = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        lbFinal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbTitulo1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTipoEqpto.setOpaque(false);
        pnlTipoEqpto.setLayout(null);

        lbTipoEqpto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTipoEqpto.setForeground(new java.awt.Color(255, 255, 255));
        lbTipoEqpto.setText("Selecione o tipo de equipamento");
        pnlTipoEqpto.add(lbTipoEqpto);
        lbTipoEqpto.setBounds(10, 70, 500, 17);

        btFinalizar.setText("Finalizar");
        btFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizarActionPerformed(evt);
            }
        });
        pnlTipoEqpto.add(btFinalizar);
        btFinalizar.setBounds(340, 400, 107, 39);

        BarraProgresso.setBorder(null);
        pnlTipoEqpto.add(BarraProgresso);
        BarraProgresso.setBounds(10, 110, 760, 30);

        jScrollPane1.setHorizontalScrollBar(null);

        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setOpaque(false);
        jScrollPane1.setViewportView(textArea);

        pnlTipoEqpto.add(jScrollPane1);
        jScrollPane1.setBounds(10, 160, 760, 270);

        lbFinal.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        lbFinal.setForeground(new java.awt.Color(255, 255, 255));
        lbFinal.setText("<html><center> Instalação concluida com sucesso !! <br> pressione finalizar para reiniciar o equipamento. </center></html>");
        pnlTipoEqpto.add(lbFinal);
        lbFinal.setBounds(130, 140, 540, 190);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Executando processo de instalação de imagem");
        pnlTipoEqpto.add(jLabel1);
        jLabel1.setBounds(10, 10, 420, 30);

        getContentPane().add(pnlTipoEqpto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 780, 470));

        lbTitulo1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbTitulo1.setForeground(new java.awt.Color(51, 51, 51));
        lbTitulo1.setText("Sistema de Instalação de Imagens");
        getContentPane().add(lbTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 430, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/cabecalho.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setMaximumSize(new java.awt.Dimension(849, 96));
        jLabel2.setMinimumSize(new java.awt.Dimension(849, 96));
        jLabel2.setPreferredSize(new java.awt.Dimension(849, 95));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 90));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/fundo.png"))); // NOI18N
        jLabel3.setText("jLabel1");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 800, 680));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarActionPerformed
        // TODO add your handling code here:
       ExecutaComandos("/sbin/reboot", "Reiniciando ...");

    }//GEN-LAST:event_btFinalizarActionPerformed

    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JProgressBar BarraProgresso;
    private javax.swing.ButtonGroup GrpTipoEqpto;
    private javax.swing.JButton btFinalizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbFinal;
    public static javax.swing.JLabel lbTipoEqpto;
    private javax.swing.JLabel lbTitulo1;
    private javax.swing.JPanel pnlTipoEqpto;
    public static javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

    @Override
    public void criaTela() {
        
        this.setVisible(true);
        
        initComponents();
        
        jScrollPane1.setBorder(BorderFactory.createTitledBorder("Saída: "));
        btFinalizar.setVisible(false);
        lbFinal.setVisible(false);
        
        new Thread(){
           public void run() {
               try {
                  DadosDisco();
               } catch (Exception ex) {
                  Logger.getLogger(FrmInstalacao.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        }.start();
        
    }
}
