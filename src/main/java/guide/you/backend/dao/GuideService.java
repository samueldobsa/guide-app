package guide.you.backend.dao;

import guide.you.backend.entity.Guide;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import java.util.UUID;

public interface GuideService extends ReactiveCassandraRepository <Guide, UUID> {

}
