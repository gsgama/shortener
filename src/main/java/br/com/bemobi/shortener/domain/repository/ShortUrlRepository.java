package br.com.bemobi.shortener.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.bemobi.shortener.domain.entity.ShortUrl;

public interface ShortUrlRepository extends MongoRepository<ShortUrl, String> {
	List<ShortUrl> findByAlias(String alias);
}
