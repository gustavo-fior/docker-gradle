package co.sban.missaojavacomgradle.controller;

import co.sban.missaojavacomgradle.model.LogData;
import co.sban.missaojavacomgradle.service.LogDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/getlogs")
@Tag(name = "Log Data Controller", description = "Endpoints para manutenção dos logs")
public class LogDataController {

    private final LogDataService logDataService;

    @Autowired
    public LogDataController(LogDataService logDataService) {
        this.logDataService = logDataService;
    }

    @GetMapping
    @Operation(summary = "Get logs", description = "Retorna todos os logs")
    public ResponseEntity<Flux<LogData>> getLogs() {

        Flux<LogData> all = logDataService.findAll();

        return ResponseEntity.ok(all);
    }

}
