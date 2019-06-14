/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.controllers;
import br.ufsc.ine5605.achadoseperdidos.models.Local;
import br.ufsc.ine5605.achadoseperdidos.views.TelaLocal;
import java.util.ArrayList;
/**
 *
 * @author Caroline Martins Alves
 */
public class ControladorLocal {
    private ArrayList<Local> locais;
    private TelaLocal telaLocal;
    //private ControladorPrincipal controladorPrincipal;
    private static ControladorLocal instancia;

    public ControladorLocal(){
        locais = new ArrayList<Local>();
        telaLocal = new TelaLocal();
        
        //this.controladorPrincipal = controladorPrincipal;
        Local local1 = new Local("CED", "Setor A");
        Local local2 = new Local("CTC", "Setor B");
        Local local3 = new Local("CFH", "Setor C");
        Local local4 = new Local("CFM", "Setor D");
        Local local5 = new Local("CCE", "Setor E");
        Local local6 = new Local("CCS", "Setor F");
        
        locais.add(local1);
        locais.add(local2);
        locais.add(local3);
        locais.add(local4);
        locais.add(local5);
        locais.add(local6);
        
    }
    
    public void inicia(){
        telaLocal.menuInicial();
    } 
    
    public static ControladorLocal getInstancia(){
        if(instancia == null){
            instancia = new ControladorLocal();
        }
        return instancia;
    }
    
    public void cadastrarLocal(String nomeLocal, String localizacao){
        if(!nomeLocal.equals("") && !localizacao.equals("")){
            if(encontrarLocalPeloNome(nomeLocal) == null){
                Local novoLocal = new Local(nomeLocal, localizacao);
                locais.add(novoLocal);
                telaLocal.exibirMensagem("Local cadastrado com sucesso!");
            }else{
                telaLocal.exibirMensagem("Local ja existe!");
            }
        }
    }
    
    public void excluirLocal(String nomeLocal){
        if(encontrarLocalPeloNome(nomeLocal) != null){
            locais.remove(encontrarLocalPeloNome(nomeLocal));
            telaLocal.exibirMensagem("Local removido com sucesso!");
        }else{
            telaLocal.exibirMensagem("Local nao existe");
        }
    }
    
    public Local encontrarLocalPeloNome(String nomeLocal){
        for(Local locaisLista: locais){
            if(locaisLista.getNomeLocal().equalsIgnoreCase(nomeLocal)){
                return locaisLista;
            }
        }
        return null;
    }
    
    public String encontrarNomeDoLocal(Local local){
        return local.getNomeLocal();
    }
    
    public void listarLocais(){
        telaLocal.exibirMensagem("---------LISTANDO LOCAIS---------");
        for(Local locaisLista: locais){
            telaLocal.exibirMensagem("Nome: "+locaisLista.getNomeLocal());
            telaLocal.exibirMensagem("Localizacao: "+locaisLista.getLocalizacao());
            telaLocal.exibirMensagem("---------------------------------");
        }
    }
    
    public void atualizarDadosLocal(String nomeLocal, String novoNomeLocal, String novaLocalizacao){
        if(!nomeLocal.equals("") && !novoNomeLocal.equals("") && !novaLocalizacao.equals("")){
            Local local = encontrarLocalPeloNome(nomeLocal);
            if(local != null){
                if(encontrarLocalPeloNome(novoNomeLocal) == null){
                    local.setNomeLocal(novoNomeLocal);
                    local.setLocalizacao(novaLocalizacao);
                    telaLocal.exibirMensagem("Local cadastrado com sucesso!");
                }else{
                    telaLocal.exibirMensagem("Novo local ja existe!");
                }
            }else{
                telaLocal.exibirMensagem("Local nao existe!");
            }
        }else{
            telaLocal.exibirMensagem("Valores nao podem ser nulos!");
        }
    }

}