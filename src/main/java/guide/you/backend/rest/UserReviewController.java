package guide.you.backend.rest;

import guide.you.backend.dao.UserReviewRepository;
import guide.you.backend.entity.UserReview;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/user_review")
@RequiredArgsConstructor
public class UserReviewController {

    private final UserReviewRepository userReviewRepository;

    @GetMapping
    public Flux<UserReview> all(){
        return userReviewRepository.findAll();
    }

    @PostMapping
    public Mono<UserReview> create(@RequestBody UserReview userReview){
        return userReviewRepository.save(userReview);
    }

    @GetMapping("/{id}")
    public Mono<UserReview> get(@PathVariable UUID id){
        return userReviewRepository.findById(id);
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<UserReview>> update(@PathVariable UUID id, @RequestBody UserReview userReview){
        return userReviewRepository.findById(id)
                .flatMap(existing -> {
                    if (userReview.getTitle() != null){
                        existing.setTitle(userReview.getTitle());
                    }
                    if (userReview.getContent() != null){
                        existing.setContent(userReview.getContent());
                    }
                    if (userReview.getRate() >= 0 && userReview.getRate() <= 5){
                        existing.setRate(userReview.getRate());
                    }
                    return userReviewRepository.save(existing);
                })
                .map(updatedUserReview -> new ResponseEntity<>(updatedUserReview, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable UUID id){
        return userReviewRepository.deleteById(id);
    }





}
