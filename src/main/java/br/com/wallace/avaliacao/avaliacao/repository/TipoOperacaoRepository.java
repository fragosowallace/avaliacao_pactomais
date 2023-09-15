package br.com.wallace.avaliacao.avaliacao.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.wallace.avaliacao.avaliacao.model.TipoOperacao;

@Repository
public interface TipoOperacaoRepository extends CrudRepository<TipoOperacao, Integer> {

    Optional<TipoOperacao> findById(int tipoOperacaoId);
}
