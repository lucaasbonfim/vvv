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
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Painel superior para busca por nome
        JPanel painelBusca = new JPanel();
        painelBusca.setLayout(new FlowLayout(FlowLayout.LEFT));
        painelBusca.add(new JLabel("Nome:"));
        txtIdBusca = new JTextField(10);
        painelBusca.add(txtIdBusca);
        JButton btnBuscar = new JButton("Buscar");
        painelBusca.add(btnBuscar);
        add(painelBusca, BorderLayout.NORTH);

        // Tabela para exibição dos dados
        tabela = new JTable();
        tabela.setDefaultEditor(Object.class, null); // Desabilita a edição de células
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        // Botão para listar todos
        JButton btnListarTodos = new JButton("Listar Todos");
        add(btnListarTodos, BorderLayout.SOUTH);

        // Configuração dos botões
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorNome();
            }
        });

        btnListarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarTodos();
            }
        });

        // Adiciona um MouseListener para detectar duplo clique com o botão esquerdo
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Verifica se foi um duplo clique com o botão esquerdo
                if (evt.getClickCount() == 2 && evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
                    System.out.println("Duplo clique com botão esquerdo detectado"); // Debug
                    int linhaSelecionada = tabela.getSelectedRow(); // Pega a linha selecionada
                    if (linhaSelecionada != -1) { // Verifica se uma linha foi selecionada
                        abrirTelaEditarModal(linhaSelecionada); // Abre a tela de edição
                    } else {
                        System.out.println("Nenhuma linha selecionada."); // Debug
                    }
                }
            }
        });

        // Lista todos os modais ao abrir a tela
        listarTodos();
    }

    private void buscarPorNome() {
        try {
            String nome = txtIdBusca.getText();
            List<ModalTransporte> modais = modalController.buscarPorNome(nome);

            if (modais != null && !modais.isEmpty()) {
                atualizarTabela(modais);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum modal encontrado com o nome informado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar modais: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Debug
        }
    }

    private void listarTodos() {
        try {
            List<ModalTransporte> modais = modalController.listarModal();
            atualizarTabela(modais);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar modais: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Debug
        }
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

    private void abrirTelaEditarModal(int linhaSelecionada) {
        try {
            // Obtém o ID do modal selecionado na tabela
            DefaultTableModel modeloTabela = (DefaultTableModel) tabela.getModel();
            Long idModal = (Long) modeloTabela.getValueAt(linhaSelecionada, 0); // Coluna 0 é o ID
            System.out.println("ID do modal selecionado: " + idModal); // Debug

            // Busca o modal no banco de dados (ou na lista) usando o ID
            ModalTransporte modalSelecionado = modalController.buscarModal(idModal);
            System.out.println("Modal selecionado: " + modalSelecionado); // Debug

            if (modalSelecionado != null) {
                // Abre a tela de edição, passando o modal selecionado
                EditarModal telaEditarModal = new EditarModal(modalSelecionado);
                telaEditarModal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Modal não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao abrir tela de edição: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Debug
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultarModal tela = new ConsultarModal();
            tela.setVisible(true);
        });
    }
}