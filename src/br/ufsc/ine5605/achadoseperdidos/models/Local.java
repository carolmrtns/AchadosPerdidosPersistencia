/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.models;

import java.io.Serializable;

/**
 *
 * @author Caroline Martins Alves
 */
public class Local implements Serializable{
    
    private String nomeLocal;
    private String localizacao;
    
    public Local(String nomeLocal, String localizacao){
        this.nomeLocal = nomeLocal;
        this.localizacao = localizacao;
    }
    
    public String getNomeLocal(){
        return this.nomeLocal;
    }
    
    public String getLocalizacao(){
        return this.localizacao;
    }
    
    public void setNomeLocal(String nomeLocal){
        this.nomeLocal = nomeLocal;
    }
    
    public void setLocalizacao(String localizacao){
        this.localizacao = localizacao;
    }
    
    
}
