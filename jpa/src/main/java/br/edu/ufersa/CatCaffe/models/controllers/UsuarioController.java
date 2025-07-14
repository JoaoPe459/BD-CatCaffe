package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.UsuarioRecordDto;
import br.edu.ufersa.CatCaffe.models.entities.Usuario;
import br.edu.ufersa.CatCaffe.models.services.UsuarioServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioServices usuarioServices;

    public UsuarioController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    // POST - Criar usuário
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioRecordDto dto) {
        Usuario usuario = usuarioServices.saveUsuario(dto);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    // GET - Listar todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioServices.getAllUsuarios());
    }

    // PUT - Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRecordDto dto) {
        UsuarioRecordDto dtoAtualizado = new UsuarioRecordDto(
                id,
                dto.username(),
                dto.email(),
                dto.telefone(),
                dto.senha()
        );
        Usuario usuarioAtualizado = usuarioServices.editUsuario(dtoAtualizado);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    // DELETE - Deletar usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioServices.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
