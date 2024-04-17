package br.insper.investimento.Investimento;

import br.insper.investimento.Investidor.Investidor;
import br.insper.investimento.Titulos.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestimentoRepository extends JpaRepository<Investimento, Integer> {
    List<Investimento> findByInvestidor(Investidor investidor);
    List<Investimento> findByTitulo(Titulo titulo);
}
