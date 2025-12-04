package br.com.nodelayapp.service;

import br.com.nodelayapp.api.dto.CreateOrderRequest;
import br.com.nodelayapp.domain.*;
import br.com.nodelayapp.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepo orderRepo;
  private final StudentRepo studentRepo;
  private final MenuItemRepo menuItemRepo;
  private final PaymentRepo paymentRepo;

  @Transactional
  public Order createOrder(CreateOrderRequest req) {
    var student = studentRepo.findById(req.studentId())
        .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

    var order = Order.builder()
        .student(student)
        .status(OrderStatus.CREATED)
        .createdAt(OffsetDateTime.now())
        .total(BigDecimal.ZERO)
        .build();

    var saved = orderRepo.save(order);
    var total = BigDecimal.ZERO;

    for (var it : req.items()) {
      var menu = menuItemRepo.findById(it.itemId())
          .orElseThrow(() -> new IllegalArgumentException("Item inválido"));
      var oi = OrderItem.builder()
          .order(saved)
          .item(menu)
          .quantity(it.quantity())
          .unitPrice(menu.getPrice())
          .build();
      saved.getItems().add(oi);
      total = total.add(menu.getPrice().multiply(BigDecimal.valueOf(it.quantity())));
    }
    saved.setTotal(total);
    return orderRepo.save(saved);
  }

  @Transactional
  public Payment simulatePay(Long orderId, String method) {
    var order = orderRepo.findById(orderId)
        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

    var payment = Payment.builder()
        .order(order)
        .status(PaymentStatus.PAID)
        .method(method == null ? "SIMULATED" : method)
        .paidAt(OffsetDateTime.now())
        .build();

    order.setStatus(OrderStatus.CONFIRMED);
    orderRepo.save(order);
    return paymentRepo.save(payment);
  }

  @Transactional
  public Order updateStatus(Long orderId, OrderStatus status) {
    var order = orderRepo.findById(orderId)
        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
    order.setStatus(status);
    return orderRepo.save(order);
  }
}
