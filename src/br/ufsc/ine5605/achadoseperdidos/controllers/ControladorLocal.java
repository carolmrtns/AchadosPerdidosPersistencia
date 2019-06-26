/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.controllers;

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

    public void cadastrarLocal(String nomeLocal, String localizacao) {
        if (!nomeLocal.equals("") && !localizacao.equals("")) {
            if (encontrarLocalPeloNome(nomeLocal) == null) {
                Local novoLocal = new Local(nomeLocal, localizacao);
                LocalDAO.getInstancia().put(novoLocal);
                telaLocal.exibirMensagem("Local cadastrado com sucesso!");
            } else {
                telaLocal.exibirMensagem("Local ja existe!");
            }
        }
    }

    public void excluirLocal(String nomeLocal) throws ObjetoComLocalException{
        if (encontrarLocalPeloNome(nomeLocal) != null) {
            Local local = encontrarLocalPeloNome(nomeLocal);
            if (!ControladorPrincipal.getInstancia().verificarUsoLocal(local)) {
                LocalDAO.getInstancia().removeLocal(local);
                telaLocal.exibirMensagem("Local removido com sucesso!");
            } else {
                throw new ObjetoComLocalException("Local nao pode ser removido, existem objetos vinculados a ele!");
                //telaLocal.exibirMensagem("Local nao pode ser removido, existem objetos vinculados a ele!");
            }
        } else {
            telaLocal.exibirMensagem("Local nao existe");
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

    public void atualizarDadosLocal(Local local, String nomeLocal, String novaLocalizacao) throws ObjetoComLocalException{
        if (!nomeLocal.equals("") && !novaLocalizacao.equals("")) {
            if (local != null) {
                if (!ControladorPrincipal.getInstancia().verificarUsoLocal(local)) {
                    if (encontrarLocalPeloNome(nomeLocal) == null) {
                        LocalDAO.getInstancia().removeLocal(local);
                        local.setNomeLocal(nomeLocal);
                        local.setLocalizacao(novaLocalizacao);
                        LocalDAO.getInstancia().put(local);
                        telaLocal.exibirMensagem("Local alterado com sucesso!");
                    } else {
                        if (!local.getLocalizacao().equals(novaLocalizacao)) {
                            LocalDAO.getInstancia().alterarLocalizacao(local, novaLocalizacao);
                            telaLocal.exibirMensagem("Somente localizacao alterada, pois local já existe!");
                        } else {
                            telaLocal.exibirMensagem("Nome do local nao alterado, pois local já existe!");
                        }
                    }
                } else {
                    //telaLocal.exibirMensagem("Local nao pode ser alterado, existem objetos vinculados a ele!");
                    throw new ObjetoComLocalException("Local nao pode ser alterado, existem objetos vinculados a ele!");
                }
            } else {
                telaLocal.exibirMensagem("Local nao existe!");
            }
        } else {
            telaLocal.exibirMensagem("Valores nao podem ser nulos!");
        }
    }

}
