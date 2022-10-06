package guide.you.backend;

import guide.you.backend.dao.TripService;
import guide.you.backend.entity.Trip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
class DataInitializer implements CommandLineRunner {

    private final TripService tripService;

    public DataInitializer(TripService tripService) {
        this.tripService = tripService;
    }


    @Override
    public void run(String[] args) {
        log.info("start data initialization  ...");
        this.tripService
                .deleteAll()
                .thenMany(
                        Flux
                                .just("Post one", "Post two")
                                .flatMap(
                                        title -> this.tripService.save(Trip.builder().title(title).content("content of " + title).build())
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
