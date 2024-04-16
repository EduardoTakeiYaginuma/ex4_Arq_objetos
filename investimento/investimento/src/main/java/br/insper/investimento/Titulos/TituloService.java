package br.insper.investimento.Titulos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TituloService {
    @Autowired
    private TituloRepository tituloRepository;

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

        tituloRepository.deleteById(id);
    }

    public List<Titulo> getTitulo(String tipoTitulo) {
        if (tipoTitulo == null) {
            return tituloRepository.findAll();
        }
        return tituloRepository.findByTipoTitulo(tipoTitulo);
    }
}
