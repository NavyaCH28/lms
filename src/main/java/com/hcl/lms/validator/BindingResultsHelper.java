package com.hcl.lms.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class BindingResultsHelper {
	public static List<String> gerErrorsList(BindingResult bindingResult){
		List<ObjectError> allErrors = bindingResult.getAllErrors();
		List<String> errorsList = new ArrayList<String>();
		if (allErrors != null ) {
			for (ObjectError objectError : allErrors) {
				errorsList.add((objectError.getCode()));
			}
		}
		return errorsList;
		
	}

}
