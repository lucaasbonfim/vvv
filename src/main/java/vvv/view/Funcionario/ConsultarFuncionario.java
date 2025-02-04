package vvv.view.Funcionario;

import vvv.controller.FuncionarioController;
import vvv.model.Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ConsultarFuncionario extends JFrame {

    private FuncionarioController funcionarioController;
    private JTable tabelaFuncionarios;
    private DefaultTableModel tableModel;

    public ConsultarFuncionario() {
        funcionarioController = new FuncionarioController();

        setTitle("Consultar Funcionários");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"ID", "Nome", "CPF", "Email", "Cargo"}, 0);
        tabelaFuncionarios = new JTable(tableModel);

        tabelaFuncionarios.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(tabelaFuncionarios);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> carregarFuncionarios());
        add(btnAtualizar, BorderLayout.SOUTH);

        tabelaFuncionarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = tabelaFuncionarios.getSelectedRow();
                    if (selectedRow != -1) {
                        long idFuncionario = Long.parseLong(tableModel.getValueAt(selectedRow, 0).toString());
                        abrirTelaEditarFuncionario(idFuncionario);
                    }
                }
            }
        });

        carregarFuncionarios();
    }

    private void carregarFuncionarios() {
        tableModel.setRowCount(0);

        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
        for (Funcionario f : funcionarios) {
            tableModel.addRow(new Object[]{
                    f.getIdFuncionario(),
                    f.getNome(),
                    f.getCpf(),
                    f.getEmail(),
                    f.getCargo() ? "Gerente" : "Funcionário"
            });
        }
    }

    private void abrirTelaEditarFuncionario(long idFuncionario) {
        Funcionario funcionario = funcionarioController.buscarFuncionarioPorId(idFuncionario);
        if (funcionario != null) {
            SwingUtilities.invokeLater(() -> {
                EditarFuncionario editarFuncionarioTela = new EditarFuncionario(funcionario);
                editarFuncionarioTela.setVisible(true);
            });
        } else {
            JOptionPane.showMessageDialog(this, "Funcionário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultarFuncionario tela = new ConsultarFuncionario();
            tela.setVisible(true);
        });
    }
}
