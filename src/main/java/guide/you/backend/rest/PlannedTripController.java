package guide.you.backend.rest;

import guide.you.backend.dao.PlannedTripRepository;
import guide.you.backend.entity.PlannedTrip;
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
@RequestMapping("/planned_trip")
@RequiredArgsConstructor
public class PlannedTripController {

    private final PlannedTripRepository plannedTripRepository;

    @GetMapping
    private Flux<PlannedTrip> all(){
        return plannedTripRepository.findAll();
    }

    @PostMapping
    private Mono<PlannedTrip> create(@RequestBody PlannedTrip plannedTrip){
        return plannedTripRepository.save(plannedTrip);
    }

    @GetMapping("/{id}")
    private Mono<PlannedTrip> get(@PathVariable UUID id) {
        return plannedTripRepository.findById(id);
    }

    @PatchMapping("/{id}")
    private Mono<ResponseEntity<PlannedTrip>> update(@PathVariable UUID id, @RequestBody PlannedTrip plannedTrip){
        return plannedTripRepository.findById(id)
                .flatMap(existing -> {
                    if (plannedTrip.getPlannedDate() != null){
                        existing.setPlannedDate(plannedTrip.getPlannedDate());
                    }
                    if (plannedTrip.getDestination() != null){
                        existing.setDestination(plannedTrip.getDestination());
                    }
                    return plannedTripRepository.save(existing);
                })
                .map(updatePlannedTrip -> new ResponseEntity<>(updatePlannedTrip, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    private Mono<Void> delete(@PathVariable UUID id){
        return plannedTripRepository.deleteById(id);
    }
}
