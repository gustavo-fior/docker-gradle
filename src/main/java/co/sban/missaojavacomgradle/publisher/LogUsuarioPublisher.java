package co.sban.missaojavacomgradle.publisher;

import co.sban.missaojavacomgradle.model.output.UsuarioDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Base64;


@Component
public class LogUsuarioPublisher extends BasePublisher<UsuarioDTO> {

    public LogUsuarioPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    public void publisher(UsuarioDTO usuarioDTO) throws JsonProcessingException {
        String message = Base64.getEncoder().encodeToString(usuarioDTO.toJsonString().getBytes());
        send(message, "CRUD_NEW_USER");
    }
}
