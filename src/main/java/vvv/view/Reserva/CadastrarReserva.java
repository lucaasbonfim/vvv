package vvv.view.Reserva;

import vvv.controller.ReservaController;
import vvv.model.Funcionario;
import vvv.model.ModalTransporte;
import vvv.model.Passageiro;
import vvv.model.PontoVenda;

import javax.swing.*;
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

    // Mapas para armazenar os nomes e IDs
    private Map<Long, String> passageiros;
    private Map<Long, String> modaisTransporte;
    private Map<Long, String> pontosVenda;
    private Map<Long, String> funcionarios;

    public CadastrarReserva() {
        reservaController = new ReservaController();
        carregarDados(); // Carregar dados antes de configurar os campos

        setTitle("Cadastrar Reserva");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(10, 2, 5, 5));

        // Campos do formulário
        add(new JLabel("Partida:"));
        txtPartida = new JTextField();
        add(txtPartida);

        add(new JLabel("Chegada:"));
        txtChegada = new JTextField();
        add(txtChegada);

        add(new JLabel("Valor:"));
        txtValor = new JTextField();
        add(txtValor);

        add(new JLabel("Passageiro:"));
        cmbPassageiro = new JComboBox<>(passageiros.values().toArray(new String[0]));
        add(cmbPassageiro);

        add(new JLabel("Modal Transporte:"));
        cmbModalTransporte = new JComboBox<>(modaisTransporte.values().toArray(new String[0]));
        add(cmbModalTransporte);

        add(new JLabel("Ponto Venda:"));
        cmbPontoVenda = new JComboBox<>(pontosVenda.values().toArray(new String[0]));
        add(cmbPontoVenda);

        add(new JLabel("Funcionário:"));
        cmbFuncionario = new JComboBox<>(funcionarios.values().toArray(new String[0]));
        add(cmbFuncionario);

        add(new JLabel("Status da Reserva:"));
        chkStatusReserva = new JCheckBox("Ativa");
        add(chkStatusReserva);

        add(new JLabel("Data da Viagem:"));
        spnDataViagem = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnDataViagem, "dd/MM/yyyy"); // Formato brasileiro
        spnDataViagem.setEditor(dateEditor); 
        add(spnDataViagem);

        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Ação do botão salvar
        btnSalvar.addActionListener(e -> salvarReserva());

        setLocationRelativeTo(null);
    }

    private void carregarDados() {
        try {
            // Lista de passageiros para um Map<Long, String>
            ArrayList<Passageiro> listaPassageiros = (ArrayList<Passageiro>) reservaController.listarPassageiros();
            passageiros = listaPassageiros.stream()
                                          .collect(Collectors.toMap(Passageiro::getId, Passageiro::getNome));

            // Lista de modais transporte para um Map<Long, String>
            ArrayList<ModalTransporte> listaModais = (ArrayList<ModalTransporte>) reservaController.listarModais();
            modaisTransporte = listaModais.stream()
                                          .collect(Collectors.toMap(ModalTransporte::getIdModal, ModalTransporte::getTipo));

            // Lista de pontos de venda para um Map<Long, String>
            ArrayList<PontoVenda> listaPontosVenda = (ArrayList<PontoVenda>) reservaController.listarPontosVenda();
            pontosVenda = listaPontosVenda.stream()
                                          .collect(Collectors.toMap(PontoVenda::getIdPontoVenda, PontoVenda::getNome));

            // Lista de funcionários para um Map<Long, String>
            ArrayList<Funcionario> listaFuncionarios = (ArrayList<Funcionario>) reservaController.listarFuncionarios();  // Usando ArrayList diretamente
            funcionarios = listaFuncionarios.stream()
                                            .collect(Collectors.toMap(Funcionario::getIdFuncionario, Funcionario::getNome));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void salvarReserva() {
        try {
            String partida = txtPartida.getText();
            String chegada = txtChegada.getText();
            BigDecimal valor = new BigDecimal(txtValor.getText());

            // Recuperar os IDs selecionados
            Long passageiroId = getKeyByValue(passageiros, (String) cmbPassageiro.getSelectedItem());
            Long modalTransporteId = getKeyByValue(modaisTransporte, (String) cmbModalTransporte.getSelectedItem());
            Long pontoVendaId = getKeyByValue(pontosVenda, (String) cmbPontoVenda.getSelectedItem());
            Long funcionarioId = getKeyByValue(funcionarios, (String) cmbFuncionario.getSelectedItem());

            boolean statusReserva = chkStatusReserva.isSelected();
            LocalDate dataViagem = ((SpinnerDateModel) spnDataViagem.getModel()).getDate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            LocalDateTime dataReserva = LocalDateTime.now();

            boolean sucesso = reservaController.salvarReserva(
                dataReserva,
                statusReserva,
                dataViagem,
                partida,
                chegada,
                valor,
                passageiroId,
                modalTransporteId,
                pontoVendaId,
                funcionarioId
            );

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Reserva salva com sucesso!");
                dispose(); // Fechar a tela após salvar
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar a reserva.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private <K, V> K getKeyByValue(Map<K, V> map, V value) {
        return map.entrySet().stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CadastrarReserva().setVisible(true);
        });
    }
}
