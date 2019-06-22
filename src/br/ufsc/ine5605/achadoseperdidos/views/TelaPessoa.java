/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.views;

import br.ufsc.ine5605.achadoseperdidos.models.Aluno;
import br.ufsc.ine5605.achadoseperdidos.models.Funcionario;
import br.ufsc.ine5605.achadoseperdidos.models.Visitante;
import br.ufsc.ine5605.achadoseperdidos.controllers.ControladorPessoa;
import java.util.InputMismatchException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author Henrique Meireles
 */
public class TelaPessoa extends TelaGlobal {

    // private Scanner teclado; > Deletado para JFrame
    private JLabel lblNomeAluno;
    private JTextField txtNomeAluno;
    private JLabel lblNomeVisitante;
    private JTextField txtNomeVisitante;
    private JLabel lblNomeFuncionario;
    private JTextField txtNomeFuncionario;
    private JLabel lblTelefoneAluno;
    private JTextField txtTelefoneAluno;
    private JLabel lblTelefoneVisitante;
    private JTextField txtTelefoneVisitante;
    private JLabel lblTelefoneFuncionario;
    private JTextField txtTelefoneFuncionario;
    private JLabel lblMatricula;
    private JTextField txtMatricula;
    private JLabel lblSiape;
    private JTextField txtSiape;
    private JLabel lblCpf;
    private JTextField txtCpf;
    private JButton btnCadastrarAluno;
    private JButton btnExcluirAluno;
    private JButton btnAlterarAluno;
    private JButton btnCadastrarFuncionario;
    private JButton btnExcluirFuncionario;
    private JButton btnAlterarFuncionario;
    private JButton btnCadastrarVisitante;
    private JButton btnExcluirVisitante;
    private JButton btnAlterarVisitante;
    private JPanel pnlTelaPrincipal;
    private JPanel pnlTelaAluno;
    private JPanel pnlTelaVisitante;
    private JPanel pnlTelaFuncionario;
    private Container container;

    public TelaPessoa() {
        super("Tela Pessoa");

    }

