package br.insper.investimento.Titulos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TituloController {
    @Autowired
    private TituloService tituloService;

    @PostMapping("/titulo")
    public Titulo salvarTitulo(@RequestBody Titulo titulo) {

        return tituloService.cadastrarTitulo(titulo);
    }


    @DeleteMapping("/titulo")
    public void deletarTitulo(@RequestParam(required = true) Integer id) {
        tituloService.deletarTitulo(id);
    }

    @GetMapping("/titulo")
    public List<Titulo> getTitulo(@RequestParam(required = false) String tipoTitulo) {
        return tituloService.getTitulo(tipoTitulo);
    }
}
