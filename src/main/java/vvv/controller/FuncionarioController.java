package vvv.controller;

import vvv.model.Funcionario;
import vvv.model.DAOs.FuncionarioDAO;
import vvv.model.PontoVenda;

import java.util.List;

public class FuncionarioController {

    private FuncionarioDAO funcionarioDAO;

    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public boolean salvarFuncionario(String nome, String cpf, String email, String senha, Boolean cargo, Long pontoVendaId) {
        try {
            // Criar o objeto PontoVenda associado
            PontoVenda pontoVenda = new PontoVenda();
            pontoVenda.setIdPontoVenda(pontoVendaId);

            // Criar o objeto Funcionario com os dados fornecidos
            Funcionario funcionario = new Funcionario(nome, cpf, email, senha, cargo, pontoVenda);

            // Salvar o funcionario usando o DAO
            return funcionarioDAO.salvar(funcionario);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioDAO.listar();
    }

    public Funcionario buscarFuncionarioPorId(long id) {
        return funcionarioDAO.buscarPorId(id);
    }

    public boolean editarFuncionario(long id, String nome, String cpf, String email, String senha, Boolean cargo, Long pontoVendaId) {
        try {
            // Buscar o funcionário existente pelo ID
            Funcionario funcionario = funcionarioDAO.buscarPorId(id);

            // Atualizar os atributos do funcionário
            funcionario.setNome(nome);
            funcionario.setCpf(cpf);
            funcionario.setEmail(email);
            funcionario.setSenha(senha);
            funcionario.setCargo(cargo);

            // Criar e associar o PontoVenda ao funcionário
            PontoVenda pontoVenda = new PontoVenda();
            pontoVenda.setIdPontoVenda(pontoVendaId);
            funcionario.setPontoDeVenda(pontoVenda);

            // Editar o funcionário usando o DAO
            return funcionarioDAO.editar(funcionario);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Funcionario> buscarFuncionariosPorNome(String nome) {
        return funcionarioDAO.buscarPorNome(nome);
    }
}
