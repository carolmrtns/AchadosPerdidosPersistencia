/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.models;

/**
 *
 * @author Henrique Meireles
 */
public class Visitante extends Pessoa{
    
    private int cpf;
    
    public Visitante(String nomePessoa, long telefonePessoa, TipoPessoa tipoPessoa, int cpf) {
        super(nomePessoa, telefonePessoa, tipoPessoa);
        this.cpf = cpf;
    }

    public long getCpf() {
        return this.cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
    
    //TEM QUE IMPLEMENTAR ESSES DOIS METODOS AQUI PARA O VISITANTE
    public Integer getId(){
        return this.cpf;
    }
    
    public void setId(Integer id){
        this.cpf = id;
    }
        
    
}
