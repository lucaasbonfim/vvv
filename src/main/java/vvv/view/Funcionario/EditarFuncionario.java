package vvv.view.Funcionario;

import vvv.controller.FuncionarioController;
import vvv.model.Funcionario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarFuncionario extends JFrame {

    private FuncionarioController funcionarioController;
    private Funcionario funcionario;

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JCheckBox chkCargo;
    private JLabel lblPontoVenda;

    public EditarFuncionario(Funcionario funcionario) {
        this.funcionarioController = new FuncionarioController();
        this.funcionario = funcionario;

        setTitle("Editar Funcionário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Nome:"));
        txtNome = new JTextField(funcionario.getNome());
        add(txtNome);

        add(new JLabel("CPF:"));
        txtCpf = new JTextField(funcionario.getCpf());
        add(txtCpf);

        add(new JLabel("Email:"));
        txtEmail = new JTextField(funcionario.getEmail());
        add(txtEmail);

        add(new JLabel("Senha:"));
        txtSenha = new JPasswordField(funcionario.getSenha());
        add(txtSenha);

        add(new JLabel("Cargo (Gerente):"));
        chkCargo = new JCheckBox();
        chkCargo.setSelected(funcionario.getCargo());
        add(chkCargo);

        add(new JLabel("Ponto de Venda:"));
        lblPontoVenda = new JLabel(funcionario.getPontoDeVenda() != null 
            ? funcionario.getPontoDeVenda().getNome() 
            : "Sem Ponto de Venda");
        add(lblPontoVenda);

        JButton btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        add(btnCancelar);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarEdicao();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void salvarEdicao() {
        try {
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();
            String email = txtEmail.getText();
            String senha = new String(txtSenha.getPassword());
            boolean cargo = chkCargo.isSelected();

            boolean sucesso = funcionarioController.editarFuncionario(
                funcionario.getIdFuncionario(), nome, cpf, email, senha, cargo, null);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Funcionário editado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao editar o funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao editar o funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Funcionario mockFuncionario = new Funcionario();
            mockFuncionario.setIdFuncionario(1L);
            mockFuncionario.setNome("João Silva");
            mockFuncionario.setCpf("123.456.789-00");
            mockFuncionario.setEmail("joao.silva@email.com");
            mockFuncionario.setSenha("123456");
            mockFuncionario.setCargo(true);

            vvv.model.PontoVenda mockPontoVenda = new vvv.model.PontoVenda();
            mockPontoVenda.setIdPontoVenda(2L);
            mockPontoVenda.setNome("Loja Central");
            mockFuncionario.setPontoDeVenda(mockPontoVenda);

            EditarFuncionario tela = new EditarFuncionario(mockFuncionario);
            tela.setVisible(true);
        });
    }
}
