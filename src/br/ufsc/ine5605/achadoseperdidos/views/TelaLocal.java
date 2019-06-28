/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.views;

import br.ufsc.ine5605.achadoseperdidos.controllers.ControladorLocal;
import br.ufsc.ine5605.achadoseperdidos.exceptions.LocalJaCadastradoException;
import br.ufsc.ine5605.achadoseperdidos.exceptions.LocalNaoExisteException;
import br.ufsc.ine5605.achadoseperdidos.exceptions.ObjetoComLocalException;
import br.ufsc.ine5605.achadoseperdidos.exceptions.ValoresNulosException;
import br.ufsc.ine5605.achadoseperdidos.models.Local;
import br.ufsc.ine5605.achadoseperdidos.persistencia.LocalDAO;
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

    private JTable tabelaLocal;
    private GridBagConstraints constraint;
    private JScrollPane spBaseTabela;
    private JLabel lblNomeLocal;
    private JTextField txtNomeLocal;
    private JLabel lblLocalizacao;
    private JTextField txtLocalizacao;
    private JButton btnCadastrar;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private Container container;
    private JPanel pnlTelaLocal;
    private String nomeLocalAlterar;

    public TelaLocal() {
        super("Tela Local");
        constraint = new GridBagConstraints();
        initComponents();
    }

    public void initComponents() {
        container = getContentPane();
        container.setLayout(new GridBagLayout());

        pnlTelaLocal = telaLocal();
        container.add(pnlTelaLocal);

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }

    private JPanel telaLocal() {
        //Componentes da tela
        JPanel painelObjeto = new JPanel();
        painelObjeto.setLayout(new GridBagLayout());
        GridBagConstraints gridConstraint = new GridBagConstraints();

        //Componentes de campo
        lblNomeLocal = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 1;
        painelObjeto.add(lblNomeLocal, gridConstraint);

        txtNomeLocal = new JTextField(20);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 1;
        painelObjeto.add(txtNomeLocal, gridConstraint);

        lblLocalizacao = new JLabel();
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 2;
        painelObjeto.add(lblLocalizacao, gridConstraint);

        txtLocalizacao = new JTextField(20);
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 2;
        painelObjeto.add(txtLocalizacao, gridConstraint);

        btnCadastrar = new JButton();
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 3;
        painelObjeto.add(btnCadastrar, gridConstraint);

        btnAlterar = new JButton();
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 4;
        painelObjeto.add(btnAlterar, gridConstraint);

        btnExcluir = new JButton();
        gridConstraint.gridx = 1;
        gridConstraint.gridy = 5;
        painelObjeto.add(btnExcluir, gridConstraint);

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
        painelObjeto.add(spBaseTabela, constraint);
        //Fim componentes da tela

        //Conteudo dentro dos componentes da tela
        lblNomeLocal.setText("Nome do Local: ");
        lblLocalizacao.setText("Localizacao: ");
        btnCadastrar.setText("Cadastrar");
        btnAlterar.setText("Alterar");
        btnExcluir.setText("Excluir");

        //Setando acoes nos botoes
        btnCadastrar.setActionCommand("1");
        btnAlterar.setActionCommand("2");
        btnExcluir.setActionCommand("3");

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
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return painelObjeto;
    }

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
        container.remove(pnlTelaLocal);
        container.add(pnlTelaLocal);
        updateData();
        container.revalidate();
        container.repaint();
    }

    public void mostrarTelas() {
        setVisible(true);
    }

    public void limparFields() {
        txtNomeLocal.setText("");
        txtLocalizacao.setText("");
    }

    public void tabelaLocalClique(MouseEvent ev) {
        DefaultTableModel model = (DefaultTableModel) tabelaLocal.getModel();
        int indexLinhaSelecionada = tabelaLocal.getSelectedRow();
        txtNomeLocal.setText(model.getValueAt(indexLinhaSelecionada, 0).toString());
        txtLocalizacao.setText(model.getValueAt(indexLinhaSelecionada, 1).toString());
        nomeLocalAlterar = "";
        nomeLocalAlterar = txtNomeLocal.getText();
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String nomeLocal = txtNomeLocal.getText();
            String localizacao = txtLocalizacao.getText();

            switch (ae.getActionCommand()) {
                case "1":
                    try {
                        ControladorLocal.getInstancia().cadastrarLocal(nomeLocal, localizacao);
                    } catch (LocalJaCadastradoException ex) {
                        exibirMensagem(ex.getMessage());
                    } catch (ValoresNulosException ex) {
                        exibirMensagem(ex.getMessage());
                    }
                    updateData();
                    limparFields();
                    break;
                case "2":
                    Local local = ControladorLocal.getInstancia().encontrarLocalPeloNome(nomeLocalAlterar);
                    try {
                        ControladorLocal.getInstancia().atualizarDadosLocal(local, nomeLocal, localizacao);
                    } catch (ObjetoComLocalException ex) {
                        exibirMensagem(ex.getMessage());
                    } catch (ValoresNulosException ex) {
                        exibirMensagem(ex.getMessage());
                    } catch (LocalJaCadastradoException ex) {
                        exibirMensagem(ex.getMessage());
                    } catch (LocalNaoExisteException ex) {
                        exibirMensagem(ex.getMessage());
                    }
                    updateData();
                    limparFields();
                    break;
                case "3":
                    int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir " + nomeLocal + "?", "Confirmar",
                            JOptionPane.YES_NO_OPTION, JOptionPane.OK_CANCEL_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        try {
                            ControladorLocal.getInstancia().excluirLocal(nomeLocal);
                        } catch (ObjetoComLocalException ex) {
                            exibirMensagem(ex.getMessage());
                        } catch (LocalNaoExisteException ex) {
                            exibirMensagem(ex.getMessage());
                        }
                    } else {
                        exibirMensagem("Botão NAO pressionado. Local nao foi excluido!");
                    }
                    updateData();
                    limparFields();
                    break;
            }

        }
    }

}
