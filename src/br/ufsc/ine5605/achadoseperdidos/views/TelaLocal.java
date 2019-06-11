/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.views;
import br.ufsc.ine5605.achadoseperdidos.controllers.ControladorLocal;
import java.util.Scanner;
/**
 *
 * @author Caroline Martins Alves
 */
public class TelaLocal extends TelaGlobal{
    private Scanner teclado;
    //private ControladorLocal controladorLocal;
    
    public TelaLocal(){
        teclado = new Scanner(System.in);
        //this.controladorLocal = ControladorLocal.getInstancia();
    }
    
    public void menuInicial(){
        int opcao;
        do{
            System.out.println("----------TELA LOCAL----------");
            System.out.println("1 - Cadastrar um local");
            System.out.println("2 - Remover um local");
            System.out.println("3 - Listar locais");
            System.out.println("4 - Atualizar local");
            System.out.println("0 - Voltar");
            System.out.println("------------------------------");
            System.out.print("Digite o numero da operacao desejada: ");
            opcao = recebeValorInteiro();
            switch(opcao){
                case 1: inserirLocal();
                    break;
                case 2: removerLocal();
                    break;
                case 3: exibirLocais();
                    break;
                case 4: alterarDadosLocal();
                    break;
                default: System.out.println("Opção invalida");
            }            
        }while(opcao != 0);
    }
    
    public void inserirLocal(){
        System.out.println("----------INCLUIR LOCAL----------");
        System.out.println("Nome do local: ");
        String nomeLocal = teclado.nextLine();
        System.out.println("Localizacao: ");
        String localizacao = teclado.nextLine();
        
        ControladorLocal.getInstancia().cadastrarLocal(nomeLocal, localizacao);
    }
    
    public void removerLocal(){
        System.out.println("----------EXCLUSAO DE LOCAIS----------");
        System.out.println("Nome do local: ");
        String nomeLocal = recebeValorString();

        ControladorLocal.getInstancia().excluirLocal(nomeLocal);
    }
    
    public void exibirLocais(){
        ControladorLocal.getInstancia().listarLocais();
    }
    
    public void alterarDadosLocal(){
        System.out.println("----------ALTERAR DADOS DO LOCAL----------");
        System.out.println("Digite o nome do local a ser alterado: ");
        String nomeLocal = recebeValorString();
        System.out.println("Digite o novo nome do local: ");
        String novoNomeLocal = recebeValorString();
        System.out.println("Digite a nova localizacao: ");
        String novaLocalizacao = recebeValorString();
        
        ControladorLocal.getInstancia().atualizarDadosLocal(nomeLocal, novoNomeLocal, novaLocalizacao);
    }
    
}
