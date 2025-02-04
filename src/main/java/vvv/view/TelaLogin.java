package vvv.view;

import vvv.controller.FuncionarioController;
import vvv.model.Funcionario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaLogin extends JFrame {

    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton loginButton;
    private FuncionarioController funcionarioController;
    private JPanel titleBar;
    private JPanel mainPanel;

    public TelaLogin() {
      try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        funcionarioController = new FuncionarioController();

        setTitle("Login - Sistema VVV");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0)); // Ajuste para não ter espaço entre componentes

        initComponents();
    }

    private void initComponents() {
        // Remover a barra de título padrão
        setUndecorated(true);

        // Barra de título personalizada
        titleBar = new JPanel();
        titleBar.setBackground(Color.DARK_GRAY);
        titleBar.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Sistema VVV");
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        buttonPanel.setOpaque(false);

        JButton minimizeButton = createTitleBarButton("-");
        minimizeButton.addActionListener(e -> setState(Frame.ICONIFIED));

        JButton closeButton = createTitleBarButton("X");
        closeButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(minimizeButton);
        buttonPanel.add(closeButton);

        titleBar.add(titleLabel, BorderLayout.WEST);
        titleBar.add(buttonPanel, BorderLayout.EAST);

        addDragFunctionality(titleBar);
        add(titleBar, BorderLayout.NORTH);

        // Painel principal sem bordas extras
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Espaçamento suave
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        emailField = new JTextField();
        senhaField = new JPasswordField();

        addField(mainPanel, gbc, row++, "Email:", emailField);
        addField(mainPanel, gbc, row++, "Senha:", senhaField);

        // Botão Login
        loginButton = new JButton("Entrar");
        loginButton.addActionListener(e -> autenticarUsuario());

        // Painel do botão sem fundo e sem borda
        JPanel loginButtonPanel = new JPanel();
        loginButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        loginButtonPanel.add(loginButton);

        // Adiciona os componentes à tela
        add(mainPanel, BorderLayout.CENTER);
        add(loginButtonPanel, BorderLayout.SOUTH);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 6;  // Ajuste para o tamanho desejado (dobro da largura)
        field.setPreferredSize(new Dimension(200, 25));  // Ajuste dinâmico
        panel.add(field, gbc);
    }

    private JButton createTitleBarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.LIGHT_GRAY);
        button.setBackground(Color.DARK_GRAY);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void addDragFunctionality(JPanel panel) {
        final Point[] dragPoint = {null};
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragPoint[0] = e.getPoint();
            }
        });
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragPoint[0] != null) {
                    Point current = e.getLocationOnScreen();
                    setLocation(current.x - dragPoint[0].x, current.y - dragPoint[0].y);
                }
            }
        });
    }

    private void autenticarUsuario() {
        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());

        Funcionario funcionario = funcionarioController.autenticarFuncionario(email, senha);

        if (funcionario != null) {
            boolean isGerente = funcionario.getCargo();
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new TelaPrincipal(isGerente).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Email ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        });
    }
}
