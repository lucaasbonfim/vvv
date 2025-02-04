package vvv.view.PontoVenda;

import vvv.controller.PontoVendaController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CadastrarPontoVendaTela extends JFrame {

    private JTextField nomeField;
    private JTextField localizacaoField;
    private JButton salvarButton;
    private PontoVendaController pontoVendaController;

    public CadastrarPontoVendaTela() {
        pontoVendaController = new PontoVendaController();

        setTitle("Cadastro de Ponto de Venda");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300); // Mantém o padrão 400x400
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel principal com borda para espaçamento
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // Campo Nome
        nomeField = new JTextField();
        nomeField.setColumns(15); // Define largura do campo
        addField(mainPanel, gbc, row++, "Nome:", nomeField);

        // Campo Localização
        localizacaoField = new JTextField();
        localizacaoField.setColumns(15); // Define largura do campo
        addField(mainPanel, gbc, row++, "Localização:", localizacaoField);

        // Botão Salvar
        salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> salvarPontoVenda());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(salvarButton);

        // Adiciona o painel principal e o botão ao frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 6; // Espaço horizontal consistente
        panel.add(field, gbc);
    }

    private void salvarPontoVenda() {
        String nome = nomeField.getText();
        String localizacao = localizacaoField.getText();

        if (nome.isEmpty() || localizacao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean sucesso = pontoVendaController.salvarPontoVenda(nome, localizacao);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Ponto de venda salvo com sucesso!");
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar ponto de venda.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        nomeField.setText("");
        localizacaoField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastrarPontoVendaTela tela = new CadastrarPontoVendaTela();
            tela.setVisible(true);
        });
    }
}
