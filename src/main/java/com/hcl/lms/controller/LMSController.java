package com.hcl.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.lms.dto.BookDTO;
import com.hcl.lms.service.LMSService;
import com.hcl.lms.validator.BindingResultsHelper;
import com.hcl.lms.validator.BookDTOValidator;

@Component
@RestController
public class LMSController {
	
	@Autowired
	private LMSService lmsService;
	
	@InitBinder("bookDTO")
	public void bookBinding(WebDataBinder binder){
		binder.addValidators(new BookDTOValidator());
	}
	
	@RequestMapping(path="/save",method=RequestMethod.POST,consumes="application/json")
	public  ResponseEntity saveBook(@RequestBody @Valid BookDTO bookDTO,BindingResult bindingResult ) {
		List<String> errorsList =BindingResultsHelper.gerErrorsList(bindingResult); 
		BookDTO createdBook = lmsService.saveBook(bookDTO);
		if (!errorsList.isEmpty()) {
			return new ResponseEntity(errorsList,HttpStatus.BAD_REQUEST);
		}
	 return new ResponseEntity(createdBook,HttpStatus.CREATED);
	}

	@RequestMapping(path="/saveall",method=RequestMethod.POST,consumes="application/json")
	public  ResponseEntity<List<BookDTO>> saveAllBooks(@RequestBody List<BookDTO> bookDTOList,BindingResult bindingResult) {
		List<String> errorsList =BindingResultsHelper.gerErrorsList(bindingResult); 
		List<BookDTO> createdBooks = lmsService.saveAllBooks(bookDTOList);
		if (!errorsList.isEmpty()) {
			return new ResponseEntity(errorsList,HttpStatus.BAD_REQUEST);
		}
	 return new ResponseEntity<List<BookDTO>>(createdBooks,HttpStatus.CREATED);
	}
	
	@RequestMapping(path="/search",method=RequestMethod.GET,produces="application/json")
	public  ResponseEntity<List<BookDTO>> searchBooks(@RequestParam String title,@RequestParam String category) {
		List<BookDTO> searchBookList = lmsService.findByTitleAndCategory(title,category);
	 return new ResponseEntity<List<BookDTO>>(searchBookList,HttpStatus.OK);
	}

}
