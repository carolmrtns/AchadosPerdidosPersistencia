/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.views;

import br.ufsc.ine5605.achadoseperdidos.controllers.ControladorPrincipal;
import java.util.Scanner;

/**
 *
 * @author Caroline Martins Alves
 */
public class TelaPrincipal extends TelaGlobal{

    private Scanner teclado;
    //private ControladorPrincipal controladorPrincipal;

    public TelaPrincipal() {
        teclado = new Scanner(System.in);
        //this.controladorPrincipal = controladorPrincipal;
    }

    public void menuInicial() {
        int opcao;
        do {
            System.out.println("----------ACHADOS E PERDIDOS----------");
            System.out.println("1 - Pessoas");
            System.out.println("2 - Locais");
            System.out.println("3 - Objetos");
            System.out.println("0 - Sair");
            System.out.println("--------------------------------------");
            System.out.println("Digite o numero da opcao desejada: ");
            opcao = recebeValorInteiro();
            switch (opcao) {
                case 1:
                    exibirTelaPessoa();
                    break;
                case 2:
                    exibirTelaLocal();
                    break;
                case 3:
                    exibirTelaObjeto();
                    break;
            }
        } while (opcao != 0);
    }

    public void exibirTelaPessoa() {
        ControladorPrincipal.getInstancia().listarTelaPessoa();
    }

    public void exibirTelaLocal() {
        ControladorPrincipal.getInstancia().listarTelaLocal();
    }

    public void exibirTelaObjeto() {
        ControladorPrincipal.getInstancia().listarTelaObjeto();
    }

}
