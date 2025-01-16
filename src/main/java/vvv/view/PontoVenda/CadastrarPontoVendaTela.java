package vvv.view.PontoVenda;

import vvv.controller.PontoVendaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarPontoVendaTela extends JFrame {

    private JTextField nomeField;
    private JTextField localizacaoField;
    private JButton salvarButton;
    private PontoVendaController pontoVendaController;

    public CadastrarPontoVendaTela() {
        setTitle("Cadastro de Ponto de Venda");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pontoVendaController = new PontoVendaController();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        JLabel localizacaoLabel = new JLabel("Localização:");
        localizacaoField = new JTextField();
        salvarButton = new JButton("Salvar");

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarPontoVenda();
            }
        });

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(localizacaoLabel);
        panel.add(localizacaoField);
        panel.add(new JLabel());  // Espaço vazio
        panel.add(salvarButton);

        add(panel);
    }

    private void salvarPontoVenda() {
        String nome = nomeField.getText();
        String localizacao = localizacaoField.getText();

        boolean sucesso = pontoVendaController.salvarPontoVenda(nome, localizacao);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Ponto de venda salvo com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar ponto de venda.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastrarPontoVendaTela tela = new CadastrarPontoVendaTela();
            tela.setVisible(true);
        });
    }
}
