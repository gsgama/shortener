package br.com.bemobi.shortener.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.bemobi.shortener.domain.entity.ShortUrl;

public interface ShortUrlRepository extends MongoRepository<ShortUrl, String> {
	Optional<ShortUrl> findByAlias(String alias);
	List<ShortUrl> findTop10ByOrderByAccessesDesc();
}
