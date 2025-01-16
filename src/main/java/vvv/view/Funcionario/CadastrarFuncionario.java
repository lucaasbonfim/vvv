package vvv.view.Funcionario;

import vvv.controller.FuncionarioController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarFuncionario extends JFrame {

    private FuncionarioController funcionarioController;

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JCheckBox chkCargo;
    private JTextField txtPontoDeVenda;

    public CadastrarFuncionario() {
        funcionarioController = new FuncionarioController();

        setTitle("Cadastrar Funcionário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new GridLayout(7, 2, 10, 10));

        // Campos de entrada
        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        add(txtCpf);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        add(txtSenha);

        add(new JLabel("Cargo (Gerente):"));
        chkCargo = new JCheckBox();
        add(chkCargo);

        add(new JLabel("ID do Ponto de Venda:"));
        txtPontoDeVenda = new JTextField();
        add(txtPontoDeVenda);

        // Botão de salvar
        JButton btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        add(btnCancelar);

        // Ações dos botões
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarFuncionario();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void salvarFuncionario() {
        try {
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();
            String email = txtEmail.getText();
            String senha = new String(txtSenha.getPassword());
            boolean cargo = chkCargo.isSelected();
            long pontoDeVendaId = Long.parseLong(txtPontoDeVenda.getText());

            boolean sucesso = funcionarioController.salvarFuncionario(nome, cpf, email, senha, cargo, pontoDeVendaId);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um ID de Ponto de Venda válido.", "Erro", JOptionPane.ERROR_MESSAGE);
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
        txtPontoDeVenda.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastrarFuncionario tela = new CadastrarFuncionario();
            tela.setVisible(true);
        });
    }
}
