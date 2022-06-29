package co.sban.missaojavacomgradle.controller;

import co.sban.missaojavacomgradle.model.Usuario;
import co.sban.missaojavacomgradle.model.input.UsuarioForm;
import co.sban.missaojavacomgradle.model.output.UsuarioDTO;
import co.sban.missaojavacomgradle.publisher.LogUsuarioPublisher;
import co.sban.missaojavacomgradle.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuário Controller", description = "Endpoints para gestão dos usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final LogUsuarioPublisher logUsuarioPublisher;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, LogUsuarioPublisher logUsuarioPublisher) {
        this.usuarioService = usuarioService;
        this.logUsuarioPublisher = logUsuarioPublisher;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by Id", description = "Retorna o usuário com Id passado")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id) {

        Optional<Usuario> usuario = usuarioService.getById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new UsuarioDTO(usuario.get()));
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Retorna todos os usuários do sistema")
    public ResponseEntity<List<UsuarioDTO>> getAll() {

        List<Usuario> usuarios = usuarioService.getAll();

        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        usuariosDTO.addAll(usuarios.stream().map(usuario -> {
            return new UsuarioDTO(usuario);
        }).collect(Collectors.toList()));

        return ResponseEntity.ok(usuariosDTO);
    }

    @PostMapping
    @Operation(summary = "Create user", description = "Criação de um novo usuário")
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioForm usuarioForm, UriComponentsBuilder URIBuilder) {

        Usuario usuario = usuarioForm.toUsuario();
        UsuarioDTO save = usuarioService.save(usuario);

        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Remoção de um usuário")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        usuarioService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Alteração nos atributos de um usuário")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioForm usuarioForm) {

        Optional<Usuario> usuario = usuarioService.getById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        UsuarioDTO update = usuarioService.update(usuario.get(), usuarioForm);

        return ResponseEntity.ok(update);
    }

    @GetMapping("/test")
    @Operation(summary = "Test", description = "Endpoint para testar a API")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("It's working!!!");
    }


}

