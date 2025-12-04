package br.com.nodelayapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  private Order order;

  @ManyToOne(optional = false)
  private MenuItem item;

  private int quantity;
  private BigDecimal unitPrice;
}
