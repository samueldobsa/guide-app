package guide.you.backend.dao;

import guide.you.backend.entity.PlannedTrip;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlannedTripRepository extends ReactiveCassandraRepository<PlannedTrip, UUID> {
}
