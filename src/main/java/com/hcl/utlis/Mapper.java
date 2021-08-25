package com.hcl.utlis;

import java.util.ArrayList;
import java.util.List;

import com.hcl.lms.dto.BookDTO;
import com.hcl.lms.model.Book;

public class Mapper {

	public static Book getBookEntity(BookDTO bookDTO) {
		Book book = Book.builder().author(bookDTO.getAuthor()).category(bookDTO.getCategory()).price(bookDTO.getPrice())
				.title(bookDTO.getTitle()).quantity(bookDTO.getQuantity()).build();
		return book;
	}

	public static BookDTO getBookDTO(Book book) {
		BookDTO bookDTO = BookDTO.builder().author(book.getAuthor()).category(book.getCategory()).price(book.getPrice())
				.title(book.getTitle()).quantity(book.getQuantity()).build();
		return bookDTO;
	}

	public static List<Book> getBookEntitys(List<BookDTO> bookDTOList) {
		List<Book> bookList = new ArrayList<Book>();
		for (BookDTO bookDTO : bookDTOList) {
			bookList.add(getBookEntity(bookDTO));
		}
		return bookList;
	}

	public static List<BookDTO> getBookDTOList(List<Book> bookList) {
		List<BookDTO> bookDTOList = new ArrayList<BookDTO>();
		for (Book book : bookList) {
			bookDTOList.add(getBookDTO(book));
		}
		return bookDTOList;
	}

}
