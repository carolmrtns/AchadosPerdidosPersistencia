/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.controllers;
import br.ufsc.ine5605.achadoseperdidos.models.Local;
import br.ufsc.ine5605.achadoseperdidos.models.Objeto;
import br.ufsc.ine5605.achadoseperdidos.models.Pessoa;
import br.ufsc.ine5605.achadoseperdidos.views.TelaObjeto;
import br.ufsc.ine5605.achadoseperdidos.models.TipoObjeto;
import br.ufsc.ine5605.achadoseperdidos.models.TipoPessoa;
import br.ufsc.ine5605.achadoseperdidos.models.TipoStatus;
import br.ufsc.ine5605.achadoseperdidos.persistencia.ObjetoDAO;
import br.ufsc.ine5605.achadoseperdidos.persistencia.PessoaDAO;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Caroline Martins Alves
 */
public class ControladorObjeto {
    //private ArrayList<Objeto> objetos;
    private TelaObjeto telaObjeto;
    //private ControladorPrincipal controladorPrincipal;
    private static ControladorObjeto instancia;
    private int codigo;
    
    private ControladorObjeto(){
        //objetos = new ArrayList<>();
        telaObjeto = new TelaObjeto();
        //this.controladorPrincipal = controladorPrincipal;
        
    }
    
    public void inicia(){
        telaObjeto.initComponents();
    }

    public static ControladorObjeto getInstancia(){
        if(instancia == null){
            instancia = new ControladorObjeto();
        }
        return instancia;
    }    
    
    public void cadastrarObjetos(String descricao, TipoStatus status, 
        TipoObjeto tipoObjeto, String nomeLocal, String nomeCadastrador){
        if(!descricao.equals("") && status != null && tipoObjeto != null && 
                !nomeLocal.equals("") && !nomeCadastrador.equals("")){
            if(ControladorPrincipal.getInstancia().retornarPessoaPeloNome(nomeCadastrador) != null){
                if(ControladorPrincipal.getInstancia().retornarLocalPeloNome(nomeLocal) != null){
                    Pessoa cadastrador = ControladorPrincipal.getInstancia().retornarPessoaPeloNome(nomeCadastrador);
                    Local local = ControladorPrincipal.getInstancia().retornarLocalPeloNome(nomeLocal);

                    Objeto novoObjeto = new Objeto(verificaCodigo(),descricao, status, tipoObjeto, local, cadastrador);

                    telaObjeto.exibirDadosObjeto(novoObjeto.getCodigo(), novoObjeto.getDescricao(), 
                            novoObjeto.getStatus(), novoObjeto.getTipoObjeto(), 
                            ControladorPrincipal.getInstancia().retornarNomeLocal(novoObjeto.getLocal()), 
                            ControladorPrincipal.getInstancia().retornarNomePessoa(novoObjeto.getCadastrador()));

                    ObjetoDAO.getInstancia().put(novoObjeto);
                    telaObjeto.exibirMensagem("Objeto cadastrado com Sucesso!");
                }else{
                    telaObjeto.exibirMensagem("Digite um local existente! Objeto nao foi inserido.");
                }
            }else{
                telaObjeto.exibirMensagem("Cadastrador nao existe! Cadastre-se e tente inserir o objeto novamente!");
            }
        }else{
            telaObjeto.exibirMensagem("Nenhum dos valores pode ser nulo. Nao foi possivel cadastrar o objeto!");
        }
    }
    
    public void atualizarStatusObjeto(Integer codigo, TipoStatus status, String nomeDono){
        if(encontrarObjetoPorCodigo(codigo) != null){
            if(encontrarObjetoPorCodigo(codigo).getStatus() != status.ENCONTRADO){
                if(ControladorPrincipal.getInstancia().retornarPessoaPeloNome(nomeDono) != null){
                    //encontrarObjetoPorCodigo(codigo).setStatus(status);
                    Pessoa dono = ControladorPrincipal.getInstancia().retornarPessoaPeloNome(nomeDono);
                    //encontrarObjetoPorCodigo(codigo).setDono(dono);
                    //funcao no DAO que faz o put do objeto de novo no hashmap
                    ObjetoDAO.getInstancia().atualizaStatus(codigo, status, dono);
                    telaObjeto.exibirMensagem("Objeto foi atualizado com sucesso!");
                }else{
                    telaObjeto.exibirMensagem("Dono nao existe! Status nao atualizado.");
                }
            }else{
                telaObjeto.exibirMensagem("Esse objeto ja esta com seu dono! Status nao atualizado.");
            }
        }else{
            telaObjeto.exibirMensagem("Objeto nao existe! Status nao atualizado.");
        }
    }
    /*
    public void listarObjetosPerdidos(){
        TipoStatus status = null;
        //telaObjeto.exibirMensagem("----------LISTANDO OBJETOS PERDIDOS----------");
        for(Objeto objetosLista : ObjetoDAO.getInstancia().getList()){
            if(objetosLista.getStatus().equals(status.PERDIDO)){
                telaObjeto.exibirMensagem("Codigo:" + objetosLista.getCodigo()+
                        " Descricao:" + objetosLista.getDescricao()+
                        " Status:" + objetosLista.getStatus()+
                        " Tipo:" + objetosLista.getTipoObjeto()+
                        " Local:" + ControladorPrincipal.getInstancia().retornarNomeLocal(objetosLista.getLocal())+
                        " Cadastrador:" + ControladorPrincipal.getInstancia().retornarNomePessoa(objetosLista.getCadastrador()));
            } 
        }
    }
    */
    public int verificaCodigo(){
        ArrayList<Objeto> objetos = new ArrayList<>();
        objetos = ObjetoDAO.getInstancia().getList();
        for(Objeto objetosLista : ObjetoDAO.getInstancia().getList()){
            if(objetosLista != null){
                int indice = objetos.size() - 1;
                codigo = objetos.get(indice).getCodigo() + 1;
            }else{
                codigo = 1;
            }
        }
        return codigo;
    }
    /*
    public void listarObjetosPorTipo(TipoObjeto tipoObjeto){
        TipoStatus status = null;
        telaObjeto.exibirMensagem("----------LISTANDO OBJETOS PERDIDOS DO TIPO " + tipoObjeto +"----------");
        for(Objeto objetosLista: ObjetoDAO.getInstancia().getList()){
            if(objetosLista.getTipoObjeto().equals(tipoObjeto)){
                if(objetosLista.getStatus().equals(status.PERDIDO)){
                    telaObjeto.exibirMensagem("Codigo:" + objetosLista.getCodigo());
                    telaObjeto.exibirMensagem("Descricao:" + objetosLista.getDescricao());
                    telaObjeto.exibirMensagem("Status:" + objetosLista.getStatus());
                    telaObjeto.exibirMensagem("Tipo:" + objetosLista.getTipoObjeto());
                    telaObjeto.exibirMensagem("Local:" + ControladorPrincipal.getInstancia().retornarNomeLocal(objetosLista.getLocal()));
                    telaObjeto.exibirMensagem("Cadastrador:" + ControladorPrincipal.getInstancia().retornarNomePessoa(objetosLista.getCadastrador()));
                    telaObjeto.exibirMensagem("---------------------------------------------");
                }
            }
        }
    }
    */
    public Objeto encontrarObjetoPorCodigo(int codigo){
        for(Objeto objetosLista: ObjetoDAO.getInstancia().getList()){
            if(objetosLista.getCodigo() == codigo){
                return objetosLista;
            }
        }
        return null;
    }
    
}
