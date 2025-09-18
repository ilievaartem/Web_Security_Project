package com.ilievaartem.gamemarket.web;

import com.ilievaartem.gamemarket.model.Game;
import com.ilievaartem.gamemarket.repository.InMemoryGameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private final InMemoryGameRepository repository;

    public GameController(InMemoryGameRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Game> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        if (game.getTitle() == null || game.getTitle().isBlank()) return ResponseEntity.badRequest().build();
        if (game.getPlatform() == null || game.getPlatform().isBlank()) return ResponseEntity.badRequest().build();
        if (game.getPrice() < 0) return ResponseEntity.badRequest().build();
        Game created = repository.save(new Game(null, game.getTitle().trim(), game.getPlatform().trim(), game.getPrice(), game.getSeller()));
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> update(@PathVariable Long id, @RequestBody Game game) {
        if (game.getTitle() == null || game.getTitle().isBlank()) return ResponseEntity.badRequest().build();
        if (game.getPlatform() == null || game.getPlatform().isBlank()) return ResponseEntity.badRequest().build();
        if (game.getPrice() < 0) return ResponseEntity.badRequest().build();
        return repository.update(id, game)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = repository.deleteById(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

