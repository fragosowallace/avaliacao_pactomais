package br.com.wallace.avaliacao.avaliacao.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.wallace.avaliacao.avaliacao.model.Conta;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Integer> {

    Optional<Conta> findById(Long contaId);
}
