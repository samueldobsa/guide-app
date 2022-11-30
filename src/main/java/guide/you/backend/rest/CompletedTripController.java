package guide.you.backend.rest;

import guide.you.backend.dao.CompletedTripRepository;
import guide.you.backend.entity.CompletedTrip;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
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
@RequestMapping("/completed_trip")
@RequiredArgsConstructor
public class CompletedTripController {

    private final CompletedTripRepository completedTripRepository;

    @GetMapping
    private Flux<CompletedTrip> all(){
        return completedTripRepository.findAll();
    }

    @PostMapping
    private Mono<CompletedTrip> create(@RequestBody CompletedTrip completedTrip){
        return completedTripRepository.save(completedTrip);
    }

    @GetMapping("/{id}")
    private Mono<CompletedTrip> get(@PathVariable UUID id){
        return completedTripRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    private Mono<Void> delete(@PathVariable UUID id){
        return completedTripRepository.deleteById(id);
    }
}
