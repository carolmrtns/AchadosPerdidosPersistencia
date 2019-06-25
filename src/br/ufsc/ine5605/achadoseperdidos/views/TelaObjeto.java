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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Caroline Martins Alves
 */
public class TelaObjeto extends TelaGlobal {
    private JTable tabelaObjetoAlterar;
    private JTable tabelaObjetoCadastrar;
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
    private JScrollPane spBaseTabelaAlt;
    private JScrollPane spBaseTabelaComp;
    private JScrollPane spBaseTabelaCad;
    private Container container;
    private JPanel pnlTelaObjeto;
    private JPanel pnlTelaCadastrar;
    private JPanel pnlTelaAlterar;

    public TelaObjeto() {
        super("Tela Objeto");
        initComponents();
    }

    public void initComponents() {

        container = getContentPane();
        container.setLayout(new GridBagLayout());

        pnlTelaCadastrar = telaCadastrar();
        pnlTelaAlterar = telaAlterar();

        pnlTelaObjeto = telaObjeto();
        container.remove(pnlTelaCadastrar);
        container.remove(pnlTelaAlterar);
        container.add(pnlTelaObjeto);

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }

    public JPanel telaObjeto() {
        //Componentes da tela
        JPanel painelObjeto = new JPanel();
        painelObjeto.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        //Tabela que lista os dados recuperados do arquivo
        tabelaCompleta = new JTable();
        tabelaCompleta.setPreferredScrollableViewportSize(new Dimension(550, 350));
        tabelaCompleta.setFillsViewportHeight(true);
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridwidth = 2;
        constraint.gridheight = 4;
        constraint.gridx = 0;
        constraint.gridy = 10;
        spBaseTabelaComp = new JScrollPane(tabelaCompleta);
        painelObjeto.add(spBaseTabelaComp, constraint);

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

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return painelObjeto;
    }

    public JPanel telaCadastrar() {
        //Componentes da tela
        JPanel painelCadastro = new JPanel();
        painelCadastro.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        //Componentes de campo
        TipoStatus tipoStatus[] = {TipoStatus.PERDIDO};
        TipoObjeto tipoObjeto[] = {TipoObjeto.ALIMENTO, TipoObjeto.DOCUMENTOS,
            TipoObjeto.ELETROELETRONICO, TipoObjeto.MATERIAIS, TipoObjeto.VESTUARIO};

        lblDescricao = new JLabel();
        constraint.gridx = 0;
        constraint.gridy = 1;
        painelCadastro.add(lblDescricao, constraint);

        txtDescricao = new JTextField(20);
        constraint.gridx = 1;
        constraint.gridy = 1;
        painelCadastro.add(txtDescricao, constraint);

        lblStatus = new JLabel();
        constraint.gridx = 0;
        constraint.gridy = 2;
        painelCadastro.add(lblStatus, constraint);

        cmbStatusCadastrar = new JComboBox(tipoStatus);
        constraint.gridx = 1;
        constraint.gridy = 2;
        painelCadastro.add(cmbStatusCadastrar, constraint);

        lblTipoObjeto = new JLabel();
        constraint.gridx = 0;
        constraint.gridy = 3;
        painelCadastro.add(lblTipoObjeto, constraint);

        cmbTipoObjeto = new JComboBox(tipoObjeto);
        constraint.gridx = 1;
        constraint.gridy = 3;
        painelCadastro.add(cmbTipoObjeto, constraint);

        lblLocal = new JLabel();
        constraint.gridx = 0;
        constraint.gridy = 4;
        painelCadastro.add(lblLocal, constraint);

        txtLocal = new JTextField(20);
        constraint.gridx = 1;
        constraint.gridy = 4;
        painelCadastro.add(txtLocal, constraint);

        lblCadastrador = new JLabel();
        constraint.gridx = 0;
        constraint.gridy = 5;
        painelCadastro.add(lblCadastrador, constraint);

        txtCadastrador = new JTextField(20);
        constraint.gridx = 1;
        constraint.gridy = 5;
        painelCadastro.add(txtCadastrador, constraint);

        btnCadastrar = new JButton();
        constraint.gridx = 0;
        constraint.gridy = 6;
        painelCadastro.add(btnCadastrar, constraint);
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

        //Tabela que lista os dados recuperados do arquivo
        tabelaObjetoCadastrar = new JTable();
        tabelaObjetoCadastrar.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabelaObjetoCadastrar.setFillsViewportHeight(true);
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridwidth = 2;
        constraint.gridheight = 4;
        constraint.gridx = 0;
        constraint.gridy = 10;
        spBaseTabelaCad = new JScrollPane(tabelaObjetoCadastrar);
        painelCadastro.add(spBaseTabelaCad, constraint);

        //Adicionando acao nos buttons
        GerenciadorBotoes gerenciadorBotoes = new GerenciadorBotoes();
        btnCadastrar.addActionListener(gerenciadorBotoes);

        //Configuracoes da tela
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return painelCadastro;
    }

