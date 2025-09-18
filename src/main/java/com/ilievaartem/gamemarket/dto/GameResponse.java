package com.ilievaartem.gamemarket.dto;

public record GameResponse(
        Long id,
        String title,
        String platform,
        double price,
        String seller
) {}

