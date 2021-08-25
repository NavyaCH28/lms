package com.hcl.lms.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hcl.lms.model.Book;

@Repository
public interface LMSRepository extends JpaRepository<Book, Long>,JpaSpecificationExecutor<Book> {

	
	static Specification<Book>  hasTitle(String title){
		return (book, cq, cb) -> cb.equal(book.get("title"), title);
	} 
	
	static Specification<Book>  hasCategory(String category){
		return (book, cq, cb) -> cb.equal(book.get("category"), category);
	}

	
	
}
