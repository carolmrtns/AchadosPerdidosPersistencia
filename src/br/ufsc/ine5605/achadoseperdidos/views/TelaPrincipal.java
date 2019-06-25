/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.views;

import br.ufsc.ine5605.achadoseperdidos.controllers.ControladorPrincipal;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Caroline Martins Alves
 */
public class TelaPrincipal extends TelaGlobal {

    private Scanner teclado;
    private JLabel labelIcone;
    private ImageIcon icone;

    public TelaPrincipal() {
        super("Achados e Perdidos - Tela Principal");
        icone = new ImageIcon("achadosperdidos.png");
    }

    public void initComponents() {
        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        //Criando menu de navegacao
        JMenuBar menuPrincipal = new JMenuBar();
        setJMenuBar(menuPrincipal);

        //Criando aba no menu de navegacao
        JMenu telas = new JMenu("Telas");
        menuPrincipal.add(telas);

        //Criando itens para menu de navegacao
        JMenuItem pessoas = new JMenuItem("Pessoas");
        JMenuItem locais = new JMenuItem("Locais");
        JMenuItem objetos = new JMenuItem("Objetos");

        //Adiocionado os itens criados na aba
        telas.add(pessoas);
        telas.add(locais);
        telas.add(objetos);

        //Setando acoes nos itens criados
        pessoas.setActionCommand("1");
        locais.setActionCommand("2");
        objetos.setActionCommand("3");

        //Passando para o gerenciados de botoes
        GerenciadorBotoes gerenciadorBotoes = new GerenciadorBotoes();
        pessoas.addActionListener(gerenciadorBotoes);
        locais.addActionListener(gerenciadorBotoes);
        objetos.addActionListener(gerenciadorBotoes);

        //Adiocionando imagem na tela
        labelIcone = new JLabel(icone);
        container.add(labelIcone);

        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            ControladorPrincipal.getInstancia().mostrarTelas(ae.getActionCommand());
        }
    }
}
