package vvv.view.Modal;

import vvv.controller.ModalController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CadastrarModal extends JFrame {

    private ModalController modalController;

    private JTextField txtModelo;
    private JTextField txtCapacidade;
    private JTextField txtAnoFabricacao;
    private JTextField txtTipo;
    private JCheckBox chkAtivo;
    private JButton btnSalvar;

    public CadastrarModal() {
        modalController = new ModalController();

        setTitle("Cadastrar Modal");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel principal com borda para espaçamento
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adiciona os componentes
        int row = 0;

        addField(mainPanel, gbc, row++, "Modelo:", txtModelo = new JTextField());
        addField(mainPanel, gbc, row++, "Capacidade:", txtCapacidade = new JTextField());
        addField(mainPanel, gbc, row++, "Ano de Fabricação:", txtAnoFabricacao = new JTextField());
        addField(mainPanel, gbc, row++, "Tipo:", txtTipo = new JTextField());
        addField(mainPanel, gbc, row++, "Ativo:", chkAtivo = new JCheckBox("Ativo"));

        // Botão Salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarModal());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnSalvar);

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
        gbc.gridwidth = 6;  // Ajuste para o tamanho desejado (dobro da largura)
        field.setPreferredSize(new Dimension(field.getPreferredSize().width * 2, field.getPreferredSize().height));  // Ajuste dinâmico
        panel.add(field, gbc);
    }

    private void salvarModal() {
        try {
            String modelo = txtModelo.getText();
            int capacidade = Integer.parseInt(txtCapacidade.getText());
            int anoFabricacao = Integer.parseInt(txtAnoFabricacao.getText());
            String tipo = txtTipo.getText();
            boolean ativo = chkAtivo.isSelected();

            boolean sucesso = modalController.salvarModal(modelo, capacidade, anoFabricacao, tipo, ativo);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Modal cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o modal.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos nos campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao cadastrar o modal.", "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void limparCampos() {
        txtModelo.setText("");
        txtCapacidade.setText("");
        txtAnoFabricacao.setText("");
        txtTipo.setText("");
        chkAtivo.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastrarModal tela = new CadastrarModal();
            tela.setVisible(true);
        });
    }
}
