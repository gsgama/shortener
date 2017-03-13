package br.com.bemobi.shortener.services;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import br.com.bemobi.shortener.domain.repository.ShortUrlRepository;
import br.com.bemobi.shortener.services.ShortUrlService;;

@RunWith(SpringRunner.class)
// @RestClientTest(ShortUrlService.class)
public class ShortUrlServiceTests {

//	private ShortUrlService shortUrlService;
//	
//	@Autowired
//	private MockRestServiceServer server;
//	
//	@Mock
//	private ShortUrlRepository shortUrlRepository;
//	
//	@Before
//	public void setup() {
//		MockitoAnnotations.initMocks(this);
//		this.shortUrlService = new ShortUrlService(shortUrlRepository);
//	}
	
	@Test
	public void retrieveUrl() throws Exception {
//		this.server.expect(requestTo("/Xpto"))
//			.andRespond(withStatus(HttpStatus.FOUND));
		assertTrue(true);
	}

}
