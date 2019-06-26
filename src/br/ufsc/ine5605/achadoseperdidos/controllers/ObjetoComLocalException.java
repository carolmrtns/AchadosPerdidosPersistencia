/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.controllers;

/**
 *
 * @author caroline
 */
public class ObjetoComLocalException extends Exception {

    /*
    public ObjetoComLocalException(){
        this("Local nao pode ser alterado, existem objetos vinculados a ele!");
    }
     */
    public ObjetoComLocalException(String message) {
        super(message);
    }
}
