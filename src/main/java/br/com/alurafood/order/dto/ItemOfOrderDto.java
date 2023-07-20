package br.com.alurafood.order.dto;

import br.com.alurafood.order.model.ItemOfOrder;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class ItemOfOrderDto {
  private Long id;
  private Integer quantity;
  private String description;

  public ItemOfOrderDto(ItemOfOrder itemOfOrder) {
    this.id = itemOfOrder.getId();
    this.quantity = itemOfOrder.getQuantity();
    this.description = itemOfOrder.getDescription();
  }
}
