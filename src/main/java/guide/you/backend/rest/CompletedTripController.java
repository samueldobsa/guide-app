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
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/completed_trip")
@RequiredArgsConstructor
public class CompletedTripController {

    private final CompletedTripRepository completedTripRepository;

    @GetMapping
    private Mono<ServerResponse> all(ServerRequest request){
        return ServerResponse.ok().body(this.completedTripRepository.findAll(), CompletedTrip.class);
    }

    @PostMapping
    private Mono<ServerResponse> create(ServerRequest request){
        return request.bodyToMono(CompletedTrip.class)
                .flatMap(completedTrip -> this.completedTripRepository.save(completedTrip))
                .flatMap(completedTrip -> ServerResponse.created(URI.create("/completed-trip/" + completedTrip.getId())).build());
    }

    @GetMapping("{id}")
    private Mono<ServerResponse> get(ServerRequest request){
        return this.completedTripRepository.findById(UUID.fromString(request.pathVariable("id")))
                .flatMap(completedTrip -> ServerResponse.ok().body(Mono.just(completedTrip), CompletedTrip.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @DeleteMapping("id")
    private Mono<ServerResponse> delete(ServerRequest request){
        return ServerResponse.noContent().build(this.completedTripRepository.deleteById(UUID.fromString(request.pathVariable("id"))));
    }
}
