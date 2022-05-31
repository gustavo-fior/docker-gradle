package co.sban.missaojavacomgradle.publisher;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

public abstract class BasePublisher<T> {

    private final KafkaTemplate<String, String> kafkaTemplate;

    protected BasePublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // ??????
    public abstract void publisher(T clazz);

    protected void send(final String message, final String topic) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, UUID.randomUUID().toString(), message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Deu ruim no envio da mensagem: " + message + " por causa de " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Mensagem: " + message + " foi enviada com offset " + result.getRecordMetadata().offset());
            }
        });

    }
}
