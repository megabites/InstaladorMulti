package instaladormultimagem;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author p532717
 */
public abstract class Tela extends javax.swing.JFrame{
    
    public abstract void criaTela();
    
    JLabel lbLogo = new JLabel();
    
    public void parteComum(){
        
        //lbLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo_azul_caixa.jpg"))); // NOI18N
        //getContentPane().add(lbLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 50));
    }
 
    
    public void centralizarComponente() {  
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();  
        Dimension dw = getSize();  
        setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);  
    } 
    
    
     
}
