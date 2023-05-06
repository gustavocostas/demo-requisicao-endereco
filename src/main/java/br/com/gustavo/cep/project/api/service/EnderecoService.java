package br.com.gustavo.cep.project.api.service;

import br.com.gustavo.cep.project.api.dto.EnderecoDto;
import br.com.gustavo.cep.project.api.exception.CepNotFoundException;
import br.com.gustavo.cep.project.api.exception.JsonFormatterException;
import br.com.gustavo.cep.project.api.model.Endereco;
import br.com.gustavo.cep.project.api.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static br.com.gustavo.cep.project.api.mapper.EnderecoMapper.enderecoDtoParaEndereco;

@Service
@AllArgsConstructor
public class EnderecoService {

    private ObjectMapper mapper;

    private EnderecoRepository enderecoRepository;

    public Endereco salvaEndereco(String cep) {
        try {
            Endereco endereco = formataEndereco(cep);
            enderecoRepository.save(endereco);
            return endereco;
        } catch (Exception e) {
            throw new CepNotFoundException("Endereço não encontrado");
        }
    }

    private Endereco formataEndereco(String cep) {
        try {
            EnderecoDto enderecoDto = mapper.readValue(buscaCep(cep), EnderecoDto.class);
            return enderecoDtoParaEndereco(enderecoDto);
        } catch (Exception e) {
            throw new JsonFormatterException("Erro em formatar String para Endereco");
        }
    }

    public String buscaCep(String cep) {
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://viacep.com.br/ws/%s/json/"
                .formatted(cep))).build();
        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new CepNotFoundException("CEP não encontrado");
        }
    }
}
