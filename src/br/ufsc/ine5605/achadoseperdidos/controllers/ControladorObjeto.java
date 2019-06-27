/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.controllers;

import br.ufsc.ine5605.achadoseperdidos.exceptions.PessoaNaoExisteException;
import br.ufsc.ine5605.achadoseperdidos.exceptions.LocalNaoExisteException;
import br.ufsc.ine5605.achadoseperdidos.exceptions.ObjetoJaTemDonoException;
import br.ufsc.ine5605.achadoseperdidos.exceptions.ObjetoNaoExisteException;
import br.ufsc.ine5605.achadoseperdidos.exceptions.ValoresNulosException;
import br.ufsc.ine5605.achadoseperdidos.models.Local;
import br.ufsc.ine5605.achadoseperdidos.models.Objeto;
import br.ufsc.ine5605.achadoseperdidos.models.Pessoa;
import br.ufsc.ine5605.achadoseperdidos.views.TelaObjeto;
import br.ufsc.ine5605.achadoseperdidos.models.TipoObjeto;
import br.ufsc.ine5605.achadoseperdidos.models.TipoStatus;
import br.ufsc.ine5605.achadoseperdidos.persistencia.ObjetoDAO;

/**
 *
 * @author Caroline Martins Alves
 */
public class ControladorObjeto {

    private TelaObjeto telaObjeto;
    private static ControladorObjeto instancia;
    private int codigo;

    public void inicia() {
        telaObjeto.mostrarTelaObjeto();
        telaObjeto.mostrarTelas();
    }

    private ControladorObjeto() {
        telaObjeto = new TelaObjeto();

    }

    public static ControladorObjeto getInstancia() {
        if (instancia == null) {
            instancia = new ControladorObjeto();
        }
        return instancia;
    }

    public void cadastrarObjetos(String descricao, TipoStatus status,
            TipoObjeto tipoObjeto, String nomeLocal, String nomeCadastrador)
            throws ValoresNulosException, PessoaNaoExisteException, LocalNaoExisteException {
        if (!descricao.equals("") && status != null && tipoObjeto != null
                && !nomeLocal.equals("") && !nomeCadastrador.equals("")) {
            if (ControladorPrincipal.getInstancia().retornarPessoaPeloNome(nomeCadastrador) != null) {
                if (ControladorPrincipal.getInstancia().retornarLocalPeloNome(nomeLocal) != null) {
                    //Pegando alguns valores de parametros
                    int codigoObjeto = verificaCodigo();
                    Pessoa cadastrador = ControladorPrincipal.getInstancia().retornarPessoaPeloNome(nomeCadastrador);
                    Local local = ControladorPrincipal.getInstancia().retornarLocalPeloNome(nomeLocal);
                    //Criando um novo objeto
                    Objeto novoObjeto = new Objeto(codigoObjeto, descricao, status, tipoObjeto, local, cadastrador);
                    //Inserindo esse novo objeto no hashmap
                    ObjetoDAO.getInstancia().put(novoObjeto);
                    telaObjeto.exibirMensagem("Objeto cadastrado com Sucesso!");
                } else {
                    throw new LocalNaoExisteException("Digite um local existente! Objeto nao foi inserido.");
                }
            } else {
                throw new PessoaNaoExisteException("Cadastrador nao existe! Cadastre-se e tente inserir o objeto novamente!");
            }
        } else {
            throw new ValoresNulosException("Nenhum dos valores pode ser nulo. Nao foi possivel cadastrar o objeto!");
        }
    }

    public void atualizarStatusObjeto(Integer codigo, TipoStatus status, String nomeDono)
            throws PessoaNaoExisteException, ObjetoJaTemDonoException, 
            ObjetoNaoExisteException, ValoresNulosException {
        if (codigo > 0 && status != null && !nomeDono.equals("")) {
            //Verifica se o objeto a ser atualizado existe
            if (encontrarObjetoPorCodigo(codigo) != null) {
                //Verifica se o objeto a ser atualizado esta com estatus de PERDIDO
                if (encontrarObjetoPorCodigo(codigo).getStatus() != status.ENCONTRADO) {
                    //Verifica se o dono existe
                    if (ControladorPrincipal.getInstancia().retornarPessoaPeloNome(nomeDono) != null) {
                        Pessoa dono = ControladorPrincipal.getInstancia().retornarPessoaPeloNome(nomeDono);
                        ObjetoDAO.getInstancia().atualizaStatus(codigo, status, dono);
                        telaObjeto.exibirMensagem("Objeto foi atualizado com sucesso!");
                    } else {
                        throw new PessoaNaoExisteException("Dono nao existe! Status nao atualizado.");
                    }
                } else {
                    throw new ObjetoJaTemDonoException("Esse objeto ja esta com seu dono! Status nao atualizado.");
                }
            } else {
                throw new ObjetoNaoExisteException("Objeto nao existe! Status nao atualizado.");
            }
        } else {
            throw new ValoresNulosException("Valores nao podem ser nulos!");
        }

    }

    //Funcao verifica o ultimo codigo adicionado e incrementa 1 para o novo objeto
    public int verificaCodigo() {
        if (!ObjetoDAO.getInstancia().getList().isEmpty()) {
            int indice = ObjetoDAO.getInstancia().getList().size() - 1;
            codigo = ObjetoDAO.getInstancia().getList().get(indice).getCodigo() + 1;
        } else {
            codigo = 1;
        }
        return codigo;
    }

    public Objeto encontrarObjetoPorCodigo(int codigo) {
        for (Objeto objetosLista : ObjetoDAO.getInstancia().getList()) {
            if (objetosLista.getCodigo() == codigo) {
                return objetosLista;
            }
        }
        return null;
    }

    public boolean localEhUsado(Local local) {
        for (Objeto objetosLista : ObjetoDAO.getInstancia().getList()) {
            if (objetosLista.getLocal().getNomeLocal().equals(local.getNomeLocal())) {
                return true;
            }
        }
        return false;
    }

}
