package co.sban.missaojavacomgradle.repository.nosql;

import co.sban.missaojavacomgradle.model.LogData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDataRepository extends ReactiveMongoRepository<LogData, String> {
}
