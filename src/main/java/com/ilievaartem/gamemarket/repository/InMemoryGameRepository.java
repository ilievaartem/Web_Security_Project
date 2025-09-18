package com.ilievaartem.gamemarket.repository;

import com.ilievaartem.gamemarket.model.Game;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryGameRepository {
    private final Map<Long, Game> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public List<Game> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Game> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Game save(Game game) {
        long id = seq.incrementAndGet();
        game.setId(id);
        store.put(id, game);
        return game;
    }

    public Optional<Game> update(Long id, Game game) {
        return Optional.ofNullable(store.computeIfPresent(id, (k, existing) -> {
            existing.setTitle(game.getTitle());
            existing.setPlatform(game.getPlatform());
            existing.setPrice(game.getPrice());
            existing.setSeller(game.getSeller());
            return existing;
        }));
    }

    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }

    public void deleteAll() {
        store.clear();
    }
}

