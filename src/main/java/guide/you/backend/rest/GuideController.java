package guide.you.backend.rest;

import guide.you.backend.dao.GuideRepository;
import guide.you.backend.entity.Guide;
import guide.you.backend.entity.PlannedTrip;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequiredArgsConstructor
@RequestMapping("/guide")
public class GuideController {

    private final GuideRepository guideRepository;

    @GetMapping
    public Flux<Guide> all(){
        return guideRepository.findAll();
    }

    @PostMapping
    public Mono<Guide> create(@RequestBody Guide guide){
        return guideRepository.save(guide);
    }

    @GetMapping("/{id}")
    public Mono<Guide> get(@PathVariable UUID id){
        return guideRepository.findById(id);
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<Guide>> update(@PathVariable UUID id, @RequestBody Guide guide){
        return guideRepository.findById(id)
                .flatMap(existing -> {
                    if (guide.getName() != null){
                        existing.setName(guide.getName());
                    }
                    if (guide.getSurname() != null){
                        existing.setSurname(guide.getSurname());
                    }
                    if (guide.getUserName() != null){
                        existing.setUserName(guide.getUserName());
                    }
                    if (guide.getEmail() != null){
                        existing.setEmail(guide.getEmail());
                    }
                    if (guide.getPhone_number() != null){
                        existing.setPhone_number(guide.getPhone_number());
                    }
                    if (guide.getDescription() != null){
                        existing.setDescription(guide.getDescription());
                    }
                    if (guide.getLanguage() != null){
                        existing.setLanguage(guide.getLanguage());
                    }
                    if (guide.getDestination() != null){
                        existing.setDestination(guide.getDestination());
                    }
                    if (guide.isActive() != false){
                        existing.setActive(guide.isActive());
                    }
                    return guideRepository.save(existing);
                })
                .map(updatedGuide -> new ResponseEntity<>(updatedGuide, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable UUID id){
        return guideRepository.deleteById(id);
    }


}
