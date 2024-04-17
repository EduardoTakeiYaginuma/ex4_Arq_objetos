package br.insper.investimento.Titulos;

import br.insper.investimento.Investidor.Investidor;
import br.insper.investimento.Investidor.InvestidorRepository;
import br.insper.investimento.Investimento.Investimento;
import br.insper.investimento.Investimento.InvestimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TituloService {
    @Autowired
    private TituloRepository tituloRepository;
    @Autowired
    private InvestimentoRepository investimentoRepository;

    public Titulo cadastrarTitulo(Titulo titulo) {
        if (titulo.getNome() == null || titulo.getTipoTitulo() == null || titulo.getTipoTitulo() == null){
            throw new RuntimeException("Nome, tipo do título e rendimento não podem ser nulos");
        }
        if (titulo.getTipoTitulo().equals("Renda Fixa") || titulo.getTipoTitulo().equals("Ação") || titulo.getTipoTitulo().equals("Fundo de Investimento")){
            return tituloRepository.save(titulo);
        }

        else{
            throw new RuntimeException("Tipo de título inválido");
        }
    }
    public void deletarTitulo(Integer id) {
        Titulo titulo = tituloRepository.findTituloById(id);
        List<Investimento> investidores = investimentoRepository.findByTitulo(titulo);
        if (!investidores.isEmpty()) {
            throw new IllegalArgumentException("O título possui investidores e não pode ser deletado");
        }
        tituloRepository.deleteById(id);
    }

    public List<Titulo> getTitulo(String tipoTitulo) {
        if (tipoTitulo == null) {
            return tituloRepository.findAll();
        }
        return tituloRepository.findByTipoTitulo(tipoTitulo);
    }
}
