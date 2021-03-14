package br.com.jmsdevel.sisresapi.controller;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jmsdevel.sisresapi.dto.TokenDto;
import br.com.jmsdevel.sisresapi.dto.usuario.UsuarioDto;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mock;

	@Test
	void deveRetornarStatus403() throws Exception {
		URI uri = new URI("/usuario");
		
		ResultActions result = mock.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON));
	
		result.andExpect(MockMvcResultMatchers.status().is(403));
	}
	
	@Test
	void deveLogarUsuarioeRetornarListaDeUsuarios() throws Exception {
		URI uri = new URI("/auth");
		String payload = "{\"username\": \"admin\", \"senha\": \"1234567890\"}";
		
		ResultActions result = mock.perform(MockMvcRequestBuilders.post(uri).content(payload).contentType(MediaType.APPLICATION_JSON));
	
		MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
		
		ObjectMapper mapper = new ObjectMapper();
		
		TokenDto tokenDto = mapper.readValue(content, TokenDto.class);
		
		uri = new URI("/usuario");
		
		result = mock.perform(MockMvcRequestBuilders.get(uri).header("Authorization", "Bearer " + tokenDto.getToken()).contentType(MediaType.APPLICATION_JSON));
	
		mvcResult = result.andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		
		content = mvcResult.getResponse().getContentAsString();
		
		List usuarioGeneric = mapper.readValue(content, List.class);
		
		List<UsuarioDto> usuarios = usuarioGeneric;
		
		assertNotNull(usuarios);
		assertNotEquals(true, usuarios.isEmpty());
	}

}
