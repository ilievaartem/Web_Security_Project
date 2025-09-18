package com.ilievaartem.gamemarket.service;

import com.ilievaartem.gamemarket.dto.GameRequest;
import com.ilievaartem.gamemarket.dto.GameResponse;
import com.ilievaartem.gamemarket.model.Game;
import com.ilievaartem.gamemarket.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public List<GameResponse> getAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public Optional<GameResponse> getById(Long id) {
        return repository.findById(id).map(this::toResponse);
    }

    public GameResponse create(GameRequest input) {
        validate(input);
        Game toSave = new Game(null, input.title().trim(), input.platform().trim(), input.price(), input.seller());
        Game saved = repository.save(toSave);
        return toResponse(saved);
    }

    public Optional<GameResponse> update(Long id, GameRequest input) {
        validate(input);
        return repository.findById(id).map(existing -> {
            existing.setTitle(input.title().trim());
            existing.setPlatform(input.platform().trim());
            existing.setPrice(input.price());
            existing.setSeller(input.seller());
            return toResponse(repository.save(existing));
        });
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    private void validate(GameRequest g) {
        if (g == null) throw new IllegalArgumentException("Game is required");
        if (g.title() == null || g.title().isBlank()) throw new IllegalArgumentException("Title is required");
        if (g.platform() == null || g.platform().isBlank()) throw new IllegalArgumentException("Platform is required");
        if (g.price() < 0) throw new IllegalArgumentException("Price must be >= 0");
    }

    private GameResponse toResponse(Game e) {
        return new GameResponse(e.getId(), e.getTitle(), e.getPlatform(), e.getPrice(), e.getSeller());
    }
}
