package br.com.bemobi.shortener.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.com.bemobi.shortener.domain.entity.ShortUrl;
import br.com.bemobi.shortener.domain.repository.ShortUrlRepository;
import br.com.bemobi.shortener.model.AliasErrorModel;
import br.com.bemobi.shortener.model.ErrorModel;
import br.com.bemobi.shortener.model.ShortUrlModel;
import br.com.bemobi.shortener.util.CustomBase64;

@RestController
@RequestMapping("/")
public class ShortUrlController {

	@Autowired
	private ShortUrlRepository shortUrlRepository;
	
	@GetMapping("/all")
	public List<ShortUrl> list() {
		return shortUrlRepository.findAll();
	}

	/*
	@GetMapping("/{id}")
	public ShortUrl get(@PathVariable String id) {
		return shortUrlRepository.findOne(id);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ShortUrl shortUrl) {
		shortUrlRepository.save(shortUrl);
		return ResponseEntity.ok(shortUrl);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody ShortUrl shortUrl) {
		shortUrlRepository.save(shortUrl);
		return ResponseEntity.ok(shortUrl);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		shortUrlRepository.delete(id);
	}
	*/
	
	@PutMapping("/create")
	public ResponseEntity<?> create(@RequestParam(value="url", required=true) String url, @RequestParam(value="CUSTOM_ALIAS", required=false, defaultValue="") String customAlias) {
		long startTime = System.currentTimeMillis();
		String alias = customAlias;
		
		if (alias.isEmpty()) {
			alias = new CustomBase64().convertFromLong(System.currentTimeMillis());
		}
		
		if (shortUrlRepository.findByAlias(alias).isPresent()) {
			return ResponseEntity.ok(new AliasErrorModel("001", "CUSTOM ALIAS ALREADY EXISTS", alias));
		}
		
		ShortUrl shortUrl = new ShortUrl();
		shortUrl.setUrl(url);
		shortUrl.setAlias(alias);
		
		shortUrlRepository.save(shortUrl);
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
	
		return ResponseEntity.ok(new ShortUrlModel(shortUrl.getUrl(), shortUrl.getAlias(), String.format("%dms", elapsedTime)));
	}
	
	@GetMapping("/{alias}")
	public ModelAndView retrieve(@PathVariable String alias) {
		Optional<ShortUrl> shortUrl = shortUrlRepository.findByAlias(alias);
		
		if (shortUrl.isPresent()) {
			return new ModelAndView("redirect:" + shortUrl.get().getUrl());
		}
		
		return new ModelAndView("forward:/notFound/" + alias);
	}
	
	@GetMapping("/notFound/{alias}")
	public ResponseEntity<?> notFound(@PathVariable String alias) {
		return ResponseEntity.ok(new ErrorModel("002", "SHORTENED URL NOT FOUND"));
	}
	
	@GetMapping("/test")
	public ShortUrlModel test() {
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		ShortUrlModel successOperation = new ShortUrlModel("http://www.google.com", "ggl", String.format("%dms", elapsedTime));
		return successOperation;
	}
}

