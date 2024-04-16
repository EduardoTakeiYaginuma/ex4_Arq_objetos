package br.insper.investimento.Investimento;

import br.insper.investimento.Investidor.Investidor;
import br.insper.investimento.Titulos.Titulo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Investimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_investidor")
    private Investidor investidor;

    @ManyToOne
    @JoinColumn(name = "id_titulo")
    private Titulo titulo;
    private Double valorInvestido;
    private LocalDateTime dataInvestimento;

    public Investimento() {
    }
    public Investimento(Investidor investidor, Titulo titulo, Double valorInvestido) {
        this.investidor = investidor;
        this.titulo = titulo;
        this.valorInvestido = valorInvestido;
        this.dataInvestimento = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public void setInvestidor(Investidor investidor) {
        this.investidor = investidor;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public Double getValorInvestido() {
        return valorInvestido;
    }

    public void setValorInvestido(Double valorInvestido) {
        this.valorInvestido = valorInvestido;
    }

    public LocalDateTime getDataInvestimento() {
        return dataInvestimento;
    }

    public void setDataInvestimento(LocalDateTime dataInvestimento) {
        this.dataInvestimento = dataInvestimento;
    }
}
