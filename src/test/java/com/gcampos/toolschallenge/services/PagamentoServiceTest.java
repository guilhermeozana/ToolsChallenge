package com.gcampos.toolschallenge.services;

import com.gcampos.toolschallenge.exceptions.EstornoPagamentoJaRealizadoException;
import com.gcampos.toolschallenge.exceptions.PagamentoNaoEncontradoException;
import com.gcampos.toolschallenge.exceptions.PagamentoNaoEstornadoException;
import com.gcampos.toolschallenge.models.domains.Pagamento;
import com.gcampos.toolschallenge.models.dtos.PagamentoDTO;
import com.gcampos.toolschallenge.models.enums.StatusTransacaoEnum;
import com.gcampos.toolschallenge.models.enums.TipoPagamentoEnum;
import com.gcampos.toolschallenge.repositories.PagamentoRepository;
import com.gcampos.toolschallenge.utils.PagamentoCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class PagamentoServiceTest {
    @InjectMocks
    private PagamentoService pagamentoService;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        //Given
    }

    @DisplayName("Given lista pagamentos When listarPagamentos Then Retorna lista pagamentos")
    @Test
    void givenListaPagamentos_whenListarPagamentos_thenRetornaListaPagamentos() {

        // Given
        given(pagamentoRepository.findAll()).willReturn(List.of(PagamentoCreator.getProcessado()));

        // When
        List<Pagamento> pagamentos = pagamentoService.listarPagamentos();

        // Then
        assertNotNull(pagamentos);
        assertFalse(pagamentos.isEmpty());
        assertEquals("5500000000000004", pagamentos.get(0).getTransacao().getCartao());
        assertEquals("1234567890", pagamentos.get(0).getTransacao().getId());
    }

    @DisplayName("Given Id When buscarPagamentoPorId Then Retorna pagamento")
    @Test
    void givenPagamento_whenBuscarPagamentoPorId_thenRetornaPagamento() {

        // Given
        given(pagamentoRepository.findById(anyString())).willReturn(Optional.of(PagamentoCreator.getProcessado()));

        // When
        Pagamento pagamentoPorId = pagamentoService.buscarPagamentoPorId("1234567890");

        // Then
        assertNotNull(pagamentoPorId);
        assertEquals("5500000000000004", pagamentoPorId.getTransacao().getCartao());
        assertEquals("1234567890", pagamentoPorId.getTransacao().getId());
    }

    @DisplayName("Given Id When buscarPagamentoPorId Não Encontra Then Lança PagamentoNaoEncontradoException")
    @Test
    void givenPagamento_whenBuscarPagamentoPorIdNaoEncontra_thenLancaPagamentoNaoEncontradoException() {

        // Given
        given(pagamentoRepository.findById(anyString())).willReturn(Optional.empty());

        // Then
        assertThrows(PagamentoNaoEncontradoException.class, () -> {
            // When
            Pagamento pagamentoPorId = pagamentoService.buscarPagamentoPorId("1");
        });
    }

    @DisplayName("Given Pagamento When realizarPagamento Then Retorna pagamento")
    @Test
    void givenPagamento_whenRealizarPagamento_thenRetornaPagamento() {

        // Given
        given(modelMapper.map(any(PagamentoDTO.class), eq(Pagamento.class))).willReturn(PagamentoCreator.getNaoProcessado());
        given(pagamentoRepository.save(any(Pagamento.class))).willReturn(PagamentoCreator.getProcessado());


        // When
        Pagamento pagamentoSalvo = pagamentoService.realizarPagamento(PagamentoCreator.getDTO());

        // Then
        assertNotNull(pagamentoSalvo);
        assertNotNull(pagamentoSalvo.getTransacao().getId());
        assertNotNull(pagamentoSalvo.getTransacao().getDescricao().getNsu());
        assertNotNull(pagamentoSalvo.getTransacao().getDescricao().getCodigoAutorizacao());
        assertEquals("5500000000000004", pagamentoSalvo.getTransacao().getCartao());
        assertEquals(TipoPagamentoEnum.AVISTA, pagamentoSalvo.getTransacao().getFormaPagamento().getTipo());
    }

    @DisplayName("Given lista pagamentos When listarEstornosPagamentos Then Retorna lista pagamentos")
    @Test
    void givenListaPagamentos_whenListarEstornosPagamentos_thenRetornaListaPagamentos() {

        // Given
        given(pagamentoRepository.findAll()).willReturn(List.of(PagamentoCreator.getEstornado()));

        // When
        List<Pagamento> pagamentos = pagamentoService.listarEstornosPagamentos();

        // Then
        assertNotNull(pagamentos);
        assertFalse(pagamentos.isEmpty());
        assertEquals("5500000000000004", pagamentos.get(0).getTransacao().getCartao());
        assertEquals("1234567890", pagamentos.get(0).getTransacao().getId());

        assertTrue(pagamentos.stream()
                .allMatch(p -> p.getTransacao().getDescricao().getStatusTransacaoEnum().equals(StatusTransacaoEnum.CANCELADO))
        );
    }

    @DisplayName("Given Id When buscarEstornoPagamentoPorId Then Retorna pagamento")
    @Test
    void givenId_whenBuscarEstornoPagamentoPorId_thenRetornaPagamento() {

        // Given
        given(pagamentoRepository.findById(anyString())).willReturn(Optional.of(PagamentoCreator.getEstornado()));

        // When
        Pagamento pagamentoPorId = pagamentoService.buscarEstornoPagamentoPorId("1234567890");

        // Then
        assertNotNull(pagamentoPorId);
        assertEquals("5500000000000004", pagamentoPorId.getTransacao().getCartao());
        assertEquals("1234567890", pagamentoPorId.getTransacao().getId());
    }

    @DisplayName("Given Id When buscarEstornoPagamentoPorId Não Encontra Then Lança PagamentoNaoEncontradoException")
    @Test
    void givenId_whenBuscarEstornoPagamentoPorIdNaoEncontra_thenLancaPagamentoNaoEncontradoException() {

        // Given
        given(pagamentoRepository.findById(anyString())).willReturn(Optional.empty());

        // Then
        assertThrows(PagamentoNaoEncontradoException.class, () -> {
            // When
            Pagamento pagamentoPorId = pagamentoService.buscarEstornoPagamentoPorId("1");
        });
    }

    @DisplayName("Given Id When buscarEstornoPagamentoPorId Não Estornado Then Lança PagamentoNaoEstornadoException")
    @Test
    void givenId_whenbuscarEstornoPagamentoPorIdNaoEstornado_thenLancaPagamentoNaoEstornadoException() {

        // Given
        given(pagamentoRepository.findById(anyString())).willReturn(Optional.of(PagamentoCreator.getProcessado()));

        // Then
        assertThrows(PagamentoNaoEstornadoException.class, () -> {
            // When
            Pagamento pagamentoPorId = pagamentoService.buscarEstornoPagamentoPorId("1");
        });
    }

    @DisplayName("Given Pagamento When realizarEstornoPagamento Then Retorna pagamento")
    @Test
    void givenPagamento_whenRealizarEstornoPagamento_thenRetornaPagamento() {

        // Given
        given(pagamentoRepository.findById(anyString())).willReturn(Optional.of(PagamentoCreator.getProcessado()));
        doNothing().when(pagamentoRepository).remove(any(Pagamento.class));
        given(pagamentoRepository.save(any(Pagamento.class))).willReturn(PagamentoCreator.getEstornado());


        // When
        Pagamento pagamentoSalvo = pagamentoService.realizarEstornoPagamento("1234567890");

        // Then
        assertNotNull(pagamentoSalvo);
        assertNotNull(pagamentoSalvo.getTransacao().getId());
        assertNotNull(pagamentoSalvo.getTransacao().getDescricao().getNsu());
        assertNotNull(pagamentoSalvo.getTransacao().getDescricao().getCodigoAutorizacao());
        assertEquals("5500000000000004", pagamentoSalvo.getTransacao().getCartao());
        assertEquals(pagamentoSalvo.getTransacao().getFormaPagamento().getTipo(), TipoPagamentoEnum.AVISTA);
        assertEquals(pagamentoSalvo.getTransacao().getDescricao().getStatusTransacaoEnum(), StatusTransacaoEnum.CANCELADO);
    }

    @DisplayName("Given Id When realizarEstornoPagamento Não Encontra Then Lança PagamentoNaoEncontradoException")
    @Test
    void givenId_whenRealizarEstornoPagamentoNaoEncontra_thenLancaPagamentoNaoEncontradoException() {

        // Given
        given(pagamentoRepository.findById(anyString())).willReturn(Optional.empty());

        // Then
        assertThrows(PagamentoNaoEncontradoException.class, () -> {
            // When
            Pagamento pagamentoPorId = pagamentoService.realizarEstornoPagamento("1");
        });
    }

    @DisplayName("Given Id When realizarEstornoPagamento Já Realizado Then Lança EstornoPagamentoJaRealizadoException")
    @Test
    void givenId_whenRealizarEstornoPagamentoJaRealizado_thenLancaEstornoPagamentoJaRealizadoException() {

        // Given
        given(pagamentoRepository.findById(anyString())).willReturn(Optional.of(PagamentoCreator.getEstornado()));

        // Then
        assertThrows(EstornoPagamentoJaRealizadoException.class, () -> {
            // When
            Pagamento pagamentoPorId = pagamentoService.realizarEstornoPagamento("1234567890");
        });
    }

}