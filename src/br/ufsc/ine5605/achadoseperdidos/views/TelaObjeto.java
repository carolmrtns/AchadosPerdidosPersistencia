/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.achadoseperdidos.views;
import br.ufsc.ine5605.achadoseperdidos.models.TipoObjeto;
import br.ufsc.ine5605.achadoseperdidos.models.TipoStatus;
import br.ufsc.ine5605.achadoseperdidos.controllers.ControladorObjeto;
import br.ufsc.ine5605.achadoseperdidos.models.Objeto;
import br.ufsc.ine5605.achadoseperdidos.persistencia.ObjetoDAO;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
/**
 *
 * @author Caroline Martins Alves
 */
public class TelaObjeto extends TelaGlobal{
    
    private Scanner teclado;
    //private ControladorObjeto controladorObjeto;
    private JTable tabelaObjeto;
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JLabel lblDescricao;
    private JTextField txtDescricao;
    private JLabel lblStatus;
    private JComboBox cmbStatus;
    private JLabel lblTipoObjeto;
    private JComboBox cmbTipoObjeto;
    private JLabel lblLocal;
    private JTextField txtLocal;
    private JLabel lblCadastrador;
    private JTextField txtCadastrador;
    private JButton btnCadastrar;
    private JButton btnAlterar;
    
    public TelaObjeto(){
        super("Tela Objeto");
        teclado = new Scanner(System.in);
        //this.controladorObjeto = ControladorObjeto.getInstancia();
    }
    
    public void menuInicial(){        
        //Componentes da tela
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        
        //Tabela que lista os dados recuperados do arquivo
        String colunas[] = {"Codigo", "Descricao", "Status", "Tipo Objeto", "Local", "Cadastrador"};
        Object dadosObjetos[][] = ControladorObjeto.getInstancia().listarObjetosPerdidos();
        
        tabelaObjeto = new JTable(dadosObjetos, colunas);
        
        
        //Componentes de campo
        TipoStatus tipoStatus[] = {TipoStatus.ENCONTRADO, TipoStatus.PERDIDO};
        TipoObjeto tipoObjeto[] = {TipoObjeto.ALIMENTO, TipoObjeto.DOCUMENTOS, 
            TipoObjeto.ELETROELETRONICO, TipoObjeto.MATERIAIS, TipoObjeto.VESTUARIO};

        lblCodigo = new JLabel();
        txtCodigo = new JTextField(10);        
        
        lblDescricao = new JLabel();
        txtDescricao = new JTextField(10);
        
        lblStatus = new JLabel();
        cmbStatus = new JComboBox(tipoStatus);
        
        lblTipoObjeto = new JLabel();
        cmbTipoObjeto = new JComboBox(tipoObjeto);

        lblLocal = new JLabel();
        txtLocal = new JTextField(10);

        lblCadastrador = new JLabel();
        txtCadastrador = new JTextField(10);        

        btnCadastrar = new JButton();
        btnAlterar = new JButton();
        //Fim componentes da tela
        
        //Conteudo dentro dos componentes da tela
        lblCodigo.setText("Codigo: ");
        txtCodigo.setEditable(false);
        lblDescricao.setText("Descricao: ");
        lblStatus.setText("Status: ");
        lblTipoObjeto.setText("Tipo Objeto: ");
        lblLocal.setText("Local: ");
        lblCadastrador.setText("Cadastrador: ");        
        btnCadastrar.setText("Cadastrar");
        btnAlterar.setText("Alterar");
        //setando acoes nos botoes
        btnCadastrar.setActionCommand("1");
        btnAlterar.setActionCommand("2");
        
        //Adicionando acao nos buttons
        GerenciadorBotoes gerenciadorBotoes = new GerenciadorBotoes();
        btnCadastrar.addActionListener(gerenciadorBotoes);
        btnAlterar.addActionListener(gerenciadorBotoes);
        
        //Adicionando componentes a tela
        //container.add(tabelaObjeto);
        container.add(lblCodigo);
        container.add(txtCodigo);
        container.add(lblDescricao);
        container.add(txtDescricao);
        container.add(lblStatus);
        container.add(cmbStatus);
        container.add(lblTipoObjeto);
        container.add(cmbTipoObjeto);
        container.add(lblLocal);
        container.add(txtLocal);
        container.add(lblCadastrador);
        container.add(txtCadastrador);
        container.add(btnCadastrar);
        container.add(btnAlterar);
        
        //Configuracoes da tela
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);          
        
    }
    
   /* public void inserirObjetos(){
        
      
        
    }
    
    public void alterarStatusObjeto(){
        System.out.println("----------REMOVENDO OBJETO ENCONTRADO----------");
        System.out.println("Codigo do objeto: ");
        int codigo = recebeValorInteiro();
        System.out.println("Status: ENCONTRADO");
        TipoStatus status = TipoStatus.ENCONTRADO;
        System.out.println("Nome do dono: ");
        String nome = recebeValorString();
        
        ControladorObjeto.getInstancia().atualizarStatusObjeto(codigo, status, nome);
    }
    
    public void exibirObjetos(){
        ControladorObjeto.getInstancia().listarObjetosPerdidos();                
    }
    
    public void exibirObjetosPorTipo(TipoObjeto tipoObjeto){
        ControladorObjeto.getInstancia().listarObjetosPorTipo(tipoObjeto);
    }
    */
    public void exibirDadosObjeto(int codigo, String descricao, TipoStatus status, TipoObjeto tipoObjeto, String nomeLocal, String nomeCadastrador){
        System.out.println("----------OBJETO CADASTRADO----------");
        System.out.println("Codigo: " + codigo);
        System.out.println("Descricao: " + descricao);
        System.out.println("Status: " + status);
        System.out.println("Tipo do objeto: " + tipoObjeto);
        System.out.println("Local: " + nomeLocal);
        System.out.println("Cadastrador: " + nomeCadastrador);
    }
    
   
    private class GerenciadorBotoes implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent ae){
            int codigo = Integer.parseInt(txtCodigo.getText());
            String descricao = txtDescricao.getText();
            TipoStatus status = (TipoStatus) cmbStatus.getSelectedItem();
            TipoObjeto tipoObjeto = (TipoObjeto) cmbTipoObjeto.getSelectedItem();
            String local = txtLocal.getText();
            String cadastrador = txtCadastrador.getText();

            System.out.println(descricao);
            System.out.println(status);
            System.out.println(tipoObjeto);
            System.out.println(local);
            System.out.println(cadastrador);            
            
            switch(ae.getActionCommand()){
                case "1": ControladorObjeto.getInstancia().cadastrarObjetos(descricao, status, 
                    tipoObjeto, local, cadastrador);
                    break;
                case "2": ControladorObjeto.getInstancia().atualizarStatusObjeto(codigo, 
                        status, cadastrador);
                    break;
            }

        }
    }
         
}
