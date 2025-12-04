package br.com.nodelayapp.api.dto;

import br.com.nodelayapp.domain.OrderStatus;

public record UpdateStatusRequest(OrderStatus status) {}
