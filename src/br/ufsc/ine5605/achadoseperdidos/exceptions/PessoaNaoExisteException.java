/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.exceptions;

/**
 *
 * @author caroline
 */
public class PessoaNaoExisteException extends Exception{
    
    public PessoaNaoExisteException(String mensagem){
        super(mensagem);
    }
    
}
