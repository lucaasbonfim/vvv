package vvv.view.Reserva;

import vvv.controller.ReservaController;
import vvv.model.Reserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EditarReserva extends JFrame {

    private ReservaController reservaController;
    private JTextField txtIdReserva;
    private JTextField txtDataReserva;
    private JCheckBox chkStatusReserva;
    private JTextField txtDataViagem;
    private JTextField txtPartida;
    private JTextField txtChegada;
    private JTextField txtValor;
    private JTextField txtPassageiroId;
    private JTextField txtModalTransporteId;
    private JTextField txtPontoVendaId;
    private JTextField txtFuncionarioId;
    private JButton btnSalvar;
    private JButton btnCancelar;

    private Long reservaId;

    public EditarReserva(Long reservaId) {
        this.reservaController = new ReservaController();
        this.reservaId = reservaId;

        setTitle("Editar Reserva");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(12, 2, 10, 10));

        add(new JLabel("ID da Reserva:"));
        txtIdReserva = new JTextField();
        txtIdReserva.setEditable(false);
        add(txtIdReserva);

        add(new JLabel("Data da Reserva (yyyy-MM-ddTHH:mm):"));
        txtDataReserva = new JTextField();
        add(txtDataReserva);

        add(new JLabel("Status da Reserva:"));
        chkStatusReserva = new JCheckBox();
        add(chkStatusReserva);

        add(new JLabel("Data da Viagem (yyyy-MM-dd):"));
        txtDataViagem = new JTextField();
        add(txtDataViagem);

        add(new JLabel("Partida:"));
        txtPartida = new JTextField();
        add(txtPartida);

        add(new JLabel("Chegada:"));
        txtChegada = new JTextField();
        add(txtChegada);

        add(new JLabel("Valor:"));
        txtValor = new JTextField();
        add(txtValor);

        add(new JLabel("ID do Passageiro:"));
        txtPassageiroId = new JTextField();
        add(txtPassageiroId);

        add(new JLabel("ID do Modal de Transporte:"));
        txtModalTransporteId = new JTextField();
        add(txtModalTransporteId);

        add(new JLabel("ID do Ponto de Venda:"));
        txtPontoVendaId = new JTextField();
        add(txtPontoVendaId);

        add(new JLabel("ID do Funcionário:"));
        txtFuncionarioId = new JTextField();
        add(txtFuncionarioId);

        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        btnCancelar = new JButton("Cancelar");
        add(btnCancelar);

        carregarDadosReserva();

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarReserva();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void carregarDadosReserva() {
        try {
            Reserva reserva = reservaController.buscarReservaPorId(reservaId);

            if (reserva != null) {
                txtIdReserva.setText(reserva.getIdReserva().toString());
                txtDataReserva.setText(reserva.getDataReserva().toString());
                chkStatusReserva.setSelected(reserva.isStatusReserva());
                txtDataViagem.setText(reserva.getDataViagem().toString());
                txtPartida.setText(reserva.getPartida());
                txtChegada.setText(reserva.getChegada());
                txtValor.setText(reserva.getValor().toString());
                txtPassageiroId.setText(String.valueOf(reserva.getPassageiro().getId()));
                txtModalTransporteId.setText(reserva.getModalTransporte().getIdModal().toString());
                txtPontoVendaId.setText(reserva.getPontoDeVenda().getIdPontoVenda().toString());
                txtFuncionarioId.setText(reserva.getFuncionario().getIdFuncionario().toString());
            } else {
                JOptionPane.showMessageDialog(this, "Reserva não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os dados da reserva.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void salvarReserva() {
        try {
            LocalDateTime dataReserva = LocalDateTime.parse(txtDataReserva.getText());
            boolean statusReserva = chkStatusReserva.isSelected();
            LocalDate dataViagem = LocalDate.parse(txtDataViagem.getText());
            String partida = txtPartida.getText();
            String chegada = txtChegada.getText();
            BigDecimal valor = new BigDecimal(txtValor.getText());
            Long passageiroId = Long.parseLong(txtPassageiroId.getText());
            Long modalTransporteId = Long.parseLong(txtModalTransporteId.getText());
            Long pontoVendaId = Long.parseLong(txtPontoVendaId.getText());
            Long funcionarioId = Long.parseLong(txtFuncionarioId.getText());

            boolean sucesso = reservaController.editarReserva(reservaId, dataReserva, statusReserva, dataViagem,
                    partida, chegada, valor, passageiroId, modalTransporteId, pontoVendaId, funcionarioId);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Reserva atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar a reserva.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados da reserva.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
