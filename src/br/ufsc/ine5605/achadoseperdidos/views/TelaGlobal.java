/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.views;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author caroline
 */
public abstract class TelaGlobal extends JFrame{
    
    public TelaGlobal(String nomeJanela){
        super(nomeJanela);
    }
    
    public void exibirMensagem(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem);
    }
}
