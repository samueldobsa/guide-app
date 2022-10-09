package guide.you.backend.dao;

import guide.you.backend.entity.FavoriteTrip;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import java.util.UUID;

public interface FavoriteTripRepository extends ReactiveCassandraRepository<FavoriteTrip, UUID> {
}
