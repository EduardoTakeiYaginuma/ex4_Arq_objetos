package br.insper.investimento.Investidor;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvestidorController {
    @Autowired
    private InvestidorService investidorService;

    @PostMapping("/investidor")
    public Investidor salvarInvestidor(@RequestBody Investidor investidor) {
        return investidorService.cadastrarInvestidor(investidor);
    }

    @DeleteMapping("/investidor")
    public void deletarInvestidor(@RequestParam(required = true) String cpf) {
        investidorService.deletarInvestidor(cpf);
    }


    @GetMapping("/investidor")
    public List<Investidor> getInvestidores(@RequestParam(required = false) String perfilInvestidor) {

        return investidorService.listarInvestidores(perfilInvestidor);
    }


}
