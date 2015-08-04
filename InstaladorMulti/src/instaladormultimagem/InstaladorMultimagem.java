/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package instaladormultimagem;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author p532717
 */
public class InstaladorMultimagem {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        Properties prop = PropertiesUtil.getProp();
        
        String property = prop.getProperty("tipo");
        
        Tela tela = null;
               
        switch(property) {
            case "thinclient":  tela = new ThinClient();
            break;
            case "atm": tela = new ATM();    
            break;
                
             
        }
        
       tela.setUndecorated(true);
       tela.setLocationRelativeTo(null);
       tela.centralizarComponente();
       tela.criaTela();
       tela.setVisible(true);
        
    }
    
    
    
}
