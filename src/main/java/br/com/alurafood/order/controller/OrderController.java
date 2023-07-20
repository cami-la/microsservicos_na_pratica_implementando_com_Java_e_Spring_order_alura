package br.com.alurafood.order.controller;

import br.com.alurafood.order.dto.OrderDto;
import br.com.alurafood.order.dto.StatusDto;
import br.com.alurafood.order.model.Order;
import br.com.alurafood.order.service.impl.OrderServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/orders")
public record OrderController(
    OrderServiceImpl orderService
) {

  @GetMapping
  public ResponseEntity<List<OrderDto>> findAll(Pageable pageable) {
    List<Order> orderPage = orderService.findAll();
    List<OrderDto> orderDtoPage = orderPage.stream()
        .map(OrderDto::new)
        .toList();
    return ResponseEntity.ok(orderDtoPage);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderDto> findById(@PathVariable @NotNull Long id) {
    Order byId = orderService.findById(id);
    OrderDto orderDto = new OrderDto(byId);
    return ResponseEntity.ok(orderDto);
  }

  @PostMapping
  public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid OrderDto dto) {
    Order order = dto.toModel();
    Order savedOrder = orderService.save(order);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("{/orders/{id}")
        .buildAndExpand(savedOrder.getId())
        .toUri();
    return ResponseEntity.created(uri).body(new OrderDto(savedOrder));
  }

  @PatchMapping("/{id}/status")
  public ResponseEntity<OrderDto> updateStatus(@PathVariable Long id, @RequestBody StatusDto status) {
    Order orderUpdatedStatus = orderService.updateStatus(id, status.getStatus());
    return ResponseEntity.ok(new OrderDto(orderUpdatedStatus));
  }

  @PatchMapping("/{id}/paid")
  public ResponseEntity<Void> confirmOrderPayment(@PathVariable @NotNull Long id) {
    orderService.confirmOrderPayment(id);
    return ResponseEntity.ok().build();
  }
}
