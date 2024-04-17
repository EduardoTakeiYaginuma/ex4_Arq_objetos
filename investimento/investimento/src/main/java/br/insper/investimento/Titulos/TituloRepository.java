package br.insper.investimento.Titulos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TituloRepository extends JpaRepository<Titulo, Integer> {
    public List<Titulo> findByTipoTitulo(String tipoTitulo);
    public Titulo findByNome(String nome);

    public Titulo findByIdentificador(String identificador);
    public Titulo findTituloById(Integer id);
}
