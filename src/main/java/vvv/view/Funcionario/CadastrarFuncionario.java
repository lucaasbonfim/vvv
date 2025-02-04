package vvv.view.Funcionario;

import vvv.controller.FuncionarioController;
import vvv.model.PontoVenda;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class CadastrarFuncionario extends JFrame {

    private FuncionarioController funcionarioController;

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JCheckBox chkCargo;
    private JComboBox<PontoVenda> cmbPontoDeVenda;
    private JButton btnSalvar;

    private List<PontoVenda> pontosVenda;  

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

        // ComboBox agora usa PontoVenda e o modelo de objetos PontoVenda
        cmbPontoDeVenda = new JComboBox<>();
        cmbPontoDeVenda.setModel(new DefaultComboBoxModel<>(pontosVenda.toArray(new PontoVenda[0])));
        
        // Personalizando a exibição no JComboBox
        cmbPontoDeVenda.setRenderer(new ListCellRenderer<PontoVenda>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends PontoVenda> list, PontoVenda value, int index, boolean isSelected, boolean cellHasFocus) {
                return new JLabel(value.getNome());  // Exibindo o nome do ponto de venda
            }
        });
        
        addField(mainPanel, gbc, row++, "Ponto de Venda:", cmbPontoDeVenda);

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
            // Agora estamos carregando os PontoVenda como objetos
            pontosVenda = funcionarioController.listarPontosVenda();

            if (pontosVenda == null || pontosVenda.isEmpty()) {
                throw new Exception("Nenhum ponto de venda encontrado.");
            }
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
            
            // Pegando o PontoDeVenda selecionado no JComboBox
            PontoVenda pontoDeVendaSelecionado = (PontoVenda) cmbPontoDeVenda.getSelectedItem();
            
            // Verificando se o ponto de venda foi selecionado corretamente
            if (pontoDeVendaSelecionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um ponto de venda.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Pegando o id do PontoDeVenda
            long pontoDeVendaId = pontoDeVendaSelecionado.getIdPontoVenda();
    
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
