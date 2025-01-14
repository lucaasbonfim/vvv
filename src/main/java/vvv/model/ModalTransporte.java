package vvv.model;


public class ModalTransporte {
    private Long idModal;
    private String modelo;
    private Integer capacidade;
    private Integer anoFabricacao;
    private String tipo;
    private Boolean ativo;

    public ModalTransporte(String modelo, int capacidade, int anoFabricacao, String tipo, boolean ativo) {
        this.modelo = modelo;
        this.capacidade = capacidade;
        this.anoFabricacao = anoFabricacao;
        this.tipo = tipo;
        this.ativo = ativo;
    }

    public Long getIdModal() {
        return idModal;
    }

    public void setIdModal(Long idModal) {
        this.idModal = idModal;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
