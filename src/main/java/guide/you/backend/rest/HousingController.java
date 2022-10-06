package guide.you.backend.rest;

import guide.you.backend.dao.HousingService;
import guide.you.backend.entity.Housing;
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
@RequestMapping("housing")
@RequiredArgsConstructor
public class HousingController {

    private final HousingService housingService;

    @GetMapping
    private Mono<ServerResponse> all(ServerRequest request){
        return ServerResponse.ok().body(this.housingService.findAll(), Housing.class);
    }

    @PostMapping
    private Mono<ServerResponse> create(ServerRequest request){
        return request.bodyToMono(Housing.class)
                .flatMap(housing -> this.housingService.save(housing))
                .flatMap(housing -> ServerResponse.created(URI.create("/housing/" + housing.getId())).build());
    }

    @GetMapping("id")
    private Mono<ServerResponse> get(ServerRequest request){
        return this.housingService.findById(UUID.fromString(request.pathVariable("id")))
                .flatMap(housing -> ServerResponse.ok().body(Mono.just(housing), Housing.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @PatchMapping("id")
    private Mono<ServerResponse> update(ServerRequest request){
        return Mono
                .zip(
                        (data) -> {
                            Housing housing = (Housing) data[0];
                            Housing housing1 = (Housing) data[1];
                            housing.setCountBeds(housing1.getCountBeds());
                            housing.setInGuidesHome(housing1.isInGuidesHome());
                            return housing;
                        },
                        this.housingService.findById(UUID.fromString(request.pathVariable("id"))),
                        request.bodyToMono(Housing.class)
                )
                .cast(Housing.class)
                .flatMap(housing -> this.housingService.save(housing))
                .flatMap(housing -> ServerResponse.noContent().build());
    }

    @DeleteMapping("id")
    private Mono<ServerResponse> delete(ServerRequest request){
        return ServerResponse.notFound().build(this.housingService.deleteById(UUID.fromString(request.pathVariable("id"))));
    }


}
