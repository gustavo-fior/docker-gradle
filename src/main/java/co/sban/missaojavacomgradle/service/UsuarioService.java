package co.sban.missaojavacomgradle.service;

import co.sban.missaojavacomgradle.model.LogData;
import co.sban.missaojavacomgradle.model.Usuario;
import co.sban.missaojavacomgradle.model.input.UsuarioForm;
import co.sban.missaojavacomgradle.model.output.UsuarioDTO;
import co.sban.missaojavacomgradle.repository.jpa.UsuarioRepository;
import co.sban.missaojavacomgradle.repository.nosql.LogDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final LogDataRepository logDataRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, LogDataRepository logDataRepository) {
        this.usuarioRepository = usuarioRepository;
        this.logDataRepository = logDataRepository;
    }

    public Optional<Usuario> getById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        return usuario;
    }

    public List<Usuario> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios;
    }

    public UsuarioDTO save(Usuario usuario) {

        LogData logData = new LogData();
        logData.setData(LocalDateTime.now());

        Usuario save = usuarioRepository.save(usuario);

        CompletableFuture.runAsync(() -> {
            logData.setData(usuario);
            logDataRepository.save(logData).subscribe();
        });

        return new UsuarioDTO(save);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO update(Usuario usuario, UsuarioForm usuarioForm) {

        LogData logData = new LogData();
        logData.setData(LocalDateTime.now());

        usuario.setCpf(usuarioForm.getCpf());
        usuario.setNome(usuarioForm.getNome());
        usuario.setSobreNome(usuarioForm.getSobreNome());
        usuario.setEmail(usuarioForm.getEmail());
        usuario.setDataNascimento(LocalDate.parse(usuarioForm.getDataNascimento()));

        Usuario save = usuarioRepository.save(usuario);

        CompletableFuture.runAsync(() -> {
            logData.setData(usuario);
            logDataRepository.save(logData).subscribe();
        });

        return new UsuarioDTO(save);

    }
}
