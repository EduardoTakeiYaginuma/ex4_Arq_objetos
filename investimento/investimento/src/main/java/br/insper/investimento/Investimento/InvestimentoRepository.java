package br.insper.investimento.Investimento;

import br.insper.investimento.Investidor.Investidor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestimentoRepository extends JpaRepository<Investimento, Integer> {
    List<Investimento> findByInvestidor(Investidor investidor);
}
