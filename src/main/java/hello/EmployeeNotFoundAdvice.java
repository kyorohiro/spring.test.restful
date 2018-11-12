package hello;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmployeeNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(EmployeeController.EmployeeNotFoundException.class)
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	String employeeNotFoundHandler(EmployeeController.EmployeeNotFoundException ex) {
		return ex.getMessage();
	}
}
