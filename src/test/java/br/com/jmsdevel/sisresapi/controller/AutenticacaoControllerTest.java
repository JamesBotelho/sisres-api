package br.com.jmsdevel.sisresapi.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class AutenticacaoControllerTest {
	
	@Autowired
	private MockMvc mock;

	@Test
	void deveRetornarSucesso() throws Exception {
		URI uri = new URI("/auth");
		String payload = "{\"username\": \"admin\", \"senha\": \"1234567890\"}";
		
		ResultActions result = mock.perform(MockMvcRequestBuilders.post(uri).content(payload).contentType(MediaType.APPLICATION_JSON));
	
		result.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	void deveRetornarUnathorized() throws Exception {
		URI uri = new URI("/auth");
		String payload = "{\"username\": \"admin\", \"senha\": \"123456789\"}";
		
		ResultActions result = mock.perform(MockMvcRequestBuilders.post(uri).content(payload).contentType(MediaType.APPLICATION_JSON));
	
		result.andExpect(MockMvcResultMatchers.status().is(401));
	}

}
