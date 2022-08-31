package guide.you.backend.rest;

import guide.you.backend.dao.FavoriteTripService;
import guide.you.backend.entity.FavoriteTrip;
import guide.you.backend.entity.PlannedTrip;
import guide.you.backend.entity.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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
@RequestMapping("favorite_trip")
@RequiredArgsConstructor
public class FavoriteTripController {

    private final FavoriteTripService favoriteTripService;

    @GetMapping
    private Mono<ServerResponse> all(ServerRequest request){
        return ServerResponse.ok().body(this.favoriteTripService.findAll(), FavoriteTrip.class);
    }

    @PostMapping
    private Mono<ServerResponse> create(ServerRequest request){
        return request.bodyToMono(FavoriteTrip.class)
                .flatMap(favoriteTrip -> this.favoriteTripService.save(favoriteTrip))
                .flatMap(favoriteTrip -> ServerResponse.created(URI.create("/favorite-trip/" + favoriteTrip.getId())).build());
    }

    @GetMapping("{id}")
    private Mono<ServerResponse> get(ServerRequest request){
        return this.favoriteTripService.findById(UUID.fromString(request.pathVariable("id")))
                .flatMap(favoriteTrip -> ServerResponse.ok().body(Mono.just(favoriteTrip), FavoriteTrip.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @DeleteMapping("{id}")
    private Mono<ServerResponse> delete(ServerRequest request){
        return ServerResponse.noContent().build(this.favoriteTripService.deleteById(UUID.fromString(request.pathVariable("id"))));
    }
}
