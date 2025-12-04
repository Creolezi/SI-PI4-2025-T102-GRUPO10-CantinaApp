package br.com.nodelayapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Payment {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private Order order;

  @Enumerated(EnumType.STRING)
  private PaymentStatus status;

  private String method; // "SIMULATED"
  private OffsetDateTime paidAt;
}
