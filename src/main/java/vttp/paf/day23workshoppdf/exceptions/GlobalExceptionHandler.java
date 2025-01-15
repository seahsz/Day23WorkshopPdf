package vttp.paf.day23workshoppdf.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import vttp.paf.day23workshoppdf.models.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( {ResourceNotFoundException.class} )
    public String resourceNotFoundExceptionHandler(Model model, ResourceNotFoundException ex) {

        model.addAttribute("errorMessage", ex.getMessage());

        return "customError";
    }

    @ExceptionHandler( {NumberFormatException.class} )
    public String numberFormatExceptionHandler(Model model, NumberFormatException ex) {

        model.addAttribute("errorMessage", "Unable to convert to number " + ex.getMessage().toLowerCase());

        return "customError";
    }
    
}
