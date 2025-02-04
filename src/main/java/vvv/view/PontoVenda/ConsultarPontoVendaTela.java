package vvv.view.PontoVenda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import vvv.controller.PontoVendaController;
import vvv.model.PontoVenda;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ConsultarPontoVendaTela extends JFrame {

    private JTable pontosVendaTable;
    private DefaultTableModel tableModel;
    private PontoVendaController pontoVendaController;

    public ConsultarPontoVendaTela() {
        setTitle("Consultar Ponto de Venda");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        pontoVendaController = new PontoVendaController();

        JPanel panel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"ID", "Nome", "Localização"}, 0);
        pontosVendaTable = new JTable(tableModel);
        pontosVendaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Desabilitar edição direta das células da tabela
        pontosVendaTable.setDefaultEditor(Object.class, null);

        pontosVendaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Verifica se foi um duplo clique com o botão esquerdo
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("Duplo clique com botão esquerdo detectado");
                    int linhaSelecionada = pontosVendaTable.getSelectedRow();
                    if (linhaSelecionada != -1) {
                        abrirTelaEditarPontoVenda(linhaSelecionada);
                    }
                }
            }
        });

        panel.add(new JScrollPane(pontosVendaTable), BorderLayout.CENTER);

        add(panel);

        // Carregar os dados de pontos de venda
        carregarPontosVenda();
    }

    private void carregarPontosVenda() {
        List<PontoVenda> pontosVenda = pontoVendaController.listarModal();
        tableModel.setRowCount(0);  // Limpar a tabela

        // Adicionar as linhas na tabela
        for (PontoVenda pontoVenda : pontosVenda) {
            tableModel.addRow(new Object[]{pontoVenda.getIdPontoVenda(), pontoVenda.getNome(), pontoVenda.getLocalizacao()});
        }
    }

    private void abrirTelaEditarPontoVenda(int linhaSelecionada) {
        DefaultTableModel modeloTabela = (DefaultTableModel) pontosVendaTable.getModel();
        Long idPontoVenda = (Long) modeloTabela.getValueAt(linhaSelecionada, 0);

        PontoVenda pontoVendaSelecionado = pontoVendaController.buscarPontoVenda(idPontoVenda);

        if (pontoVendaSelecionado != null) {
            EditarPontoVendaTela telaEditarPontoVenda = new EditarPontoVendaTela(pontoVendaSelecionado.getIdPontoVenda());
            telaEditarPontoVenda.setVisible(true);
            this.dispose();  // Fechar a tela de consulta
        } else {
            JOptionPane.showMessageDialog(this, "Ponto de venda não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultarPontoVendaTela tela = new ConsultarPontoVendaTela();
            tela.setVisible(true);
        });
    }
}
