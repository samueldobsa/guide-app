package guide.you.backend.rest;


import guide.you.backend.dao.TripRepository;
import guide.you.backend.entity.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final TripRepository tripRepository;

    @GetMapping
    public Flux<Trip> all() {
        return tripRepository.findAll();
    }

    @PostMapping
    public Mono<Trip> create(@RequestBody Trip trip) {
        return tripRepository.save(trip);
    }

    @GetMapping("/{id}")
    public Mono<Trip> get(@PathVariable UUID id) {
        return tripRepository.findById(id);
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<Trip>> update(@PathVariable UUID id, @RequestBody Trip trip) {
        return tripRepository.findById(id)
                .flatMap(existing -> {
                    if (trip.getDestination() != null){
                        existing.setDestination(trip.getDestination());
                    }
                    if (trip.getTitle() != null){
                        existing.setTitle(trip.getTitle());
                    }
                    if (trip.getContent() != null){
                        existing.setContent(trip.getContent());
                    }
                    if (trip.getCategory() != null){
                        existing.setCategory(trip.getCategory());
                    }
                    if (trip.getImage() != null){
                        existing.setImage(trip.getImage());
                    }
                    if (trip.getDuration() != 0.0){
                        existing.setDuration(trip.getDuration());
                    }
                    if (trip.getPrice() != null){
                        existing.setPrice(trip.getPrice());
                    }
                    return tripRepository.save(existing);
                })
                .map(updatedTrip -> new ResponseEntity<>(updatedTrip, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable UUID id) {
        return tripRepository.deleteById(id);
    }

}
