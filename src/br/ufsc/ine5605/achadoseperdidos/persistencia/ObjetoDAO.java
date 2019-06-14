/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.persistencia;


import br.ufsc.ine5605.achadoseperdidos.models.Objeto;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henrique
 */
public class ObjetoDAO {
    private static ObjetoDAO instancia;
    private HashMap<Integer, Objeto> cacheObjetos;
    private final String fileName;
    
    private ObjetoDAO(){
        cacheObjetos = new HashMap<Integer, Objeto>();
        fileName = "objeto.dat";
        load();
    }
    
    public static ObjetoDAO getInstancia(){
        if(instancia == null){
            instancia = new ObjetoDAO();
        }
        return instancia;
    }
    
    public Objeto getObjeto(Integer id){
        return cacheObjetos.get(id);
    }

    public void put(Objeto objeto){
        cacheObjetos.put(objeto.getId(), objeto);
        persist();
    }
    
    public void load(){
        try{
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.cacheObjetos = (HashMap<Integer, Objeto>) ois.readObject();
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

    private void persist() {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cacheObjetos);
            oos.flush();
            fos.flush();
            oos.close();
            fos.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ObjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ObjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList getLista(){
        return new ArrayList(this.cacheObjetos.values());
    }
    
    public void removeObjeto(Objeto objeto){
        cacheObjetos.remove(objeto.getId());
    }
    
        
    
}
