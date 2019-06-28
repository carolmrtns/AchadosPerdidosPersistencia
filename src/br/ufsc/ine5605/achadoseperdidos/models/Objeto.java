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
public class Objeto implements Identificavel, Serializable{
    
    private static int proxCodigo = 1;
    
    private int codigo;
    private String descricao; 
    private TipoStatus status;
    private TipoObjeto tipoObjeto;
    private Local local;
    private Pessoa cadastrador;
    private Pessoa dono;
    
    public Objeto(int codigo, String descricao, TipoStatus status, TipoObjeto tipoObjeto,
            Local local, Pessoa cadastrador){
        this.codigo = codigo;
        this.descricao = descricao;
        this.status = status;
        this.tipoObjeto = tipoObjeto;
        this.local = local;
        this.cadastrador = cadastrador;
    }
    
    public static int getProxCodigo(){
        return proxCodigo++;
    }
    
    public int getCodigo(){
        return this.codigo;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public TipoStatus getStatus(){
        return this.status;
    }
    
    public TipoObjeto getTipoObjeto(){
        return this.tipoObjeto;
    }
    
    public Local getLocal(){
        return this.local;
    }
    
    public Pessoa getCadastrador(){
        return this.cadastrador;
    }
    
    public Pessoa getDono(){
        return this.dono;
    }
    
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public void setStatus(TipoStatus Status){
        this.status = Status;
    }

    public void setTipoObjeto(TipoObjeto tipoObjeto){
        this.tipoObjeto = tipoObjeto;
    }    

    public void setLocal(Local local){
        this.local = local;
    }
    
    public void setCadastrador(Pessoa cadastrador){
        this.cadastrador = cadastrador;
    }
    
    public void setDono(Pessoa dono){
        this.dono = dono;
    }    
    
    @Override
    public Integer getId(){
        return this.codigo;
    }
    
    @Override 
    public void setId(Integer id){};
    
}
