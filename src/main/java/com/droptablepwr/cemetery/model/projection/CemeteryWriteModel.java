package com.droptablepwr.cemetery.model.projection;

import com.droptablepwr.cemetery.model.Cemetery;
import com.droptablepwr.cemetery.repository.CemeteryRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class CemeteryWriteModel {
    @NotNull
    @Length(max = 64)
    @IsUnique
    private String name;
    @NotNull
    @Length(max = 200)
    private String description;
    @NotNull
    private Integer maxGridX;
    @NotNull
    private Integer maxGridY;
    @NotNull
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxGridX() {
        return maxGridX;
    }

    public void setMaxGridX(Integer maxGridX) {
        this.maxGridX = maxGridX;
    }

    public Integer getMaxGridY() {
        return maxGridY;
    }

    public void setMaxGridY(Integer maxGridY) {
        this.maxGridY = maxGridY;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Cemetery generateCemetery() {
        return new Cemetery(name, description, maxGridX, maxGridY, type);
    }

    @Target({FIELD})
    @Retention(RUNTIME)
    @Constraint(validatedBy = IsUnique.IsUniqueValidator.class)
    private @interface IsUnique {
        String message() default "Name is not unique";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        class IsUniqueValidator implements ConstraintValidator<IsUnique, String> {
            @Autowired
            CemeteryRepository cemeteryRepository;

            @Override
            public boolean isValid(String value, ConstraintValidatorContext context) {
                return !cemeteryRepository.existsByName(value);
            }
        }
    }
}
