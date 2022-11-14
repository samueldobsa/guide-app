package guide.you.backend.dao;

import guide.you.backend.entity.Trip;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TripRepository extends ReactiveCassandraRepository<Trip, UUID> {


}
