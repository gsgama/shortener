package br.com.bemobi.shortener.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.bemobi.shortener.domain.entity.ShortUrl;

public interface ShortUrlRepository extends MongoRepository<ShortUrl, String> {

	
}
