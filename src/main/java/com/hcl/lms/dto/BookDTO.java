package com.hcl.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
	
	private String title;
	private String author;
	private String category;
	private Long price;
	private Long quantity;

}
