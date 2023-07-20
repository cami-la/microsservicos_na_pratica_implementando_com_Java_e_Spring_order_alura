package br.com.alurafood.order.dto;

import br.com.alurafood.order.enummeration.Status;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class StatusDto {
  private Status status;
}
