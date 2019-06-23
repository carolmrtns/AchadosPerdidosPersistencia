/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.controllers;

import br.ufsc.ine5605.achadoseperdidos.models.Local;
import br.ufsc.ine5605.achadoseperdidos.models.Pessoa;
import br.ufsc.ine5605.achadoseperdidos.views.TelaPrincipal;

/**
 *
 * @author Caroline Martins Alves
 */
public class ControladorPrincipal {
    
    private ControladorLocal controladorLocal;
    private ControladorObjeto controladorObjeto;
    private ControladorPessoa controladorPessoa;
    private TelaPrincipal telaPrincipal;
    private static ControladorPrincipal instancia;
    
    private ControladorPrincipal(){
        //this.controladorLocal = new ControladorLocal(this);
        this.controladorLocal = ControladorLocal.getInstancia();
        this.controladorObjeto = ControladorObjeto.getInstancia();
        this.controladorPessoa = ControladorPessoa.getInstancia();
        this.telaPrincipal = new TelaPrincipal();
    }
    
    public static ControladorPrincipal getInstancia(){
        if(instancia == null){
            instancia = new ControladorPrincipal();
        }
        return instancia;
    }
    
    public void inicia(){
        telaPrincipal.menuInicial();
    }
    
    public void listarTelaPessoa(){
        controladorPessoa.inicia();
    }
    
    public void listarTelaObjeto(){
        controladorObjeto.inicia();
    }
    
    public void listarTelaLocal(){
        controladorLocal.inicia();
    }
    
    public Pessoa retornarPessoaPeloNome(String nomePessoa){
        return controladorPessoa.encontrarPessoaPeloNome(nomePessoa);
    }
    
    public Local retornarLocalPeloNome(String nomeLocal){
        return controladorLocal.encontrarLocalPeloNome(nomeLocal);
    }
    
    public String retornarNomePessoa(Pessoa pessoa){
        return controladorPessoa.encontrarNomePessoa(pessoa);
    }
    
    public String retornarNomeLocal(Local local){
        return controladorLocal.encontrarNomeDoLocal(local);
    }
    
    public void mostrarTelas(String opcao){
        switch(opcao){
            case "1": listarTelaPessoa();
                break;
            case "2": listarTelaLocal();
                break;
            case "3": listarTelaObjeto();
                break;
        }
    }
}
