package com.ilievaartem.gamemarket.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GameRequest(
        String title,
        String platform,
        double price,
        String seller
) {}

