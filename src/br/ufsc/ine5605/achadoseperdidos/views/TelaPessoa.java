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
import java.awt.Container;
import java.awt.GridBagLayout;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Henrique Meireles
 */public class TelaPessoa extends TelaGlobal{

   // private Scanner teclado; > Deletado para JFrame
     private JButton btnVoltar;
    

    public TelaPessoa() {
         super("Tela Pessoa");
        
         
    }

    public void initComponents() {
       
        //teclado = new Scanner(System.in);
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        
        btnVoltar = new JButton();
        btnVoltar.setText("Voltar");
        btnVoltar.setActionCommand("1");
        
        container.add(btnVoltar);
        
        setSize(360, 150);
        
        setVisible(true);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*int escolhaTipo;

        System.out.println("----------TELA PESSOA----------");
        System.out.println("1 - Cadastrar uma Pessoa");
        System.out.println("2 - Excluir uma Pessoa");
        System.out.println("3 - Alterar uma Pessoa");
        System.out.println("4 - Listar Pessoas");
        System.out.println("0 - Voltar");
        System.out.println("-------------------------------");
        System.out.print("Digite o número da operação desejada: ");
        escolhaTipo = recebeValorInteiro();
        switch (escolhaTipo) {
            case 1:
                System.out.println("----------CADASTRAR PESSOA----------");
                System.out.println("1 - Cadastrar um Aluno");
                System.out.println("2 - Cadastrar um Funcionario");
                System.out.println("3 - Cadastrar um Visitante");
                System.out.println("0 - Voltar");
                System.out.println("------------------------------------");
                System.out.print("Digite o número da operação desejada: ");
                int opcao = recebeValorInteiro();
                switch (opcao) {
                    case 1:
                        this.incluirAluno();
                        break;
                    case 2:
                        this.incluirFuncionario();
                        break;
                    case 3:
                        this.incluirVisitante();
                        break;
                    case 0:
                        this.menuInicial();
                        break;
                    default:
                        System.out.println("Operacao invalida!");
                }
                break;
            case 2:
                System.out.println("----------EXCLUIR PESSOA----------");
                System.out.println("1 - Excluir Aluno");
                System.out.println("2 - Excluir Funcionario");
                System.out.println("3 - Excluir Visitante");
                System.out.println("0 - Voltar");
                System.out.println("------------------------------------");
                System.out.print("Digite o número da operação desejada: ");
                opcao = recebeValorInteiro();
                switch (opcao) {
                    case 1:
                        this.excluirAluno();
                        break;
                    case 2:
                        this.excluirFuncionario();
                        break;
                    case 3:
                        this.excluirVisitante();
                        break;
                    case 0:
                        this.menuInicial();
                        break;
                    default:
                        System.out.println("Operacao invalida!");
                }
                break;
            case 3:
                System.out.println("----------ALTERAR PESSOA----------");
                System.out.println("1 - Alterar um Aluno");
                System.out.println("2 - Alterar um Funcionario");
                System.out.println("3 - Alterar um Visitante");
                System.out.println("0 - Voltar");
                System.out.println("------------------------------------");
                System.out.print("Digite o número da operação desejada: ");
                opcao = recebeValorInteiro();
                switch (opcao) {
                    case 1:
                        this.alterarAluno();
                        break;
                    case 2:
                        this.alterarFuncionario();
                        break;
                    case 3:
                        this.alterarVisitante();
                        break;
                    case 0:
                        this.menuInicial();
                        break;
                    default:
                        System.out.println("Operacao invalida!");
                }
                break;
            case 4:
                System.out.println("----------LISTAR PESSOA----------");
                System.out.println("1 - Listar Alunos");
                System.out.println("2 - Listar Funcionarios");
                System.out.println("3 - Listar Visitantes");
                System.out.println("0 - Voltar");
                System.out.println("------------------------------------");
                System.out.print("Digite o número da operação desejada: ");
                opcao = recebeValorInteiro();
                switch (opcao) {
                    case 1:
                        this.listarAlunos();
                        break;
                    case 2:
                        this.listarFuncionarios();
                        break;
                    case 3:
                        this.listarVisitantes();
                        break;
                    case 0:
                        this.menuInicial();
                        break;
                    default:
                        System.out.println("Operacao invalida!");
                }
                break;
            case 0:
                (ControladorPessoa.getInstancia().getControladorPrincipal()).inicia();
                break;
            default:
                System.out.println("--------------------------------------");
                System.out.println("Operação inválida!");
                break;
        }
      */
    }

    public void incluirAluno() {
        System.out.println("----------CADASTRAR ALUNO----------");
        System.out.println("Nome: ");
        String nome = recebeValorString();
        System.out.println("Telefone: ");
        long telefone = recebeValorLong();
        System.out.println("Matricula: ");
        int matricula = recebeValorInteiro();
        ControladorPessoa.getInstancia().cadastrarAluno(nome, telefone, matricula);
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

    private void incluirFuncionario() {
        System.out.println("----------CADASTRAR FUNCIONARIO----------");
        System.out.println("Nome: ");
        String nome = recebeValorString();
        System.out.println("Telefone: ");
        long telefone = recebeValorLong();
        System.out.println("Siape: ");
        int siape = recebeValorInteiro();
        ControladorPessoa.getInstancia().cadastrarFuncionario(nome, telefone, siape);
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

    public void incluirVisitante() {
        System.out.println("----------CADASTRAR VISITANTE----------");
        System.out.println("Nome: ");
        String nome = recebeValorString();
        System.out.println("Telefone: ");
        long telefone = recebeValorLong();
        System.out.println("Cpf: ");
        int cpf = recebeValorInteiro();
        ControladorPessoa.getInstancia().cadastrarVisitante(nome, telefone, cpf);
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

}
