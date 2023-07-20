package br.com.alurafood.order.repository;

import br.com.alurafood.order.enummeration.Status;
import br.com.alurafood.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {
  @Transactional
  @Modifying(clearAutomatically = true)
  @Query("update Order p set p.status = :status where p = :pedido")
  void updateStatus(Status status, Order pedido);

  @Query(value = "SELECT p from Order p LEFT JOIN FETCH p.items where p.id = :id")
  Order findByIdWithItems(Long id);
}
