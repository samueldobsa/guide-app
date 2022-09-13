package guide.you.backend.dao;

import guide.you.backend.entity.UserReview;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import java.util.UUID;

public interface UserReviewService extends ReactiveCassandraRepository<UserReview, UUID> {
}
