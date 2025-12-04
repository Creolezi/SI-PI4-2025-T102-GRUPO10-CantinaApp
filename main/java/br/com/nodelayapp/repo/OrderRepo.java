package br.com.nodelayapp.repo;

import br.com.nodelayapp.domain.Order;
import br.com.nodelayapp.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
  List<Order> findByStatusOrderByCreatedAtAsc(OrderStatus status);
}
