package guide.you.backend.rest;


import guide.you.backend.dao.TripRepository;
import guide.you.backend.entity.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final TripRepository tripRepository;


    @GetMapping
    public Mono<ServerResponse> all(ServerRequest req) {
        return ServerResponse.ok().body(this.tripRepository.findAll(), Trip.class);
    }

    @PostMapping
    public Mono<ServerResponse> create(ServerRequest req) {
        return req.bodyToMono(Trip.class)
                .flatMap(trip -> this.tripRepository.save(trip))
                .flatMap(t -> ServerResponse.created(URI.create("/trip/" + t.getId())).build());
    }

    @GetMapping("{id}")
    public Mono<ServerResponse> get(ServerRequest req) {
        return this.tripRepository.findById(UUID.fromString(req.pathVariable("id")))
                .flatMap(trip -> ServerResponse.ok().body(Mono.just(trip), Trip.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @PatchMapping("{id}")
    public Mono<ServerResponse> update(ServerRequest req) {

        return Mono
                .zip(
                        (data) -> {
                            Trip trip = (Trip) data[0];
                            Trip trip2 = (Trip) data[1];
                            trip.setTitle(trip2.getTitle());
                            trip.setContent(trip2.getContent());
                            trip.setCategory(trip2.getCategory());
                            trip.setImage(trip2.getImage());
                            trip.setDuration(trip2.getDuration());
                            trip.setPrice(trip2.getPrice());
                            trip.setDestination(trip2.getDestination());
                            return trip;
                        },
                        this.tripRepository.findById(UUID.fromString(req.pathVariable("id"))),
                        req.bodyToMono(Trip.class)
                )
                .cast(Trip.class)
                .flatMap(trip -> this.tripRepository.save(trip))
                .flatMap(trip -> ServerResponse.noContent().build());

    }

    @DeleteMapping("{id}")
    public Mono<ServerResponse> delete(ServerRequest req) {
        return ServerResponse.noContent().build(this.tripRepository.deleteById(UUID.fromString(req.pathVariable("id"))));
    }

}
