/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author caroline
 */
public class GerenciadorBotoes implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent ae){
        JOptionPane.showMessageDialog(null, "Botão Pressionado: "+
                ae.getActionCommand(), "Titulo", 2);
        ControladorPrincipal.getInstancia().mostrarTelas(ae.getActionCommand());
    }
    
}
