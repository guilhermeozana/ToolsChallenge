package com.gcampos.toolschallenge.services.factories;

import com.gcampos.toolschallenge.exceptions.FormaPagamentoNaoEncontradaException;
import com.gcampos.toolschallenge.models.enums.TipoPagamentoEnum;
import com.gcampos.toolschallenge.services.strategies.FormaPagamentoStrategy;
import com.gcampos.toolschallenge.services.strategies.PagamentoAVistaStrategy;
import com.gcampos.toolschallenge.services.strategies.PagamentoParceladoEmissorStrategy;
import com.gcampos.toolschallenge.services.strategies.PagamentoParceladoLojaStrategy;

public class FormaPagamentoFactory {

    private FormaPagamentoFactory(){
    }

    public static FormaPagamentoStrategy getInstance(TipoPagamentoEnum tipoPagamentoEnum) {
        return switch (tipoPagamentoEnum) {
            case AVISTA -> new PagamentoAVistaStrategy();
            case PARCELADO_LOJA -> new PagamentoParceladoLojaStrategy();
            case PARCELADO_EMISSOR -> new PagamentoParceladoEmissorStrategy();
            default -> throw new FormaPagamentoNaoEncontradaException("Forma de Pagamento não existe!");
        };
    }

}
