package br.insper.investimento.Investimento;

import br.insper.investimento.Investidor.Investidor;
import br.insper.investimento.Investidor.InvestidorRepository;
import br.insper.investimento.Titulos.Titulo;
import br.insper.investimento.Titulos.TituloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class InvestimentoService {
    @Autowired
    private InvestimentoRepository investimentoRepository;
    @Autowired
    private InvestidorRepository investidorRepository;
    @Autowired
    private TituloRepository tituloRepository;

    public Investimento cadastrarInvestimento(Investimento investimento) {
        investimento.setDataInvestimento(LocalDateTime.now());
        if (investimento.getInvestidor() == null) {
            throw new IllegalArgumentException("Investidor não pode ser nulo");
        }

        if (investimento.getInvestidor().getPerfilInvestidor().equals("Conservador") && investimento.getTitulo().getTipoTitulo().equals("Ação")) {
            throw new IllegalArgumentException("Proibido conservadores investirem em ações");
        }
        Investidor investidor = investidorRepository.findByCpf(investimento.getInvestidor().getCpf());
        if (investidor == null) {
            throw new IllegalArgumentException("Investidor não encontrado");
        }
        investimento.setInvestidor(investidor);
        List<Investimento> investimentos = investimentoRepository.findByInvestidor(investidor);

        if (investimento.getInvestidor().getPerfilInvestidor().equals("MODERADO")){
            Double valorInvestido = 0.0;
            for (Investimento i : investimentos) {
                if (i.getTitulo().getTipoTitulo().equals("Ação"){
                    valorInvestido += i.getValorInvestido();
                }
            }
            if (valorInvestido > 0){
                if (investimento.getValorInvestido() > 0.5*valorInvestido){
                    throw new IllegalArgumentException("Valor investido não pode ser maior que 50% do valor total investido");
                }
            }
        }
        Titulo titulo = tituloRepository.findByIdentificador(investimento.getTitulo().getIdentificador());
        if (titulo == null) {
            throw new IllegalArgumentException("Título não encontrado");
        }
        investimento.setTitulo(titulo);
        return investimentoRepository.save(investimento);
    }

    public List<Investimento> getInvestimento(String cpf) {
       Investidor investidor = investidorRepository.findByCpf(cpf);
       if (investidor == null) {
              throw new IllegalArgumentException("Investidor não encontrado");
       }
       return investimentoRepository.findByInvestidor(investidor);
    }
}
