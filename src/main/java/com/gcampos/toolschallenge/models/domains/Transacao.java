package com.gcampos.toolschallenge.models.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transacao {

    private String id;
    private String cartao;
    private TransacaoDescricao descricao;
    private FormaPagamento formaPagamento;
}
