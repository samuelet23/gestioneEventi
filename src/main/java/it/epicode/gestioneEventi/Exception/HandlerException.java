package it.epicode.gestioneEventi.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequestException(BadRequestException e){
        return new ErrorResponse(e.getMessage());
    }
    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse unathorizedException(UnAuthorizedException e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(LoginFaultException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse unathorizedException(LoginFaultException e){
        return new ErrorResponse(e.getMessage());
    }


    public static void badRequestException (BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
    }
    public static void unathorizedException (BindingResult bindingResult) throws UnAuthorizedException {
        if (bindingResult.hasErrors()) {
            throw new UnAuthorizedException(bindingResult.getAllErrors().toString());
        }
    }
}
