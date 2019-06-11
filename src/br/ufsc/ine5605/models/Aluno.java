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
public class Aluno extends Pessoa {
    
    private Integer id;
    private int matricula;

    public Aluno(String nomePessoa, long telefonePessoa, TipoPessoa tipoPessoa, int matricula) {
        super(nomePessoa, telefonePessoa, tipoPessoa);
        this.matricula = matricula;        
    }
    
    public int getMatricula(){
        return this.matricula;
    }
    
    public void setMatricula(int matricula){
        this.matricula = matricula;
    }

    @Override
    public Integer getId() {
        return this.matricula;
    }

    @Override
    public void setId(Integer id) {
        this.matricula = id;
    }
 
}
