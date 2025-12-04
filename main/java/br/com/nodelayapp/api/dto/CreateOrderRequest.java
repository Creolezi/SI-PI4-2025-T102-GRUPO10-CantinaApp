package br.com.nodelayapp.api.dto;

import java.util.List;

public record CreateOrderRequest(Long studentId, List<Item> items) {
  public record Item(Long itemId, int quantity) {}
}
