package vvv.view.PontoVenda;

import vvv.controller.PontoVendaController;
import vvv.model.PontoVenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarPontoVendaTela extends JFrame {

    private JTextField nomeField;
    private JTextField localizacaoField;
    private JButton atualizarButton;
    private JButton deletarButton;
    private PontoVendaController pontoVendaController;
    private long idPontoVenda;

    public EditarPontoVendaTela(long id) {
        setTitle("Editar Ponto de Venda");
        setSize(400, 350);  // Ajustado o tamanho para incluir o botão Deletar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pontoVendaController = new PontoVendaController();
        idPontoVenda = id;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));  

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        JLabel localizacaoLabel = new JLabel("Localização:");
        localizacaoField = new JTextField();
        atualizarButton = new JButton("Atualizar");
        deletarButton = new JButton("Deletar");

        // Botão Atualizar
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarPontoVenda();
            }
        });

        // Botão Deletar
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarPontoVenda();
            }
        });

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(localizacaoLabel);
        panel.add(localizacaoField);
        panel.add(new JLabel());  
        panel.add(atualizarButton);
        panel.add(new JLabel()); 
        panel.add(deletarButton);

        add(panel);

        carregarDadosPontoVenda();
    }

    private void carregarDadosPontoVenda() {
        PontoVenda pontoVenda = pontoVendaController.buscarPontoVenda(idPontoVenda);

        if (pontoVenda != null) {
            nomeField.setText(pontoVenda.getNome());
            localizacaoField.setText(pontoVenda.getLocalizacao());
        } else {
            JOptionPane.showMessageDialog(this, "Ponto de venda não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarPontoVenda() {
        String nome = nomeField.getText();
        String localizacao = localizacaoField.getText();

        boolean sucesso = pontoVendaController.EditarPontoVenda(idPontoVenda, nome, localizacao);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Ponto de venda atualizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar ponto de venda.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarPontoVenda() {
        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja excluir este ponto de venda?",
                "Confirmar Deletação",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (resposta == JOptionPane.YES_OPTION) {
            boolean sucesso = pontoVendaController.deletarPontoVenda(idPontoVenda);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Ponto de venda deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();  // Fecha a janela após a deleção
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao deletar ponto de venda.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EditarPontoVendaTela tela = new EditarPontoVendaTela(1);
            tela.setVisible(true);
        });
    }
}
