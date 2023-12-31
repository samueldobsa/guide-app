package guide.you.backend;


import guide.you.backend.dao.TripRepository;
import guide.you.backend.entity.Trip;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataCassandraTest
@Testcontainers
@Slf4j
public class PostRepositoryWithTestContainersTest {

    @Container
    static CassandraContainer<?> cassandraContainer = new CassandraContainer<>("cassandra")
            .withInitScript("init.cql")
            .withStartupTimeout(Duration.ofMinutes(5));

    @DynamicPropertySource
    static void bindCassandraProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.cassandra.keyspace-name", () -> "demo");
        registry.add("spring.data.cassandra.contact-points", () -> "localhost:" + cassandraContainer.getMappedPort(9042));
        registry.add("spring.data.cassandra.local-datacenter", () -> "datacenter1");
        registry.add("spring.data.cassandra.schema-action", () -> "RECREATE");
    }


    @Autowired
    private TripRepository tripService;

    @BeforeEach
    public void setup() {
        this.tripService.deleteAll()
                .then()
                .thenMany(
                        tripService.saveAll(
                                List.of(Trip.builder().title("test").content("content of test title").build(),
                                        Trip.builder().title("test2").content("content of test2 title").build()
                                )
                        )
                )
                .log()
                .blockLast(Duration.ofSeconds(5));
    }

    @Test
    void testAllPosts() {

        tripService.findAll().sort(Comparator.comparing(Trip::getTitle))
                .as(StepVerifier::create)
                .consumeNextWith(p -> assertEquals("test", p.getTitle()))
                .consumeNextWith(p -> assertEquals("test2", p.getTitle()))
                .verifyComplete();
    }


}