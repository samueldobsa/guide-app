package guide.you.backend.dao;

import guide.you.backend.entity.CompletedTrip;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import java.util.UUID;

public interface CompletedTripService extends ReactiveCassandraRepository<CompletedTrip, UUID> {
}
