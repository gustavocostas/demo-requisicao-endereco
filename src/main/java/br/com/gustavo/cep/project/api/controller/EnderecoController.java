package br.com.gustavo.cep.project.api.controller;

import br.com.gustavo.cep.project.api.model.Endereco;
import br.com.gustavo.cep.project.api.service.EnderecoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("endereco")
@AllArgsConstructor
public class EnderecoController {

    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Endereco> salvaEndereco(@RequestBody String numeroCep) {
        return ResponseEntity.ok(enderecoService.salvaEndereco(numeroCep));
    }

    @GetMapping("{cep}")
    public ResponseEntity<String> buscaEndereco(@PathVariable String cep) {
        return ResponseEntity.ok(enderecoService.buscaCep(cep));
    }
}
