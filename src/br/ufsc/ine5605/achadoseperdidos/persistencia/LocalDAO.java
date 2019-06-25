/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.persistencia;

import br.ufsc.ine5605.achadoseperdidos.models.Local;
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
public class LocalDAO {
    
    public static LocalDAO instancia;
    private HashMap<String, Local> cacheLocais;
    private final String fileName;
    
    private LocalDAO() {
        cacheLocais = new HashMap<String, Local>();
        fileName = "locais.dat";
        load();
    }
    
    public static LocalDAO getInstancia(){
        if(instancia == null){
            instancia = new LocalDAO();
        }
        return instancia;
    }
    
    public Local getLocal(String id){
        return cacheLocais.get(id);
    }
    
    public void put(Local local){
        cacheLocais.put(local.getNomeLocal(), local);
        persist();
    }
    
    public void load(){
        try{
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            this.cacheLocais = (HashMap<String, Local>) ois.readObject();
            
            ois.close();
            fis.close();
            
        }catch(ClassNotFoundException ex){
            System.out.println("Erro: "+ex);
        }catch(FileNotFoundException ex){
            System.out.println("Arquivo nao encontrado! "+ex);
        }catch(IOException ex){
            System.out.println("Erro: "+ ex);
        }
    }
    
    public void persist(){
        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cacheLocais);
            
            oos.flush();
            fos.flush();
            
            oos.close();
            fos.close();
            
        }catch(FileNotFoundException ex){
            System.out.println("Arquivo nao encontrado: "+ex);
        }catch(IOException ex){
            System.out.println("Erro: "+ex);
        }
        
    }
    
    public ArrayList<Local> getList(){
        return new ArrayList(this.cacheLocais.values());
    }
    
    public void removeLocal(Local local){
        cacheLocais.remove(local.getNomeLocal());
        persist();
    }
    
    public void alterarNomeLocal(Local local, String nomeLocal, String novaLocalizacao){
        local.setLocalizacao(novaLocalizacao);
        local.setNomeLocal(nomeLocal);
        cacheLocais.put(nomeLocal, local);
        persist();
    }
    
    public void alterarLocalizacao(Local local, String nomeLocal, String novaLocalizacao){
        local.setLocalizacao(novaLocalizacao);
        cacheLocais.put(nomeLocal, local);
        persist();
    }
    
}
