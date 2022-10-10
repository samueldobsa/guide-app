package guide.you.backend.dao;

import guide.you.backend.entity.UserReview;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserReviewRepository extends ReactiveCassandraRepository<UserReview, UUID> {
}
