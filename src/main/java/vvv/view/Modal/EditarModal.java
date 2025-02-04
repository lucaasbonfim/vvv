package vvv.view.Modal;

import vvv.model.ModalTransporte;
import javax.swing.*;
import java.awt.*;

public class EditarModal extends JFrame {
    private ModalTransporte modal;

    public EditarModal(ModalTransporte modal) {
        this.modal = modal;

        setTitle("Editar Modal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Painel para campos de edição
        JPanel painelCampos = new JPanel(new GridLayout(5, 2));
        painelCampos.add(new JLabel("Modelo:"));
        JTextField txtModelo = new JTextField(modal.getModelo());
        painelCampos.add(txtModelo);

        painelCampos.add(new JLabel("Capacidade:"));
        JTextField txtCapacidade = new JTextField(String.valueOf(modal.getCapacidade()));
        painelCampos.add(txtCapacidade);

        painelCampos.add(new JLabel("Ano de Fabricação:"));
        JTextField txtAnoFabricacao = new JTextField(String.valueOf(modal.getAnoFabricacao()));
        painelCampos.add(txtAnoFabricacao);

        painelCampos.add(new JLabel("Tipo:"));
        JTextField txtTipo = new JTextField(modal.getTipo());
        painelCampos.add(txtTipo);

        painelCampos.add(new JLabel("Ativo:"));
        JCheckBox checkAtivo = new JCheckBox("Ativo", modal.getAtivo());
        painelCampos.add(checkAtivo);

        add(painelCampos, BorderLayout.CENTER);

        // Botão para salvar as alterações
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            // Atualiza os dados do modal com os valores dos campos
            modal.setModelo(txtModelo.getText());
            modal.setCapacidade(Integer.parseInt(txtCapacidade.getText()));
            modal.setAnoFabricacao(Integer.parseInt(txtAnoFabricacao.getText()));
            modal.setTipo(txtTipo.getText());
            modal.setAtivo(checkAtivo.isSelected());

            // Aqui você pode chamar o método do controller para salvar as alterações no banco de dados
            JOptionPane.showMessageDialog(this, "Modal atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Fecha a tela de edição
        });

        add(btnSalvar, BorderLayout.SOUTH);
    }
}