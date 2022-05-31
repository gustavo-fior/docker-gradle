package co.sban.missaojavacomgradle.service;

import co.sban.missaojavacomgradle.model.LogData;
import co.sban.missaojavacomgradle.model.Usuario;
import co.sban.missaojavacomgradle.model.input.UsuarioForm;
import co.sban.missaojavacomgradle.model.output.UsuarioDTO;
import co.sban.missaojavacomgradle.publisher.UsuarioPublisher;
import co.sban.missaojavacomgradle.repository.jpa.UsuarioRepository;
import co.sban.missaojavacomgradle.repository.nosql.LogDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final LogDataRepository logDataRepository;

    private final UsuarioPublisher usuarioPublisher;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, LogDataRepository logDataRepository, UsuarioPublisher usuarioPublisher) {
        this.usuarioRepository = usuarioRepository;
        this.logDataRepository = logDataRepository;
        this.usuarioPublisher = usuarioPublisher;
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

        Usuario save = usuarioRepository.save(usuario);

        UsuarioDTO usuarioDTO = new UsuarioDTO(save);

        usuarioPublisher.publisher(usuarioDTO);

        return usuarioDTO;
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

    public void saveUsuarioLogThroughKafka(String mensagem) {

        LogData logData = new LogData();
        logData.setDate(LocalDateTime.now());
        logData.setData(mensagem);

        logDataRepository.save(logData).subscribe();

    }


}
