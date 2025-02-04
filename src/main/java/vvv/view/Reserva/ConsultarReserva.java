package vvv.view.Reserva;

import vvv.controller.ReservaController;
import vvv.model.Reserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ConsultarReserva extends JFrame {

    private JTable tabelaReservas;
    private DefaultTableModel modeloTabela;
    private ReservaController reservaController;

    public ConsultarReserva() {
        reservaController = new ReservaController();
        configurarJanela();
        carregarReservas();
    }

    private void configurarJanela() {
        setTitle("Consultar Reservas");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Tabela
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 20, 640, 400);
        add(scrollPane);

        tabelaReservas = new JTable();
        modeloTabela = new DefaultTableModel(
            new Object[]{"ID", "Data Reserva", "Status", "Partida", "Chegada", "Valor"},
            0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede a edição direta dos dados na tabela
            }
        };
        tabelaReservas.setModel(modeloTabela);
        tabelaReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(tabelaReservas);

        // Evento de clique duplo na tabela
        tabelaReservas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int linhaSelecionada = tabelaReservas.getSelectedRow();
                    if (linhaSelecionada != -1) {
                        Long idReserva = (Long) modeloTabela.getValueAt(linhaSelecionada, 0);
                        abrirTelaEditarReserva(idReserva);
                    }
                }
            }
        });
    }

    private void carregarReservas() {
        try {
            List<Reserva> reservas = reservaController.listarReservas();
            modeloTabela.setRowCount(0); // Limpa os dados da tabela
            for (Reserva reserva : reservas) {
                modeloTabela.addRow(new Object[]{
                    reserva.getIdReserva(),
                    reserva.getDataReserva(),
                    reserva.isStatusReserva() ? "Ativa" : "Inativa",
                    reserva.getPartida(),
                    reserva.getChegada(),
                    reserva.getValor()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar as reservas.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void abrirTelaEditarReserva(Long idReserva) {
        EditarReserva editarReserva = new EditarReserva(idReserva);
        editarReserva.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ConsultarReserva().setVisible(true);
        });
    }
}
