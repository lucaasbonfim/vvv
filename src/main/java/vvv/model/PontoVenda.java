package vvv.model;


public class PontoVenda {
    private Long idPontoVenda;
    private String nome;
    private String localizacao;

    public PontoVenda() {
    }

    public Long getIdPontoVenda() {
        return idPontoVenda;
    }

    public void setIdPontoVenda(Long idPontoVenda) {
        this.idPontoVenda = idPontoVenda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
