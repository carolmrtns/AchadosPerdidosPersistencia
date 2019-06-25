/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.views;

import br.ufsc.ine5605.achadoseperdidos.controllers.ControladorLocal;
import br.ufsc.ine5605.achadoseperdidos.models.Local;
import br.ufsc.ine5605.achadoseperdidos.persistencia.LocalDAO;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caroline Martins Alves
 */
public class TelaLocal extends TelaGlobal {

    //private Scanner teclado;
    //private ControladorLocal controladorLocal;
    private JTable tabelaLocal;
    private GridBagConstraints constraint;
    private JScrollPane spBaseTabela;
    private JLabel lblNomeLocal;
    private JTextField txtNomeLocal;
    private JTextField txtNomeLocalAlterar;
    private JTextField txtNomeLocalExcluir;
    private JLabel lblLocalizacao;
    private JTextField txtLocalizacao;
    private JLabel lblNovoNomeLocal;
    private JTextField txtNovoNomeLocal;
    private JLabel lblNovaLocalizacao;
    private JTextField txtNovaLocalizacao;
    private JButton btnCadastrar;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private Container container;
    private JPanel pnlTelaLocal;
    private JPanel pnlTelaCadastrar;
    private JPanel pnlTelaAlterar;
    private JPanel pnlTelaExcluir;
    private String nomeLocalAlterar;

    public TelaLocal() {
        super("Tela Local");
        constraint = new GridBagConstraints();
        initComponents();
        //teclado = new Scanner(System.in);
        //this.controladorLocal = ControladorLocal.getInstancia();
    }

