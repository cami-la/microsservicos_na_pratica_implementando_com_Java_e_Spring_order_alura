package br.com.alurafood.order.dto;

import br.com.alurafood.order.enummeration.Status;
import br.com.alurafood.order.model.ItemOfOrder;
import br.com.alurafood.order.model.Order;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class OrderDto {
  private Long id;
  private LocalDateTime dateTime;
  private Status status;
  private List<ItemOfOrder> items = new ArrayList<>();

  public OrderDto(Order order) {
    this.id = order.getId();
    this.dateTime = order.getDateTime();
    this.status = order.getStatus();
    this.items = order.getItems();
  }

  public Order toModel() {
    Order order = Order.builder()
        .dateTime(this.dateTime)
        .status(this.status)
        .items(this.items)
        .build();
    order.getItems().forEach(item -> item.setOrder(order));
    return order;
  }
}
