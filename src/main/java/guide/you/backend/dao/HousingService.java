package guide.you.backend.dao;

import guide.you.backend.entity.Housing;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import java.util.UUID;

public interface HousingService extends ReactiveCassandraRepository<Housing, UUID> {
}
