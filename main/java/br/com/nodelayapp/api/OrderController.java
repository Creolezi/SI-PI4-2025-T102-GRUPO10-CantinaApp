package br.com.nodelayapp.api;

import br.com.nodelayapp.api.dto.CreateOrderRequest;
import br.com.nodelayapp.api.dto.PaymentRequest;
import br.com.nodelayapp.domain.Order;
import br.com.nodelayapp.repo.OrderRepo;
import br.com.nodelayapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;
  private final OrderRepo orderRepo;

  @PostMapping
  public Order create(@RequestBody CreateOrderRequest req) {
    return orderService.createOrder(req);
  }

  @PostMapping("/{id}/pay")
  public Map<String, Object> pay(@PathVariable Long id, @RequestBody(required = false) PaymentRequest req) {
    var p = orderService.simulatePay(id, req == null ? null : req.method());
    return Map.of("paymentId", p.getId(), "status", p.getStatus());
  }

  @GetMapping("/{id}")
  public Order get(@PathVariable Long id) { return orderRepo.findById(id).orElseThrow(); }

  @GetMapping("/{id}/status")
  public Map<String, Object> status(@PathVariable Long id) {
    var o = orderRepo.findById(id).orElseThrow();
    return Map.of("orderId", o.getId(), "status", o.getStatus());
  }
}
