package guide.you.backend.rest;

import guide.you.backend.dao.HousingRepository;
import guide.you.backend.entity.Housing;
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
@RequestMapping("/housing")
@RequiredArgsConstructor
public class HousingController {

    private final HousingRepository housingRepository;

    @GetMapping
    private Flux<Housing> all(){
        return housingRepository.findAll();
    }

    @PostMapping
    private Mono<Housing> create(@RequestBody Housing housing){
        return housingRepository.save(housing);
    }

    @GetMapping("/{id}")
    private Mono<Housing> get(@PathVariable UUID id){
        return housingRepository.findById(id);
    }

    @PatchMapping("/{id}")
    private Mono<ResponseEntity<Housing>> update(@PathVariable UUID id, @RequestBody Housing housing){
        return housingRepository.findById(id)
                .flatMap(existing -> {
                    if (housing.getCountBeds() != 0){
                        existing.setCountBeds(housing.getCountBeds());
                    }
                    if (housing.isInGuidesHome() != false){
                        existing.setInGuidesHome(housing.isInGuidesHome());
                    }
                    return housingRepository.save(existing);
                })
                .map(updatedHousing -> new ResponseEntity<>(updatedHousing, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    private Mono<Void> delete(@PathVariable UUID id){
        return housingRepository.deleteById(id);
    }
}
