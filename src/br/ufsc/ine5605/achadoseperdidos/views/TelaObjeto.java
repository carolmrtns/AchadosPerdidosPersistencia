/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.views;

import br.ufsc.ine5605.achadoseperdidos.models.TipoObjeto;
import br.ufsc.ine5605.achadoseperdidos.models.TipoStatus;
import br.ufsc.ine5605.achadoseperdidos.controllers.ControladorObjeto;
import br.ufsc.ine5605.achadoseperdidos.controllers.ControladorPrincipal;
import br.ufsc.ine5605.achadoseperdidos.models.Objeto;
import br.ufsc.ine5605.achadoseperdidos.persistencia.ObjetoDAO;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caroline Martins Alves
 */
public class TelaObjeto extends TelaGlobal {

    //private Scanner teclado;
    //private ControladorObjeto controladorObjeto;
    private JTable tabelaObjeto;
    private JTable tabelaCompleta;
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JLabel lblDescricao;
    private JTextField txtDescricao;
    private JLabel lblStatus;
    private JComboBox cmbStatusAlterar;
    private JComboBox cmbStatusCadastrar;
    private JLabel lblTipoObjeto;
    private JComboBox cmbTipoObjeto;
    private JLabel lblLocal;
    private JTextField txtLocal;
    private JLabel lblCadastrador;
    private JTextField txtCadastrador;
    private JLabel lblDono;
    private JTextField txtDono;
    private JButton btnCadastrar;
    private JButton btnAlterar;
    private JScrollPane spBaseTabela;
    private GridBagConstraints constraint;
    private Container container;
    private JPanel pnlTelaObjeto;
    private JPanel pnlTelaCadastrar;
    private JPanel pnlTelaAlterar;

    public TelaObjeto() {
        super("Tela Objeto");
        constraint = new GridBagConstraints();
    }

