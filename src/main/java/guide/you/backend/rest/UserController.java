package guide.you.backend.rest;

import guide.you.backend.dao.UserRepository;
import guide.you.backend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public Flux<User> all() {
        return userRepository.findAll();
    }

    @PostMapping
    public Mono<User> create(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public Mono<User> get(@PathVariable UUID id){
        return userRepository.findById(id);
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<User>> update(@PathVariable UUID id, @RequestBody User user){
        return userRepository.findById(id)
                .flatMap(existing -> {
                    if (user.getName() != null){
                        existing.setName(user.getName());
                    }
                    if (user.getSurname() != null){
                        existing.setSurname(user.getSurname());
                    }
                    if (user.getEmail() != null){
                        existing.setEmail(user.getEmail());
                    }
                    if (user.getPhone_number() != null){
                        existing.setPhone_number(user.getPhone_number());
                    }
                    return userRepository.save(existing);
                })
                .map(updatedUser -> new ResponseEntity<>(updatedUser, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable UUID id){
            return userRepository.deleteById(id);
    }
}
