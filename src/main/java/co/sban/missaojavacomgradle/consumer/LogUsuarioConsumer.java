package co.sban.missaojavacomgradle.consumer;

import co.sban.missaojavacomgradle.model.LogData;
import co.sban.missaojavacomgradle.model.Usuario;
import co.sban.missaojavacomgradle.repository.nosql.LogDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Component
public class LogUsuarioConsumer {

    private final LogDataRepository logDataRepository;

    @Autowired
    public LogUsuarioConsumer(LogDataRepository logDataRepository) {
        this.logDataRepository = logDataRepository;
    }

    @KafkaListener(topics = "CRUD_NEW_USER", groupId = "group-id-crud-usuario", containerFactory = "kafkaListenerContainerFactory",
            autoStartup = "${crud.kafka.listen.auto.start}")
    public void usuarioConsumer(final ConsumerRecord<String, String> record) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Base64.Decoder decoder = Base64.getDecoder();

        byte[] bytes = decoder.decode(record.value());
        Usuario usuario = mapper.readValue(bytes, Usuario.class);

        logDataRepository.save(new LogData(UUID.randomUUID().toString(), LocalDateTime.now(), usuario)).subscribe();

    }
}
