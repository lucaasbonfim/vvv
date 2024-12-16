package vvv.view;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends javax.swing.JFrame {

    public TelaPrincipal() {
        initComponents();
    }

    private void initComponents() {
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCadastro = new javax.swing.JMenu();
        jMenuItemPassageiro = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vai&Volta Viagens");

        JPanel painelFundo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagemFundo = new ImageIcon(getClass().getResource("/./img/logo.png"));
                g.drawImage(imagemFundo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        painelFundo.setLayout(new BorderLayout()); // Definindo layout do painel de fundo

        jMenuCadastro.setText("Cadastro");

        jMenuItemPassageiro.setText("Passageiro");
        jMenuItemPassageiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPassageiroActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemPassageiro);

        jMenuBar1.add(jMenuCadastro);
        setJMenuBar(jMenuBar1);

        getContentPane().add(painelFundo);

        pack();
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void jMenuItemPassageiroActionPerformed(java.awt.event.ActionEvent evt) {
        TelaCriarPassageiro telaCriarPassageiro = new TelaCriarPassageiro(this, true);
        telaCriarPassageiro.setVisible(true);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    private javax.swing.JMenu jMenuCadastro;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemPassageiro;
}
