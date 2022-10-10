package guide.you.backend.dao;

import guide.you.backend.entity.Guide;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GuideRepository extends ReactiveCassandraRepository <Guide, UUID> {

}
