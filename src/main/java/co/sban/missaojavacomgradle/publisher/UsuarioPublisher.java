package co.sban.missaojavacomgradle.publisher;

import co.sban.missaojavacomgradle.model.output.UsuarioDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPublisher extends BasePublisher<UsuarioDTO> {

    public UsuarioPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    public void publisher(UsuarioDTO usuarioDTO) {
        send("Usuário com email: " + usuarioDTO.getEmail() + " foi criado com sucesso!", "CRUD_NEW_USER");
    }
}
