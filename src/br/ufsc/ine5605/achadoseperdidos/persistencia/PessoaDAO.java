/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.persistencia;

import br.ufsc.ine5605.achadoseperdidos.models.Aluno;
import br.ufsc.ine5605.achadoseperdidos.models.Funcionario;
import br.ufsc.ine5605.achadoseperdidos.models.Pessoa;
import br.ufsc.ine5605.achadoseperdidos.models.Visitante;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Caroline Martins Alves
 */
public class PessoaDAO {
    
    private static PessoaDAO instancia;
    private HashMap<Integer, Pessoa> cachePessoas;
    private final String fileName;
    
    private PessoaDAO(){
        cachePessoas = new HashMap<Integer, Pessoa>();
        fileName = "pessoa.dat";
        load();
    }
    
    public static PessoaDAO getInstancia(){
        if(instancia == null){
            instancia = new PessoaDAO();
        }
        return instancia;
    }
    
    public Pessoa getPessoa(Integer id){
        return cachePessoas.get(id);
    }
    
    public void put(Pessoa pessoa){
        cachePessoas.put(pessoa.getId(), pessoa);
        persist();
    }
    
    public void load(){
        try{
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.cachePessoas = (HashMap<Integer, Pessoa>) ois.readObject();            
            
            ois.close();
            fis.close();
        }catch(FileNotFoundException ex){
            System.out.println("Arquivo nao encontrado: "+ex);
            persist();
        }catch(IOException ex){
            System.out.println("Erro: "+ex);
        }catch(ClassNotFoundException ex){
            System.out.println("Erro: "+ex);
        }
    }
    
    public void persist() {
        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(cachePessoas);

            oos.flush();
            fos.flush();

            oos.close();
            fos.close();            
        }catch(FileNotFoundException ex){
            System.out.println("Erro: "+ex);
        }catch(IOException ex){
            System.out.println("Erro: "+ex);
        }
    }
    
    public ArrayList<Pessoa> getList(){
        return new ArrayList(this.cachePessoas.values());
    }
    
    
    public void removeAluno(Aluno aluno){
        //REMOVE PELA CHAVE, COM ELA ENCONTRA E REMOVE A PESSOA
        cachePessoas.remove(aluno.getId());
        persist();
    }
    
    public void removeFuncionario(Funcionario funcionario){
        //REMOVE PELA CHAVE, COM ELA ENCONTRA E REMOVE A PESSOA
        cachePessoas.remove(funcionario.getId());
        persist();
    }
    

    public void removeVisitante(Visitante visitante){
        //REMOVE PELA CHAVE, COM ELA ENCONTRA E REMOVE A PESSOA
        cachePessoas.remove(visitante.getId());
        persist();
    }
    
    
}
