package br.com.bemobi.shortener.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.bemobi.shortener.domain.entity.ShortUrl;
import br.com.bemobi.shortener.services.ShortUrlService;
import br.com.bemobi.shortener.web.controller.ShortUrlController;

@RunWith(SpringRunner.class)
@WebMvcTest(ShortUrlController.class)
public class ShortUrlControllerTests {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ShortUrlService shortUrlService;
	
	@Test
	public void retrieveUrl() throws Exception {
		ShortUrl shortUrl = new ShortUrl();
		shortUrl.setAlias("url-alias");
		shortUrl.setUrl("http://www.url.com/path");
		
		given(this.shortUrlService.retrieveUrl("url-alias"))
			.willReturn(Optional.of(shortUrl));
		
		this.mvc.perform(get("/url-alias"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("http://www.url.com/path"));
	}
	
	@Test
	public void urlNotFound() throws Exception {
		given(this.shortUrlService.retrieveUrl("dont-exists"))
			.willReturn(Optional.empty());
		
		this.mvc.perform(get("/dont-exists"))
			.andExpect(forwardedUrl("/notFound/dont-exists"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void urlNotFoundMessage() throws Exception {
		given(this.shortUrlService.retrieveUrl("dont-exists"))
			.willReturn(Optional.empty());
		
		this.mvc.perform(get("/notFound/dont-exists"))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.err_code").value("002"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("SHORTENED URL NOT FOUND"));
	}
	
	@Test
	public void shortenUrlWithAlias() throws Exception {
		ShortUrl shortUrl = new ShortUrl();
		shortUrl.setAlias("umaurl");
		shortUrl.setUrl("http://www.umaurl.com.br");
		
		given(this.shortUrlService.shortenUrl("http://www.umaurl.com.br", "umaurl"))
			.willReturn(Optional.of(shortUrl));
		
		this.mvc.perform(put("/create?url=http://www.umaurl.com.br&CUSTOM_ALIAS=umaurl"))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.url").value("http://www.umaurl.com.br"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.alias").value("umaurl"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.statistics.time_taken").exists());
	}
	
	@Test
	public void shortenUrlWithoutAlias() throws Exception {
		ShortUrl shortUrl = new ShortUrl();
		shortUrl.setAlias("auto-generated");
		shortUrl.setUrl("http://www.outraurl.com.br");
		
		given(this.shortUrlService.shortenUrl("http://www.outraurl.com.br"))
			.willReturn(Optional.of(shortUrl));
		
		this.mvc.perform(put("/create?url=http://www.outraurl.com.br"))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.url").value("http://www.outraurl.com.br"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.alias").value("auto-generated"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.statistics.time_taken").exists());
	}
	
	@Test
	public void tryShortenRepeatedAlias() throws Exception {
		given(this.shortUrlService.shortenUrl("http://www.nova.com.br", "alias-existente"))
			.willReturn(Optional.empty());
		
		this.mvc.perform(put("/create?url=http://www.nova.com.br&CUSTOM_ALIAS=alias-existente"))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.alias").value("alias-existente"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.err_code").value("001"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("CUSTOM ALIAS ALREADY EXISTS"));
	}

}
