package com.droptablepwr.cemetery.model.projection;

import com.droptablepwr.cemetery.model.Guest;
import com.droptablepwr.cemetery.model.Tombstone;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDate;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@GuestWriteModel.IsEndDateValid(startDate = "birthDate", endDate = "deathDate")
public class GuestWriteModel {
    @NotNull
    LocalDate birthDate;
    @NotNull
    LocalDate deathDate;
    @NotNull
    String firstName;
    @NotNull
    String lastName;


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Guest toGuest(Tombstone tombstone) {
        return new Guest(firstName, lastName, birthDate, deathDate, tombstone);
    }

    @Target({TYPE})
    @Retention(RUNTIME)
    @Constraint(validatedBy = IsEndDateValid.IsEndDateValidValidator.class)
    @interface IsEndDateValid {
        String message() default "Invalid dates";

        String startDate();

        String endDate();

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        class IsEndDateValidValidator implements ConstraintValidator<IsEndDateValid, Object> {

            private String startDate;
            private String endDate;

            @Override
            public void initialize(IsEndDateValid constraintAnnotation) {
                this.startDate = constraintAnnotation.startDate();
                this.endDate = constraintAnnotation.endDate();
            }

            @Override
            public boolean isValid(Object value, ConstraintValidatorContext context) {
                LocalDate startDateValue = (LocalDate) new BeanWrapperImpl(value)
                        .getPropertyValue(startDate);
                LocalDate endDateValue = (LocalDate) new BeanWrapperImpl(value)
                        .getPropertyValue(endDate);
                return startDateValue != null && endDateValue != null && startDateValue.isBefore(endDateValue);
            }
        }
    }

}
