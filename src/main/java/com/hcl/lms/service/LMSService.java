package com.hcl.lms.service;

import java.util.List;

import com.hcl.lms.dto.BookDTO;
import com.hcl.lms.model.Book;

public interface LMSService{
	
	public BookDTO saveBook(BookDTO bookDTO);

	public List<BookDTO> findByTitleAndCategory(String title,String category);
	public List<BookDTO> saveAllBooks(List<BookDTO> bookDTOList);
	
	
	
}
