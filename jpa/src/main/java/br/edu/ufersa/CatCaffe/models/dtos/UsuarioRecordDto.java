package br.edu.ufersa.CatCaffe.models.dtos;

public record UsuarioRecordDto(Long id_usuario,
                               String username,
                               String email,
                               String telefone,
                               String senha) {
}
