package guide.you.backend.dao;

import guide.you.backend.entity.Post;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import java.util.UUID;

public interface PostRepository extends ReactiveCassandraRepository<Post, UUID> {
}
