package br.com.gustavo.cep.project.api.mapper;

import br.com.gustavo.cep.project.api.dto.EnderecoDto;
import br.com.gustavo.cep.project.api.model.Endereco;

public class EnderecoMapper {
    public static Endereco enderecoDtoParaEndereco(EnderecoDto enderecoDto) {
        return Endereco.builder()
                .id(enderecoDto.getId())
                .cep(enderecoDto.getCep())
                .rua(enderecoDto.getRua())
                .bairro(enderecoDto.getBairro())
                .cidade(enderecoDto.getCidade())
                .estado(enderecoDto.getEstado())
                .build();
    }
}
