package co.sban.missaojavacomgradle.router;

import co.sban.missaojavacomgradle.handler.LogDataHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class LogDataRouter {

    @Bean
    public RouterFunction<ServerResponse> getLogs(LogDataHandler logHandler) {
        return RouterFunctions
                .route(GET("/logs")
                        .and(accept(MediaType.APPLICATION_JSON)), logHandler::getAllLogs);
    }

}
