package vvv.view.Modal;

import vvv.controller.ModalController;
import vvv.model.ModalTransporte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultarModal extends JFrame {

    private ModalController modalController;
    private JTable tabela;
    private JTextField txtIdBusca;

    public ConsultarModal() {
        modalController = new ModalController();

        setTitle("Consultar Modal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Painel superior para busca por ID
        JPanel painelBusca = new JPanel();
        painelBusca.setLayout(new FlowLayout(FlowLayout.LEFT));
        painelBusca.add(new JLabel("ID do Modal:"));
        txtIdBusca = new JTextField(10);
        painelBusca.add(txtIdBusca);
        JButton btnBuscar = new JButton("Buscar");
        painelBusca.add(btnBuscar);
        add(painelBusca, BorderLayout.NORTH);

        // Tabela para exibição dos dados
        tabela = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        // Botão para listar todos
        JButton btnListarTodos = new JButton("Listar Todos");
        add(btnListarTodos, BorderLayout.SOUTH);

        // Configuração dos botões
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorId();
            }
        });

        btnListarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarTodos();
            }
        });

        // Lista todos os modais ao abrir a tela
        listarTodos();
    }

    private void buscarPorId() {
        try {
            long id = Long.parseLong(txtIdBusca.getText());
            ModalTransporte modal = modalController.buscarModal(id);

            if (modal != null) {
                atualizarTabela(List.of(modal));
            } else {
                JOptionPane.showMessageDialog(this, "Modal não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarTodos() {
        List<ModalTransporte> modais = modalController.listarModal();
        atualizarTabela(modais);
    }

    private void atualizarTabela(List<ModalTransporte> modais) {
        String[] colunas = {"ID", "Modelo", "Capacidade", "Ano_Fabricação", "Tipo", "Ativo"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);

        for (ModalTransporte modal : modais) {
            Object[] linha = {
                modal.getIdModal(),
                modal.getModelo(),
                modal.getCapacidade(),
                modal.getAnoFabricacao(),
                modal.getTipo(),
                modal.getAtivo() ? "Sim" : "Não"
            };
            modeloTabela.addRow(linha);
        }

        tabela.setModel(modeloTabela);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultarModal tela = new ConsultarModal();
            tela.setVisible(true);
        });
    }
}
