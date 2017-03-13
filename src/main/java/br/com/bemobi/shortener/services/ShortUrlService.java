package br.com.bemobi.shortener.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bemobi.shortener.domain.entity.ShortUrl;
import br.com.bemobi.shortener.domain.repository.ShortUrlRepository;
import br.com.bemobi.shortener.util.CustomBase64;

@Service
@Transactional
public class ShortUrlService {

	@Autowired
	private ShortUrlRepository shortUrlRepository;
	
	public Optional<ShortUrl> shortenUrl(String url) {
		String alias = new CustomBase64().convertFromLong(System.currentTimeMillis());
		return shortenUrl(url, alias);
	}

	public Optional<ShortUrl> shortenUrl(String url, String alias) {
		if (shortUrlRepository.findByAlias(alias).isPresent()) {
			return Optional.empty();
		}
		
		ShortUrl shortUrl = new ShortUrl();
		shortUrl.setUrl(url);
		shortUrl.setAlias(alias);
		
		return Optional.of(shortUrlRepository.save(shortUrl));
	}
	
	public Optional<ShortUrl> retrieveUrl(String alias) {
		Optional<ShortUrl> shortUrl = shortUrlRepository.findByAlias(alias);
		
		if (shortUrl.isPresent()) {
			ShortUrl shortUrlToUpdate = shortUrl.get(); 
			shortUrlToUpdate.setAccesses(shortUrlToUpdate.getAccesses() + 1);
			shortUrl = Optional.of(shortUrlRepository.save(shortUrlToUpdate));
		}
		
		return shortUrl;
	}
}
