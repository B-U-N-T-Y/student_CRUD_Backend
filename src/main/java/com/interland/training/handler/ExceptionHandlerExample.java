package com.interland.training.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerExample extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> MethodvalidityExample(MethodArgumentNotValidException ex) {

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	private Map<String, List<String>> getErrorsMap(List<String> errors) {
		Map<String, List<String>> errorResponse = new HashMap<>();
		errorResponse.put("errors", errors);
		return errorResponse;
	}
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<JSONObject> handleValidationExceptions(HttpClientErrorException ex) {
		JSONObject response = new JSONObject();
		JSONArray details = new JSONArray();
		
		response.put("code", "VALERRCOD");
			response.put("message", "Incorrect Password");
			response.put("details", details);
			response.put("access_token","No token");
			return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		}
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<JSONObject> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
		JSONObject response = new JSONObject();
		JSONArray details = new JSONArray();
		response.put("code", "NULLCOD");
		response.put("message", "id is null");
		response.put("details", details);
		response.put("access_token","Username Not Found");
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
	}

}