    public void initComponents() {

        container = getContentPane();
        container.setLayout(new GridBagLayout());
        
        pnlTelaCadastrar = telaCadastrar();
        pnlTelaAlterar = telaAlterar();
        
        pnlTelaObjeto = telaObjeto();
        container.add(pnlTelaObjeto);

        setSize(600, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }

    public JPanel telaObjeto() {
        //Componentes da tela
        
        JPanel painelObjeto = new JPanel();
        painelObjeto.setLayout(new GridBagLayout());
        
        //Tabela que lista os dados recuperados do arquivo
        tabelaCompleta = new JTable();
        tabelaCompleta.setPreferredScrollableViewportSize(new Dimension(550, 350));
        tabelaCompleta.setFillsViewportHeight(true);
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridwidth = 2;
        constraint.gridheight = 4;
        constraint.gridx = 0;
        constraint.gridy = 10;
        spBaseTabela = new JScrollPane(tabelaCompleta);
        painelObjeto.add(spBaseTabela, constraint);         
        
        //Criando menu de navegacao
        JMenuBar menuObjeto = new JMenuBar();
        setJMenuBar(menuObjeto);
        
        //Criando aba no menu de navegacao
        JMenu objeto = new JMenu("Objeto");
        menuObjeto.add(objeto);
        
        //Criando itens para menu de navegacao
        JMenuItem cadastrar = new JMenuItem("Cadastrar Objetos");
        JMenuItem alterar = new JMenuItem("Encontrei meu Objeto");
        
        //Adiocionado os itens criados na aba
        objeto.add(cadastrar);
        objeto.add(alterar);
        
        //Setando acoes nos itens criados
        cadastrar.setActionCommand("1");
        alterar.setActionCommand("2");

        GerenciadorBotoes gerenciadorBotoes = new GerenciadorBotoes();
        cadastrar.addActionListener(gerenciadorBotoes);
        alterar.addActionListener(gerenciadorBotoes);

        updateDataCompleta();
        
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return painelObjeto;
    }

    public JPanel telaCadastrar() {
        //Componentes da tela
        JPanel painelCadastro = new JPanel();
        painelCadastro.setLayout(new GridBagLayout());
        GridBagConstraints gridConstraint = new GridBagConstraints();

        //Tabela que lista os dados recuperados do arquivo
        tabelaObjeto = new JTable();
        tabelaObjeto.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabelaObjeto.setFillsViewportHeight(true);
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridwidth = 2;
        constraint.gridheight = 4;
        constraint.gridx = 0;
        constraint.gridy = 10;
        spBaseTabela = new JScrollPane(tabelaObjeto);
        painelCadastro.add(spBaseTabela, constraint);

        //Componentes de campo
        TipoStatus tipoStatus[] = {TipoStatus.PERDIDO};
        TipoObjeto tipoObjeto[] = {TipoObjeto.ALIMENTO, TipoObjeto.DOCUMENTOS,
            TipoObjeto.ELETROELETRONICO, TipoObjeto.MATERIAIS, TipoObjeto.VESTUARIO};
        
        lblDescricao = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 1;
        painelCadastro.add(lblDescricao, gridConstraint);

        txtDescricao = new JTextField(20);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 1;
        painelCadastro.add(txtDescricao, gridConstraint);

        lblStatus = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 2;
        painelCadastro.add(lblStatus, gridConstraint);

        cmbStatusCadastrar = new JComboBox(tipoStatus);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 2;
        painelCadastro.add(cmbStatusCadastrar, gridConstraint);

        lblTipoObjeto = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 3;
        painelCadastro.add(lblTipoObjeto, gridConstraint);

        cmbTipoObjeto = new JComboBox(tipoObjeto);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 3;
        painelCadastro.add(cmbTipoObjeto, gridConstraint);

        lblLocal = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 4;
        painelCadastro.add(lblLocal, gridConstraint);

        txtLocal = new JTextField(20);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 4;
        painelCadastro.add(txtLocal, gridConstraint);

        lblCadastrador = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 5;
        painelCadastro.add(lblCadastrador, gridConstraint);

        txtCadastrador = new JTextField(20);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 5;
        painelCadastro.add(txtCadastrador, gridConstraint);

        btnCadastrar = new JButton();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 6;
        painelCadastro.add(btnCadastrar, gridConstraint);
        //Fim componentes da tela

        //Conteudo dentro dos componentes da tela
        lblDescricao.setText("Descricao: ");
        lblStatus.setText("Status: ");
        lblTipoObjeto.setText("Tipo Objeto: ");
        lblLocal.setText("Local: ");
        lblCadastrador.setText("Cadastrador: ");
        btnCadastrar.setText("Cadastrar");

        //Setando acoes nos botoes
        btnCadastrar.setActionCommand("3");

        //Adicionando acao nos buttons
        GerenciadorBotoes gerenciadorBotoes = new GerenciadorBotoes();
        btnCadastrar.addActionListener(gerenciadorBotoes);

        /*
        ONDE QUE CHAMA ESSA FUNC PARA ATUALIZAR OS DADOS DA JTABLE?
        EU CHUTO QUE É DEPOIS DE CADASTRAR-VER ISSO
        */
        //TELA CADASTRAR
        updateData();

        //Configuracoes da tela
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return painelCadastro;
    }
    
    public JPanel telaAlterar(){
        //Componentes da tela
        JPanel painelAlteracao = new JPanel();
        painelAlteracao.setLayout(new GridBagLayout());
        GridBagConstraints gridConstraint = new GridBagConstraints();

        //Tabela que lista os dados recuperados do arquivo
        tabelaObjeto = new JTable();
        tabelaObjeto.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabelaObjeto.setFillsViewportHeight(true);
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridwidth = 2;
        constraint.gridheight = 4;
        constraint.gridx = 0;
        constraint.gridy = 10;
        spBaseTabela = new JScrollPane(tabelaObjeto);
        painelAlteracao.add(spBaseTabela, constraint);
        
        //Componentes de campo
        TipoStatus tipoStatus[] = {TipoStatus.ENCONTRADO};
        
        lblCodigo = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 0;
        painelAlteracao.add(lblCodigo, gridConstraint);

        txtCodigo = new JTextField(20);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 0;
        painelAlteracao.add(txtCodigo, gridConstraint);
        
        lblStatus = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 2;
        painelAlteracao.add(lblStatus, gridConstraint);

        cmbStatusAlterar = new JComboBox(tipoStatus);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 2;
        painelAlteracao.add(cmbStatusAlterar, gridConstraint);
        
        lblDono = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 3;
        painelAlteracao.add(lblDono, gridConstraint);

        txtDono = new JTextField(20);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 3;
        painelAlteracao.add(txtDono, gridConstraint);
        
        btnAlterar = new JButton();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 4;
        painelAlteracao.add(btnAlterar, gridConstraint);
        //Fim dos componentes de tela
        
        lblCodigo.setText("Codigo: ");
        lblStatus.setText("Status: ");
        lblDescricao.setText("Descricao: ");
        lblDono.setText("Dono do Objeto: ");
        btnAlterar.setText("Atualizar");
        
        //Setando acao no button
        btnAlterar.setActionCommand("4");
        
        GerenciadorBotoes gerenciadorBotoes = new GerenciadorBotoes();
        btnAlterar.addActionListener(gerenciadorBotoes);
        
        //Atualiza o conteudo na jtable
        //Tela alterar
        updateData();
        
        //Configuracoes de tela
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);        

        return painelAlteracao;
    }


