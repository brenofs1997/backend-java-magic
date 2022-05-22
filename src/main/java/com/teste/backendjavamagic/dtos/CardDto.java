package com.teste.backendjavamagic.dtos;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class CardDto {

    @NotEmpty(message = "Name may not be empty")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters long")
    private String nome;
    @NotEmpty(message = "nomeJogador may not be empty")
    @Size(min = 1, max = 50, message = "nomeJogador must be between 1 and 50 characters long")
    private String nomeJogador;
    @NotBlank
    private String edicao;
    @NotBlank
    private String idioma;
    @NotNull
    private Boolean  foil;


    @NotNull(message = "preco may not be null")
    @Range(min = 1)
    private BigDecimal preco;

    @NotNull(message = "qtde_semelhantes may not be null")
    private Integer qtde_semelhantes;

    public String getNome() {
        return nome;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Boolean getFoil() {
        return foil;
    }

    public void setFoil(Boolean foil) {
        this.foil = foil;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQtde_semelhantes() {
        return qtde_semelhantes;
    }

    public void setQtde_semelhantes(Integer qtde_semelhantes) {
        this.qtde_semelhantes = qtde_semelhantes;
    }
}
