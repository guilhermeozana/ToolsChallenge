package com.gcampos.toolschallenge.services.strategies;

import com.gcampos.toolschallenge.models.domains.Pagamento;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PagamentoParceladoEmissorStrategy extends FormaPagamentoStrategy {
    @Override
    public Pagamento processarPagamento(Pagamento pagamento) {
        log.info("Processando Pagamento Parcelado Emissor com ID de Transação: " + pagamento.getTransacao().getId());

        //Aqui viriam as regras de negócio referentes ao pagamento Parcelado Emissor

        return realizarTransacao(pagamento);
    }

    @Override
    public Pagamento processarEstornoPagamento(Pagamento pagamento) {
        log.info("Processando Estorno de Pagamento Parcelado Emissor com ID de Transação: " + pagamento.getTransacao().getId());

        //Aqui viriam as regras de negócio referentes ao estorno de pagamento Parcelado Emissor

        return realizarEstorno(pagamento);
    }


}
