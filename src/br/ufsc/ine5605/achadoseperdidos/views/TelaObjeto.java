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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Caroline Martins Alves
 */
public class TelaObjeto extends TelaGlobal{
    
    //private Scanner teclado;
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
    private JScrollPane spBaseTabela;
    private GridBagConstraints constraint;
    
    public TelaObjeto(){
        super("Tela Objeto");
        constraint = new GridBagConstraints();
    }
    
    public void menuInicial(){        
        //Componentes da tela
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        
        //Tabela que lista os dados recuperados do arquivo
        tabelaObjeto = new JTable();
        tabelaObjeto.setPreferredScrollableViewportSize(new Dimension(500,70));
        tabelaObjeto.setFillsViewportHeight(true);
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridwidth = 2;
        constraint.gridheight = 4;
        constraint.gridx = 0;
        constraint.gridy = 4;
        spBaseTabela = new JScrollPane(tabelaObjeto);
        
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
        //Setando acoes nos botoes
        btnCadastrar.setActionCommand("1");
        btnAlterar.setActionCommand("2");
        
        //Adicionando acao nos buttons
        GerenciadorBotoes gerenciadorBotoes = new GerenciadorBotoes();
        btnCadastrar.addActionListener(gerenciadorBotoes);
        btnAlterar.addActionListener(gerenciadorBotoes);
        
        //Adicionando componentes a tela
        container.add(spBaseTabela, constraint);
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
        
        //ONDÉ QUE CHAMA ISSO AQUI PARA ATUALIZAR OS DADOS DA JTABLE?
        //EU CHUTO QUE É DEPOIS DE CADASTRAR
        //VER ESSA PARADINHA
        updateData();
        
        //Configuracoes da tela
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);            
    }
    
    //NAO SEI ONDE QUE FICA ISSO AQUI
    private void updateData(){
        DefaultTableModel modelTbItens = new DefaultTableModel();
        modelTbItens.addColumn("Codigo");
        modelTbItens.addColumn("Descricao");
        modelTbItens.addColumn("Status");
        modelTbItens.addColumn("Tipo Objeto");
        modelTbItens.addColumn("Local");
        modelTbItens.addColumn("Cadastrador");
        
        for(Objeto objetoLista: ObjetoDAO.getInstancia().getList()){
            modelTbItens.addRow(new Object[]{objetoLista.getCodigo(), objetoLista.getDescricao(), objetoLista.getStatus(), 
                objetoLista.getTipoObjeto(), ControladorPrincipal.getInstancia().retornarNomeLocal(objetoLista.getLocal()), 
                ControladorPrincipal.getInstancia().retornarNomePessoa(objetoLista.getCadastrador())});
        }
        tabelaObjeto.setModel(modelTbItens);
        this.repaint();
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
            //int codigo = Integer.parseInt(txtCodigo.getText());
            String descricao = txtDescricao.getText();
            TipoStatus status = (TipoStatus) cmbStatus.getSelectedItem();
            TipoObjeto tipoObjeto = (TipoObjeto) cmbTipoObjeto.getSelectedItem();
            String local = txtLocal.getText();
            String cadastrador = txtCadastrador.getText();
            
            //System.out.println(codigo);
            System.out.println(descricao);
            System.out.println(status);
            System.out.println(tipoObjeto);
            System.out.println(local);
            System.out.println(cadastrador);            
            
            switch(ae.getActionCommand()){
                case "1": ControladorObjeto.getInstancia().cadastrarObjetos(descricao, status, 
                    tipoObjeto, local, cadastrador);
                    break;
               // case "2": ControladorObjeto.getInstancia().atualizarStatusObjeto(codigo, 
                        //status, cadastrador);
                    //break;
            }

        }
    }
         
}
