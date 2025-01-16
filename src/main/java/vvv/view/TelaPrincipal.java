package vvv.view;

import javax.swing.*;

import vvv.view.Funcionario.CadastrarFuncionario;
import vvv.view.Funcionario.ConsultarFuncionario;
import vvv.view.Modal.CadastrarModal;
import vvv.view.Modal.ConsultarModal;
import vvv.view.Passageiro.TelaConsultarPassageiro;
import vvv.view.Passageiro.TelaCriarPassageiro;
import vvv.view.PontoVenda.*;

import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends javax.swing.JFrame {

    public TelaPrincipal() {
        initComponents();
    }

    private void initComponents() {
        jMenuBar1 = new JMenuBar();

        jMenuPassageiro = new JMenu("Passageiro");
        jMenuFuncionario = new JMenu("Funcionario");
        jMenuModal = new JMenu("Modal");
        jMenuPontoVenda = new JMenu("Ponto Venda");

        jMenuItemPassageiroCadastrar = new JMenuItem("Cadastrar");
        jMenuItemPassageiroConsultar = new JMenuItem("Consultar");
        jMenuItemFuncionarioConsultar = new JMenuItem("Consultar");
        jMenuItemFuncionarioCadastrar = new JMenuItem("Cadastrar");
        jMenuItemModalConsultar = new JMenuItem("Consultar");
        jMenuItemModalCadastrar = new JMenuItem("Cadastrar");
        jMenuItemPontoVendaConsultar = new JMenuItem("Consultar");
        jMenuItemPontoVendaCadastrar = new JMenuItem("Cadastrar");


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vai&Volta Viagens");

        // Painel de fundo com imagem
        JPanel painelFundo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagemFundo = new ImageIcon(getClass().getResource("/img/logo.png"));
                g.drawImage(imagemFundo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        painelFundo.setLayout(new BorderLayout());

        // Configuração dos menus

        setJMenuBar(jMenuBar1);

        jMenuBar1.add(jMenuPassageiro);
        jMenuBar1.add(jMenuFuncionario);
        jMenuBar1.add(jMenuModal);
        jMenuBar1.add(jMenuPontoVenda);
      
        jMenuPassageiro.add(jMenuItemPassageiroCadastrar);
        jMenuPassageiro.add(jMenuItemPassageiroConsultar);
        jMenuFuncionario.add(jMenuItemFuncionarioCadastrar);
        jMenuFuncionario.add(jMenuItemFuncionarioConsultar);
        jMenuModal.add(jMenuItemModalCadastrar);
        jMenuModal.add(jMenuItemModalConsultar);
        jMenuPontoVenda.add(jMenuItemPontoVendaConsultar);
        jMenuPontoVenda.add(jMenuItemPontoVendaCadastrar);


        jMenuItemPassageiroCadastrar.addActionListener(this::abrirTelaCadastrar);
        jMenuItemPassageiroConsultar.addActionListener(this::abrirTelaConsultar);
        jMenuItemModalConsultar.addActionListener(this::abrirTelaConsultarModal);
        jMenuItemModalCadastrar.addActionListener(this::abrirTelaCadasTrarModal);
        jMenuItemPontoVendaConsultar.addActionListener(this::abrirTelaConsultarPontoVenda);
        jMenuItemPontoVendaCadastrar.addActionListener(this::abrirTelaCadastarPontoVenda);
        jMenuItemFuncionarioCadastrar.addActionListener(this::abrirTelaCadastrarFuncionario);
        jMenuItemFuncionarioConsultar.addActionListener(this::abrirTelaConsultarFuncionario);
        

        // Adiciona o painel de fundo ao conteúdo principal
        getContentPane().add(painelFundo);

        pack();
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void abrirTelaCadastrar(ActionEvent evt) {
        new TelaCriarPassageiro(this).setVisible(true);
    }

    private void abrirTelaConsultar(ActionEvent evt) {
        new TelaConsultarPassageiro(this).setVisible(true);
    }

    private void abrirTelaConsultarModal(ActionEvent evt) {
        new ConsultarModal().setVisible(true);
    }

    private void abrirTelaCadasTrarModal(ActionEvent evt) {
        new CadastrarModal().setVisible(true);
    }

    private void abrirTelaConsultarPontoVenda(ActionEvent evt) {
        new ConsultarPontoVendaTela().setVisible(true);
    }

    private void abrirTelaCadastarPontoVenda(ActionEvent evt) {
        new CadastrarPontoVendaTela().setVisible(true);
    }

    private void abrirTelaCadastrarFuncionario(ActionEvent evt) {
        new CadastrarFuncionario().setVisible(true);
    }
    
    private void abrirTelaConsultarFuncionario(ActionEvent evt) {
        new ConsultarFuncionario().setVisible(true);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName())
                .log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }

    private JMenuBar jMenuBar1;

    private JMenu jMenuPassageiro;
    private JMenu jMenuFuncionario;
    private JMenu jMenuModal;
    private JMenu jMenuPontoVenda;

    private JMenuItem jMenuItemPassageiroCadastrar;
    private JMenuItem jMenuItemPassageiroConsultar;
    private JMenuItem jMenuItemFuncionarioCadastrar;
    private JMenuItem jMenuItemFuncionarioConsultar;
    private JMenuItem jMenuItemModalConsultar;
    private JMenuItem jMenuItemModalCadastrar;
    private JMenuItem jMenuItemPontoVendaConsultar;
    private JMenuItem jMenuItemPontoVendaCadastrar;


}
