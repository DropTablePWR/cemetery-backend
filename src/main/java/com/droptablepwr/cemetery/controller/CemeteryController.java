package com.droptablepwr.cemetery.controller;

import com.droptablepwr.cemetery.model.Cemetery;
import com.droptablepwr.cemetery.model.projection.CemeteryFullReadModel;
import com.droptablepwr.cemetery.model.projection.CemeteryInfoAdvanced;
import com.droptablepwr.cemetery.model.projection.CemeteryInfoBasic;
import com.droptablepwr.cemetery.repository.CemeteryRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cemetery")
public class CemeteryController {
    private final CemeteryRepository cemeteryRepository;

    public CemeteryController(CemeteryRepository cemeteryRepository) {
        this.cemeteryRepository = cemeteryRepository;
    }

//    not important
//    @PostMapping
//    ResponseEntity<?> createNewCemetery(@RequestBody @Valid CemeteryWriteModel cemetery) {
//        Cemetery created = cemeteryRepository.save(cemetery.generateCemetery());
//        return ResponseEntity.ok(created);
//    }
// not important
//    @PutMapping("/{id}")
//    ResponseEntity<?> updateCemetery(@PathVariable("id") Integer id, @RequestBody @Valid CemeteryWriteModel cemetery) {
//        Optional<Cemetery> result = cemeteryRepository.findById(id, Cemetery.class);
//        if (result.isPresent()) {
//            Cemetery oldVal = result.get();
//            oldVal.change(cemetery);
//            cemeteryRepository.save(oldVal);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }

    @GetMapping("/{id}")
    ResponseEntity<?> getInfoCemetery(@PathVariable("id") Integer id) {
        return cemeteryRepository.findById(id, CemeteryInfoAdvanced.class).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

// not important
//    @DeleteMapping("/{id}")
//    ResponseEntity<?> deleteCemetery(@PathVariable("id") Integer id) {
//        if (cemeteryRepository.existsById(id)) {
//            cemeteryRepository.deleteById(id);
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.notFound().build();
//    }

    @GetMapping("/{id}/all")
    ResponseEntity<?> getFullCemetery(@PathVariable("id") Integer id) {
        return cemeteryRepository.findById(id, Cemetery.class)
                .map(CemeteryFullReadModel::generateCemeteryFullRead)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping
    List<?> getAllCemeteries() {
        return cemeteryRepository.findBy(CemeteryInfoBasic.class);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleSQLIntegrityException(
            ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getConstraintName(), ex.getCause().getMessage());
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
