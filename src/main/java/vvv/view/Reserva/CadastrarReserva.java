package vvv.view.Reserva;

import vvv.controller.ReservaController;
import vvv.model.Funcionario;
import vvv.model.ModalTransporte;
import vvv.model.Passageiro;
import vvv.model.PontoVenda;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class CadastrarReserva extends JFrame {

    private JTextField txtPartida;
    private JTextField txtChegada;
    private JTextField txtValor;
    private JComboBox<String> cmbPassageiro;
    private JComboBox<String> cmbModalTransporte;
    private JComboBox<String> cmbPontoVenda;
    private JComboBox<String> cmbFuncionario;
    private JCheckBox chkStatusReserva;
    private JSpinner spnDataViagem;
    private JButton btnSalvar;

    private ReservaController reservaController;

    private Map<Long, String> passageiros;
    private Map<Long, String> modaisTransporte;
    private Map<Long, String> pontosVenda;
    private Map<Long, String> funcionarios;

    public CadastrarReserva() {
        reservaController = new ReservaController();
        carregarDados();

        UIManager.put("FlatLaf.classicScheme", new FlatDarkLaf());

        setTitle("Cadastrar Reserva");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel principal com borda para espaçamento
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adiciona os componentes
        int row = 0;

        addField(mainPanel, gbc, row++, "Partida:", txtPartida = new JTextField());
        addField(mainPanel, gbc, row++, "Chegada:", txtChegada = new JTextField());
        addField(mainPanel, gbc, row++, "Valor:", txtValor = new JTextField());
        addField(mainPanel, gbc, row++, "Passageiro:", cmbPassageiro = new JComboBox<>(passageiros.values().toArray(new String[0])));
        addField(mainPanel, gbc, row++, "Modal Transporte:", cmbModalTransporte = new JComboBox<>(modaisTransporte.values().toArray(new String[0])));
        addField(mainPanel, gbc, row++, "Ponto Venda:", cmbPontoVenda = new JComboBox<>(pontosVenda.values().toArray(new String[0])));
        addField(mainPanel, gbc, row++, "Funcionário:", cmbFuncionario = new JComboBox<>(funcionarios.values().toArray(new String[0])));
        addField(mainPanel, gbc, row++, "Status da Reserva:", chkStatusReserva = new JCheckBox("Ativa"));

        // Data da viagem com Spinner
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Data da Viagem:"), gbc);

        gbc.gridx = 1;
        spnDataViagem = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnDataViagem, "dd/MM/yyyy");
        spnDataViagem.setEditor(dateEditor);
        mainPanel.add(spnDataViagem, gbc);

        // Botão Salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarReserva());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnSalvar);

        // Adiciona o painel principal e o botão ao frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Torna visível
        setVisible(true);

        System.out.println(UIManager.getLookAndFeel().getName());
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 5;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        field.setPreferredSize(new Dimension(field.getPreferredSize().width * 2, field.getPreferredSize().height));  // Ajuste dinâmico
        panel.add(field, gbc);
    }

    private void carregarDados() {
        try {
            ArrayList<Passageiro> listaPassageiros = (ArrayList<Passageiro>) reservaController.listarPassageiros();
            passageiros = listaPassageiros.stream()
                    .collect(Collectors.toMap(Passageiro::getId, Passageiro::getNome));

            ArrayList<ModalTransporte> listaModais = (ArrayList<ModalTransporte>) reservaController.listarModais();
            modaisTransporte = listaModais.stream()
                    .collect(Collectors.toMap(ModalTransporte::getIdModal, ModalTransporte::getTipo));

            ArrayList<PontoVenda> listaPontosVenda = (ArrayList<PontoVenda>) reservaController.listarPontosVenda();
            pontosVenda = listaPontosVenda.stream()
                    .collect(Collectors.toMap(PontoVenda::getIdPontoVenda, PontoVenda::getNome));

            ArrayList<Funcionario> listaFuncionarios = (ArrayList<Funcionario>) reservaController.listarFuncionarios();
            funcionarios = listaFuncionarios.stream()
                    .collect(Collectors.toMap(Funcionario::getIdFuncionario, Funcionario::getNome));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os dados: " + e.getMessage());
        }
    }

    private void salvarReserva() {
        try {
            String partida = txtPartida.getText();
            String chegada = txtChegada.getText();
            BigDecimal valor = new BigDecimal(txtValor.getText());

            Long passageiroId = getKeyByValue(passageiros, (String) cmbPassageiro.getSelectedItem());
            Long modalTransporteId = getKeyByValue(modaisTransporte, (String) cmbModalTransporte.getSelectedItem());
            Long pontoVendaId = getKeyByValue(pontosVenda, (String) cmbPontoVenda.getSelectedItem());
            Long funcionarioId = getKeyByValue(funcionarios, (String) cmbFuncionario.getSelectedItem());

            boolean statusReserva = chkStatusReserva.isSelected();
            LocalDate dataViagem = ((SpinnerDateModel) spnDataViagem.getModel()).getDate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            LocalDateTime dataReserva = LocalDateTime.now();

            boolean sucesso = reservaController.salvarReserva(dataReserva, statusReserva, dataViagem, partida, chegada, valor, passageiroId, modalTransporteId, pontoVendaId, funcionarioId);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Reserva salva com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar a reserva.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private <K, V> K getKeyByValue(Map<K, V> map, V value) {
        return map.entrySet().stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
}