    public JPanel telaAlterar() {
        //Componentes da tela
        JPanel painelAlteracao = new JPanel();
        painelAlteracao.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        //Componentes de campo
        TipoStatus tipoStatus[] = {TipoStatus.ENCONTRADO};

        lblCodigo = new JLabel();
        constraint.gridx = 0;
        constraint.gridy = 0;
        painelAlteracao.add(lblCodigo, constraint);

        txtCodigo = new JTextField(20);
        constraint.gridx = 1;
        constraint.gridy = 0;
        painelAlteracao.add(txtCodigo, constraint);

        lblStatus = new JLabel();
        constraint.gridx = 0;
        constraint.gridy = 2;
        painelAlteracao.add(lblStatus, constraint);

        cmbStatusAlterar = new JComboBox(tipoStatus);
        constraint.gridx = 1;
        constraint.gridy = 2;
        painelAlteracao.add(cmbStatusAlterar, constraint);

        lblDono = new JLabel();
        constraint.gridx = 0;
        constraint.gridy = 3;
        painelAlteracao.add(lblDono, constraint);

        txtDono = new JTextField(20);
        constraint.gridx = 1;
        constraint.gridy = 3;
        painelAlteracao.add(txtDono, constraint);

        btnAlterar = new JButton();
        constraint.gridx = 0;
        constraint.gridy = 4;
        painelAlteracao.add(btnAlterar, constraint);
        //Fim dos componentes de tela

        lblCodigo.setText("Codigo: ");
        lblStatus.setText("Status: ");
        lblDescricao.setText("Descricao: ");
        lblDono.setText("Dono do Objeto: ");
        btnAlterar.setText("Atualizar");

        //Setando acao no button
        btnAlterar.setActionCommand("4");

        //Tabela que lista os dados recuperados do arquivo
        tabelaObjetoAlterar = new JTable();
        tabelaObjetoAlterar.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabelaObjetoAlterar.setFillsViewportHeight(true);
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridwidth = 2;
        constraint.gridheight = 4;
        constraint.gridx = 0;
        constraint.gridy = 10;
        spBaseTabelaAlt = new JScrollPane(tabelaObjetoAlterar);
        painelAlteracao.add(spBaseTabelaAlt, constraint);

        GerenciadorBotoes gerenciadorBotoes = new GerenciadorBotoes();
        btnAlterar.addActionListener(gerenciadorBotoes);

        //Configuracoes de tela
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return painelAlteracao;
    }

    public void updateData() {
        DefaultTableModel modelTbItens = new DefaultTableModel();
        modelTbItens.addColumn("Codigo");
        modelTbItens.addColumn("Descricao");
        modelTbItens.addColumn("Status");
        modelTbItens.addColumn("Tipo Objeto");
        modelTbItens.addColumn("Local");
        modelTbItens.addColumn("Cadastrador");

        for (Objeto objetoLista : ObjetoDAO.getInstancia().getList()) {
            if (objetoLista.getStatus().equals(TipoStatus.PERDIDO)) {
                modelTbItens.addRow(new Object[]{objetoLista.getCodigo(),
                    objetoLista.getDescricao(), objetoLista.getStatus(),
                    objetoLista.getTipoObjeto(),
                    ControladorPrincipal.getInstancia().retornarNomeLocal(objetoLista.getLocal()),
                    ControladorPrincipal.getInstancia().retornarNomePessoa(objetoLista.getCadastrador())

                });
            }
        }
        tabelaObjetoAlterar.setModel(modelTbItens);
        tabelaObjetoCadastrar.setModel(modelTbItens);
        this.repaint();
    }

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

    public void mostrarTelaObjeto() {
        container.remove(pnlTelaObjeto);
        container.remove(pnlTelaCadastrar);
        container.remove(pnlTelaAlterar);
        container.add(pnlTelaObjeto);
        updateDataCompleta();
        container.revalidate();
        container.repaint();
    }

    public void mostrarTelaCadastrar() {
        container.remove(pnlTelaObjeto);
        container.remove(pnlTelaCadastrar);
        container.remove(pnlTelaAlterar);
        container.add(pnlTelaCadastrar);
        updateData();
        container.revalidate();
        container.repaint();
    }

    public void mostrarTelaAlterar() {
        container.remove(pnlTelaObjeto);
        container.remove(pnlTelaCadastrar);
        container.remove(pnlTelaAlterar);
        container.add(pnlTelaAlterar);
        updateData();
        container.revalidate();
        container.repaint();
    }

    public void mostrarTelas() {
        setVisible(true);
    }

    public void limpaBotaoCadastrar() {
        txtDescricao.setText("");
        txtLocal.setText("");
        txtCadastrador.setText("");
    }

    public void limpaBotaoAlterar() {
        txtCodigo.setText("");
        txtDono.setText("");
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String codigo;
            String descricao;
            TipoStatus statusCadastrar;
            TipoStatus statusAlterar;
            TipoObjeto tipoObjeto;
            String local;
            String cadastrador;
            String dono;

            switch (ae.getActionCommand()) {
                case "1":
                    mostrarTelaCadastrar();
                    break;
                case "2":
                    mostrarTelaAlterar();
                    break;
                case "3":
                    descricao = txtDescricao.getText();
                    statusCadastrar = (TipoStatus) cmbStatusCadastrar.getSelectedItem();
                    tipoObjeto = (TipoObjeto) cmbTipoObjeto.getSelectedItem();
                    local = txtLocal.getText();
                    cadastrador = txtCadastrador.getText();
                    ControladorObjeto.getInstancia().cadastrarObjetos(descricao, statusCadastrar,
                            tipoObjeto, local, cadastrador);
                    updateData();
                    limpaBotaoCadastrar();
                    break;
                case "4":
                    codigo = txtCodigo.getText();
                    statusAlterar = (TipoStatus) cmbStatusAlterar.getSelectedItem();
                    dono = txtDono.getText();
                    ControladorObjeto.getInstancia().atualizarStatusObjeto(Integer.parseInt(codigo),
                            statusAlterar, dono);
                    updateData();
                    limpaBotaoAlterar();
                    break;
            }
        }
    }

}
