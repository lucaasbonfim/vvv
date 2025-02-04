package vvv.view;

import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;

import vvv.view.Funcionario.CadastrarFuncionario;
import vvv.view.Funcionario.ConsultarFuncionario;
import vvv.view.Modal.CadastrarModal;
import vvv.view.Modal.ConsultarModal;
import vvv.view.Passageiro.TelaConsultarPassageiro;
import vvv.view.Passageiro.TelaCriarPassageiro;
import vvv.view.PontoVenda.CadastrarPontoVendaTela;
import vvv.view.PontoVenda.ConsultarPontoVendaTela;
import vvv.view.Reserva.CadastrarReserva;
import vvv.view.Reserva.ConsultarReserva;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaPrincipal extends JFrame {

    private JPanel titleBar;
    private boolean isGerente = false;

    public TelaPrincipal(boolean isGerente) {
        this.isGerente = isGerente;
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        initComponents();
    }


    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setLayout(new BorderLayout());

        titleBar = new JPanel();
        titleBar.setBackground(Color.DARK_GRAY);
        titleBar.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Vai&Volta Viagens");
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

        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        if (isGerente) {
            JButton btnFuncionario = createCentralButton("Funcionário");
            btnFuncionario.addActionListener(e -> showOptions("Funcionário"));
            painelCentral.add(btnFuncionario, gbc);
            gbc.gridy++;

            JButton btnModal = createCentralButton("Modal");
            btnModal.addActionListener(e -> showOptions("Modal"));
            painelCentral.add(btnModal, gbc);
            gbc.gridy++;

            JButton btnPontoVenda = createCentralButton("Ponto de Venda");
            btnPontoVenda.addActionListener(e -> showOptions("Ponto de Venda"));
            painelCentral.add(btnPontoVenda, gbc);
            gbc.gridy++;
        }

        JButton btnPassageiro = createCentralButton("Passageiro");
        btnPassageiro.addActionListener(e -> showOptions("Passageiro"));
        painelCentral.add(btnPassageiro, gbc);
        gbc.gridy++;

        JButton btnReserva = createCentralButton("Reserva");
        btnReserva.addActionListener(e -> showOptions("Reserva"));
        painelCentral.add(btnReserva, gbc);

        JPanel painelFundo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagemFundo = new ImageIcon(getClass().getResource("/img/logo.png"));
                g.drawImage(imagemFundo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        painelFundo.setLayout(new BorderLayout());
        painelFundo.add(painelCentral, BorderLayout.WEST);

        add(painelFundo, BorderLayout.CENTER);

        setSize(800, 600);
        setLocationRelativeTo(null);
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

    private JButton createCentralButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(60, 63, 65));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 40));
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

    private void showOptions(String category) {
        Object[] options = {"Cadastrar", "Consultar"};
        int response = JOptionPane.showOptionDialog(this, "Escolha uma ação para " + category,
                category + " - Opções", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        switch (category) {
            case "Funcionário":
                if (response == 0) new CadastrarFuncionario().setVisible(true);
                else if (response == 1) new ConsultarFuncionario().setVisible(true);
                break;
            case "Modal":
                if (response == 0) new CadastrarModal().setVisible(true);
                else if (response == 1) new ConsultarModal().setVisible(true);
                break;
            case "Passageiro":
                if (response == 0) new TelaCriarPassageiro().setVisible(true);
                else if (response == 1) new TelaConsultarPassageiro(this).setVisible(true);
                break;
            case "Ponto de Venda":
                if (response == 0) new CadastrarPontoVendaTela().setVisible(true);
                else if (response == 1) new ConsultarPontoVendaTela().setVisible(true);
                break;
            case "Reserva":
                if (response == 0) new CadastrarReserva().setVisible(true);
                else if (response == 1) new ConsultarReserva().setVisible(true);
                break;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new TelaPrincipal(true).setVisible(true));
    }
}
