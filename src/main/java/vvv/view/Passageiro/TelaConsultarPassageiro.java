package vvv.view.Passageiro;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import vvv.controller.PassageiroController;
import vvv.model.Passageiro;

public class TelaConsultarPassageiro extends JDialog {

    private JTable tabelaPassageiros;
    private DefaultTableModel modeloTabela;
    private PassageiroController passageiroController;
    private JTextField campoBuscaNome;
    private JButton btnBuscar;

    public TelaConsultarPassageiro(Frame parent) {
        super(parent, "Consultar Passageiros", true);
        passageiroController = new PassageiroController();
        initComponents();
    }

    private void initComponents() {
        setSize(800, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel de filtro
        JPanel panelFiltro = new JPanel();
        panelFiltro.setLayout(new FlowLayout(FlowLayout.LEFT));

        campoBuscaNome = new JTextField(20); // Campo para digitar o nome
        btnBuscar = new JButton("Buscar");

        btnBuscar.addActionListener(e -> carregarPassageiros(campoBuscaNome.getText()));

        panelFiltro.add(new JLabel("Buscar por nome:"));
        panelFiltro.add(campoBuscaNome);
        panelFiltro.add(btnBuscar);

        // Modelo da tabela
        modeloTabela = new DefaultTableModel(new Object[]{"id", "Nome", "Email", "CPF", "Telefone", "Data de Nascimento"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede a edição direta dos dados na tabela
            }
        };
        tabelaPassageiros = new JTable(modeloTabela);

        // Adicionar evento de duplo clique
        tabelaPassageiros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Verifica se foi duplo clique
                    int row = tabelaPassageiros.getSelectedRow();
                    long id = (long) modeloTabela.getValueAt(row, 0); // Pega o id do passageiro
                    Passageiro passageiro = passageiroController.buscarPassageiro(id); // Busca o passageiro pelo id

                    if (passageiro != null) {
                        // Chama a tela de edição passando o passageiro encontrado
                        new TelaEditarPassageiro((Frame) getParent(), passageiroController, passageiro).setVisible(true);
                    }
                }
            }
        });

        // Painel com barra de rolagem
        JScrollPane scrollPane = new JScrollPane(tabelaPassageiros);

        // Botões
        JPanel panelBotoes = new JPanel();
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnFechar = new JButton("Fechar");

        btnAtualizar.addActionListener(e -> carregarPassageiros(campoBuscaNome.getText()));
        btnFechar.addActionListener(e -> dispose());

        panelBotoes.add(btnAtualizar);
        panelBotoes.add(btnFechar);

        // Adicionando os componentes ao layout
        add(panelFiltro, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);

        // Carregar os passageiros ao abrir
        carregarPassageiros(""); // Inicializa com todos os passageiros
    }

    private void carregarPassageiros(String nomeFiltro) {
        modeloTabela.setRowCount(0); // Limpa os dados existentes na tabela
        List<Passageiro> passageiros;

        if (nomeFiltro.isEmpty()) {
            passageiros = passageiroController.listarPassageiros(); // Sem filtro
        } else {
            passageiros = passageiroController.buscarPorNome(nomeFiltro); // Com filtro de nome
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Passageiro passageiro : passageiros) {
            modeloTabela.addRow(new Object[]{
                    passageiro.getId(),
                    passageiro.getNome(),
                    passageiro.getEmail(),
                    passageiro.getCpf(),
                    passageiro.getTelefone(),
                    passageiro.getDataNascimento().format(formatter)
            });
        }
    }
}