    //NAO SEI ONDE QUE FICA ISSO AQUI
    private void updateData() {
        DefaultTableModel modelTbItens = new DefaultTableModel();
        modelTbItens.addColumn("Codigo");
        modelTbItens.addColumn("Descricao");
        modelTbItens.addColumn("Status");
        modelTbItens.addColumn("Tipo Objeto");
        modelTbItens.addColumn("Local");
        modelTbItens.addColumn("Cadastrador");

        for (Objeto objetoLista : ObjetoDAO.getInstancia().getList()) {
            if(objetoLista.getStatus().equals(TipoStatus.PERDIDO)){
                modelTbItens.addRow(new Object[]{objetoLista.getCodigo(), objetoLista.getDescricao(), objetoLista.getStatus(),
                    objetoLista.getTipoObjeto(), ControladorPrincipal.getInstancia().retornarNomeLocal(objetoLista.getLocal()),
                    ControladorPrincipal.getInstancia().retornarNomePessoa(objetoLista.getCadastrador())});                
            }
            
        }
        tabelaObjeto.setModel(modelTbItens);
        this.repaint();
    }
    
    //NAO SEI ONDE QUE FICA ISSO AQUI
    private void updateDataCompleta() {
        DefaultTableModel modelTbCompItens = new DefaultTableModel();
        modelTbCompItens.addColumn("Codigo");
        modelTbCompItens.addColumn("Descricao");
        modelTbCompItens.addColumn("Status");
        modelTbCompItens.addColumn("Tipo Objeto");
        modelTbCompItens.addColumn("Local");
        modelTbCompItens.addColumn("Cadastrador");
        modelTbCompItens.addColumn("Dono");

        for (Objeto objetoLista : ObjetoDAO.getInstancia().getList()) {
            modelTbCompItens.addRow(new Object[]{objetoLista.getCodigo(), 
                objetoLista.getDescricao(), objetoLista.getStatus(), objetoLista.getTipoObjeto(), 
                ControladorPrincipal.getInstancia().retornarNomeLocal(objetoLista.getLocal()),
                ControladorPrincipal.getInstancia().retornarNomePessoa(objetoLista.getCadastrador()),
                ControladorPrincipal.getInstancia().retornarNomePessoa(objetoLista.getDono())});                
            
        }
        tabelaCompleta.setModel(modelTbCompItens);
        this.repaint();
    }    

    public void exibirDadosObjeto(int codigo, String descricao, TipoStatus status, TipoObjeto tipoObjeto, String nomeLocal, String nomeCadastrador) {
        System.out.println("----------OBJETO CADASTRADO----------");
        System.out.println("Codigo: " + codigo);
        System.out.println("Descricao: " + descricao);
        System.out.println("Status: " + status);
        System.out.println("Tipo do objeto: " + tipoObjeto);
        System.out.println("Local: " + nomeLocal);
        System.out.println("Cadastrador: " + nomeCadastrador);
    }

    public void mostrarTelaCadastrar() {
        container.remove(pnlTelaObjeto);
        container.remove(pnlTelaAlterar);
        container.remove(pnlTelaCadastrar);
        container.add(pnlTelaCadastrar);
        container.revalidate();
        container.repaint();
    }
    
    public void mostrarTelaAlterar() {
        container.remove(pnlTelaObjeto);
        container.remove(pnlTelaCadastrar);
        container.remove(pnlTelaAlterar);
        container.add(pnlTelaAlterar);
        container.revalidate();
        container.repaint();
    }
    
    public void limpaBotaoCadastrar(){
        txtDescricao.setText("");
        txtLocal.setText("");
        txtCadastrador.setText("");
    }
    
    public void limpaBotaoAlterar(){
        txtCodigo.setText("");
        txtDono.setText("");
    }    

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String codigo = txtCodigo.getText();
            String descricao = txtDescricao.getText();
            TipoStatus statusCadastrar = (TipoStatus) cmbStatusCadastrar.getSelectedItem();
            TipoStatus statusAlterar = (TipoStatus) cmbStatusAlterar.getSelectedItem();
            TipoObjeto tipoObjeto = (TipoObjeto) cmbTipoObjeto.getSelectedItem();
            String local = txtLocal.getText();
            String cadastrador = txtCadastrador.getText();
            String dono = txtDono.getText();
            
            System.out.println(codigo);
            System.out.println(descricao);
            System.out.println(statusCadastrar);
            System.out.println(statusAlterar);
            System.out.println(tipoObjeto);
            System.out.println(local);
            System.out.println(cadastrador);
            System.out.println(dono);

            switch (ae.getActionCommand()) {
                case "1": mostrarTelaCadastrar();
                    break;
                case "2": mostrarTelaAlterar();
                    break;
                case "3":
                    ControladorObjeto.getInstancia().cadastrarObjetos(descricao, statusCadastrar,
                            tipoObjeto, local, cadastrador);
                    limpaBotaoCadastrar();
                    updateData();
                    break;
                case "4":
                    ControladorObjeto.getInstancia().atualizarStatusObjeto(Integer.parseInt(codigo),
                            statusAlterar, dono);
                    limpaBotaoAlterar();
                    updateData();
                    break;
            }

        }
    }

}
