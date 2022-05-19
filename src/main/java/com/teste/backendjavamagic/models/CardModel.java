package com.teste.backendjavamagic.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "card")
public class CardModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 50)
    private String nome;
    @Column(nullable = false, length = 50)
    private String edicao;
    @Column(nullable = false, length = 10)
    private String idioma;
    @Column(nullable = false)
    private Boolean  foil;
    @Column(nullable = false, precision = 8, scale = 2)
    @Type(type = "big_decimal")
    private BigDecimal preco;
    @Column(nullable = false)
    private Integer qtde_semelhantes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
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
