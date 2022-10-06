package guide.you.backend.rest;

import guide.you.backend.dao.PlannedTripRepository;
import guide.you.backend.entity.PlannedTrip;
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

@Component
@Controller
@RequestMapping("planned_trip")
@RequiredArgsConstructor
public class PlannedTripController {

    private final PlannedTripRepository plannedTripRepository;

    @GetMapping
    private Mono<ServerResponse> all(ServerRequest request){
        return ServerResponse.ok().body(this.plannedTripRepository.findAll(), PlannedTrip.class);
    }

    @PostMapping
    private Mono<ServerResponse> create(ServerRequest request){
        return request.bodyToMono(PlannedTrip.class)
                .flatMap(plannedTrip -> this.plannedTripRepository.save(plannedTrip))
                .flatMap(plannedTrip -> ServerResponse.created(URI.create("/planned-trip/" + plannedTrip.getId())).build());
    }

    @GetMapping("{id}")
    private Mono<ServerResponse> get(ServerRequest request){
        return this.plannedTripRepository.findById(UUID.fromString(request.pathVariable("id")))
                .flatMap(plannedTrip -> ServerResponse.ok().body(Mono.just(plannedTrip), PlannedTrip.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @PatchMapping("{id}")
    private Mono<ServerResponse> update(ServerRequest request){
        return Mono
                .zip(
                        (data) -> {
                            PlannedTrip plannedTrip = (PlannedTrip) data[0];
                            PlannedTrip plannedTrip1 = (PlannedTrip) data[1];
                            plannedTrip.setPlannedDate(plannedTrip1.getPlannedDate());
                            plannedTrip.setDestination(plannedTrip1.getDestination());
                            return plannedTrip;
                        },
                        this.plannedTripRepository.findById(UUID.fromString(request.pathVariable("id"))),
                        request.bodyToMono(PlannedTrip.class)
                )
                .cast(PlannedTrip.class)
                .flatMap(plannedTrip -> this.plannedTripRepository.save(plannedTrip))
                .flatMap(plannedTrip -> ServerResponse.noContent().build());
    }

    @DeleteMapping("{id}")
    private Mono<ServerResponse> delete(ServerRequest request){
        return ServerResponse.noContent().build(this.plannedTripRepository.deleteById(UUID.fromString(request.pathVariable("id"))));
    }

}
