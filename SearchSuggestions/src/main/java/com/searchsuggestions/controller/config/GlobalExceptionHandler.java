package com.searchsuggestions.controller.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(MultipartException.class)
    public ResponseEntity<String> handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return new ResponseEntity<>("Please upload only CSV format !", HttpStatus.UNSUPPORTED_MEDIA_TYPE);

    }
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		 StringBuilder builder = new StringBuilder();
		    builder.append(ex.getContentType());
		    builder.append(" media type is not supported. Supported media types are ");
		    ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));
		 
		    ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, 
		      ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
		    return new ResponseEntity<Object>(
		      apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@ExceptionHandler(BadRequest.class)
    public String handleError2(BadRequest e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/BadRequest";

    }
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleError3(MethodArgumentTypeMismatchException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "Please enter valid number !";

    }
	
	

}