package vvv.controller;

import vvv.model.PontoVenda;
import vvv.model.DAOs.PontoVendaDAO;

import java.util.List;

public class PontoVendaController {
    
    private PontoVendaDAO pontoVendaDAO;

    public PontoVendaController() {
        this.pontoVendaDAO = new PontoVendaDAO();
    }

    public boolean salvarPontoVenda(String nome, String localizacao){

        try {
            PontoVenda pontoVenda = new PontoVenda(nome, localizacao);
            return pontoVendaDAO.salvar(pontoVenda);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PontoVenda> listarModal(){
        return pontoVendaDAO.listar();
    }

    public PontoVenda buscarPontoVenda(long id) {
        return pontoVendaDAO.buscarPorId(id);
    }

    public boolean EditarPontoVenda(long id, String nome, String localizacao)
    {
        try {
           
            PontoVenda pontoVenda = pontoVendaDAO.buscarPorId(id);

            pontoVenda.setNome(nome);
            pontoVenda.setLocalizacao(localizacao);

            
            return pontoVendaDAO.editar(pontoVenda);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PontoVenda> buscarPorNome(String nome) {
        return pontoVendaDAO.listarPontoVendasPorNome(nome);
    }
}
