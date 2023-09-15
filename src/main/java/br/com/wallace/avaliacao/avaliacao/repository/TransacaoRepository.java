package br.com.wallace.avaliacao.avaliacao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.wallace.avaliacao.avaliacao.model.Transacao;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Integer> {
}