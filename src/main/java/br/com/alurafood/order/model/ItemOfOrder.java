package br.com.alurafood.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "item_do_pedido")
public class ItemOfOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Positive
  private Integer quantity;
  private String description;
  @ManyToOne(optional = false)
  @JsonIgnore
  private Order order;
}
