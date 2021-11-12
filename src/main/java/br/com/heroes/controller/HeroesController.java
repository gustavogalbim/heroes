package br.com.heroes.controller;

import br.com.heroes.document.Heroes;
import br.com.heroes.service.HeroesService;
import lombok.extern.slf4j.Slf4j;

import static br.com.heroes.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(HEROES_ENDPOINT_LOCAL)
public class HeroesController {

    private HeroesService heroesService;

    public HeroesController(HeroesService heroesService) {
        this.heroesService = heroesService;
    }

    @GetMapping
    public Flux<Heroes> getAllItems() {
        log.info("Requesting the list off all heroes");
        return heroesService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Heroes>> findByIdHero(@PathVariable String id) {
        log.info("Requesting the hero with id: {}", id);
        return heroesService.findByIdHero(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Mono<Heroes> createHero(@RequestBody Heroes heroes) {
        log.info("A new Hero was created");
        return heroesService.save(heroes);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletebyIDHero(@PathVariable String id) {
        log.info("Deleting the hero with id: {}", id);
        heroesService.deleteByIDHero(id);
    }

}
