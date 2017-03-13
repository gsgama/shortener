package br.com.bemobi.shortener.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.bemobi.shortener.domain.entity.ShortUrl;
import br.com.bemobi.shortener.model.AliasErrorModel;
import br.com.bemobi.shortener.model.ErrorModel;
import br.com.bemobi.shortener.model.ShortUrlModel;
import br.com.bemobi.shortener.services.ShortUrlService;

@RestController
public class ShortUrlController {
	
	@Autowired
	private ShortUrlService shortUrlService;
	
	@PutMapping("/create")
	public ResponseEntity<?> create(@RequestParam(value="url", required=true) String url, @RequestParam(value="CUSTOM_ALIAS", required=false, defaultValue="") String customAlias) {
		long startTime = System.currentTimeMillis();
		Optional<ShortUrl> shortUrl;
		
		if (customAlias.isEmpty()) {
			shortUrl = shortUrlService.shortenUrl(url);
		} else {
			shortUrl = shortUrlService.shortenUrl(url, customAlias);	
		}
		
		if (!shortUrl.isPresent()) {
			return ResponseEntity.ok(new AliasErrorModel("001", "CUSTOM ALIAS ALREADY EXISTS", customAlias));
		}
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
	
		return ResponseEntity.ok(new ShortUrlModel(shortUrl.get().getUrl(), shortUrl.get().getAlias(),
				String.format("%dms", elapsedTime)));
	}
	
	@GetMapping("/{alias}")
	public ModelAndView retrieve(@PathVariable String alias) {
		Optional<ShortUrl> shortUrl = shortUrlService.retrieveUrl(alias);
		
		if (shortUrl.isPresent()) {
			return new ModelAndView("redirect:" + shortUrl.get().getUrl());
		}
		
		return new ModelAndView("forward:/notFound/" + alias);
	}
	
	@GetMapping("/notFound/{alias}")
	public ResponseEntity<?> notFound(@PathVariable String alias) {
		return ResponseEntity.ok(new ErrorModel("002", "SHORTENED URL NOT FOUND"));
	}
}

