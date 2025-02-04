package vvv.view.Reserva;

import vvv.controller.ReservaController;
import vvv.model.Reserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        add(new JLabel("Data da Reserva (dd/MM/yyyy HH:mm:ss):"));
        txtDataReserva = new JTextField();
        txtDataReserva.setEditable(false);
        add(txtDataReserva);

        add(new JLabel("Status da Reserva:"));
        chkStatusReserva = new JCheckBox();
        chkStatusReserva.setEnabled(false);
        add(chkStatusReserva);

        add(new JLabel("Data da Viagem (dd/MM/yyyy):"));
        txtDataViagem = new JTextField();
        txtDataViagem.setEditable(false);
        add(txtDataViagem);

        add(new JLabel("Partida:"));
        txtPartida = new JTextField();
        txtPartida.setEditable(false);
        add(txtPartida);

        add(new JLabel("Chegada:"));
        txtChegada = new JTextField();
        txtChegada.setEditable(false);
        add(txtChegada);

        add(new JLabel("Valor:"));
        txtValor = new JTextField();
        txtValor.setEditable(false);
        add(txtValor);

        add(new JLabel("ID do Passageiro:"));
        txtPassageiroId = new JTextField();
        txtPassageiroId.setEditable(false);
        add(txtPassageiroId);

        add(new JLabel("ID do Modal de Transporte:"));
        txtModalTransporteId = new JTextField();
        txtModalTransporteId.setEditable(false);
        add(txtModalTransporteId);

        add(new JLabel("ID do Ponto de Venda:"));
        txtPontoVendaId = new JTextField();
        txtPontoVendaId.setEditable(false);
        add(txtPontoVendaId);

        add(new JLabel("ID do Funcionário:"));
        txtFuncionarioId = new JTextField();
        txtFuncionarioId.setEditable(false);
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                txtIdReserva.setText(reserva.getIdReserva().toString());
                txtDataReserva.setText(reserva.getDataReserva().format(formatter));
                chkStatusReserva.setSelected(reserva.isStatusReserva());
                txtDataViagem.setText(reserva.getDataViagem().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                txtPartida.setText(reserva.getPartida());
                txtChegada.setText(reserva.getChegada());
                txtValor.setText(reserva.getValor().toString());
                txtPassageiroId.setText(String.valueOf(reserva.getPassageiro().getNome()));
                txtModalTransporteId.setText(reserva.getModalTransporte().getModelo());
                txtPontoVendaId.setText(reserva.getPontoDeVenda().getNome());
                txtFuncionarioId.setText(reserva.getFuncionario().getNome());
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
            LocalDate dataReserva = LocalDate.parse(txtDataReserva.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            boolean statusReserva = chkStatusReserva.isSelected();
            LocalDate dataViagem = LocalDate.parse(txtDataViagem.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
