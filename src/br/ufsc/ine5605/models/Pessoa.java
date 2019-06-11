/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.models;

import java.io.Serializable;

/**
 *
 * @author Henrique Meireles
 */
public abstract class Pessoa implements Identificavel, Serializable{
    
    private String nomePessoa;
    private long telefonePessoa;
    private TipoPessoa tipoPessoa;
    
    public Pessoa(String nomePessoa, long telefonePessoa, TipoPessoa tipoPessoa){
        this.nomePessoa = nomePessoa;
        this.telefonePessoa = telefonePessoa;
        this.tipoPessoa = tipoPessoa;
    }
    
    public String getNomePessoa(){
        return this.nomePessoa;
    }
    
    public long getTelefonePessoa(){
        return this.telefonePessoa;
    }
    
    public TipoPessoa getTipoPessoa(){
        return this.tipoPessoa;
    }
    
    public void setNomePessoa(String nomePessoa){
        this.nomePessoa = nomePessoa;
    }
    
    public void setTelefonePessoa(long telefonePessoa){
        this.telefonePessoa = telefonePessoa;
    }
    
    public void setTipoPessoa(TipoPessoa tipoPessoa){
        this.tipoPessoa = tipoPessoa;
    }
    
/*
    public abstract Integer getId();
    public abstract void setId(Integer id);    
*/
    
}
