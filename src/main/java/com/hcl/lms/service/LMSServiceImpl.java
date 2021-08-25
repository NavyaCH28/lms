package com.hcl.lms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.hcl.lms.dto.BookDTO;
import com.hcl.lms.model.Book;
import com.hcl.lms.repository.LMSRepository;
import com.hcl.utlis.Mapper;

@Service
public class LMSServiceImpl implements LMSService {
	
	Logger logger = LoggerFactory.getLogger(LMSServiceImpl.class);

	@Autowired
	private LMSRepository lMSRepository;

	public BookDTO saveBook(BookDTO bookDTO) {
		Book book = lMSRepository.save(Mapper.getBookEntity(bookDTO));
		logger.info("LMSServiceImpl.saveBook():?" ,book);
		return Mapper.getBookDTO(book);
	}

	public List<BookDTO> findByTitleAndCategory(String title, String category) {
		List<Book> bookList = lMSRepository
				.findAll(Specification.where(LMSRepository.hasTitle(title)).and(LMSRepository.hasCategory(category)));
		return bookList.stream().map(book -> Mapper.getBookDTO(book)).collect(Collectors.toList());
	}

	public List<BookDTO> saveAllBooks(List<BookDTO> bookDTOList) {
		List<Book> bookList = lMSRepository.saveAll(Mapper.getBookEntitys(bookDTOList));
		return bookList.stream().map(book -> Mapper.getBookDTO(book)).collect(Collectors.toList());
	}
}
