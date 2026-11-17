package com.shopwise.product.api;
import com.shopwise.product.service.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.stream.Collectors;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler(ProductNotFoundException.class) @ResponseStatus(HttpStatus.NOT_FOUND) ProblemDetail notFound(ProductNotFoundException e){return problem(HttpStatus.NOT_FOUND,"Product not found",e.getMessage());}
 @ExceptionHandler(DuplicateSkuException.class) @ResponseStatus(HttpStatus.CONFLICT) ProblemDetail duplicate(DuplicateSkuException e){return problem(HttpStatus.CONFLICT,"Duplicate SKU",e.getMessage());}
 @ExceptionHandler(MethodArgumentNotValidException.class) @ResponseStatus(HttpStatus.BAD_REQUEST) ProblemDetail validation(MethodArgumentNotValidException e){String d=e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).collect(Collectors.joining("; "));return problem(HttpStatus.BAD_REQUEST,"Validation failed",d);}
 @ExceptionHandler(DataIntegrityViolationException.class) @ResponseStatus(HttpStatus.CONFLICT) ProblemDetail integrity(DataIntegrityViolationException e){return problem(HttpStatus.CONFLICT,"Data integrity violation","The request conflicts with an existing product record.");}
 private ProblemDetail problem(HttpStatus s,String title,String detail){ProblemDetail p=ProblemDetail.forStatusAndDetail(s,detail);p.setTitle(title);p.setType(URI.create("https://shopwise.example/problems/"+s.value()));return p;}
}
