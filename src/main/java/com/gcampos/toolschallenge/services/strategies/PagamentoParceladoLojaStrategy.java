package com.gcampos.toolschallenge.services.strategies;

import com.gcampos.toolschallenge.models.domains.Pagamento;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PagamentoParceladoLojaStrategy extends FormaPagamentoStrategy {
    @Override
    public Pagamento processarPagamento(Pagamento pagamento) {
        log.info("Processando Pagamento A Vista com ID de Transação: " + pagamento.getTransacao().getId());

        return realizarTransacao(pagamento);
    }

    @Override
    public Pagamento processarEstornoPagamento(Pagamento pagamento) {
        log.info("Processando Estorno de Pagamento A Vista com ID de Transação: " + pagamento.getTransacao().getId());

        return realizarEstorno(pagamento);
    }
}
