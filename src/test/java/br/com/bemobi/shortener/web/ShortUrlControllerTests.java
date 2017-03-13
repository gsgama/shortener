package br.com.bemobi.shortener.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

}
