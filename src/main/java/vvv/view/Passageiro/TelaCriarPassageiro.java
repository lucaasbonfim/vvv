package vvv.view.Passageiro;

import vvv.controller.PassageiroController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaCriarPassageiro extends JFrame {

    private JTextField txtNome, txtEmail, txtTelefone, txtCPF;
    private JSpinner spnDataNascimento;
    private PassageiroController passageiroController;
    private JButton btnSalvar;

    public TelaCriarPassageiro() {
        passageiroController = new PassageiroController();

        UIManager.put("FlatLaf.classicScheme", new FlatDarkLaf());

        setTitle("Cadastro de Passageiro");
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
        addField(mainPanel, gbc, row++, "Email:", txtEmail = new JTextField());
        addField(mainPanel, gbc, row++, "Telefone:", txtTelefone = new JTextField());
        addField(mainPanel, gbc, row++, "CPF:", txtCPF = new JTextField());

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Data de Nascimento:"), gbc);

        gbc.gridx = 1;
        spnDataNascimento = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnDataNascimento, "dd/MM/yyyy");
        spnDataNascimento.setEditor(dateEditor);
        mainPanel.add(spnDataNascimento, gbc);
        row++;

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this::validarFormulario);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnSalvar);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
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

    private void validarFormulario(ActionEvent e) {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String cpf = txtCPF.getText().trim();

        LocalDate dataNascimento = ((SpinnerDateModel) spnDataNascimento.getModel()).getDate().toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        String dataNascimentoStr = dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo Nome não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(this, "Email inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (telefone.length() != 11 || !telefone.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Telefone deve conter 11 dígitos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cpf.length() != 11 || !cpf.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "CPF deve conter 11 dígitos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        salvarPassageiro(nome, email, telefone, cpf, dataNascimentoStr);
    }

    private void salvarPassageiro(String nome, String email, String telefone, String cpf, String dataNascimento) {
        boolean sucesso = passageiroController.salvarPassageiro(nome, email, cpf, telefone, dataNascimento);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Passageiro salvo com sucesso!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar passageiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaCriarPassageiro tela = new TelaCriarPassageiro();
            tela.setVisible(true);
        });
    }
}
