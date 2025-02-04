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

        pontosVendaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editarPontoVenda();
                }
            }
        });

        panel.add(new JScrollPane(pontosVendaTable), BorderLayout.CENTER);

        add(panel);

        carregarPontosVenda();
    }

    private void carregarPontosVenda() {
        List<PontoVenda> pontosVenda = pontoVendaController.listarModal();
        tableModel.setRowCount(0);  // Limpar a tabela antes de carregar os novos dados

        for (PontoVenda pontoVenda : pontosVenda) {
            tableModel.addRow(new Object[]{pontoVenda.getIdPontoVenda(), pontoVenda.getNome(), pontoVenda.getLocalizacao()});
        }
    }

    private void editarPontoVenda() {
        int selectedRow = pontosVendaTable.getSelectedRow();
        if (selectedRow != -1) {
            long id = (long) tableModel.getValueAt(selectedRow, 0);
            new EditarPontoVendaTela(id).setVisible(true);
            this.dispose();  // Fecha a tela atual após abrir a de edição
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultarPontoVendaTela tela = new ConsultarPontoVendaTela();
            tela.setVisible(true);
        });
    }
}
