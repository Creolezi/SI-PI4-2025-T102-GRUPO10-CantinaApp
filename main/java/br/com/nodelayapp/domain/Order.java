package br.com.nodelayapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="orders")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Order {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  private Student student;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<OrderItem> items = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  private BigDecimal total;
  private OffsetDateTime createdAt;
}
