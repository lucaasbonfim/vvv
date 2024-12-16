package vvv.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Reserva {
    private Long idReserva;
    private LocalDateTime dataReserva;
    private boolean statusReserva;
    private LocalDate dataViagem;
    private String partida;
    private String chegada;
    private BigDecimal valor;
    private Passageiro passageiro;
    private ModalTransporte modalTransporte;
    private PontoVenda pontoDeVenda;
    private Funcionario funcionario;

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDateTime getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }

    public boolean isStatusReserva() {
        return statusReserva;
    }

    public void setStatusReserva(boolean statusReserva) {
        this.statusReserva = statusReserva;
    }

    public LocalDate getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(LocalDate dataViagem) {
        this.dataViagem = dataViagem;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getChegada() {
        return chegada;
    }

    public void setChegada(String chegada) {
        this.chegada = chegada;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public ModalTransporte getModalTransporte() {
        return modalTransporte;
    }

    public void setModalTransporte(ModalTransporte modalTransporte) {
        this.modalTransporte = modalTransporte;
    }

    public PontoVenda getPontoDeVenda() {
        return pontoDeVenda;
    }

    public void setPontoDeVenda(PontoVenda pontoDeVenda) {
        this.pontoDeVenda = pontoDeVenda;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}