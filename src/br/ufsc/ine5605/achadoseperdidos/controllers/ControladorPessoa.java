/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.controllers;

import br.ufsc.ine5605.achadoseperdidos.exceptions.PessoaNaoExisteException;
import br.ufsc.ine5605.achadoseperdidos.exceptions.PessoaExistenteException;
import br.ufsc.ine5605.achadoseperdidos.exceptions.PessoaPossuiVinculoException;
import br.ufsc.ine5605.achadoseperdidos.exceptions.ValoresNulosException;
import br.ufsc.ine5605.achadoseperdidos.models.Aluno;
import br.ufsc.ine5605.achadoseperdidos.models.Funcionario;
import br.ufsc.ine5605.achadoseperdidos.models.Pessoa;
import br.ufsc.ine5605.achadoseperdidos.views.TelaPessoa;
import br.ufsc.ine5605.achadoseperdidos.models.TipoPessoa;
import br.ufsc.ine5605.achadoseperdidos.models.Visitante;
import br.ufsc.ine5605.achadoseperdidos.persistencia.PessoaDAO;
import java.util.ArrayList;

/**
 *
 * @author Henrique Meireles
 */
public class ControladorPessoa {

    private static ControladorPessoa instancia;
    //private ArrayList<Pessoa> pessoas;
    private TelaPessoa telaPessoa;

    public void inicia() {
        telaPessoa.mostrarTelaPrincipal();
        telaPessoa.mostraTela();
    }

    public ControladorPrincipal getControladorPrincipal() {
        return ControladorPrincipal.getInstancia();
    }

    private ControladorPessoa() {
        //pessoas = new ArrayList<>();
        telaPessoa = new TelaPessoa();
    }

    public static ControladorPessoa getInstancia() {
        if (instancia == null) {
            instancia = new ControladorPessoa();
        }
        return instancia;
    }

    public void cadastrarAluno(String nome, long telefone, int matricula) throws PessoaExistenteException, ValoresNulosException {
        if (nome.equals("") || nome.equals("") || telefone == 0l || matricula == 0) {
            throw new ValoresNulosException("Preencha todos os campos corretamente!");
        }
        Aluno aluno = new Aluno(nome, telefone, TipoPessoa.ALUNO, matricula);

        if (!verificarPessoaExistente(aluno)) {
            telaPessoa.exibirDadosAluno(aluno.getNomePessoa(), aluno.getTelefonePessoa(), aluno.getMatricula());
            //pessoas.add(aluno);
            PessoaDAO.getInstancia().put(aluno);
        } else {
            throw new PessoaExistenteException("Matricula ja cadastrada!");
        }
    }

    public void cadastrarFuncionario(String nome, long telefone, int siape) throws PessoaExistenteException, ValoresNulosException {
        if (nome.equals("") || nome.equals(" ") || telefone == 0l || siape == 0) {
            throw new ValoresNulosException("Preencha todos os campos corretamente!");
        }
        Funcionario funcionario = new Funcionario(nome, telefone, TipoPessoa.FUNCIONARIO, siape);
        if (!verificarPessoaExistente(funcionario)) {
            telaPessoa.exibirDadosFuncionario(funcionario.getNomePessoa(), funcionario.getTelefonePessoa(), funcionario.getSiape());
            //pessoas.add(funcionario);
            PessoaDAO.getInstancia().put(funcionario);
        } else {
            throw new PessoaExistenteException("Siape ja cadastrado!");
        }
    }

    public void cadastrarVisitante(String nome, long telefone, int cpf) throws PessoaExistenteException, ValoresNulosException {
        if (nome.equals("") || nome.equals(" ") || telefone == 0l || cpf == 0l) {
             throw new ValoresNulosException("Preencha todos os campos corretamente!");
        }
        Visitante visitante = new Visitante(nome, telefone, TipoPessoa.VISITANTE, cpf);
        if (!verificarPessoaExistente(visitante)) {
            telaPessoa.exibirDadosVisitante(visitante.getNomePessoa(), visitante.getTelefonePessoa(), visitante.getCpf());
            //pessoas.add(visitante);
            PessoaDAO.getInstancia().put(visitante);
        } else {
            throw new PessoaExistenteException("Cpf ja cadastrado!");
        }
    }

    public void excluirAluno(int matricula) throws PessoaNaoExisteException {
        Aluno aluno = null;
        for (Pessoa pessoasLista : PessoaDAO.getInstancia().getList()) {
            if (pessoasLista instanceof Aluno && ((Aluno) pessoasLista).getMatricula() == matricula) {
                aluno = (Aluno) pessoasLista;
                telaPessoa.exibirMensagem("Aluno matricula: " + ((Aluno) pessoasLista).getMatricula() + " excluido com sucesso!");
            }
        }
        if (aluno != null) {
            PessoaDAO.getInstancia().removeAluno(aluno);
        } else {
            throw new PessoaNaoExisteException("Não foi possivel excluir - Aluno nao existente");
        }
    }

