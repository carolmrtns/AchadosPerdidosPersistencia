/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos;

import br.ufsc.ine5605.achadoseperdidos.controllers.ControladorPrincipal;

/**
 *
 * @author caroline
 */
public class AchadosEPerdidos {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        //ControladorPrincipal controladorPrincipal = new ControladorPrincipal();
        ControladorPrincipal.getInstancia().inicia();
        
    }
}
