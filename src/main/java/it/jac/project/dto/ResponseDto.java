package it.jac.project.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
	
	private T result;
	
	private boolean resultTest;
	
	private String error;
	
	
}