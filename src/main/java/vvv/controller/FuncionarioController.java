package vvv.controller;

import vvv.model.Funcionario;
import vvv.model.DAOs.FuncionarioDAO;
import vvv.model.DAOs.PontoVendaDAO;
import vvv.model.PontoVenda;
import vvv.model.DAOs.PontoVendaDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {

    private FuncionarioDAO funcionarioDAO;

    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public boolean salvarFuncionario(String nome, String cpf, String email, String senha, Boolean cargo, Long pontoVendaId) {
        try {
            PontoVenda pontoVenda = new PontoVenda();
            pontoVenda.setIdPontoVenda(pontoVendaId);

            Funcionario funcionario = new Funcionario(nome, cpf, email, senha, cargo, pontoVenda);

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
            Funcionario funcionario = funcionarioDAO.buscarPorId(id);

            funcionario.setNome(nome);
            funcionario.setCpf(cpf);
            funcionario.setEmail(email);
            funcionario.setSenha(senha);
            funcionario.setCargo(cargo);

            PontoVenda pontoVenda = new PontoVenda();
            pontoVenda.setIdPontoVenda(pontoVendaId);
            funcionario.setPontoDeVenda(pontoVenda);

            return funcionarioDAO.editar(funcionario);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Funcionario> buscarFuncionariosPorNome(String nome) {
        return funcionarioDAO.buscarPorNome(nome);
    }

    public List<PontoVenda> listarPontosVenda() throws Exception {
        PontoVendaDAO pontoVendaDAO = new PontoVendaDAO();
        return pontoVendaDAO.listar();
    }

    public Funcionario autenticarFuncionario(String email, String senha) {
        return funcionarioDAO.autenticar(email, senha);
    }

    public boolean deletarFuncionario(Long idFuncionario) {
        try {
            boolean sucesso = funcionarioDAO.deletar(idFuncionario);

            return sucesso;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
