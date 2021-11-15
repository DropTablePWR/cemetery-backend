package com.droptablepwr.cemetery.controller;

import com.droptablepwr.cemetery.model.Cemetery;
import com.droptablepwr.cemetery.repository.CemeteryRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

@RestController
@RequestMapping("/cemetery")
public class CemeteryController {
    private final CemeteryRepository cemeteryRepository;

    public CemeteryController(CemeteryRepository cemeteryRepository) {
        this.cemeteryRepository = cemeteryRepository;
    }

    @PostMapping
    ResponseEntity<?> createNewCemetery(@RequestBody @Valid Cemetery cemetery){
        Cemetery created = cemeteryRepository.save(cemetery);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateCemetery(@PathVariable("id") Integer id,@RequestBody @Valid Cemetery newVal){
        Optional<Cemetery> result = cemeteryRepository.findById(id);
        if (result.isPresent()){
            Cemetery oldVal = result.get();
            oldVal.setAll(newVal);
            Cemetery saved = cemeteryRepository.save(oldVal);
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    Optional<Cemetery> getCemetery(@PathVariable("id") Integer id){
        return cemeteryRepository.findById(id);
    }


    @GetMapping
    List<Cemetery> getAllCemeteries(){
        return cemeteryRepository.findAll();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleSQLIntegrityException(
            ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getConstraintName(),ex.getCause().getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
