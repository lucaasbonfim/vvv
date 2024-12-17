package vvv.view.Passageiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import vvv.controller.PassageiroController;

public class TelaCriarPassageiro extends JDialog {

    private JTextField txtNome, txtEmail, txtTelefone, txtCPF, txtDataNascimento;
    private PassageiroController passageiroController; // A controller

    public TelaCriarPassageiro(Frame parent) {
        super(parent, "Cadastro de Passageiro", true);
        passageiroController = new PassageiroController(); // Inicializando a controller
        initComponents();
    }

    private void initComponents() {
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel principal
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Componentes
        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();

        JLabel lblTelefone = new JLabel("Telefone:");
        txtTelefone = new JTextField();

        JLabel lblCPF = new JLabel("CPF:");
        txtCPF = new JTextField();

        JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
        txtDataNascimento = new JTextField();

        // Adicionando os componentes ao painel
        panel.add(lblNome);
        panel.add(txtNome);

        panel.add(lblEmail);
        panel.add(txtEmail);

        panel.add(lblTelefone);
        panel.add(txtTelefone);

        panel.add(lblCPF);
        panel.add(txtCPF);

        panel.add(lblDataNascimento);
        panel.add(txtDataNascimento);

        // Botões
        JPanel panelBotoes = new JPanel();
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> validarFormulario());
        btnCancelar.addActionListener(e -> dispose());

        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnCancelar);

        add(panel, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);

        // Evitar digitação de letras
        txtTelefone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || txtTelefone.getText().length() >= 11) {
                    e.consume();  // Impede a digitação de caracteres não numéricos e limita a 11 caracteres
                }
            }
        });

        txtCPF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || txtCPF.getText().length() >= 11) {
                    e.consume();  // Impede a digitação de caracteres não numéricos e limita a 11 caracteres
                }
            }
        });

        // Formatação do campo de Data de Nascimento
        txtDataNascimento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Impede a digitação de mais de 8 caracteres e garante que só números sejam inseridos
                if (!Character.isDigit(e.getKeyChar()) || txtDataNascimento.getText().length() >= 10) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String text = txtDataNascimento.getText();
                if (text.length() > 2 && text.length() <= 4 && !text.contains("/")) {
                    txtDataNascimento.setText(text.substring(0, 2) + "/" + text.substring(2));
                }
                if (text.length() > 5 && text.length() <= 7 && text.charAt(2) == '/') {
                    txtDataNascimento.setText(text.substring(0, 5) + "/" + text.substring(5));
                }
            }
        });

        setVisible(true);
    }

    private void validarFormulario() {
        // Validação do nome
        if (txtNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo Nome não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validação do email
        String email = txtEmail.getText();
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(this, "Email inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validação do CPF
        String cpf = txtCPF.getText().replaceAll("\\D", "");  // Remove tudo o que não é número
        if (cpf.length() != 11) {
            JOptionPane.showMessageDialog(this, "CPF inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validação do telefone
        String telefone = txtTelefone.getText().replaceAll("\\D", "");  // Remove tudo o que não é número
        if (telefone.length() != 11) {
            JOptionPane.showMessageDialog(this, "Telefone inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validação da data de nascimento
        String dataNascimento = txtDataNascimento.getText(); //.replaceAll("\\D", "");  // Remove tudo o que não é número
        if (dataNascimento.length() != 10) {
            JOptionPane.showMessageDialog(this, "Data de nascimento inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Chama a controller para salvar o passageiro
        boolean sucesso = passageiroController.salvarPassageiro(txtNome.getText(), txtEmail.getText(), cpf, telefone, dataNascimento);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Passageiro salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar passageiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
