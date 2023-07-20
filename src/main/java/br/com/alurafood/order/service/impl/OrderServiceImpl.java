package br.com.alurafood.order.service.impl;

import br.com.alurafood.order.enummeration.Status;
import br.com.alurafood.order.model.Order;
import br.com.alurafood.order.repository.OrderRepository;
import br.com.alurafood.order.service.IOrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public record OrderServiceImpl(
    OrderRepository orderRepository

) implements IOrderService{
  @Override
  public Order save(Order order) {
    order.setDateTime(LocalDateTime.now());
    order.setStatus(Status.CREATED);
    return orderRepository.save(order);
  }

  @Override
  public List<Order> findAll() {
    return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "dateTime"));
  }

  @Override
  public Order findById(Long id) {
    return orderRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
  }

  public Order updateStatus(Long id, Status status) {
    Order order = orderRepository.findByIdWithItems(id);
    if(order == null) {
      throw new EntityNotFoundException();
    }
    order.setStatus(status);
    orderRepository.updateStatus(status, order);
    return order;
  }

  public void confirmOrderPayment(Long id) {
    Order order = orderRepository.findByIdWithItems(id);
    if(order == null) {
      throw new EntityNotFoundException();
    }
    order.setStatus(Status.PAID);
    orderRepository.updateStatus(Status.PAID, order);
  }
}
