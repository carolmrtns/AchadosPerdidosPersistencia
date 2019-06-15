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
    Scanner teclado;
    
    public TelaGlobal(String nomeJanela){
        super(nomeJanela);
    }
    
    public void exibirMensagem(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem);
    }
    
    public String recebeValorString(){
        return teclado.nextLine();
    }
    
    public int recebeValorInteiro(){
        int valor = -1;
        do {
            try {
                valor = teclado.nextInt();
            } catch (Exception e) {
                System.out.println("Valor invalido! Digite um numero inteiro");
                valor = -1;
            } finally {
                teclado.nextLine();
            }
        } while (valor == -1);
        return valor;
    }    
    
    public long recebeValorLong(){
        long valor = -1;
        do {
            try {
                valor = teclado.nextLong();
            } catch (Exception e) {
                System.out.println("Valor invalido! Digite um valor valido");
                valor = -1;
            } finally {
                teclado.nextLine();
            }
        } while (valor == -1);
        return valor;
    }
}
