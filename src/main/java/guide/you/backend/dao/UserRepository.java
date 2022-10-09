package guide.you.backend.dao;

import guide.you.backend.entity.User;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;


import java.util.UUID;

public interface UserRepository extends ReactiveCassandraRepository<User, UUID> {

}
