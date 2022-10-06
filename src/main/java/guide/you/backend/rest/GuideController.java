package guide.you.backend.rest;

import guide.you.backend.dao.GuideService;
import guide.you.backend.entity.Guide;
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
@RequiredArgsConstructor
@RequestMapping("guide")
public class GuideController {

    private final GuideService guideService;

    @GetMapping("{id}")
    public Mono<ServerResponse> get(ServerRequest request){
        return this.guideService.findById(UUID.fromString(request.pathVariable("id")))
                .flatMap(guide -> ServerResponse.ok().body(Mono.just(guide), Guide.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @PostMapping
    public Mono<ServerResponse> create(ServerRequest request){
        return request.bodyToMono(Guide.class)
                .flatMap(guide -> this.guideService.save(guide))
                .flatMap(guide -> ServerResponse.created(URI.create("/guide/" + guide.getId())).build());
    }

    @GetMapping
    public Mono<ServerResponse> all(ServerRequest request){
        return ServerResponse.ok().body(this.guideService.findAll(), Guide.class);
    }

    @PatchMapping("{id}")
    public Mono<ServerResponse> update(ServerRequest request){
        return Mono
                .zip(
                        (data) -> {
                            Guide g = (Guide) data[0];
                            Guide g2 = (Guide) data[1];
                            g.setName(g2.getName());
                            g.setSurname(g2.getSurname());
                            g.setUserName(g2.getUserName());
                            g.setEmail(g2.getEmail());
                            g.setPhone_number(g2.getPhone_number());
                            g.setDescription(g2.getDescription());
                            g.setLanguage(g2.getLanguage());
                            g.setDestination(g2.getDestination());
                            g.setActive(g2.isActive());
                            return g;
                        },
                        this.guideService.findById(UUID.fromString(request.pathVariable("id"))),
                        request.bodyToMono(Guide.class)
                )
                .cast(Guide.class)
                .flatMap(guide -> this.guideService.save(guide))
                .flatMap(guide -> ServerResponse.noContent().build());
    }

    @DeleteMapping("{id}")
    public Mono<ServerResponse> delete(ServerRequest request){
        return ServerResponse.noContent().build(this.guideService.deleteById(UUID.fromString(request.pathVariable("id"))));
    }


}
