package br.insper.investimento.Investidor;

import br.insper.investimento.Investimento.Investimento;
import br.insper.investimento.Investimento.InvestimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestidorService {
    @Autowired
    private InvestidorRepository investidorRepository;
    @Autowired
    private InvestimentoRepository investimentoRepository;

    public Investidor cadastrarInvestidor(Investidor investidor) {
        if (investidor.getPerfilInvestidor() == null || investidor.getNome() == null || investidor.getCpf() == null){
            throw new RuntimeException("Perfil do investidor não pode ser nulo");
        }
        if (!investidor.getPerfilInvestidor().equals("CONSERVADOR") && !investidor.getPerfilInvestidor().equals("MODERADO") && !investidor.getPerfilInvestidor().equals("AGRESSIVO")) {
            throw new IllegalArgumentException("Perfil do investidor inválido");
        }
        return investidorRepository.save(investidor);
    }

    public List<Investidor> listarInvestidores(String perfilInvestidor) {
        if (perfilInvestidor == null) {
            return investidorRepository.findAll();
        }
        return investidorRepository.findByPerfilInvestidor(perfilInvestidor);
    }

    public void deletarInvestidor(String cpf) {
        Investidor investidor = investidorRepository.findByCpf(cpf);
        List<Investimento> investimentos = investimentoRepository.findByInvestidor(investidor);
        if (!investimentos.isEmpty()) {
            throw new IllegalArgumentException("O investidor possui investimentos e não pode ser deletado");
        }
        investidorRepository.delete(investidor);
    }

}
