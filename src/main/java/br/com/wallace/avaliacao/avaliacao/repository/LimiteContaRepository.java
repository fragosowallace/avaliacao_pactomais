package br.com.wallace.avaliacao.avaliacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.wallace.avaliacao.avaliacao.model.LimiteConta;

@Repository
public interface LimiteContaRepository extends CrudRepository<LimiteConta, Integer> {

    @Query("SELECT l.limite FROM LimiteConta l WHERE l.conta.id = :contaId")
    Optional<Double> calcularLimiteAtual(@Param("contaId") int contaId);

    Optional<LimiteConta> findByContaId(int id);
}   