    public void excluirFuncionario(int siape) throws PessoaNaoExisteException {
        Funcionario funcionario = null;
        for (Pessoa pessoasLista : PessoaDAO.getInstancia().getList()) {
            if (pessoasLista instanceof Funcionario && ((Funcionario) pessoasLista).getSiape() == siape) {
                funcionario = (Funcionario) pessoasLista;
                telaPessoa.exibirMensagem("Funcionario siape: " + ((Funcionario) pessoasLista).getSiape() + " excluido com sucesso!");
            }
        }
        if (funcionario != null) {
            //pessoas.remove(funcionario);
            PessoaDAO.getInstancia().removeFuncionario(funcionario);
        } else {
            throw new PessoaNaoExisteException("Não foi possivel excluir - Funcionario nao existente");
        }
    }

    public void excluirVisitante(long cpf) throws PessoaNaoExisteException {
        Visitante visitante = null;
        for (Pessoa pessoasLista : PessoaDAO.getInstancia().getList()) {
            if (pessoasLista instanceof Visitante && ((Visitante) pessoasLista).getCpf() == cpf) {
                visitante = (Visitante) pessoasLista;
                telaPessoa.exibirMensagem("Visitante cpf: " + ((Visitante) pessoasLista).getCpf() + " excluido com sucesso!");
            }
        }
        if (visitante != null) {
            //pessoas.remove(visitante);
            //CRIAR METODO QUE REMOVE VISITANTE
            PessoaDAO.getInstancia().removeVisitante(visitante);
        } else {
            throw new PessoaNaoExisteException("Não foi possivel excluir - Visitante nao existente");
        }
    }

    public void listarAlunos() {
        for (Pessoa pessoasLista : PessoaDAO.getInstancia().getList()) {
            if (pessoasLista instanceof Aluno) {
                telaPessoa.exibirMensagem(pessoasLista.getNomePessoa() + "               " + pessoasLista.getTelefonePessoa() + "               "
                        + ((Aluno) pessoasLista).getMatricula());
            }
        }

    }

    public Aluno encontrarAlunoPelaMatricula(int matricula) {
        for (Pessoa pessoasLista : PessoaDAO.getInstancia().getList()) {
            if (pessoasLista instanceof Aluno && ((Aluno) pessoasLista).getMatricula() == matricula) {
                return (Aluno) pessoasLista;
            }
        }
        return null;
    }

    public Funcionario encontrarFuncionarioPeloSiape(int siape) {
        for (Pessoa pessoasLista : PessoaDAO.getInstancia().getList()) {
            if (pessoasLista instanceof Funcionario && ((Funcionario) pessoasLista).getSiape() == siape) {
                return (Funcionario) pessoasLista;
            }
        }
        return null;
    }

    public Visitante encontrarVisitantePeloCpf(int cpf) {
        for (Pessoa pessoasLista : PessoaDAO.getInstancia().getList()) {
            if (pessoasLista instanceof Visitante && ((Visitante) pessoasLista).getCpf() == cpf) {
                return (Visitante) pessoasLista;
            }
        }
        return null;
    }

    public void alterarAluno(Aluno aluno, String nome, long telefone, int matricula) throws ValoresNulosException {
        if (nome.equals("") || nome.equals(" ") || telefone == 0l || matricula == 0) {
            throw new ValoresNulosException("Preencha todos os campos corretamente!");
        } else {
            PessoaDAO.getInstancia().removeAluno(aluno);
            aluno.setNomePessoa(nome);
            aluno.setTelefonePessoa(telefone);
            aluno.setMatricula(matricula);
            PessoaDAO.getInstancia().put(aluno);
        }
    }

    public void alterarFuncionario(Funcionario funcionario, String nome, long telefone, int siape) throws ValoresNulosException {
        if (nome.equals("") || nome.equals(" ") || telefone == 0l || siape == 0) {
            throw new ValoresNulosException("Preencha todos os campos corretamente!");
        } else {
            PessoaDAO.getInstancia().removeFuncionario(funcionario);
            funcionario.setNomePessoa(nome);
            funcionario.setTelefonePessoa(telefone);
            funcionario.setSiape(siape);
            PessoaDAO.getInstancia().put(funcionario);
        }
    }

