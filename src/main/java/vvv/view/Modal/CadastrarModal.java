package vvv.view.Modal;

import vvv.controller.ModalController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarModal extends JFrame {

    private ModalController modalController;

    private JTextField txtModelo;
    private JTextField txtCapacidade;
    private JTextField txtAnoFabricacao;
    private JTextField txtTipo;
    private JCheckBox chkAtivo;

    public CadastrarModal() {
        modalController = new ModalController();

        setTitle("Cadastrar Modal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new GridLayout(6, 2, 10, 10));

        // Campos de entrada
        add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        add(txtModelo);

        add(new JLabel("Capacidade:"));
        txtCapacidade = new JTextField();
        add(txtCapacidade);

        add(new JLabel("Ano de Fabricação:"));
        txtAnoFabricacao = new JTextField();
        add(txtAnoFabricacao);

        add(new JLabel("Tipo:"));
        txtTipo = new JTextField();
        add(txtTipo);

        add(new JLabel("Ativo:"));
        chkAtivo = new JCheckBox();
        add(chkAtivo);

        // Botão de salvar
        JButton btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        add(btnCancelar);

        // Ações dos botões
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarModal();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
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
