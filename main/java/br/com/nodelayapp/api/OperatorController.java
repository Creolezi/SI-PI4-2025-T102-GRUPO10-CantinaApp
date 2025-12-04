package br.com.nodelayapp.api;

import br.com.nodelayapp.api.dto.UpdateStatusRequest;
import br.com.nodelayapp.domain.Order;
import br.com.nodelayapp.domain.OrderStatus;
import br.com.nodelayapp.repo.OrderRepo;
import br.com.nodelayapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operator")
@RequiredArgsConstructor
public class OperatorController {
  private final OrderRepo orderRepo;
  private final OrderService orderService;

  @GetMapping("/orders")
  public List<Order> byStatus(@RequestParam(required = false) OrderStatus status) {
    if (status == null) return orderRepo.findAll();
    return orderRepo.findByStatusOrderByCreatedAtAsc(status);
  }

  @PatchMapping("/orders/{id}/status")
  public Order update(@PathVariable Long id, @RequestBody UpdateStatusRequest req) {
    return orderService.updateStatus(id, req.status());
  }
}
