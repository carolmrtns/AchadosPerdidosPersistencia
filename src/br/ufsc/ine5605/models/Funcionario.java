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
public class Funcionario extends Pessoa implements Identificavel{
    
    private int siape;
    
    public Funcionario(String nomePessoa, long telefonePessoa, TipoPessoa tipoPessoa, int siape) {
        super(nomePessoa, telefonePessoa, tipoPessoa);
        this.siape = siape;
    }

    public int getSiape() {
        return this.siape;
    }

    public void setSiape(int siape) {
        this.siape = siape;
    }

    @Override
    public Integer getId() {
        return siape;
    }

    @Override
    public void setId(Integer id) {
        this.siape = id;
    }
    
    
}
