package com.gcampos.toolschallenge.services;

import com.gcampos.toolschallenge.exceptions.EstornoPagamentoJaRealizadoException;
import com.gcampos.toolschallenge.exceptions.PagamentoNaoEstornadoException;
import com.gcampos.toolschallenge.exceptions.PagamentoNaoEncontradoException;
import com.gcampos.toolschallenge.models.domains.Pagamento;
import com.gcampos.toolschallenge.models.dtos.PagamentoDTO;
import com.gcampos.toolschallenge.models.enums.StatusTransacaoEnum;
import com.gcampos.toolschallenge.repositories.PagamentoRepository;
import com.gcampos.toolschallenge.services.factories.FormaPagamentoFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Repository
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    private final ModelMapper modelMapper;

    public List<Pagamento> listarPagamentos() {
        return pagamentoRepository.findAll();
    }

    public Pagamento buscarPagamentoPorId(String id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento não encontrado com o ID de Transação: " + id));
    }

    public Pagamento realizarPagamento(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = modelMapper.map(pagamentoDTO, Pagamento.class);

        Pagamento pagamentoProcessado = FormaPagamentoFactory
                                            .getInstance(pagamento.getTransacao().getFormaPagamento().getTipo())
                                            .processarPagamento(pagamento);

        return pagamentoRepository.save(pagamentoProcessado);
    }

    public List<Pagamento> listarEstornosPagamentos() {
        return pagamentoRepository.findAll().stream()
                .filter(pagamento -> pagamento.getTransacao().getDescricao()
                        .getStatusTransacaoEnum().equals(StatusTransacaoEnum.CANCELADO)
                )
                .collect(Collectors.toList());
    }

    public Pagamento buscarEstornoPagamentoPorId(String id) {
        Pagamento pagamento = buscarPagamentoPorId(id);

        if (!pagamento.getTransacao().getDescricao().getStatusTransacaoEnum()
                .equals(StatusTransacaoEnum.CANCELADO)) {
            throw new PagamentoNaoEstornadoException("Estorno não solicitado ou não concluído para o Pagamento com ID de Transação: " + id);
        }

        return pagamento;
    }

    public Pagamento realizarEstornoPagamento(String id){
        Pagamento pagamento = buscarPagamentoPorId(id);

        if (pagamento.getTransacao().getDescricao().getStatusTransacaoEnum()
                .equals(StatusTransacaoEnum.CANCELADO)) {
            throw new EstornoPagamentoJaRealizadoException("Estorno já foi realizado para o Pagamento com ID de Transação:" + id);
        }

        pagamentoRepository.remove(pagamento);

        Pagamento pagamentoEstornado = FormaPagamentoFactory
                .getInstance(pagamento.getTransacao().getFormaPagamento().getTipo())
                .processarEstornoPagamento(pagamento);

        return pagamentoRepository.save(pagamentoEstornado);
    }
}
