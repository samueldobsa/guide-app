package guide.you.backend.rest;

import guide.you.backend.dao.UserReviewRepository;
import guide.you.backend.entity.UserReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@Controller
@RequestMapping("user_review")
@RequiredArgsConstructor
public class UserReviewController {

    private final UserReviewRepository userReviewRepository;

    @GetMapping
    public Mono<ServerResponse> all(ServerRequest request){
        return ServerResponse.ok().body(this.userReviewRepository.findAll(), UserReviewRepository.class);
    }

    @PostMapping
    public Mono<ServerResponse> create(ServerRequest request){
        return request.bodyToMono(UserReview.class)
                .flatMap(userReview -> this.userReviewRepository.save(userReview))
                .flatMap(u -> ServerResponse.created(URI.create("/user-rreview/" + u.getId())).build());
    }

    @GetMapping("{id}")
    public Mono<ServerResponse> get(ServerRequest request){
        return this.userReviewRepository.findById(UUID.fromString(request.pathVariable("id")))
                .flatMap(userReview -> ServerResponse.ok().body(Mono.just(userReview), UserReview.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @PatchMapping("{id}")
    public Mono<ServerResponse> update(ServerRequest request){
        return Mono.zip(
                (data) -> {
                    UserReview userReview = (UserReview) data[0];
                    UserReview userReview1 = (UserReview) data[1];
                    userReview.setTitle(userReview1.getTitle());
                    userReview.setContent(userReview1.getContent());
                    userReview.setRate(userReview1.getRate());
                    return userReview;
                },
                this.userReviewRepository.findById(UUID.fromString(request.pathVariable("id"))),
                request.bodyToMono(UserReview.class)
        )
                .cast(UserReview.class)
                .flatMap(userReview -> this.userReviewRepository.save(userReview))
                .flatMap(userReview -> ServerResponse.noContent().build());
    }

    @DeleteMapping("{id}")
    public Mono<ServerResponse> delete(ServerRequest request){
        return ServerResponse.noContent().build(this.userReviewRepository.deleteById(UUID.fromString(request.pathVariable("id"))));
    }





}
