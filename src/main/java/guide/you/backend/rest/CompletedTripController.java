package guide.you.backend.rest;

import guide.you.backend.dao.CompletedTripService;
import guide.you.backend.entity.CompletedTrip;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@Component
@Controller
@RequestMapping("completed_trip")
@RequiredArgsConstructor
public class CompletedTripController {

    private final CompletedTripService completedTripService;

    @GetMapping
    private Mono<ServerResponse> all(ServerRequest request){
        return ServerResponse.ok().body(this.completedTripService.findAll(), CompletedTrip.class);
    }

    @PostMapping
    private Mono<ServerResponse> create(ServerRequest request){
        return request.bodyToMono(CompletedTrip.class)
                .flatMap(completedTrip -> this.completedTripService.save(completedTrip))
                .flatMap(completedTrip -> ServerResponse.created(URI.create("/completed-trip/" + completedTrip.getId())).build());
    }

    @GetMapping("{id}")
    private Mono<ServerResponse> get(ServerRequest request){
        return this.completedTripService.findById(UUID.fromString(request.pathVariable("id")))
                .flatMap(completedTrip -> ServerResponse.ok().body(Mono.just(completedTrip), CompletedTrip.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @DeleteMapping("id")
    private Mono<ServerResponse> delete(ServerRequest request){
        return ServerResponse.noContent().build(this.completedTripService.deleteById(UUID.fromString(request.pathVariable("id"))));
    }
}
