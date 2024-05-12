package com.gcampos.toolschallenge.controllers;

import com.gcampos.toolschallenge.models.domains.Pagamento;
import com.gcampos.toolschallenge.models.dtos.PagamentoDTO;
import com.gcampos.toolschallenge.services.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<List<Pagamento>> listarPagamentos() {
        return ResponseEntity.ok(pagamentoService.listarPagamentos());
    }

    @GetMapping("{id}")
    public ResponseEntity<Pagamento> buscarPagamentoPorId(@PathVariable String id) {
        return ResponseEntity.ok(pagamentoService.buscarPagamentoPorId(id));
    }

    @PostMapping
    public ResponseEntity<Pagamento> realizarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        return ResponseEntity.ok(pagamentoService.realizarPagamento(pagamentoDTO));
    }

    @GetMapping("estornos")
    public ResponseEntity<List<Pagamento>> listarEstornosPagamentos() {
        return ResponseEntity.ok(pagamentoService.listarEstornosPagamentos());
    }

    @GetMapping("estornos/{id}")
    public ResponseEntity<Pagamento> buscarEstornoPagamentoPorId(@PathVariable String id) {
        return ResponseEntity.ok(pagamentoService.buscarEstornoPagamentoPorId(id));
    }

    @PostMapping("estornos/{id}")
    public ResponseEntity<Pagamento> realizarEstornoPagamento(@PathVariable String id) {
        return ResponseEntity.ok(pagamentoService.realizarEstornoPagamento(id));
    }
}
