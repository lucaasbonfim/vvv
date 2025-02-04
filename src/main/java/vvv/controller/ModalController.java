package vvv.controller;

import vvv.model.ModalTransporte;
import vvv.model.DAOs.ModalDAO;

import java.util.List;

public class ModalController {
    
    private ModalDAO modalDAO;

    public ModalController() {
        this.modalDAO = new ModalDAO();
    }

    public boolean salvarModal(String modelo, Integer capacidade, Integer anoFabricacao, String tipo, Boolean ativo){

        try {
            ModalTransporte modal = new ModalTransporte(modelo, capacidade, anoFabricacao, tipo, ativo);
            return modalDAO.salvar(modal);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ModalTransporte> listarModal(){
        return modalDAO.listar();
    }

    public ModalTransporte buscarModal(long id) {
        return modalDAO.buscarPorId(id);
    }

    public boolean EditarModal(long id, String modelo, Integer capacidade, Integer anoFabricacao, String tipo, Boolean ativo)
    {
        try {
            ModalTransporte modal = modalDAO.buscarPorId(id);

            modal.setModelo(modelo);
            modal.setCapacidade(capacidade);
            modal.setAnoFabricacao(anoFabricacao);
            modal.setTipo(tipo);
            modal.setAtivo(ativo);

            return modalDAO.editar(modal);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ModalTransporte> buscarPorNome(String nome) {
        return modalDAO.listarModaisPorNome(nome);
    }

    public boolean deletarModal(Long idModal) {
        try {
            boolean sucesso = modalDAO.deletar(idModal);

            return sucesso;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
