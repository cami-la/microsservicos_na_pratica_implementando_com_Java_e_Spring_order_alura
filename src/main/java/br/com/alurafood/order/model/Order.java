package br.com.alurafood.order.model;

import br.com.alurafood.order.enummeration.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "pedidos")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private LocalDateTime dateTime;
  @NotNull
  @Enumerated(EnumType.STRING)
  private Status status;
  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "order")
  private List<ItemOfOrder> items = new ArrayList<>();
}
