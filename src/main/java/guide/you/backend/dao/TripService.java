package guide.you.backend.dao;

import guide.you.backend.entity.Trip;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import java.util.UUID;

public interface TripService extends ReactiveCassandraRepository<Trip, UUID> {
}
