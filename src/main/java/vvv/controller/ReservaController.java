package vvv.controller;

import vvv.model.Reserva;
import vvv.model.Passageiro;
import vvv.model.ModalTransporte;
import vvv.model.PontoVenda;
import vvv.model.Funcionario;
import vvv.model.DAOs.ReservaDAO;
import vvv.model.DAOs.PassageiroDAO;
import vvv.model.DAOs.PontoVendaDAO;
import vvv.model.DAOs.FuncionarioDAO;
import vvv.model.DAOs.ModalDAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReservaController {

    private ReservaDAO reservaDAO;
    private PassageiroDAO passageiroDAO;
    private ModalDAO modalDAO;
    private PontoVendaDAO pontoVendaDAO;
    private FuncionarioDAO funcionarioDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
        this.passageiroDAO = new PassageiroDAO();
        this.modalDAO = new ModalDAO();
        this.pontoVendaDAO = new PontoVendaDAO();
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public boolean salvarReserva(LocalDateTime dataReserva, boolean statusReserva, LocalDate dataViagem, 
                                 String partida, String chegada, BigDecimal valor, 
                                 Long passageiroId, Long modalTransporteId, 
                                 Long pontoVendaId, Long funcionarioId) {
        try {
            Passageiro passageiro = passageiroDAO.buscarPorId(passageiroId);
            ModalTransporte modalTransporte = modalDAO.buscarPorId(modalTransporteId);
            PontoVenda pontoVenda = pontoVendaDAO.buscarPorId(pontoVendaId);
            Funcionario funcionario = funcionarioDAO.buscarPorId(funcionarioId);

            Reserva reserva = new Reserva();
            reserva.setDataReserva(dataReserva);
            reserva.setStatusReserva(statusReserva);
            reserva.setDataViagem(dataViagem);
            reserva.setPartida(partida);
            reserva.setChegada(chegada);
            reserva.setValor(valor);
            reserva.setPassageiro(passageiro);
            reserva.setModalTransporte(modalTransporte);
            reserva.setPontoDeVenda(pontoVenda);
            reserva.setFuncionario(funcionario);

            return reservaDAO.salvar(reserva);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarReserva(Long idReserva, LocalDateTime dataReserva, boolean statusReserva, LocalDate dataViagem, 
                                 String partida, String chegada, BigDecimal valor, 
                                 Long passageiroId, Long modalTransporteId, 
                                 Long pontoVendaId, Long funcionarioId) {
        try {
            Reserva reserva = reservaDAO.buscarPorId(idReserva);

            if (reserva == null) {
                return false;
            }

            reserva.setDataReserva(dataReserva);
            reserva.setStatusReserva(statusReserva);
            reserva.setDataViagem(dataViagem);
            reserva.setPartida(partida);
            reserva.setChegada(chegada);
            reserva.setValor(valor);

            Passageiro passageiro = passageiroDAO.buscarPorId(passageiroId);
            reserva.setPassageiro(passageiro);

            ModalTransporte modalTransporte = modalDAO.buscarPorId(modalTransporteId);
            reserva.setModalTransporte(modalTransporte);

            PontoVenda pontoVenda = pontoVendaDAO.buscarPorId(pontoVendaId);
            reserva.setPontoDeVenda(pontoVenda);

            Funcionario funcionario = funcionarioDAO.buscarPorId(funcionarioId);
            reserva.setFuncionario(funcionario);

            return reservaDAO.editar(reserva);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Reserva buscarReservaPorId(Long idReserva) {
        return reservaDAO.buscarPorId(idReserva);
    }

    public List<Reserva> listarReservas() {
        return reservaDAO.listar();
    }

    public List<Passageiro> listarPassageiros() {
        return passageiroDAO.listar();
    }

    public List<ModalTransporte> listarModais() {
        return modalDAO.listar();
    }

    public List<PontoVenda> listarPontosVenda() {
        return pontoVendaDAO.listar();
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioDAO.listar();
    }

    public boolean editarReserva(Long reservaId, LocalDate dataReserva, boolean statusReserva, LocalDate dataViagem,
            String partida, String chegada, BigDecimal valor, Long passageiroId, Long modalTransporteId,
            Long pontoVendaId, Long funcionarioId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editarReserva'");
    }
}
