package com.gcampos.toolschallenge.utils;

import com.gcampos.toolschallenge.models.domains.FormaPagamento;
import com.gcampos.toolschallenge.models.domains.Pagamento;
import com.gcampos.toolschallenge.models.domains.Transacao;
import com.gcampos.toolschallenge.models.domains.TransacaoDescricao;
import com.gcampos.toolschallenge.models.dtos.PagamentoDTO;
import com.gcampos.toolschallenge.models.enums.StatusTransacaoEnum;
import com.gcampos.toolschallenge.models.enums.TipoPagamentoEnum;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class PagamentoCreator {

    private PagamentoCreator() {}

    public static Pagamento getNaoProcessado(){

        TransacaoDescricao transacaoDescricao = TransacaoDescricao.builder()
                .valor("100.00")
                .dataHora(LocalDateTime.now())
                .estabelecimento("PetShop Mundo cão")
                .build();

        FormaPagamento formaPagamento = FormaPagamento.builder()
                .tipo(TipoPagamentoEnum.AVISTA)
                .parcelas("1")
                .build();

        Transacao transacao = Transacao.builder()
                .id("1234567890")
                .cartao("5500000000000004")
                .descricao(transacaoDescricao)
                .formaPagamento(formaPagamento)
                .build();

        return Pagamento.builder().transacao(transacao).build();
    }

    public static Pagamento getProcessado(){

        TransacaoDescricao transacaoDescricao = TransacaoDescricao.builder()
                .valor("100.00")
                .dataHora(LocalDateTime.now())
                .estabelecimento("PetShop Mundo cão")
                .nsu("1234567890")
                .codigoAutorizacao("123456789")
                .statusTransacaoEnum(StatusTransacaoEnum.AUTORIZADO)
                .build();

        FormaPagamento formaPagamento = FormaPagamento.builder()
                .tipo(TipoPagamentoEnum.AVISTA)
                .parcelas("1")
                .build();

        Transacao transacao = Transacao.builder()
                .id("1234567890")
                .cartao("5500000000000004")
                .descricao(transacaoDescricao)
                .formaPagamento(formaPagamento)
                .build();

        return Pagamento.builder().transacao(transacao).build();
    }

    public static Pagamento getEstornado(){
        Pagamento pagamento = getProcessado();
        pagamento.getTransacao().getDescricao().setStatusTransacaoEnum(StatusTransacaoEnum.CANCELADO);

        return pagamento;
    }

    public static PagamentoDTO getDTO(){
        ModelMapper modelMapper = new ModelMapper();

        PagamentoDTO pagamentoDTO = modelMapper.map(getNaoProcessado(), PagamentoDTO.class);

        pagamentoDTO.getTransacao().setId(null);

        return pagamentoDTO;
    }
}
