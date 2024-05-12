package com.gcampos.toolschallenge.models.dtos;

import com.gcampos.toolschallenge.models.enums.TipoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoDTO {
    private TipoPagamentoEnum tipo;

    private String parcelas;
}
