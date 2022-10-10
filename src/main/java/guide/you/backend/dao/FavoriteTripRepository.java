package guide.you.backend.dao;

import guide.you.backend.entity.FavoriteTrip;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FavoriteTripRepository extends ReactiveCassandraRepository<FavoriteTrip, UUID> {
}