    public void initComponents() {

        //Criando o container principal
        container = getContentPane();
        container.setLayout(new GridBagLayout());

        //carregando os paineis nas variaveis da classe
        pnlTelaAluno = telaAluno();
        pnlTelaFuncionario = telaFuncionario();
        pnlTelaVisitante = telaVisitante();

        //setando o panel principal
        pnlTelaPrincipal = telaPrincipal();
        container.add(pnlTelaPrincipal);

        setSize(600, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }

    public JPanel telaPrincipal() {
        //Componentes da tela
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JMenuBar menPessoa = new JMenuBar();
        setJMenuBar(menPessoa);

        JMenu aluno = new JMenu("Aluno");
        menPessoa.add(aluno);

        JMenuItem telaAluno = new JMenuItem("Operações Aluno");
        aluno.add(telaAluno);
        telaAluno.setActionCommand("1");

        JMenu funcionario = new JMenu("Funcionario");
        menPessoa.add(funcionario);

        JMenuItem telaFuncionario = new JMenuItem("Operações Funcionario");
        funcionario.add(telaFuncionario);
        telaFuncionario.setActionCommand("2");

        JMenu visitante = new JMenu("Visitante");
        menPessoa.add(visitante);

        JMenuItem telaVisitante = new JMenuItem("Operações Visitante");
        visitante.add(telaVisitante);
        telaVisitante.setActionCommand("3");

        GerenciadorBotoes gerenciadorBotoes = new GerenciadorBotoes();
        telaAluno.addActionListener(gerenciadorBotoes);
        telaFuncionario.addActionListener(gerenciadorBotoes);
        telaVisitante.addActionListener(gerenciadorBotoes);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return painelPrincipal;
    }

    public JPanel telaAluno() {
        //Componentes da tela
        JPanel painelAluno = new JPanel();
        painelAluno.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        lblMatricula = new JLabel();
        c.gridx = 0;
        c.gridy = 0;
        painelAluno.add(lblMatricula, c);

        txtMatricula = new JTextField(20);
        c.gridx = 1;
        c.gridy = 0;
        painelAluno.add(txtMatricula, c);

        lblNomeAluno = new JLabel();
        c.gridx = 0;
        c.gridy = 1;
        painelAluno.add(lblNomeAluno, c);

        txtNomeAluno = new JTextField(20);
        c.gridx = 1;
        c.gridy = 1;
        painelAluno.add(txtNomeAluno, c);

        lblTelefoneAluno = new JLabel();
        c.gridx = 0;
        c.gridy = 2;
        painelAluno.add(lblTelefoneAluno, c);

        txtTelefoneAluno = new JTextField(20);
        c.gridx = 1;
        c.gridy = 2;
        painelAluno.add(txtTelefoneAluno, c);

        btnCadastrarAluno = new JButton();
        c.gridx = 1;
        c.gridy = 3;
        painelAluno.add(btnCadastrarAluno, c);

        btnExcluirAluno = new JButton();
        c.gridx = 1;
        c.gridy = 4;
        painelAluno.add(btnExcluirAluno, c);

        btnAlterarAluno = new JButton();
        c.gridx = 1;
        c.gridy = 5;
        painelAluno.add(btnAlterarAluno, c);
        //Conteudo dos componentes
        lblNomeAluno.setText("Nome: ");
        lblTelefoneAluno.setText("Telefone: ");
        lblMatricula.setText("Matricula: ");
        btnCadastrarAluno.setText("Cadastrar Aluno");
        btnExcluirAluno.setText("Excluir Aluno");
        btnAlterarAluno.setText("Alterar Aluno");

        //setando os action commands
        btnCadastrarAluno.setActionCommand("1");
        btnAlterarAluno.setActionCommand("2");
        btnExcluirAluno.setActionCommand("3");

        //Acao dos Botoes
        GerenciadorBotoesAluno gerenciadorBtnCadastrarAluno = new GerenciadorBotoesAluno();
        btnCadastrarAluno.addActionListener(gerenciadorBtnCadastrarAluno);
        btnExcluirAluno.addActionListener(gerenciadorBtnCadastrarAluno);
        btnAlterarAluno.addActionListener(gerenciadorBtnCadastrarAluno);

        //Adicionando componentes a tela
        //Configuracoes da tela
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        return painelAluno;
    }

    public void excluirAluno() {
        System.out.println("----------EXCLUIR ALUNO----------");
        System.out.println("Matricula: ");
        int matricula = recebeValorInteiro();
        ControladorPessoa.getInstancia().excluirAluno(matricula);
    }

    public void listarAlunos() {
        System.out.println("----------LISTA DE ALUNOS----------");
        System.out.println("Nome               Telefone               Matricula");
        ControladorPessoa.getInstancia().listarAlunos();
    }

    public void alterarAluno() {
        System.out.println("----------ALTERAR ALUNO----------");
        System.out.println("Digite a Matricula do aluno que deseja alterar: ");
        int matricula = recebeValorInteiro();
        Aluno aluno = ControladorPessoa.getInstancia().encontrarAlunoPelaMatricula(matricula);
        this.exibirDadosAluno(aluno.getNomePessoa(), aluno.getTelefonePessoa(), aluno.getMatricula());
        System.out.println("----------------------------------");
        System.out.println("Novo Nome: ");
        String novoNome = recebeValorString();
        System.out.println("Novo Telefone: ");
        long novoTelefone = recebeValorLong();
        System.out.println("Nova Matricula: ");
        int novaMatricula = recebeValorInteiro();
        ControladorPessoa.getInstancia().alterarAluno(aluno, novoNome, novoTelefone, novaMatricula);
        this.exibirDadosAluno(novoNome, novoTelefone, novaMatricula);
    }

    public JPanel telaFuncionario() {
        JPanel painelFuncionario = new JPanel();
        painelFuncionario.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        lblSiape = new JLabel();
        c.gridx = 0;
        c.gridy = 0;
        painelFuncionario.add(lblSiape, c);

        txtSiape = new JTextField(20);
        c.gridx = 1;
        c.gridy = 0;
        painelFuncionario.add(txtSiape, c);

        lblNomeFuncionario = new JLabel();
        c.gridx = 0;
        c.gridy = 1;
        painelFuncionario.add(lblNomeFuncionario, c);

        txtNomeFuncionario = new JTextField(20);
        c.gridx = 1;
        c.gridy = 1;
        painelFuncionario.add(txtNomeFuncionario, c);

        lblTelefoneFuncionario = new JLabel();
        c.gridx = 0;
        c.gridy = 2;
        painelFuncionario.add(lblTelefoneFuncionario, c);

        txtTelefoneFuncionario = new JTextField(20);
        c.gridx = 1;
        c.gridy = 2;
        painelFuncionario.add(txtTelefoneFuncionario, c);

        btnCadastrarFuncionario = new JButton();
        c.gridx = 1;
        c.gridy = 3;
        painelFuncionario.add(btnCadastrarFuncionario, c);

        btnExcluirFuncionario = new JButton();
        c.gridx = 1;
        c.gridy = 4;
        painelFuncionario.add(btnExcluirFuncionario, c);

        btnAlterarFuncionario = new JButton();
        c.gridx = 1;
        c.gridy = 5;
        painelFuncionario.add(btnAlterarFuncionario, c);

        lblNomeFuncionario.setText("Nome: ");
        lblTelefoneFuncionario.setText("Telefone: ");
        lblSiape.setText("Siape: ");
        btnCadastrarFuncionario.setText("Cadastrar Funcionario");
        btnExcluirFuncionario.setText("Excluir Funcionario");
        btnAlterarFuncionario.setText("Alterar Funcionario");

        //setando os action commands
        btnCadastrarFuncionario.setActionCommand("1");
        btnAlterarFuncionario.setActionCommand("2");
        btnExcluirFuncionario.setActionCommand("3");

        //Acao dos Botoes
        GerenciadorBotoesFuncionario gerenciadorBtnFuncionario = new GerenciadorBotoesFuncionario();
        btnCadastrarFuncionario.addActionListener(gerenciadorBtnFuncionario);
        btnExcluirFuncionario.addActionListener(gerenciadorBtnFuncionario);
        btnAlterarFuncionario.addActionListener(gerenciadorBtnFuncionario);

        //Configuracoes da tela
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return painelFuncionario;

    }

    public void excluirFuncionario() {
        System.out.println("----------EXCLUIR FUNCIONARIO----------");
        System.out.println("Siape: ");
        int siape = recebeValorInteiro();
        ControladorPessoa.getInstancia().excluirFuncionario(siape);
    }

    public void listarFuncionarios() {
        System.out.println("----------LISTA DE FUNCIONARIOS----------");
        System.out.println("Nome               Telefone               Siape");
        ControladorPessoa.getInstancia().listarFuncionarios();
    }

    public void alterarFuncionario() {
        System.out.println("----------ALTERAR FUNCIONARIO----------");
        System.out.println("Digite o Siape do funcionario que deseja alterar: ");
        int siape = recebeValorInteiro();
        Funcionario funcionario = ControladorPessoa.getInstancia().encontrarFuncionarioPeloSiape(siape);
        this.exibirDadosFuncionario(funcionario.getNomePessoa(), funcionario.getTelefonePessoa(), funcionario.getSiape());
        System.out.println("----------------------------------");
        System.out.println("Novo Nome: ");
        String novoNome = recebeValorString();
        System.out.println("Novo Telefone: ");
        long novoTelefone = recebeValorLong();
        System.out.println("Novo Siape: ");
        int novoSiape = recebeValorInteiro();
        ControladorPessoa.getInstancia().alterarFuncionario(funcionario, novoNome, novoTelefone, novoSiape);
        this.exibirDadosFuncionario(novoNome, novoTelefone, novoSiape);
    }

    public JPanel telaVisitante() {
        JPanel painelVisitante = new JPanel();
        painelVisitante.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        lblCpf = new JLabel();
        c.gridx = 0;
        c.gridy = 0;
        painelVisitante.add(lblCpf, c);

        txtCpf = new JTextField(20);
        c.gridx = 1;
        c.gridy = 0;
        painelVisitante.add(txtCpf, c);

        lblNomeVisitante = new JLabel();
        c.gridx = 0;
        c.gridy = 1;
        painelVisitante.add(lblNomeVisitante, c);

        txtNomeVisitante = new JTextField(20);
        c.gridx = 1;
        c.gridy = 1;
        painelVisitante.add(txtNomeVisitante, c);

        lblTelefoneVisitante = new JLabel();
        c.gridx = 0;
        c.gridy = 2;
        painelVisitante.add(lblTelefoneVisitante, c);

        txtTelefoneVisitante = new JTextField(20);
        c.gridx = 1;
        c.gridy = 2;
        painelVisitante.add(txtTelefoneVisitante, c);

        btnCadastrarVisitante = new JButton();
        c.gridx = 1;
        c.gridy = 3;
        painelVisitante.add(btnCadastrarVisitante, c);

        btnExcluirVisitante = new JButton();
        c.gridx = 1;
        c.gridy = 4;
        painelVisitante.add(btnExcluirVisitante, c);

        btnAlterarVisitante = new JButton();
        c.gridx = 1;
        c.gridy = 5;
        painelVisitante.add(btnAlterarVisitante, c);

        lblNomeVisitante.setText("Nome: ");
        lblTelefoneVisitante.setText("Telefone: ");
        lblCpf.setText("Cpf: ");
        btnCadastrarVisitante.setText("Cadastrar Visitante");
        btnExcluirVisitante.setText("Excluir Visitante");
        btnAlterarVisitante.setText("Alterar Visitante");

        btnCadastrarVisitante.setActionCommand("1");
        btnAlterarVisitante.setActionCommand("2");
        btnExcluirVisitante.setActionCommand("3");

        GerenciadorBotoesVisitante gerenciadorBtnVisitante = new GerenciadorBotoesVisitante();
        btnCadastrarVisitante.addActionListener(gerenciadorBtnVisitante);
        btnExcluirVisitante.addActionListener(gerenciadorBtnVisitante);
        btnAlterarVisitante.addActionListener(gerenciadorBtnVisitante);

        return painelVisitante;
    }

    public void excluirVisitante() {
        System.out.println("----------EXCLUIR VISITANTE----------");
        System.out.println("Cpf: ");
        long cpf = recebeValorLong();
        ControladorPessoa.getInstancia().excluirVisitante(cpf);
    }

    public void listarVisitantes() {
        System.out.println("----------LISTA DE VISITANTES----------");
        System.out.println("Nome               Telefone               Cpf");
        ControladorPessoa.getInstancia().listarVisitantes();
    }

    public void alterarVisitante() {
        System.out.println("----------ALTERAR VISITANTE----------");
        System.out.println("Digite o Cpf do visitante que deseja alterar: ");
        int cpf = recebeValorInteiro();
        Visitante visitante = ControladorPessoa.getInstancia().encontrarVisitantePeloCpf(cpf);
        this.exibirDadosVisitante(visitante.getNomePessoa(), visitante.getTelefonePessoa(), visitante.getCpf());
        System.out.println("----------------------------------");
        System.out.println("Novo Nome: ");
        String novoNome = recebeValorString();
        System.out.println("Novo Telefone: ");
        long novoTelefone = recebeValorLong();
        System.out.println("Novo Cpf: ");
        int novoCpf = recebeValorInteiro();
        ControladorPessoa.getInstancia().alterarVisitante(visitante, novoNome, novoTelefone, novoCpf);
        this.exibirDadosVisitante(novoNome, novoTelefone, novoCpf);
    }

    public void exibirDadosAluno(String nome, long telefone, int matricula) {
        System.out.println("------------------------");
        System.out.println("Nome: " + nome);
        System.out.println("Telefone: " + telefone);
        System.out.println("Matricula: " + matricula);
    }

    public void exibirDadosVisitante(String nome, long telefone, long cpf) {
        System.out.println("------------------------");
        System.out.println("Nome: " + nome);
        System.out.println("Telefone: " + telefone);
        System.out.println("Cpf: " + cpf);
    }

    public void exibirDadosFuncionario(String nome, long telefone, int siape) {
        System.out.println("------------------------");
        System.out.println("Nome: " + nome);
        System.out.println("Telefone: " + telefone);
        System.out.println("Siape: " + siape);
    }

    public void mostrarTelaPrincipal() {
        container.remove(pnlTelaPrincipal);
        container.remove(pnlTelaAluno);
        container.remove(pnlTelaFuncionario);
        container.remove(pnlTelaVisitante);
        container.add(pnlTelaPrincipal);
        container.revalidate();
        container.repaint();
    }

    public void mostrarTelaAluno() {
        container.remove(pnlTelaPrincipal);
        container.remove(pnlTelaAluno);
        container.remove(pnlTelaFuncionario);
        container.remove(pnlTelaVisitante);
        container.add(pnlTelaAluno);
        container.revalidate();
        container.repaint();
    }

    public void mostrarTelaVisitante() {
        container.remove(pnlTelaPrincipal);
        container.remove(pnlTelaAluno);
        container.remove(pnlTelaFuncionario);
        container.remove(pnlTelaVisitante);
        container.add(pnlTelaVisitante);
        container.revalidate();
        container.repaint();
    }

    public void mostrarTelaFuncionario() {
        container.remove(pnlTelaPrincipal);
        container.remove(pnlTelaAluno);
        container.remove(pnlTelaFuncionario);
        container.remove(pnlTelaVisitante);
        container.add(pnlTelaFuncionario);
        container.revalidate();
        container.repaint();
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            ControladorPessoa.getInstancia().exibirTelas(ae.getActionCommand());
        }
    }

