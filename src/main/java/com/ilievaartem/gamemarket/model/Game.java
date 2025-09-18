package com.ilievaartem.gamemarket.model;

import java.util.Objects;

public class Game {
    private Long id;
    private String title;
    private String platform;
    private double price;
    private String seller;

    public Game() {}

    public Game(Long id, String title, String platform, double price, String seller) {
        this.id = id;
        this.title = title;
        this.platform = platform;
        this.price = price;
        this.seller = seller;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Double.compare(game.price, price) == 0 && Objects.equals(id, game.id) && Objects.equals(title, game.title) && Objects.equals(platform, game.platform) && Objects.equals(seller, game.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, platform, price, seller);
    }
}

