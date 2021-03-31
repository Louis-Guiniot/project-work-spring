package it.jac.project.dto;

public class ResponseDto<T> {
	
	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public boolean isResultTest() {
		return resultTest;
	}

	public void setResultTest(boolean resultTest) {
		this.resultTest = resultTest;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	private T result;
	
	private boolean resultTest;
	
	private String error;
	
	
}