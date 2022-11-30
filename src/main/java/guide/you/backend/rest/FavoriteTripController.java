package guide.you.backend.rest;

import guide.you.backend.dao.FavoriteTripRepository;
import guide.you.backend.entity.FavoriteTrip;
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
@RequestMapping("/favorite_trip")
@RequiredArgsConstructor
public class FavoriteTripController {

    private final FavoriteTripRepository favoriteTripRepository;

    @GetMapping
    public Flux<FavoriteTrip> all(){
        return favoriteTripRepository.findAll();
    }

    @PostMapping
    public Mono<FavoriteTrip> create(@RequestBody FavoriteTrip favoriteTrip){
        return favoriteTripRepository.save(favoriteTrip);
    }

    @GetMapping("/{id}")
    public Mono<FavoriteTrip> get(@PathVariable UUID id){
        return favoriteTripRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable UUID id){
        return favoriteTripRepository.deleteById(id);
    }
}
