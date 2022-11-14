package guide.you.backend;

import guide.you.backend.dao.TripRepository;
import guide.you.backend.entity.Trip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
class DataInitializer implements CommandLineRunner {

    private final TripRepository tripRepository;

    @Autowired
    public DataInitializer(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }


    @Override
    public void run(String[] args) {
        log.info("start data initialization  ...");
        this.tripRepository
                .deleteAll()
                .thenMany(
                        Flux
                                .just("Post one", "Post two")
                                .flatMap(
                                        title -> this.tripRepository.save(Trip.builder().title(title).content("content of " + title).build())
                                )
                )
                .log()
                .subscribe(
                        null,
                        null,
                        () -> log.info("done initialization...")
                );

    }

}
