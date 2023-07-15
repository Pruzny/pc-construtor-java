package com.pccontrutor.controller;

import com.pccontrutor.model.Peca;
import com.pccontrutor.service.PecaService;
import com.pccontrutor.util.PecasPaginadas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("pecas")   // http://localhost:8080/pecas
public class PecaController {
    @Autowired
    private PecaService pecaService;

    @GetMapping       // http://localhost:8080/pecas
    public List<Peca> getTodas() {
        return pecaService.getTodas();
    }

    @PostMapping
    public Peca cadastrar(@RequestBody Peca peca) {
        return pecaService.cadastrar(peca);
    }

    @GetMapping("{idPeca}")       // http://localhost:8080/pecas/1
    public Peca get(@PathVariable("idPeca") Long id) {
        return pecaService.get(id);
    }

    @GetMapping("tipo")     // http://localhost:8080/pecas/tipo?tipo=gabinete
    public List<Peca> getPorTipo(@RequestParam("tipo") String tipo) {
        if (tipo == null) {
            return pecaService.getTodas();
        }
        return pecaService.getPorTipo(tipo);
    }

    @PutMapping                  // http://localhost:8080/pecas
    public Peca atualizar(@RequestBody Peca peca) {
        return pecaService.atualiza(peca);
    }

    @DeleteMapping("{idPeca}")
    public void remover(@PathVariable("idPeca") Long id) {
        pecaService.remover(id);
    }

    @PostMapping("remover")
    public Peca removerPorPeca(@RequestBody Peca peca) {
        return pecaService.remover(peca);
    }

    @GetMapping("paginacao")   // http://localhost:8080/pecas/paginacao?pagina=0&tamanho=5
    public PecasPaginadas getPaginadas(
            @RequestParam(name="pagina", defaultValue = "0") int pagina,
            @RequestParam(name="tamanho", defaultValue = "5") int tamanho,
            @RequestParam(name="nome", defaultValue = "") String nome
    ) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Peca> paginaDePeca = pecaService.getPaginadas(nome, pageable);
        return new PecasPaginadas(
            paginaDePeca.getTotalElements(),
            paginaDePeca.getTotalPages(),
            paginaDePeca.getNumber(),
            paginaDePeca.getContent()
        );
    }
}
