package vvv.view;

import javax.swing.*;

import vvv.view.Passageiro.TelaConsultarPassageiro;
import vvv.view.Passageiro.TelaCriarPassageiro;

import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends javax.swing.JFrame {

    public TelaPrincipal() {
        initComponents();
    }

    private void initComponents() {
        jMenuBar1 = new JMenuBar();
        jMenuPassageiro = new JMenu("Passageiro");
        jMenuItemCadastrar = new JMenuItem("Cadastrar");
        jMenuItemConsultar = new JMenuItem("Consultar");

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
        jMenuItemCadastrar.addActionListener(this::abrirTelaCadastrar);
        jMenuItemConsultar.addActionListener(this::abrirTelaConsultar);

        jMenuPassageiro.add(jMenuItemCadastrar);
        jMenuPassageiro.add(jMenuItemConsultar);

        jMenuBar1.add(jMenuPassageiro);
        setJMenuBar(jMenuBar1);

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

    private JMenu jMenuPassageiro;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItemCadastrar;
    private JMenuItem jMenuItemConsultar;
}
