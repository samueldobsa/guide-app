package guide.you.backend.dao;

import guide.you.backend.entity.FavoriteTrip;
import guide.you.backend.entity.PlannedTrip;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import java.util.UUID;

public interface FavoriteTripService extends ReactiveCassandraRepository<FavoriteTrip, UUID> {
}
