package com.gcampos.toolschallenge.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoDTO {

    private String id;
    private String cartao;
    private TransacaoDescricaoDTO descricao;
    private FormaPagamentoDTO formaPagamento;
}
