package banquemisr.challenge05.task_mangment.Controllers.exceptions;

import java.util.Date;
import java.util.HashMap; 
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;




@RestControllerAdvice
public class CustomExceptionHandler {
	
	
	
	@ExceptionHandler(exception = ResourceNotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(ResourceNotFoundException ex , WebRequest request){
		
		
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND); 		
	}
	
	@ExceptionHandler(exception = MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleNotFoundException(MethodArgumentNotValidException ex , WebRequest request){
		Map<String, String> errors = new HashMap<String, String>();
			for (FieldError err : ex.getFieldErrors()) {
				errors.put(err.getField(), err.getDefaultMessage());
			}
			
//		ErrorDetails errorDetails = new ErrorDetails(new Date(), errors.toString(), request.getDescription(false));
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST); 		
	}
	
	
	 @ExceptionHandler(exception = Exception.class)
	    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
	        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
	        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	
}
