package com.gcampos.toolschallenge.models.domains;

import com.gcampos.toolschallenge.models.enums.TipoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormaPagamento {
    private TipoPagamentoEnum tipo;

    private String parcelas;
}