    public void alterarVisitante(Visitante visitante, String nome, long telefone, int cpf) throws ValoresNulosException {
        if (nome.equals("") || nome.equals(" ") || telefone == 0l || cpf == 0) {
            throw new ValoresNulosException("Preencha todos os campos corretamente!");
        } else {
            PessoaDAO.getInstancia().removeVisitante(visitante);
            visitante.setNomePessoa(nome);
            visitante.setTelefonePessoa(telefone);
            visitante.setCpf(cpf);
            PessoaDAO.getInstancia().put(visitante);
        }

    }

    public void listarFuncionarios() {
        for (Pessoa pessoasLista : PessoaDAO.getInstancia().getList()) {
            if (pessoasLista instanceof Funcionario) {
                telaPessoa.exibirMensagem(pessoasLista.getNomePessoa() + "               " + pessoasLista.getTelefonePessoa() + "               "
                        + ((Funcionario) pessoasLista).getSiape());
            }
        }

    }

    public void listarVisitantes() {
        for (Pessoa pessoasLista : PessoaDAO.getInstancia().getList()) {
            if (pessoasLista instanceof Visitante) {
                telaPessoa.exibirMensagem(pessoasLista.getNomePessoa() + "               " + pessoasLista.getTelefonePessoa() + "               "
                        + ((Visitante) pessoasLista).getCpf());
            }
        }

    }

    public boolean verificarPessoaExistente(Pessoa pessoa) {
        if (pessoa.getTipoPessoa() == TipoPessoa.ALUNO) {
            ArrayList<Aluno> alunos = new ArrayList<>();
            for (Pessoa pessoaLista : PessoaDAO.getInstancia().getList()) {
                if (pessoaLista.getTipoPessoa() == TipoPessoa.ALUNO) {
                    alunos.add((Aluno) pessoaLista);
                }
            }
            for (Aluno alunoLista : alunos) {
                if (alunoLista.getMatricula() == ((Aluno) pessoa).getMatricula()) {
                    return true;
                }
            }
        } else if (pessoa.getTipoPessoa() == TipoPessoa.VISITANTE) {
            ArrayList<Visitante> visitantes = new ArrayList<>();
            for (Pessoa pessoaLista : PessoaDAO.getInstancia().getList()) {
                if (pessoaLista.getTipoPessoa() == TipoPessoa.VISITANTE) {
                    visitantes.add((Visitante) pessoaLista);
                    //PessoaDAO.getInstancia().put((Visitante) pessoaLista);
                }
            }
            for (Visitante visitanteLista : visitantes) {
                if (visitanteLista.getCpf() == ((Visitante) pessoa).getCpf()) {
                    return true;
                }
            }
        } else if (pessoa.getTipoPessoa() == TipoPessoa.FUNCIONARIO) {
            ArrayList<Funcionario> funcionarios = new ArrayList<>();
            for (Pessoa pessoaLista : PessoaDAO.getInstancia().getList()) {
                if (pessoaLista.getTipoPessoa() == TipoPessoa.FUNCIONARIO) {
                    funcionarios.add((Funcionario) pessoaLista);
                }
            }
            for (Funcionario funcionarioLista : funcionarios) {
                if (funcionarioLista.getSiape() == ((Funcionario) pessoa).getSiape()) {
                    return true;
                }
            }

        }
        return false;
    }

    public Pessoa encontrarPessoaPeloNome(String nome) {
        for (Pessoa pessoasLista : PessoaDAO.getInstancia().getList()) {
            if (pessoasLista.getNomePessoa().equalsIgnoreCase(nome)) {
                return pessoasLista;
            }
        }
        return null;
    }

    public String encontrarNomePessoa(Pessoa pessoa) {
        try {
            return pessoa.getNomePessoa();
        } catch (NullPointerException ex) {
            //telaPessoa.exibirMensagem("Pessoa nao encontrada: "+ex);
            return "";
        }
    }
    
    public void avisaPessoaVinculadaComObjeto(int id) throws PessoaPossuiVinculoException{
        for (Pessoa pessoaLista : PessoaDAO.getInstancia().getList()) {
                if (pessoaLista.getId() == id) {
                    if(ControladorPrincipal.getInstancia().vinculoComObjeto(pessoaLista)){
                        throw new PessoaPossuiVinculoException("Não é possivel excluir - Possui vinculo com objeto cadastrado");
                    }
                }
            }
    }

    public void exibirTelas(String opcao) {
        switch (opcao) {
            case "1":
                telaPessoa.mostrarTelaAluno();
                break;
            case "2":
                telaPessoa.mostrarTelaFuncionario();
                break;
            case "3":
                telaPessoa.mostrarTelaVisitante();
                break;
            case "4":
                telaPessoa.mostrarTelaPrincipal();
                telaPessoa.atualizaListaPessoas();
                break;
        }
    }

}
