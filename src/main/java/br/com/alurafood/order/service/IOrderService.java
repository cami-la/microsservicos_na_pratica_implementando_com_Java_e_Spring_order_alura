package br.com.alurafood.order.service;

import br.com.alurafood.order.model.Order;

import java.util.List;

public interface IOrderService {
  Order save(Order order);
  List<Order> findAll();
  Order findById(Long id);
}
