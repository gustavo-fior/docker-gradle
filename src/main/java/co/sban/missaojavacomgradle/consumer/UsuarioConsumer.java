package co.sban.missaojavacomgradle.consumer;

import co.sban.missaojavacomgradle.service.UsuarioService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConsumer {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioConsumer(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @KafkaListener(topics = "CRUD_NEW_USER", groupId = "group-id-crud-usuario", containerFactory = "kafkaListenerContainerFactory",
            autoStartup = "${crud.kafka.listen.auto.start}")
    public void usuarioConsumer(final ConsumerRecord<String, String> record) {
        usuarioService.saveUsuarioLogThroughKafka(record.value());
    }
}
