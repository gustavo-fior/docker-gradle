package co.sban.missaojavacomgradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableJpaRepositories("co.sban.missaojavacomgradle.repository.jpa")
@EnableReactiveMongoRepositories("co.sban.missaojavacomgradle.repository.nosql")
@EnableWebFlux
public class MissaoJavaComGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MissaoJavaComGradleApplication.class, args);
	}

}
