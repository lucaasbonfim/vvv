package vvv.view.Modal;

import vvv.controller.ModalController;
import vvv.model.ModalTransporte;

import javax.swing.*;
import java.awt.*;

public class EditarModal extends JFrame {
    private ModalTransporte modal;
    private ModalController modalController;

    public EditarModal(ModalTransporte modal) {
        this.modal = modal;
        this.modalController = new ModalController();  // Controlador que gerencia a lógica do modal

        setTitle("Editar Modal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel painelCampos = new JPanel(new GridLayout(6, 2));
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

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            modal.setModelo(txtModelo.getText());
            modal.setCapacidade(Integer.parseInt(txtCapacidade.getText()));
            modal.setAnoFabricacao(Integer.parseInt(txtAnoFabricacao.getText()));
            modal.setTipo(txtTipo.getText());
            modal.setAtivo(checkAtivo.isSelected());

            JOptionPane.showMessageDialog(this, "Modal atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        JButton btnDeletar = new JButton("Deletar");
        btnDeletar.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Tem certeza que deseja excluir este modal?",
                    "Confirmar Deletação",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (resposta == JOptionPane.YES_OPTION) {
                boolean sucesso = modalController.deletarModal(modal.getIdModal());
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Modal deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao deletar o modal.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnDeletar);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ModalTransporte mockModal = new ModalTransporte();
            mockModal.setIdModal(1L);
            mockModal.setModelo("Caminhão X");
            mockModal.setCapacidade(2000);
            mockModal.setAnoFabricacao(2022);
            mockModal.setTipo("Caminhão");
            mockModal.setAtivo(true);

            EditarModal tela = new EditarModal(mockModal);
            tela.setVisible(true);
        });
    }
}
