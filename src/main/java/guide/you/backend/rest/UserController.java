package guide.you.backend.rest;

import guide.you.backend.dao.UserRepository;
import guide.you.backend.entity.User;
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

@RequiredArgsConstructor
@Controller
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("{id}")
    public Mono<ServerResponse>get(ServerRequest request){
        return this.userRepository.findById(UUID.fromString(request.pathVariable("id")))
                .flatMap(user -> ServerResponse.ok().body(Mono.just(user), User.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @PostMapping
    public Mono<ServerResponse>create(ServerRequest request){
        return request.bodyToMono(User.class)
                .flatMap(user -> this.userRepository.save(user))
                .flatMap(user -> ServerResponse.created(URI.create("/user/" + user.getId())).build());
    }

    @GetMapping
    public Mono<ServerResponse>all(ServerRequest request){
        return ServerResponse.ok().body(this.userRepository.findAll(), User.class);
    }

    @PatchMapping("{id}")
    public Mono<ServerResponse> update(ServerRequest request){
        return Mono
                .zip(
                        (data) -> {
                            User u = (User) data[0];
                            User u2 = (User) data[1];
                            u.setName(u2.getName());
                            u.setSurname(u2.getSurname());
                            u.setUsername(u2.getUsername());
                            u.setEmail(u2.getEmail());
                            u.setPhone_number(u2.getPhone_number());
                            u.setMoney(u2.getMoney());
                            return u;
                        },
                        this.userRepository.findById(UUID.fromString(request.pathVariable("id"))),
                        request.bodyToMono(User.class)
                )
                .cast(User.class)
                .flatMap(user -> this.userRepository.save(user))
                .flatMap(user -> ServerResponse.noContent().build());
    }

    @DeleteMapping
    public Mono<ServerResponse> delete(ServerRequest request){
        return ServerResponse.noContent().build(this.userRepository.deleteById(UUID.fromString(request.pathVariable("id"))));
    }


}
