/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vvv.model;

import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class Funcionario {
    private Long idFuncionario;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private Boolean cargo;
    private PontoVenda pontoVenda;

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

        public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public Boolean getCargo() {
        return cargo;
    }

    public void setCargo(Boolean cargo) {
        this.cargo = cargo;
    }

    public PontoVenda getPontoDeVenda() {
        return pontoVenda;
    }

    public void setPontoDeVenda(PontoVenda pontoDeVenda) {
        this.pontoVenda = pontoDeVenda;
    }
}