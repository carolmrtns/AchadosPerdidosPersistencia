/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.views;
import br.ufsc.ine5605.achadoseperdidos.models.TipoObjeto;
import br.ufsc.ine5605.achadoseperdidos.models.TipoStatus;
import br.ufsc.ine5605.achadoseperdidos.controllers.ControladorObjeto;
import java.util.Scanner;
/**
 *
 * @author Caroline Martins Alves
 */
public class TelaObjeto extends TelaGlobal{
    
    private Scanner teclado;
    //private ControladorObjeto controladorObjeto;
    
    
    public TelaObjeto(){
        super("Tela Objeto");
        teclado = new Scanner(System.in);
        //this.controladorObjeto = ControladorObjeto.getInstancia();
    }
    
    public void menuInicial(){
        int opcao;
        do{
            System.out.println("----------TELA OBJETO----------");
            System.out.println("1 - Cadastrar um objeto");
            System.out.println("2 - Encontrei meu objeto");
            System.out.println("3 - Listar objetos perdidos");
            System.out.println("4 - Listar objetos perdidos por tipo");
            System.out.println("0 - Voltar");
            System.out.println("-------------------------------");
            System.out.print("Digite o numero da operacao desejada: ");
            opcao = recebeValorInteiro();
            switch(opcao){
                case 1: inserirObjetos();
                    break;
                case 2: alterarStatusObjeto();
                    break;
                case 3: exibirObjetos();
                    break;
                case 4: System.out.println("----------SELECIONE O TIPO DO OBJETO----------");
                        System.out.println("Tipo do objeto [1 - Eletroeletronico, 2 - Materiais, 3 - Vestuario, 4 - Alimento, 5 - Documento] :");
                        int opcaoTipo = recebeValorInteiro();
                        TipoObjeto objetoTipo;
                        switch(opcaoTipo){
                            case 1: objetoTipo = TipoObjeto.ELETROELETRONICO;
                                break;
                            case 2: objetoTipo = TipoObjeto.MATERIAIS;
                                break;
                            case 3: objetoTipo = TipoObjeto.VESTUARIO;
                                break;
                            case 4: objetoTipo = TipoObjeto.ALIMENTO;
                                break;
                            case 5: objetoTipo = TipoObjeto.DOCUMENTOS;
                                break;
                            default: objetoTipo = null;
                        }                        
                        exibirObjetosPorTipo(objetoTipo);
                    break;
                default: System.out.println("Opcao invalida");
            }
        }while(opcao != 0);

    }
    
    public void inserirObjetos(){
        System.out.println("----------INSERINDO OBJETOS----------");
        System.out.println("Descricao: ");
        String descricao = recebeValorString();
        System.out.println("Status: PERDIDO");
        TipoStatus status = TipoStatus.PERDIDO;
        System.out.println("Tipo do objeto [1 - Eletroeletronico, 2 - Materiais, 3 - Vestuario, 4 - Alimento, 5 - Documento] :");
        int escolhaTipoObjeto = recebeValorInteiro();
        TipoObjeto tipoObjeto;
        switch(escolhaTipoObjeto){
            case 1: tipoObjeto = TipoObjeto.ELETROELETRONICO;
                break;
            case 2: tipoObjeto = TipoObjeto.MATERIAIS;
                break;
            case 3: tipoObjeto = TipoObjeto.VESTUARIO;
                break;
            case 4: tipoObjeto = TipoObjeto.ALIMENTO;
                break;
            case 5: tipoObjeto = TipoObjeto.DOCUMENTOS;
                break;
            default: tipoObjeto = null;
        }
        System.out.println("Local: ");
        String local = recebeValorString();
        System.out.println("Cadastrador: ");
        String cadastrador = recebeValorString();
        
        ControladorObjeto.getInstancia().cadastrarObjetos(descricao, status, tipoObjeto, local, cadastrador);
        
    }
    
    public void alterarStatusObjeto(){
        System.out.println("----------REMOVENDO OBJETO ENCONTRADO----------");
        System.out.println("Codigo do objeto: ");
        int codigo = recebeValorInteiro();
        System.out.println("Status: ENCONTRADO");
        TipoStatus status = TipoStatus.ENCONTRADO;
        System.out.println("Nome do dono: ");
        String nome = recebeValorString();
        
        ControladorObjeto.getInstancia().atualizarStatusObjeto(codigo, status, nome);
    }
    
    public void exibirObjetos(){
        ControladorObjeto.getInstancia().listarObjetosPerdidos();                
    }
    
    public void exibirObjetosPorTipo(TipoObjeto tipoObjeto){
        ControladorObjeto.getInstancia().listarObjetosPorTipo(tipoObjeto);
    }
    
    public void exibirDadosObjeto(int codigo, String descricao, TipoStatus status, TipoObjeto tipoObjeto, String nomeLocal, String nomeCadastrador){
        System.out.println("----------OBJETO CADASTRADO----------");
        System.out.println("Codigo: " + codigo);
        System.out.println("Descricao: " + descricao);
        System.out.println("Status: " + status);
        System.out.println("Tipo do objeto: " + tipoObjeto);
        System.out.println("Local: " + nomeLocal);
        System.out.println("Cadastrador: " + nomeCadastrador);
    }
    
}
