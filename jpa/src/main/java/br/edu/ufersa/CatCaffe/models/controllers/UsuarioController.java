package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.request.UsuarioRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.UsuarioResponseDto;
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
    public ResponseEntity<UsuarioResponseDto> criarUsuario(@RequestBody UsuarioRequestDto dto) {
        UsuarioResponseDto usuario = usuarioServices.saveUsuario(dto);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    // GET - Listar todos os usuários
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios() {
        return ResponseEntity.ok(usuarioServices.getAllUsuarios());
    }

    // PUT - Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDto dto) {
        UsuarioResponseDto usuarioAtualizado = usuarioServices.editUsuario(id, dto);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    // DELETE - Deletar usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioServices.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
