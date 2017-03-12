package br.com.bemobi.shortener.web.controller;

import java.util.List;

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
import org.springframework.web.servlet.view.RedirectView;

import br.com.bemobi.shortener.domain.entity.ShortUrl;
import br.com.bemobi.shortener.domain.repository.ShortUrlRepository;
import br.com.bemobi.shortener.model.ShortUrlViewModel;

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
	public ShortUrlViewModel create(@RequestParam(value="url", required=true) String url, @RequestParam(value="CUSTOM_ALIAS", required=false, defaultValue="") String customAlias) {
		String alias = customAlias;
		if (alias.isEmpty()) {
			alias = "Xsaxa";
		}
		
		//url += shortUrlRepository.count();
		
		ShortUrlViewModel successOperation = new ShortUrlViewModel(url, alias, String.format("%dms", 1));
		return successOperation;
	}
	
	@GetMapping("/{alias}")
	public RedirectView retrieve(@PathVariable String alias) {
		List<ShortUrl> shortUrls = shortUrlRepository.findByAlias(alias);
		
		if (shortUrls.size() >= 1) {
			// Não virá mais de um resultado, então somente o primeiro é recuperado.
			return new RedirectView(shortUrls.get(0).getUrl());
			
		}
		
		// Não achou
		return new RedirectView("test");
	}
	
	@GetMapping("/test")
	public ShortUrlViewModel test() {
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		ShortUrlViewModel successOperation = new ShortUrlViewModel("http://www.google.com", "ggl", String.format("%dms", elapsedTime));
		return successOperation;
	}
}

