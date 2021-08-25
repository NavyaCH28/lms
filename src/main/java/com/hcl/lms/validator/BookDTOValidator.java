package com.hcl.lms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hcl.lms.dto.BookDTO;

@Component
public class BookDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return BookDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BookDTO bookDTO = (BookDTO) target;
		if (bookDTO.getAuthor() == null || bookDTO.getAuthor().isEmpty()) {
			errors.rejectValue("author", "author should not be empty");
		}
		if (bookDTO.getTitle() == null || bookDTO.getTitle().isEmpty()) {
			errors.rejectValue("title", "title should not be empty");
		}
		if (bookDTO.getCategory() == null || bookDTO.getCategory().isEmpty()) {
			errors.rejectValue("category", "category should not be empty");
		}
		if (bookDTO.getPrice() == null || bookDTO.getPrice() == 0) {
			errors.rejectValue("price", "price should not be empty");
		}
		if (bookDTO.getQuantity() == null || bookDTO.getQuantity() == 0) {
			errors.rejectValue("quantity", "quantity should not be empty");

		}

	}

}
