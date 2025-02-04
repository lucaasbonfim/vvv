package vvv.view.Funcionario;

import vvv.controller.FuncionarioController;
import vvv.model.PontoVenda;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;
import java.util.stream.Collectors;

public class CadastrarFuncionario extends JFrame {

    private FuncionarioController funcionarioController;

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JCheckBox chkCargo;
    private JComboBox<String> cmbPontoDeVenda;
    private JButton btnSalvar;

    private Map<Long, String> pontosVenda;

    public CadastrarFuncionario() {
        funcionarioController = new FuncionarioController();
        carregarDados();

        setTitle("Cadastrar Funcionário");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        addField(mainPanel, gbc, row++, "Nome:", txtNome = new JTextField());
        addField(mainPanel, gbc, row++, "CPF:", txtCpf = new JTextField());
        addField(mainPanel, gbc, row++, "Email:", txtEmail = new JTextField());
        addField(mainPanel, gbc, row++, "Senha:", txtSenha = new JPasswordField());
        addField(mainPanel, gbc, row++, "Cargo (Gerente):", chkCargo = new JCheckBox("Gerente"));
        addField(mainPanel, gbc, row++, "Ponto de Venda:", cmbPontoDeVenda = new JComboBox<>(pontosVenda.values().toArray(new String[0])));

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarFuncionario());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnSalvar);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void carregarDados() {
        try {
            pontosVenda = funcionarioController.listarPontosVenda()
                                                .stream()
                                                .collect(Collectors.toMap(PontoVenda::getIdPontoVenda, PontoVenda::getNome));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os pontos de venda: " + e.getMessage());
        }
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 6; 
        field.setPreferredSize(new Dimension(field.getPreferredSize().width * 2, field.getPreferredSize().height)); 
        panel.add(field, gbc);
    }

    private void salvarFuncionario() {
        try {
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();
            String email = txtEmail.getText();
            String senha = new String(txtSenha.getPassword());
            boolean cargo = chkCargo.isSelected();
            long pontoDeVendaId = (Long) cmbPontoDeVenda.getSelectedItem();

            boolean sucesso = funcionarioController.salvarFuncionario(nome, cpf, email, senha, cargo, pontoDeVendaId);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao cadastrar o funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        chkCargo.setSelected(false);
        cmbPontoDeVenda.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastrarFuncionario tela = new CadastrarFuncionario();
            tela.setVisible(true);
        });
    }
}
