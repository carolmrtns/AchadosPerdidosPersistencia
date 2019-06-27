/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.exceptions;

import javax.swing.JOptionPane;

/**
 *
 * @author henrique
 */
public class PessoaExistenteException extends Exception{
    public PessoaExistenteException(String message){
        JOptionPane.showMessageDialog(null, message);
    }
}
