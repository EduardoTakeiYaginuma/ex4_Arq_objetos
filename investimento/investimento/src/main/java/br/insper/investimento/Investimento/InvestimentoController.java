package br.insper.investimento.Investimento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvestimentoController {
    @Autowired
    private InvestimentoService investimentoService;

    @PostMapping("/investimento")
    public Investimento salvarInvestimento(@RequestBody Investimento investimento) {
        return investimentoService.cadastrarInvestimento(investimento);
    }

    @GetMapping("/investimento")
    public List<Investimento> getInvestimento(@RequestParam (required = true) String cpf) {

        return investimentoService.getInvestimento(cpf);
    }
}
