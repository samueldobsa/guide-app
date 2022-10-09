package guide.you.backend;

import guide.you.backend.dao.TripRepository;
import guide.you.backend.entity.Trip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
class DataInitializer implements CommandLineRunner {

    private final TripRepository trip;

    public DataInitializer(TripRepository trip) {
        this.trip = trip;
    }

    @Override
    public void run(String[] args) {
        log.info("start data initialization  ...");
        this.trip
                .deleteAll()
                .thenMany(
                        Flux
                                .just("Post one", "Post two")
                                .flatMap(
                                        title -> this.trip.save(Trip.builder().title(title).content("content of " + title).build())
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
