package vvv.view.Passageiro;

import vvv.controller.PassageiroController;
import vvv.model.Passageiro;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class TelaEditarPassageiro extends JDialog {

    private JTextField txtNome, txtEmail, txtCpf, txtTelefone, txtDataNascimento;
    private PassageiroController passageiroController;
    private Passageiro passageiro;
    private boolean editou = false;

    public TelaEditarPassageiro(Frame parent, PassageiroController controller, Passageiro passageiro) {
        super(parent, "Editar Passageiro", true);
        this.passageiroController = controller;
        this.passageiro = passageiro;

        initComponents();
        preencherCampos();
    }

    private void initComponents() {
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        // Campos de texto
        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        add(txtCpf);

        add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        add(txtTelefone);

        add(new JLabel("Data de Nascimento (dd/MM/yyyy):"));
        txtDataNascimento = new JTextField();
        add(txtDataNascimento);

        // Botões
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> salvar());
        btnCancelar.addActionListener(e -> dispose());

        add(btnSalvar);
        add(btnCancelar);
    }

    private void preencherCampos() {
        txtNome.setText(passageiro.getNome());
        txtEmail.setText(passageiro.getEmail());
        txtCpf.setText(passageiro.getCpf());
        txtTelefone.setText(passageiro.getTelefone());
        txtDataNascimento.setText(passageiro.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    private void salvar() {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String cpf = txtCpf.getText();
        String telefone = txtTelefone.getText();
        String dataNascimentoStr = txtDataNascimento.getText();

        try {

            boolean sucesso = passageiroController.EditarPassageiro(
                    passageiro.getId(), nome, email, cpf, telefone, dataNascimentoStr);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Passageiro atualizado com sucesso!");
                editou = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar passageiro!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Dados inválidos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isEditou() {
        return editou;
    }
}