    public void initComponents() {

        container = getContentPane();
        container.setLayout(new GridBagLayout());

        pnlTelaCadastrar = telaCadastrar();
        pnlTelaAlterar = telaAlterar();
        pnlTelaExcluir = telaRemover();

        pnlTelaLocal = telaLocal();
        container.remove(pnlTelaCadastrar);
        container.remove(pnlTelaAlterar);
        container.remove(pnlTelaAlterar);
        container.add(pnlTelaLocal);

        setSize(600, 500);
        //setLocationRelativeTo(null);
        //setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }

    private JPanel telaLocal() {

        JPanel painelLocal = new JPanel();
        painelLocal.setLayout(new GridBagLayout());
/*
        //Tabela que lista os dados recuperados do arquivo
        tabelaLocal = new JTable();
        tabelaLocal.setPreferredScrollableViewportSize(new Dimension(550, 350));
        tabelaLocal.setFillsViewportHeight(true);
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridwidth = 2;
        constraint.gridheight = 4;
        constraint.gridx = 0;
        constraint.gridy = 10;
        spBaseTabela = new JScrollPane(tabelaLocal);
        painelLocal.add(spBaseTabela, constraint);
*/
        //Criando menu de navegacao
        JMenuBar menuLocal = new JMenuBar();
        setJMenuBar(menuLocal);

        //Criando aba no menu de navegacao
        JMenu local = new JMenu("Local");
        menuLocal.add(local);

        //Criando itens para menu de navegacao
        JMenuItem cadastrar = new JMenuItem("Cadastrar Locais");
        JMenuItem alterar = new JMenuItem("Alterar Locais");
        JMenuItem excluir = new JMenuItem("Excluir Locais");

        //Adiocionado os itens criados na aba
        local.add(cadastrar);
        local.add(alterar);
        local.add(excluir);

        //Setando acoes nos itens criados
        cadastrar.setActionCommand("1");
        alterar.setActionCommand("2");
        excluir.setActionCommand("3");

        GerenciadorBotoes gerenciadorBotoes = new GerenciadorBotoes();
        cadastrar.addActionListener(gerenciadorBotoes);
        alterar.addActionListener(gerenciadorBotoes);
        excluir.addActionListener(gerenciadorBotoes);

        //updateData();

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return painelLocal;
    }

    private JPanel telaCadastrar() {
        //Componentes da tela
        JPanel painelCadastrar = new JPanel();
        painelCadastrar.setLayout(new GridBagLayout());
        GridBagConstraints gridConstraint = new GridBagConstraints();

        //Componentes de campo
        lblNomeLocal = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 1;
        painelCadastrar.add(lblNomeLocal, gridConstraint);

        txtNomeLocal = new JTextField(20);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 1;
        painelCadastrar.add(txtNomeLocal, gridConstraint);

        lblLocalizacao = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 2;
        painelCadastrar.add(lblLocalizacao, gridConstraint);

        txtLocalizacao = new JTextField(20);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 2;
        painelCadastrar.add(txtLocalizacao, gridConstraint);

        btnCadastrar = new JButton();
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 3;
        painelCadastrar.add(btnCadastrar, gridConstraint);

        btnAlterar = new JButton();
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 4;
        painelCadastrar.add(btnAlterar, gridConstraint);

        btnExcluir = new JButton();
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 5;
        painelCadastrar.add(btnExcluir, gridConstraint);        
        
        //Fim componentes da tela
        //Conteudo dentro dos componentes da tela
        lblNomeLocal.setText("Nome do Local: ");
        lblLocalizacao.setText("Localizacao: ");
        btnCadastrar.setText("Cadastrar");
        btnAlterar.setText("Alterar");
        btnExcluir.setText("Excluir");

        //Tabela que lista os dados recuperados do arquivo
        tabelaLocal = new JTable();
        tabelaLocal.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabelaLocal.setFillsViewportHeight(true);
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridwidth = 2;
        constraint.gridheight = 4;
        constraint.gridx = 0;
        constraint.gridy = 10;
        spBaseTabela = new JScrollPane(tabelaLocal);
        painelCadastrar.add(spBaseTabela, constraint);

        updateData();

        //Setando acoes nos botoes
        btnCadastrar.setActionCommand("4");
        btnAlterar.setActionCommand("5");
        btnExcluir.setActionCommand("6");

        tabelaLocal.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tabelaLocalClique(evt);
            }
        });

        //Adicionando acao nos buttons
        GerenciadorBotoes gerenciadorBotoes = new GerenciadorBotoes();
        btnCadastrar.addActionListener(gerenciadorBotoes);
        btnAlterar.addActionListener(gerenciadorBotoes);
        btnExcluir.addActionListener(gerenciadorBotoes);

        //Configuracoes da tela
        setSize(600, 500);
        setLocationRelativeTo(null);
        //setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return painelCadastrar;
    }

    private JPanel telaAlterar() {
        //Componentes da tela
        JPanel painelAlterar = new JPanel();
        painelAlterar.setLayout(new GridBagLayout());
        GridBagConstraints gridConstraint = new GridBagConstraints();

        //Componentes de campo
        lblNomeLocal = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 1;
        painelAlterar.add(lblNomeLocal, gridConstraint);

        txtNomeLocalAlterar = new JTextField(20);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 1;
        painelAlterar.add(txtNomeLocalAlterar, gridConstraint);

        lblNovoNomeLocal = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 2;
        painelAlterar.add(lblNovoNomeLocal, gridConstraint);

        txtNovoNomeLocal = new JTextField(20);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 2;
        painelAlterar.add(txtNovoNomeLocal, gridConstraint);

        lblNovaLocalizacao = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 3;
        painelAlterar.add(lblNovaLocalizacao, gridConstraint);

        txtNovaLocalizacao = new JTextField(20);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 3;
        painelAlterar.add(txtNovaLocalizacao, gridConstraint);

        btnAlterar = new JButton();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 4;
        painelAlterar.add(btnAlterar, gridConstraint);

        //Fim componentes da tela
        //Conteudo dentro dos componentes da tela
        lblNomeLocal.setText("Nome do Local: ");
        lblNovoNomeLocal.setText("Novo Nome: ");
        lblNovaLocalizacao.setText("Nova Localizacao: ");
        btnAlterar.setText("Alterar");

        //Setando acoes nos botoes
        btnAlterar.setActionCommand("5");
/*
        //Tabela que lista os dados recuperados do arquivo
        tabelaLocal = new JTable();
        tabelaLocal.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabelaLocal.setFillsViewportHeight(true);
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridwidth = 2;
        constraint.gridheight = 4;
        constraint.gridx = 0;
        constraint.gridy = 10;
        spBaseTabela = new JScrollPane(tabelaLocal);
        painelAlterar.add(spBaseTabela, constraint);

        updateData();
*/
        //Adicionando acao nos buttons
        GerenciadorBotoes gerenciadorBotoes = new GerenciadorBotoes();
        btnAlterar.addActionListener(gerenciadorBotoes);

        //Configuracoes da tela
        setSize(600, 500);
        setLocationRelativeTo(null);
        //setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return painelAlterar;
    }

    private JPanel telaRemover() {
        //Componentes da tela
        JPanel painelRemover = new JPanel();
        painelRemover.setLayout(new GridBagLayout());
        GridBagConstraints gridConstraint = new GridBagConstraints();

        //Componentes de campo
        lblNomeLocal = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 1;
        painelRemover.add(lblNomeLocal, gridConstraint);

        txtNomeLocalExcluir = new JTextField(20);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 1;
        painelRemover.add(txtNomeLocalExcluir, gridConstraint);

        btnExcluir = new JButton();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 6;
        painelRemover.add(btnExcluir, gridConstraint);
        //Fim componentes da tela

        //Conteudo dentro dos componentes da tela
        lblNomeLocal.setText("Nome do Local: ");
        btnExcluir.setText("Excluir");

        //Setando acoes nos botoes
        btnExcluir.setActionCommand("6");
/*
        //Tabela que lista os dados recuperados do arquivo
        tabelaLocal = new JTable();
        tabelaLocal.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabelaLocal.setFillsViewportHeight(true);
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridwidth = 2;
        constraint.gridheight = 4;
        constraint.gridx = 0;
        constraint.gridy = 10;
        spBaseTabela = new JScrollPane(tabelaLocal);
        painelRemover.add(spBaseTabela, constraint);

        updateData();
*/
        //Adicionando acao nos buttons
        GerenciadorBotoes gerenciadorBotoes = new GerenciadorBotoes();
        btnExcluir.addActionListener(gerenciadorBotoes);

        //Configuracoes da tela
        setSize(600, 500);
        setLocationRelativeTo(null);
        //setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return painelRemover;
    }

    //NAO SEI ONDE QUE FICA ISSO AQUI
    private void updateData() {
        DefaultTableModel modelTbItens = new DefaultTableModel();
        modelTbItens.addColumn("Local");
        modelTbItens.addColumn("Descricao");

        for (Local localLista : LocalDAO.getInstancia().getList()) {
            modelTbItens.addRow(new Object[]{localLista.getNomeLocal(),
                localLista.getLocalizacao()});
        }

        tabelaLocal.setModel(modelTbItens);
        this.repaint();

    }

    public void mostrarTelaLocal() {
        container.remove(pnlTelaAlterar);
        container.remove(pnlTelaExcluir);
        container.remove(pnlTelaLocal);
        container.remove(pnlTelaCadastrar);
        container.add(pnlTelaLocal);
        updateData();
        container.revalidate();
        container.repaint();
    }

    public void mostrarTelaCadastrar() {
        container.remove(pnlTelaAlterar);
        container.remove(pnlTelaExcluir);
        container.remove(pnlTelaLocal);
        container.remove(pnlTelaCadastrar);
        container.add(pnlTelaCadastrar);
        updateData();
        container.revalidate();
        container.repaint();
    }

    public void mostrarTelaAlterar() {
        container.remove(pnlTelaExcluir);
        container.remove(pnlTelaLocal);
        container.remove(pnlTelaCadastrar);
        container.remove(pnlTelaAlterar);
        container.add(pnlTelaAlterar);
        updateData();
        container.revalidate();
        container.repaint();
    }

    public void mostrarTelaExcluir() {
        container.remove(pnlTelaLocal);
        container.remove(pnlTelaCadastrar);
        container.remove(pnlTelaAlterar);
        container.remove(pnlTelaExcluir);
        container.add(pnlTelaExcluir);
        container.revalidate();
        container.repaint();
    }

    public void mostrarTelas() {
        setVisible(true);
    }

    public void limparFieldsCadastrar() {
        txtNomeLocal.setText("");
        txtLocalizacao.setText("");
    }

    public void limparFieldsAlterar() {
        txtNomeLocal.setText("");
        txtNovoNomeLocal.setText("");
        txtNovaLocalizacao.setText("");
    }

    public void tabelaLocalClique(MouseEvent ev) {
        DefaultTableModel model = (DefaultTableModel) tabelaLocal.getModel();
        int indexLinhaSelecionada = tabelaLocal.getSelectedRow();
        txtNomeLocal.setText(model.getValueAt(indexLinhaSelecionada, 0).toString());
        txtLocalizacao.setText(model.getValueAt(indexLinhaSelecionada, 1).toString());
        nomeLocalAlterar = txtNomeLocal.getText();
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String nomeLocal = txtNomeLocal.getText();
            //String nomeLocalAlterar = txtNomeLocalAlterar.getText();
            String nomeLocalExcluir = txtNomeLocalExcluir.getText();
            String localizacao = txtLocalizacao.getText();
            //String novoNomeLocal = txtNovoNomeLocal.getText();
            //String novaLocalizacao = txtNovaLocalizacao.getText();

            System.out.println("-------------------------------------------");
            System.out.println("Nome Local: " + nomeLocal);
            System.out.println("Nome Local Alterar: " + nomeLocalAlterar);
            System.out.println("Localizacao: " + localizacao);
            //System.out.println("Novo Nome: " + novoNomeLocal);
            //System.out.println("Nova Localizacao: " + novaLocalizacao);

            switch (ae.getActionCommand()) {
                case "1":
                    mostrarTelaCadastrar();
                    break;
                case "2":
                    mostrarTelaAlterar();
                    break;
                case "3":
                    mostrarTelaExcluir();
                    break;
                case "4":
                    ControladorLocal.getInstancia().cadastrarLocal(nomeLocal, localizacao);
                    updateData();
                    limparFieldsCadastrar();
                    break;
                case "5":
                    Local local = ControladorLocal.getInstancia().encontrarLocalPeloNome(nomeLocalAlterar);
                    ControladorLocal.getInstancia().atualizarDadosLocal(local, nomeLocal, localizacao);
                    limparFieldsAlterar();
                    updateData();
                    break;
                case "6":
                    ControladorLocal.getInstancia().excluirLocal(nomeLocalExcluir);
                    updateData();
                    break;
            }

        }
    }

}
