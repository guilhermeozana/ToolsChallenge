package com.gcampos.toolschallenge.repositories;

import com.gcampos.toolschallenge.models.domains.Pagamento;
import com.gcampos.toolschallenge.models.enums.StatusTransacaoEnum;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PagamentoRepository extends BaseRepository<Pagamento, String> {

    @Override
    public Optional<Pagamento> findById(String id) {
        return list.stream()
                .filter(pagamento -> pagamento.getTransacao().getId().equals(id))
                .findFirst();
    }
}
