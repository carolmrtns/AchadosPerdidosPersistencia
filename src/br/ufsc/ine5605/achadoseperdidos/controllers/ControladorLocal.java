/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.controllers;

import br.ufsc.ine5605.achadoseperdidos.exceptions.LocalJaCadastradoException;
import br.ufsc.ine5605.achadoseperdidos.exceptions.LocalNaoExisteException;
import br.ufsc.ine5605.achadoseperdidos.exceptions.ObjetoComLocalException;
import br.ufsc.ine5605.achadoseperdidos.exceptions.ValoresNulosException;
import br.ufsc.ine5605.achadoseperdidos.models.Local;
import br.ufsc.ine5605.achadoseperdidos.persistencia.LocalDAO;
import br.ufsc.ine5605.achadoseperdidos.views.TelaLocal;

/**
 *
 * @author Caroline Martins Alves
 */
public class ControladorLocal {

    private TelaLocal telaLocal;
    private static ControladorLocal instancia;

    private ControladorLocal() {
        telaLocal = new TelaLocal();
    }

    public void inicia() {
        telaLocal.mostrarTelaLocal();
        telaLocal.mostrarTelas();
    }

    public static ControladorLocal getInstancia() {
        if (instancia == null) {
            instancia = new ControladorLocal();
        }
        return instancia;
    }

    public void cadastrarLocal(String nomeLocal, String localizacao) throws LocalJaCadastradoException, ValoresNulosException {
        //Verifica se os valores a serem cadastrados nao sao nulos
        if (!nomeLocal.equals("") && !localizacao.equals("")) {
            //Verifica se o local a ser cadastrado ja nao existe
            if (encontrarLocalPeloNome(nomeLocal) == null) {
                Local novoLocal = new Local(nomeLocal, localizacao);
                LocalDAO.getInstancia().put(novoLocal);
                telaLocal.exibirMensagem("Local cadastrado com sucesso!");
            } else {
                throw new LocalJaCadastradoException("Local ja existe");
            }
        } else {
            throw new ValoresNulosException("Valores nao podem ser nulos!");
        }
    }

    public void excluirLocal(String nomeLocal) throws ObjetoComLocalException, LocalNaoExisteException {
        //Verifica se o local a ser excluido existe
        if (encontrarLocalPeloNome(nomeLocal) != null) {
            Local local = encontrarLocalPeloNome(nomeLocal);
            //Verifica se o local a ser excluido nao eh usado por nenhum objeto
            if (!ControladorPrincipal.getInstancia().verificarUsoLocal(local)) {
                LocalDAO.getInstancia().removeLocal(local);
                telaLocal.exibirMensagem("Local removido com sucesso!");
            } else {
                throw new ObjetoComLocalException("Local nao pode ser removido, existem objetos vinculados a ele!");
            }
        } else {
            throw new LocalNaoExisteException("Local nao existe");
        }
    }

    public Local encontrarLocalPeloNome(String nomeLocal) {
        for (Local locaisLista : LocalDAO.getInstancia().getList()) {
            if (locaisLista.getNomeLocal().equalsIgnoreCase(nomeLocal)) {
                return locaisLista;
            }
        }
        return null;
    }

    public String encontrarNomeDoLocal(Local local) {
        return local.getNomeLocal();
    }

    public void atualizarDadosLocal(Local local, String nomeLocal, String novaLocalizacao) 
            throws ObjetoComLocalException, ValoresNulosException, 
            LocalJaCadastradoException, LocalNaoExisteException {
        //Verifica se nenhum dos valores é nulo
        if (!nomeLocal.equals("") && !novaLocalizacao.equals("")) {
            //Verifica se o local a se alterar existe
            if (local != null) {
                //Verifica se o local a se alterar nao eh usado por nenhum objeto
                if (!ControladorPrincipal.getInstancia().verificarUsoLocal(local)) {
                    //Verifica se o novo nome de local nao existe
                    if (encontrarLocalPeloNome(nomeLocal) == null) {
                        LocalDAO.getInstancia().removeLocal(local);
                        local.setNomeLocal(nomeLocal);
                        local.setLocalizacao(novaLocalizacao);
                        LocalDAO.getInstancia().put(local);
                        telaLocal.exibirMensagem("Local alterado com sucesso!");
                    } else {
                        //Verifica se a localizacao nova eh diferente da atual
                        if (!local.getLocalizacao().equals(novaLocalizacao)) {
                            LocalDAO.getInstancia().alterarLocalizacao(local, novaLocalizacao);
                            telaLocal.exibirMensagem("Somente localizacao alterada, local permanece inalterado!");
                        } else {
                            /*
                            Se nao for significa que estava tentando alterar o nome
                            Entao nada eh alterado, pois ele jah existe
                            */
                            throw new LocalJaCadastradoException("Nome do local nao alterado, pois local já existe!");
                        }
                    }
                } else {
                    throw new ObjetoComLocalException("Local nao pode ser alterado, existem objetos vinculados a ele!");
                }
            } else {
                throw new LocalNaoExisteException("Local nao existe");
            }
        } else {
            throw new ValoresNulosException("Valores nao podem ser nulos!");
        }
    }

}
