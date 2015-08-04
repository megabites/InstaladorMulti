/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package instaladormultimagem;


import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;



/**
 *
 * @author Leonardo Bites
 */
public class  InstaladorWorker extends SwingWorker<Integer, String> {

    private final List<String> commands = new ArrayList<>();
    private int status;
    private static final Logger log = Logger.getLogger(InstaladorWorker.class.getName());
    String[] saida = new String[10];  
    int posicao;  
    //private final ArrayList<String> commands = new ArrayList();

    public InstaladorWorker(String comando) {
        commands.clear();
        commands.add("/bin/bash");
        commands.add("-c");
        commands.add(comando);
    }
    
  
    @Override
    protected Integer doInBackground() throws Exception {
        
        try {
                
                ProcessBuilder pb = new ProcessBuilder(commands);
                pb.redirectErrorStream(true);
                Process p = pb.start();
                String s;
                BufferedReader stdout = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
                while ((s = stdout.readLine()) != null && !isCancelled()) {
                    publish(s);
                }
                if (!isCancelled()) {
                    status = p.waitFor();
                }
                p.getInputStream().close();
                p.getOutputStream().close();
                p.getErrorStream().close();
                p.destroy();
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace(System.err);
            }
            return status;
        


    }
    
    @Override
    protected void process(java.util.List<String> messages) {
            //FrmInstalacao.lbTipoEqpto.setText(FrmInstalacao.lbTipoEqpto.getText()+ " - " +(this.getState()).toString());
            for (String message : messages) {
               FrmInstalacao.textArea.append(message + "\n");
            }
        }

    @Override
    protected void done() {
            //FrmInstalacao.lbTipoEqpto.setText(FrmInstalacao.lbTipoEqpto.getText()+ " - " +(this.getState()).toString() + " " + status);
            FrmInstalacao.BarraProgresso.setIndeterminate(false);
            
            
            
    }
    
    
    
//    public void adicionar(String result) {  
//          
//        for (int i = 0; i < this.saida.length; i++) {  
//            if (this.saida[i] != null) {  
//                this.posicao += 1;  
//            }  
//        }  
//        this.saida[posicao] = result;  
//    }  
//    
//    private void secureClose(final Closeable resource) {
//        try {
//            if (resource != null) {
//                resource.close();
//            }
//        } catch (IOException ex) {
//            log.severe("Erro = " + ex.getMessage());
//        }
//    }

    }
    
    


