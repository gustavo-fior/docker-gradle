package co.sban.missaojavacomgradle.service;

import co.sban.missaojavacomgradle.model.LogData;
import co.sban.missaojavacomgradle.repository.nosql.LogDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class LogDataService {

    private final LogDataRepository logDataRepository;

    @Autowired
    public LogDataService(LogDataRepository logDataRepository) {
        this.logDataRepository = logDataRepository;
    }

    public Flux<LogData> findAll() {
        return this.logDataRepository.findAll();
    }

}
