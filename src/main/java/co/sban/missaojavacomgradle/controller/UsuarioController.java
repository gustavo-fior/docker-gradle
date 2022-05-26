package co.sban.missaojavacomgradle.controller;

import co.sban.missaojavacomgradle.model.Usuario;
import co.sban.missaojavacomgradle.model.input.UsuarioForm;
import co.sban.missaojavacomgradle.model.output.UsuarioDTO;
import co.sban.missaojavacomgradle.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id) {

        Optional<Usuario> usuario = usuarioService.getById(id);

        if(usuario.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new UsuarioDTO(usuario.get()));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAll() {

        List<Usuario> usuarios = usuarioService.getAll();

        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        usuarios.forEach(usuario -> {
            usuariosDTO.add(new UsuarioDTO(usuario));
        });

        return ResponseEntity.ok(usuariosDTO);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioForm usuarioForm, UriComponentsBuilder URIBuilder) {

        Usuario usuario = usuarioForm.toUsuario();
        usuarioService.save(usuario);

        URI uri = URIBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        usuarioService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioForm usuarioForm) {

        Optional<Usuario> usuario = usuarioService.getById(id);

        if(usuario.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        usuarioService.update(usuario.get(), usuarioForm);

        return ResponseEntity.ok(new UsuarioDTO(usuario.get()));
    }


}

