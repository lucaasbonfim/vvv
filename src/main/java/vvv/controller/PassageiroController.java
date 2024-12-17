package vvv.controller;

import java.time.LocalDate;
import vvv.model.Passageiro;
import vvv.model.PassageiroDAO;

import java.util.List;

public class PassageiroController {

    private PassageiroDAO passageiroDAO;

    public PassageiroController() {
        this.passageiroDAO = new PassageiroDAO();
    }

    public boolean salvarPassageiro(String nome, String email, String cpf, String telefone, String dataNascimento) {
        
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("CPF: " + cpf);
        System.out.println("Telefone: " + telefone);
        System.out.println("Data de Nascimento: " + dataNascimento);
        try {
            // Conversão da data de nascimento
            LocalDate data = LocalDate.parse(dataNascimento, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            // Criação do objeto passageiro
            Passageiro passageiro = new Passageiro( nome, email, cpf, telefone, data);

            // Chama o DAO para salvar
            return passageiroDAO.salvar(passageiro);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Passageiro> listarPassageiros() {
        return passageiroDAO.listar();
    }
}
