package com.hcl.lms.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hcl.lms.dto.BookDTO;
import com.hcl.lms.repository.LMSRepository;
import com.hcl.lms.service.LMSService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LMSController.class)
//@WithMockUser
public class LMSControllerTest  {

	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LMSService lMSService;
	
	@MockBean
	private LMSRepository lMSRepository;
	
	
	@Test
	public void saveBooks_success() throws Exception{
		String exampleBookJson = "[{\"title\":\"Java\",\"author\":\"Mark\",\"category\":\"programming\",\"price\":100,\"quantity\":3}]";
	Mockito.when(lMSRepository.saveAll(Mockito.anyIterable())).thenReturn(new ArrayList());
	Mockito.when(lMSService.saveAllBooks(Mockito.anyList())).thenReturn(getBookDTO());
	RequestBuilder requestBuilder = MockMvcRequestBuilders
			.post("/saveall")
			.accept(MediaType.APPLICATION_JSON).content(exampleBookJson)
			.contentType(MediaType.APPLICATION_JSON);
	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	MockHttpServletResponse response = result.getResponse();
	assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	
	}
	
	@Test
	public void saveBooks_failure_bad_req() throws Exception{
		String exampleBookJson = "{\"title\":\"Java\",\"author\":\"Mark\",\"category\":\"programming\",\"price\":100,\"quantity\":3}";
	Mockito.when(lMSRepository.saveAll(Mockito.anyIterable())).thenReturn(new ArrayList());
	Mockito.when(lMSService.saveAllBooks(Mockito.anyList())).thenReturn(getBookDTO());
	RequestBuilder requestBuilder = MockMvcRequestBuilders
			.post("/saveall")
			.accept(MediaType.APPLICATION_JSON).content(exampleBookJson)
			.contentType(MediaType.APPLICATION_JSON);
	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	MockHttpServletResponse response = result.getResponse();
	assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

	
	}
	
	private List<BookDTO> getBookDTO(){
		BookDTO mockBookDTO = new BookDTO("Java","Mark","programming",100l,3l);
		List<BookDTO> list = new ArrayList<BookDTO>();
		list.add(mockBookDTO);
		return list;
		}
}
