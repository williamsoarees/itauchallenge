package com.example.itauchallenge.error;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorServiceResponse {

	private String code;
	private String message;
	private List<String> details;
	
	public ErrorServiceResponse(final ErrosCodes errorCodes, String details) {
		this.code = errorCodes.name();
		this.message = errorCodes.getMensagem();
		this.details = Collections.singletonList(details);
	}
	
	public ErrorServiceResponse(final ErrosCodes errorCodes, List<String> details) {
		this.code = errorCodes.name();
		this.message = errorCodes.getMensagem();
		this.details = details;
	}
}
