package br.com.nodelayapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
  private BigDecimal price;
  private boolean active;
}
