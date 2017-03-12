package br.com.bemobi.shortener.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bemobi.shortener.domain.entity.ShortUrl;
import br.com.bemobi.shortener.domain.repository.ShortUrlRepository;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private ShortUrlRepository shortUrlRepository;

	@GetMapping
	public List<ShortUrl> list() {
		return shortUrlRepository.findAll();
	}

	@GetMapping("/{id}")
	public ShortUrl get(@PathVariable String id) {
		return shortUrlRepository.findOne(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		shortUrlRepository.delete(id);
	}

	@GetMapping("/most-viewed")
	public List<ShortUrl> mostViewed() {
		return shortUrlRepository.findTop10ByOrderByAccessesDesc();
	}
}
