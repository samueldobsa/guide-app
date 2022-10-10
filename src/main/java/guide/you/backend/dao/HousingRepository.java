package guide.you.backend.dao;

import guide.you.backend.entity.Housing;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HousingRepository extends ReactiveCassandraRepository<Housing, UUID> {
}
