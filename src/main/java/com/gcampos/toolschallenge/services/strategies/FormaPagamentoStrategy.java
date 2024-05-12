package com.gcampos.toolschallenge.services.strategies;

import com.gcampos.toolschallenge.models.domains.Pagamento;
import com.gcampos.toolschallenge.models.enums.StatusTransacaoEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import static com.gcampos.toolschallenge.utils.RandomUtil.gerarIdNumeros;

@Component
@Log4j2
public abstract class FormaPagamentoStrategy {
    public abstract Pagamento processarPagamento(Pagamento pagamento);

    public abstract Pagamento processarEstornoPagamento(Pagamento pagamento);

    protected Pagamento realizarTransacao(Pagamento pagamento) {

        log.info("Realizando transação com a operadora do cartão de número: " + pagamento.getTransacao().getCartao());

        //Simulando retorno de dados da api da operadora do cartão
        pagamento.getTransacao().setId(gerarIdNumeros(10));
        pagamento.getTransacao().getDescricao().setNsu(gerarIdNumeros(10));
        pagamento.getTransacao().getDescricao().setCodigoAutorizacao(gerarIdNumeros(9));
        pagamento.getTransacao().getDescricao().setStatusTransacaoEnum(StatusTransacaoEnum.AUTORIZADO);

        return pagamento;
    }

    protected Pagamento realizarEstorno(Pagamento pagamento) {

        log.info("Realizando estorno com a operadora do cartão de número: " + pagamento.getTransacao().getCartao());

        //Simulando retorno de dados da api da operadora do cartão
        pagamento.getTransacao().getDescricao().setNsu(gerarIdNumeros(10));
        pagamento.getTransacao().getDescricao().setCodigoAutorizacao(gerarIdNumeros(9));
        pagamento.getTransacao().getDescricao().setStatusTransacaoEnum(StatusTransacaoEnum.CANCELADO);

        return pagamento;
    }

}