    private class GerenciadorBotoesAluno implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String nome;
            long telefone;
            int matricula;
            switch (ae.getActionCommand()) {
                case "1":
                    nome = txtNomeAluno.getText();
                    telefone = Long.parseLong(txtTelefoneAluno.getText());
                    matricula = Integer.parseInt(txtMatricula.getText());
                    ControladorPessoa.getInstancia().cadastrarAluno(nome, telefone, matricula);
                    break;
                case "2":
                    nome = txtNomeAluno.getText();
                    telefone = Long.parseLong(txtTelefoneAluno.getText());
                    matricula = Integer.parseInt(txtMatricula.getText());
                    Aluno aluno = ControladorPessoa.getInstancia().encontrarAlunoPelaMatricula(matricula);
                    ControladorPessoa.getInstancia().alterarAluno(aluno, nome, telefone, matricula);
                    break;
                case "3":
                    matricula = Integer.parseInt(txtMatricula.getText());
                    ControladorPessoa.getInstancia().excluirAluno(matricula);
                    break;
            }
        }
    }

    private class GerenciadorBotoesFuncionario implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String nome;
            long telefone;
            int siape;

            switch (ae.getActionCommand()) {
                case "1":
                    nome = txtNomeFuncionario.getText();
                    telefone = Long.parseLong(txtTelefoneFuncionario.getText());
                    siape = Integer.parseInt(txtSiape.getText());
                    ControladorPessoa.getInstancia().cadastrarFuncionario(nome, telefone, siape);
                    break;
                case "2":
                    nome = txtNomeFuncionario.getText();
                    telefone = Long.parseLong(txtTelefoneFuncionario.getText());
                    siape = Integer.parseInt(txtSiape.getText());
                    Funcionario funcionario = ControladorPessoa.getInstancia().encontrarFuncionarioPeloSiape(siape);
                    ControladorPessoa.getInstancia().alterarFuncionario(funcionario, nome, telefone, siape);
                    break;
                case "3":
                    siape = Integer.parseInt(txtSiape.getText());
                    ControladorPessoa.getInstancia().excluirFuncionario(siape);
                    break;
            }
        }

    }

    private class GerenciadorBotoesVisitante implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String nome;
            long telefone;
            int cpf;

            switch (ae.getActionCommand()) {
                case "1":
                    nome = txtNomeVisitante.getText();
                    telefone = Long.parseLong(txtTelefoneVisitante.getText());
                    cpf = Integer.parseInt(txtCpf.getText());
                    ControladorPessoa.getInstancia().cadastrarVisitante(nome, telefone, cpf);
                    break;
                case "2":
                    nome = txtNomeVisitante.getText();
                    telefone = Long.parseLong(txtTelefoneVisitante.getText());
                    cpf = Integer.parseInt(txtCpf.getText());
                    Visitante visitante = ControladorPessoa.getInstancia().encontrarVisitantePeloCpf(cpf);
                    ControladorPessoa.getInstancia().alterarVisitante(visitante, nome, telefone, cpf);
                    break;
                case "3":
                    cpf = Integer.parseInt(txtCpf.getText());
                    ControladorPessoa.getInstancia().excluirVisitante(cpf);
                    break;
            }
        }

    }

}
